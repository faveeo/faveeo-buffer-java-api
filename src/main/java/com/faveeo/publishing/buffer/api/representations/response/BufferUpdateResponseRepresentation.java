package com.faveeo.publishing.buffer.api.representations.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BufferUpdateResponseRepresentation {
    public String message;
    public String code;
    public boolean success;
    public long buffer_count;
    public float buffer_percentage;
    public List<BufferUpdateItemRepresentation> updates;

}
