package com.faveeo.publishing.buffer.api.representations;

public abstract class BufferAccessTokenRepresentation {

    public String access_token;

    public BufferAccessTokenRepresentation(String access_token) {
        this.access_token = access_token;
    }
}
