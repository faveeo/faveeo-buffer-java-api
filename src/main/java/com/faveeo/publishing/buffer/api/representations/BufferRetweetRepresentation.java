package com.faveeo.publishing.buffer.api.representations;

public class BufferRetweetRepresentation {

    public final String tweet_id;
    public final String comment;

    public BufferRetweetRepresentation(String tweet_id, String comment) {
        this.tweet_id = tweet_id;
        this.comment = comment;
    }
}
