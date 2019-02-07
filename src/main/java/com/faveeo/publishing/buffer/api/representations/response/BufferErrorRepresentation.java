package com.faveeo.publishing.buffer.api.representations.response;

import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BufferErrorRepresentation {

    public int code;
    public List<ProfileId> errored_profiles;
    public String message;
    public boolean success;

    public static class ProfileId {
        public String profiled_id;
    }
}
