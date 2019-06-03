package com.faveeo.publishing.buffer.api.representations.request;

import com.faveeo.publishing.buffer.api.representations.BufferAccessTokenRepresentation;
import com.faveeo.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import com.faveeo.publishing.buffer.api.representations.BufferRetweetRepresentation;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@ToString
public class BufferCreateUpdateRepresentation extends BufferAccessTokenRepresentation {

    public final List<String> profile_ids;
    public final String text;
    public final boolean shorten = true;
    public final DateTime scheduled_at;
    public final BufferRetweetRepresentation retweet;
    public final Boolean now;
    public final Boolean top;
    public final Boolean attachment;
    public final BufferMediaItemRepresentation media;

    public BufferCreateUpdateRepresentation(final String access_token, final List<String> profile_ids,
                                            final String text, final DateTime scheduled_at,
                                            final BufferRetweetRepresentation retweet, final BufferMediaItemRepresentation media) {
        super(access_token);
        this.profile_ids = profile_ids;
        this.text = text;
        this.scheduled_at = scheduled_at;
        this.retweet = retweet;
        now = false;
        top = false;
        attachment = false;
        if (media == null) {
            this.media = null;
        } else {
            this.media = media;
        }
    }

    public BufferCreateUpdateRepresentation(final String access_token, final List<String> profile_ids,
                                            final String text) {
        this(access_token, profile_ids, text, null, null, null);
    }

    public BufferCreateUpdateRepresentation(final String access_token, final List<String> profile_ids,
                                            final String text, final BufferMediaItemRepresentation media) {
        this(access_token, profile_ids, text, null, null, media);
    }

    public BufferCreateUpdateRepresentation(final String access_token, final List<String> profile_ids,
                                            final BufferRetweetRepresentation retweet) {
        this(access_token, profile_ids, null, null, retweet, null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile_ids, text, shorten, scheduled_at, retweet, now, top, attachment, media);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BufferCreateUpdateRepresentation that = (BufferCreateUpdateRepresentation) o;
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
}
