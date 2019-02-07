package com.faveeo.publishing.buffer.api.representations.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BufferUpdatesRepresentation {
    public int total;
    public List<BufferUpdateItemRepresentation> updates;
}
