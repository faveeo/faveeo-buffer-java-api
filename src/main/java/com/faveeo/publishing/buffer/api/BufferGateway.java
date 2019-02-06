package com.faveeo.publishing.buffer.api;

import com.faveeo.pipeline.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.request.BufferCreateUpdateRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.response.BufferErrorRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.response.BufferProfileRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.response.BufferUpdatesRepresentation;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.*;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Timer.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BufferGateway {

    private static final String METER_NAME_BUFFER_UPDATES_OK    = "publishing.buffer.updates.ok";
    private static final String METER_NAME_BUFFER_UPDATES_ERROR = "publishing.buffer.updates.error";

    private static final Logger logger                          = LoggerFactory.getLogger(BufferGateway.class);

    private BufferRetrofit      bufferService;
    private final Timer         okMeter;
    private Timer               errorMeter;

    public BufferGateway(String endpoint) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BufferMediaItemRepresentation[].class, new BufferMediaDeserializer())
                .create();
        this.bufferService = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                // .setLogLevel(RestAdapter.LogLevel.FULL) // Useful for debugging
                .build().create(BufferRetrofit.class);
        this.okMeter = Metrics.timer(METER_NAME_BUFFER_UPDATES_OK);
        this.errorMeter = Metrics.timer(METER_NAME_BUFFER_UPDATES_ERROR);
    }

    @VisibleForTesting
    public BufferGateway(BufferRetrofit retrofit, String endpoint) {
        this(endpoint);
        this.bufferService = retrofit;
    }

    public Optional<BufferUpdateResponseRepresentation> createUpdate(
            BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation) throws Exception {
        Map<String, String> mediaMap = bufferCreateUpdateRepresentation.media == null ? Collections.emptyMap()
                : bufferCreateUpdateRepresentation.media.toMap();
        Sample start = Timer.start();
        try {
            logger.info("Pushing Buffer update text={}", bufferCreateUpdateRepresentation.text);
            BufferUpdateResponseRepresentation update = bufferService.createUpdate(
                    bufferCreateUpdateRepresentation.profile_ids,
                    bufferCreateUpdateRepresentation.text,
                    bufferCreateUpdateRepresentation.shorten,
                    bufferCreateUpdateRepresentation.now,
                    bufferCreateUpdateRepresentation.top,
                    mediaMap,
                    bufferCreateUpdateRepresentation.attachment,
                    bufferCreateUpdateRepresentation.scheduled_at,
                    bufferCreateUpdateRepresentation.access_token);
            start.stop(okMeter);

            return Optional.of(update);
        } catch (RetrofitError error) {
            
            start.stop(errorMeter);
            BufferErrorRepresentation bufferError = (BufferErrorRepresentation) error
                    .getBodyAs(BufferErrorRepresentation.class);
            Response response = error.getResponse();
            if (bufferError == null) {
                logger.error("Error creating buffer update. HTTP error: {}", response.getStatus(), error);
            } else {
                logger.error("Error creating buffer update. HTTP error: {}. Buffer Error:{} - {}", response.getStatus(),
                        bufferError.code, bufferError.message);
            }
            if (response.getStatus() == 400) {// don't throw errors for Bad Requests
                return Optional.empty();
            } else {
                throw error;
            }
        }
    }

    public Optional<BufferUpdateResponseRepresentation> createUpdateAsRetweet(
            BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation) throws Exception {
        Sample sample = Timer.start();

        try {
            BufferUpdateResponseRepresentation update = bufferService.createUpdateAsRetweet(
                    bufferCreateUpdateRepresentation.profile_ids,
                    bufferCreateUpdateRepresentation.now,
                    bufferCreateUpdateRepresentation.top,
                    bufferCreateUpdateRepresentation.scheduled_at,
                    bufferCreateUpdateRepresentation.retweet.tweet_id,
                    bufferCreateUpdateRepresentation.retweet.comment,
                    bufferCreateUpdateRepresentation.access_token);
            sample.stop(okMeter);

            return Optional.of(update);
        } catch (RetrofitError error) {
            sample.stop(errorMeter);
            
            BufferErrorRepresentation bufferError = (BufferErrorRepresentation) error
                    .getBodyAs(BufferErrorRepresentation.class);
            Response response = error.getResponse();
            logger.error("Error creating buffer update. HTTP error: {}. Buffer Error:{} - {}", response.getStatus(),
                    bufferError.code, bufferError.message);
            if (response.getStatus() == 400) {// don't throw errors for Bad Requests
                return Optional.empty();
            } else {
                throw error;
            }
        }
    }

    public List<BufferProfileRepresentation> getBufferUserProfiles(String accessToken) throws Exception {
        return bufferService.getBufferUserProfiles(accessToken);
    }

    public BufferUpdatesRepresentation getSentUpdates(String profileId, String accessToken, int page, int count,
            Integer timestamp) {
        return bufferService.getSentUpdates(profileId, accessToken, page, count, timestamp, true, null);
    }

    public BufferProfileRepresentation getProfile(String profileId, String accessToken) {
        return bufferService.getProfile(profileId, accessToken);
    }

    public BufferUpdateResponseRepresentation shuffleQueue(String profileId, String accessToken) {
        return bufferService.shuffleQueue(profileId, accessToken);
    }

    // needed because BufferMediaItemRepresentation sometimes comes as an object,
    // sometimes as an array
    private class BufferMediaDeserializer implements JsonDeserializer<BufferMediaItemRepresentation[]> {

        @Override
        public BufferMediaItemRepresentation[] deserialize(JsonElement jsonElement, Type type,
                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement instanceof JsonArray) {
                return new Gson().fromJson(jsonElement, BufferMediaItemRepresentation[].class);
            } else {
                return new BufferMediaItemRepresentation[] {
                        jsonDeserializationContext.deserialize(jsonElement, BufferMediaItemRepresentation.class) };
            }
        }
    }

}
