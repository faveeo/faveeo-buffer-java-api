package com.faveeo.publishing.buffer.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faveeo.publishing.buffer.api.representations.request.BufferCreateUpdateRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferProfileRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdatesRepresentation;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BufferGatewayImpl implements BufferGateway {

    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("api");
    private static final Logger logger = LoggerFactory.getLogger(BufferGatewayImpl.class);
    private final BufferRetrofit bufferClient;

    public BufferGatewayImpl(final BufferRetrofit bufferClientAPI) {
        this.bufferClient = bufferClientAPI;
    }

    /**
     * Returns the internal object mapper.
     *
     * @return the internal object mapper.
     */
    @Override
    public ObjectMapper internalObjectMapper() {
        return BufferJacksonConfigFactory.getObjectMapper();
    }

    @Override
    public void createUpdateFromPayload(final JsonNode bufferPayload, final BufferCallback<BufferUpdateResponseRepresentation> callback) throws JsonProcessingException {
        final BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation = internalObjectMapper().treeToValue(bufferPayload,
                BufferCreateUpdateRepresentation.class);
        createUpdate(bufferCreateUpdateRepresentation, callback);
    }

    /**
     * Creates a new buffer update (sending a publication)
     *
     * @param bufferCreateUpdateRepresentation the buffer create update representation
     * @param callback                         the callback to be executed after the REST Call.
     */
    @Override
    public void createUpdate(final BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation,
                             final BufferCallback<BufferUpdateResponseRepresentation> callback) {
        Validate.notNull(callback);
        Validate.notNull(bufferCreateUpdateRepresentation);

        final Map<String, String> mediaMap = bufferCreateUpdateRepresentation.media == null ? Collections.emptyMap()
                : bufferCreateUpdateRepresentation.media.toMap();
        Response<BufferUpdateResponseRepresentation> execute = null;
        logger.info(resourceBundle.getString("pushing.buffer.update.text"), bufferCreateUpdateRepresentation.text);
        final Call<BufferUpdateResponseRepresentation> update = bufferClient.createUpdate(
                bufferCreateUpdateRepresentation.profile_ids,
                bufferCreateUpdateRepresentation.text,
                bufferCreateUpdateRepresentation.shorten,
                bufferCreateUpdateRepresentation.now,
                bufferCreateUpdateRepresentation.top,
                mediaMap,
                bufferCreateUpdateRepresentation.attachment,
                bufferCreateUpdateRepresentation.scheduled_at,
                bufferCreateUpdateRepresentation.access_token);
        update.enqueue(new BufferRetrofitCallback(callback));

    }

    @Override
    public void createUpdateAsRetweet(
            final BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation,
            final BufferCallback<BufferUpdateResponseRepresentation> callback) {

        final Call<BufferUpdateResponseRepresentation> update = bufferClient.createUpdateAsRetweet(
                bufferCreateUpdateRepresentation.profile_ids,
                bufferCreateUpdateRepresentation.now,
                bufferCreateUpdateRepresentation.top,
                bufferCreateUpdateRepresentation.scheduled_at,
                bufferCreateUpdateRepresentation.retweet.tweet_id,
                bufferCreateUpdateRepresentation.retweet.comment,
                bufferCreateUpdateRepresentation.access_token);
        update.enqueue(new BufferRetrofitCallback(callback));
    }

    @Override
    public List<BufferProfileRepresentation> getBufferUserProfiles(final String accessToken) throws IOException {
        return bufferClient.getBufferUserProfiles(accessToken).execute().body();
    }

    @Override
    public BufferUpdatesRepresentation getSentUpdates(final String profileId, final String accessToken,
                                                      final int page, final int count,
                                                      final DateTime timestamp) throws IOException {
        return bufferClient.getSentUpdates(profileId, accessToken, page, count, timestamp == null ? null : (long) timestamp.getMillisOfSecond(), true, null).execute().body();
    }

    @Override
    public BufferUpdatesRepresentation getPendingUpdates(final String profileId, final String accessToken, final int page, final int count,
                                                         final DateTime timestamp) throws IOException {
        return bufferClient.getPendingUpdates(profileId, accessToken, page, count, timestamp == null ? null : (long)timestamp.getMillisOfSecond(), true).execute().body();
    }

    @Override
    public BufferProfileRepresentation getProfile(final String profileId, final String accessToken) throws IOException {
        return bufferClient.getProfile(profileId, accessToken).execute().body();
    }

    @Override
    public BufferUpdateResponseRepresentation shuffleQueue(final String profileId, final String accessToken) throws IOException {
        return bufferClient.shuffleQueue(profileId, accessToken).execute().body();
    }

}
