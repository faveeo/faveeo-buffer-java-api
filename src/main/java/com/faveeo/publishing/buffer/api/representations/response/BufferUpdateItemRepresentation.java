package com.faveeo.publishing.buffer.api.representations.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.faveeo.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BufferUpdateItemRepresentation {
    public String id;
    public long created_at;
    public String day;
    public long due_at;
    public String due_time;
    public List<BufferMediaItemRepresentation> media;
    public String profile_id;
    public String profile_service;
    public String status;
    public String text;
    public String text_formatted;
    public String user_id;
    public String via;
    public BufferStatistics statistics = new BufferStatistics();
    public String service_link;

    public BufferUpdateItemRepresentation() {
    }

    public BufferUpdateItemRepresentation(final BufferUpdateItemRepresentation update) {
        this.id = update.id;
        this.created_at = update.created_at;
        this.day = update.day;
        this.due_at = update.due_at;
        this.due_time = update.due_time;
        this.media = update.media;
        this.profile_id = update.profile_id;
        this.profile_service = update.profile_service;
        this.status = update.status;
        this.text = update.text;
        this.text_formatted = update.text_formatted;
        this.user_id = update.user_id;
        this.via = update.via;
        this.statistics = update.statistics;
        this.service_link = update.service_link;
    }

    public DateTime toDateTime() {
        return new DateTime(due_at * 1000L);
    }

    public class Mapping {
        public static final String DUE_AT = "due_at";
        public static final String TEXT = "text";
        public static final String STATISTICS_RETWEETS = "statistics.retweets";
        public static final String STATISTICS_CLICKS = "statistics.clicks";
        public static final String STATISTICS_FAVORITES = "statistics.favorites";
        public static final String VIA = "via";
        public static final String PROFILE_ID = "profile_id";
    }

}
