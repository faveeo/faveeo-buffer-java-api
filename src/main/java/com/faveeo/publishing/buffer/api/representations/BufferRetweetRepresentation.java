package com.faveeo.publishing.buffer.api.representations;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferRetweetRepresentation {

    public String tweet_id;
    public String comment;


}
