package com.faveeo.publishing.buffer.api.representations;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferRetweetRepresentation {

    public final String tweet_id;
    public final String comment;


}
