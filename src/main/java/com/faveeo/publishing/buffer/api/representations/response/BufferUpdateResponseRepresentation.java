package com.faveeo.publishing.buffer.api.representations.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BufferUpdateResponseRepresentation {
    public String message;
    public boolean success;
    public long buffer_count;
    public float buffer_percentage;
    public List<BufferUpdateItemRepresentation> updates;

}
