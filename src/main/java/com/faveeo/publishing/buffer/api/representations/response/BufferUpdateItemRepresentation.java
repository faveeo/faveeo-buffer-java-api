package com.faveeo.publishing.buffer.api.representations.response;

import com.faveeo.publishing.buffer.api.representations.BufferMediaItemRepresentation;

import java.util.Arrays;
import java.util.Objects;

public class BufferUpdateItemRepresentation {

    public String id;
    public long created_at;
    public String day;
    public long due_at;
    public String due_time;
    public BufferMediaItemRepresentation[] media;
    public String profile_id;
    public String profile_service;
    public String status;
    public String text;
    public String text_formatted;
    public String user_id;
    public String via;
    public BufferStatistics statistics;
    public String service_link;

    public BufferUpdateItemRepresentation() {
    }

    public BufferUpdateItemRepresentation(BufferUpdateItemRepresentation update) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(created_at, day, due_at, due_time, id, profile_id, profile_service,
                service_link, statistics, status, text, text_formatted, user_id, via);
        result = prime * result + Arrays.hashCode(media);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BufferUpdateItemRepresentation other = (BufferUpdateItemRepresentation) obj;
        return created_at == other.created_at && Objects.equals(day, other.day) && due_at == other.due_at
                && Objects.equals(due_time, other.due_time) && Objects.equals(id, other.id)
                && Arrays.equals(media, other.media) && Objects.equals(profile_id, other.profile_id)
                && Objects.equals(profile_service, other.profile_service)
                && Objects.equals(service_link, other.service_link) && Objects.equals(statistics, other.statistics)
                && Objects.equals(status, other.status) && Objects.equals(text, other.text)
                && Objects.equals(text_formatted, other.text_formatted) && Objects.equals(user_id, other.user_id)
                && Objects.equals(via, other.via);
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
