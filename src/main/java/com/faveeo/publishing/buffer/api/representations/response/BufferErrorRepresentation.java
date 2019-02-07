package com.faveeo.publishing.buffer.api.representations.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BufferErrorRepresentation {

    public int code;
    public List<ProfileId> errored_profiles;
    public String message;
    public boolean success;
    public String error;

    public static class ProfileId {
        public String profiled_id;
    }
}
