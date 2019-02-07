package com.faveeo.publishing.buffer.api.representations.response;

import com.faveeo.publishing.buffer.api.representations.BufferMediaItemRepresentation;
import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
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


    public static class Mapping {
        public static final String DUE_AT = "due_at"; //NON-NLS
        public static final String TEXT = "text"; //NON-NLS
        public static final String STATISTICS_RETWEETS = "statistics.retweets"; //NON-NLS
        public static final String STATISTICS_CLICKS = "statistics.clicks"; //NON-NLS
        public static final String STATISTICS_FAVORITES = "statistics.favorites"; //NON-NLS
        public static final String VIA = "via"; //NON-NLS
        public static final String PROFILE_ID = "profile_id"; //NON-NLS
    }
}
