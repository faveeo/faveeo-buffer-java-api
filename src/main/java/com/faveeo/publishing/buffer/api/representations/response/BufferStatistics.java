package com.faveeo.publishing.buffer.api.representations.response;

import lombok.*;

@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BufferStatistics {
    public int reach;
    public int clicks;
    public int retweets;
    public int favorites;
    public int mentions;


}
