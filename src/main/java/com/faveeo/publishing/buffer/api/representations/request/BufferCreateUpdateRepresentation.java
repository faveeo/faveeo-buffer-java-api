package com.faveeo.publishing.buffer.api.representations.request;

import com.faveeo.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import com.faveeo.publishing.buffer.api.representations.BufferRetweetRepresentation;
import lombok.*;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@ToString
@NoArgsConstructor
public class BufferCreateUpdateRepresentation {

    public List<String> profile_ids;
    public String access_token;
    public String text;

    @Builder.Default
    public boolean shorten = true;

    public DateTime scheduled_at;
    public BufferRetweetRepresentation retweet;
    public Boolean now;
    public Boolean top;
    public Boolean attachment;
    public BufferMediaItemRepresentation media;


}
