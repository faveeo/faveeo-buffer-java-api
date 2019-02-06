package com.faveeo.publishing.buffer.api.representations.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferUpdateResponseRepresentation {

    public boolean success;
    public long buffer_count;
    public float buffer_percentage;
    public List<BufferUpdateItemRepresentation> updates;

}
