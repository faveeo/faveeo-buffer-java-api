package com.faveeo.publishing.buffer.api.representations.response;

import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.Objects;

public class BufferStatistics implements ToXContent {
    public int reach;
    public int clicks;
    public int retweets;
    public int favorites;
    public int mentions;

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        return builder
                .startObject()
                    .field("reach", reach)
                    .field("clicks", clicks)
                    .field("retweets", retweets)
                    .field("favorites", favorites)
                    .field("mentions", mentions)
                .endObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BufferStatistics that = (BufferStatistics) o;
        return reach == that.reach &&
                clicks == that.clicks &&
                retweets == that.retweets &&
                favorites == that.favorites &&
                mentions == that.mentions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reach, clicks, retweets, favorites, mentions);
    }
}
