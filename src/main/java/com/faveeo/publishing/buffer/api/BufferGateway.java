package com.faveeo.publishing.buffer.api;

import com.faveeo.publishing.buffer.api.representations.request.BufferCreateUpdateRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferProfileRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdatesRepresentation;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

public class BufferGateway {

    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("api");
    private static final Logger logger = LoggerFactory.getLogger(BufferGateway.class);
    private final BufferRetrofit bufferClient;

    public BufferGateway(final BufferRetrofit bufferClientAPI) {
        this.bufferClient = bufferClientAPI;
    }

    /**
     * Creates a new buffer update (sending a publication)
     *
     * @param bufferCreateUpdateRepresentation the buffer create update representation
     * @param callback                         the callback to be executed after the REST Call.
     */
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

    public List<BufferProfileRepresentation> getBufferUserProfiles(final String accessToken) throws IOException {
        return bufferClient.getBufferUserProfiles(accessToken).execute().body();
    }

    public BufferUpdatesRepresentation getSentUpdates(final String profileId, final String accessToken,
                                                      final int page, final int count,
                                                      final Integer timestamp) throws IOException {
        return bufferClient.getSentUpdates(profileId, accessToken, page, count, timestamp, true, null).execute().body();
    }

    public BufferProfileRepresentation getProfile(final String profileId, final String accessToken) throws IOException {
        return bufferClient.getProfile(profileId, accessToken).execute().body();
    }

    public BufferUpdateResponseRepresentation shuffleQueue(final String profileId, final String accessToken) throws IOException {
        return bufferClient.shuffleQueue(profileId, accessToken).execute().body();
    }

}
