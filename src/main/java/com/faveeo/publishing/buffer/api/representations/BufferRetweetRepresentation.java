package com.faveeo.publishing.buffer.api.representations;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class BufferRetweetRepresentation {

    public final String tweet_id;
    public final String comment;

    /**
     * Instantiates a new Buffer retweet representation.
     *
     * @param tweet_id the tweet id
     * @param comment  the comment
     */
    public BufferRetweetRepresentation(String tweet_id, String comment) {
        this.tweet_id = tweet_id;
        this.comment = comment;
    }
}
