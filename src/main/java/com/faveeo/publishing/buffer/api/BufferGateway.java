package com.faveeo.publishing.buffer.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faveeo.publishing.buffer.api.representations.request.BufferCreateUpdateRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferProfileRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdatesRepresentation;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * This interface is the main entry to operate with the buffer API.
 */
public interface BufferGateway {
    /**
     * Returns the internal object mapper.
     *
     * @return the internal object mapper.
     */
    ObjectMapper internalObjectMapper();

    /**
     * Creates a new buffer update (sending a publication)
     *
     * @param bufferPayload the buffer payload
     */
    Optional<BufferUpdateResponseRepresentation> createUpdateFromPayload(final JsonNode bufferPayload);


    /**
     * Creates a new buffer update (sending a publication)
     *
     * @param bufferCreateUpdateRepresentation the buffer create update representation
     */
    Optional<BufferUpdateResponseRepresentation> createUpdate(final BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation);

    /**
     * Creates a buffer update as a retweet from a previous tweet.
     *
     * @param bufferCreateUpdateRepresentation the data required to publish the update
     */
    Optional<BufferUpdateResponseRepresentation> createUpdateAsRetweet(final BufferCreateUpdateRepresentation bufferCreateUpdateRepresentation);

    /**
     * Returns the list of buffer user profiles for a given Buffer access token
     *
     * @param accessToken the access token
     * @return the list of buffer profiles.
     */
    List<BufferProfileRepresentation> getBufferUserProfiles(final String accessToken);

    /**
     * Collects the list of updates that has been sent given a date.
     *
     * @param profileId   the buffer profile ID
     * @param accessToken the buffer access token
     * @param page        the page number
     * @param count       the number of updates expected
     * @param timestamp   the timestamps
     * @return the list of sent buffer updates.
     */
    BufferUpdatesRepresentation getSentUpdates(final String profileId,
                                               final String accessToken,
                                               final int page,
                                               final int count,
                                               final DateTime timestamp);


    /**
     * Collects the list of updates that has been sent given a date.
     *
     * @param profileId   the buffer profile ID
     * @param accessToken the buffer access token
     * @param page        the page number
     * @param count       the number of updates expected
     * @param timestamp   the timestamps
     * @return the list of sent buffer updates.
     */
    BufferUpdatesRepresentation getPendingUpdates(final String profileId,
                                                  final String accessToken,
                                                  final int page,
                                                  final int count,
                                                  final DateTime timestamp);


    /**
     * Returns the Buffer profile information for a given profile ID and its access token
     *
     * @param profileId   the buffer profile ID
     * @param accessToken the buffer access token
     * @return the Buffer profile.
     */
    BufferProfileRepresentation getProfile(final String profileId, final String accessToken);

    /**
     * Shuffle an existing queue:
     *
     * @param profileId   the Buffer profile ID
     * @param accessToken the Buffer access token
     * @return the response after the queue shuffling.
     */
    BufferUpdateResponseRepresentation shuffleQueue(final String profileId, final String accessToken);
}
