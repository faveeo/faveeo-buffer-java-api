package com.faveeo.publishing.buffer.api.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BufferRetweetRepresentation {

    public String tweet_id;
    public String comment;


}
