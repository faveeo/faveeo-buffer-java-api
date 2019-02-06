package com.faveeo.publishing.buffer.api;

import com.faveeo.publishing.buffer.api.representations.response.BufferProfileRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdatesRepresentation;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BufferRetrofit {

    @POST("/updates/create.json")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    BufferUpdateResponseRepresentation createUpdate(@Field("profile_ids") List<String> profile_ids,
                                                    @Field("text") String text,
                                                    @Field("shorten") boolean shorten,
                                                    @Field("now") Boolean now,
                                                    @Field("top") Boolean top,
                                                    @FieldMap(encoded = false) Map<String, String> media,
                                                    @Field("attachment") Boolean attachment,
                                                    @Field("scheduled_at") Date scheduled_at,
                                                    @Field("access_token") String access_token) throws Exception;

    @POST("/updates/create.json")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    BufferUpdateResponseRepresentation createUpdateAsRetweet(@Field("profile_ids") List<String> profile_ids,
                                                             @Field("now") Boolean now,
                                                             @Field("top") Boolean top,
                                                             @Field("scheduled_at") Date scheduled_at,
                                                             @Field("retweet[tweet_id]") String originalTweetId,
                                                             @Field("retweet[comment]") String comment,
                                                             @Field("access_token") String access_token) throws Exception;

    @GET("/profiles.json")
    List<BufferProfileRepresentation> getBufferUserProfiles(@Query("access_token") String accessToken) throws Exception;

    @GET("/profiles/{id}/updates/sent.json")
    BufferUpdatesRepresentation getSentUpdates(@Path("id") String profileId,
                                               @Query("access_token") String accessToken,
                                               @Query("page") int page,
                                               @Query("count") int count,
                                               @Query("since") Integer timestamp,
                                               @Query("utc") boolean utc,
                                               @Query("filter") String filter);

    @GET("/profiles/{id}.json")
    BufferProfileRepresentation getProfile(@Path("id") String profileId,
                                           @Query("access_token") String accessToken);

    @POST("/profiles/{id}/updates/shuffle.json")
    BufferUpdateResponseRepresentation shuffleQueue(@Path("id") String profileId,
                                                    @Query("access_token") String accessToken);

}