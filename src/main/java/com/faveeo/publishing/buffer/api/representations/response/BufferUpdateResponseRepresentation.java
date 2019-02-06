package com.faveeo.publishing.buffer.api.representations.response;

import java.util.List;

public class BufferUpdateResponseRepresentation {

    public boolean success;
    public long buffer_count;
    public float buffer_percentage;
    public List<BufferUpdateItemRepresentation> updates;

}
