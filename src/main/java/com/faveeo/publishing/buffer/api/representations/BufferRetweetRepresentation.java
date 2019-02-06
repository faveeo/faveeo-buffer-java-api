package com.faveeo.publishing.buffer.api.representations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferRetweetRepresentation {

    public final String tweet_id;
    public final String comment;


}
