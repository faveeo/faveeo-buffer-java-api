package com.faveeo.publishing.buffer.api.representations.response;

import lombok.ToString;

import java.util.List;

@ToString
public class BufferUpdatesRepresentation {
    public int total;
    public List<BufferUpdateItemRepresentation> updates;
}
