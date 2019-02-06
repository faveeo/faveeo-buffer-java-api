package com.faveeo.publishing.buffer.api.representations.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferUpdatesRepresentation {
    public int total;
    public List<BufferUpdateItemRepresentation> updates;
}
