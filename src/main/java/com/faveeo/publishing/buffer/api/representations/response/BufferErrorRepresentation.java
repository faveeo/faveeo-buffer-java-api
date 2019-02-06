package com.faveeo.publishing.buffer.api.representations.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class BufferErrorRepresentation {

    public int code;
    public List<ProfileId> errored_profiles;
    public String message;
    public boolean success;

    public static class ProfileId {
        public String profiled_id;
    }
}
