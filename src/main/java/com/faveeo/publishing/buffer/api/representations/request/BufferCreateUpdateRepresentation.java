package com.faveeo.publishing.buffer.api.representations.request;

import com.faveeo.pipeline.publishing.buffer.api.representations.BufferAccessTokenRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import com.faveeo.pipeline.publishing.buffer.api.representations.BufferRetweetRepresentation;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BufferCreateUpdateRepresentation extends BufferAccessTokenRepresentation {

    public final List<String> profile_ids;
    public final String text;
    public final boolean shorten = true;
    public final Date scheduled_at;
    public final BufferRetweetRepresentation retweet;
    public final Boolean now;
    public final Boolean top;
    public final Boolean attachment;
    public final BufferMediaItemRepresentation media;

    public BufferCreateUpdateRepresentation(String access_token, List<String> profile_ids,
                                            String text, Date scheduled_at,
                                            BufferRetweetRepresentation retweet, BufferMediaItemRepresentation media) {
        super(access_token);
        this.profile_ids = profile_ids;
        this.text = text;
        this.scheduled_at = scheduled_at;
        this.retweet = retweet;
        this.now = false;
        this.top = false;
        this.attachment = false;
        if(media == null) {
            this.media = null;
        } else {
            this.media = media;
        }
    }

    public BufferCreateUpdateRepresentation(String access_token, List<String> profile_ids,
                                            String text) {
        this(access_token, profile_ids, text, null, null, null);
    }

    public BufferCreateUpdateRepresentation(String access_token, List<String> profile_ids,
                                            String text, BufferMediaItemRepresentation media) {
        this(access_token, profile_ids, text, null, null, media);
    }

    public BufferCreateUpdateRepresentation(String access_token, List<String> profile_ids,
                                            BufferRetweetRepresentation retweet) {
        this(access_token, profile_ids, null, null, retweet, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BufferCreateUpdateRepresentation that = (BufferCreateUpdateRepresentation) o;
        return shorten == that.shorten &&
                Objects.equals(profile_ids, that.profile_ids) &&
                Objects.equals(text, that.text) &&
                Objects.equals(scheduled_at, that.scheduled_at) &&
                Objects.equals(retweet, that.retweet) &&
                Objects.equals(now, that.now) &&
                Objects.equals(top, that.top) &&
                Objects.equals(attachment, that.attachment) &&
                Objects.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile_ids, text, shorten, scheduled_at, retweet, now, top, attachment, media);
    }
}
