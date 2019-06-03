package com.faveeo.publishing.buffer.api.representations.response;

import lombok.ToString;

import java.util.List;

@ToString
public class BufferErrorRepresentation {

    public int code;
    public List<ProfileId> errored_profiles;
    public String message;
    public boolean success;

    public class ProfileId {
        public String profiled_id;
    }
}
