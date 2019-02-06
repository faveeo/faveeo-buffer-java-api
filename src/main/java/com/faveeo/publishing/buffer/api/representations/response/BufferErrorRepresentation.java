package com.faveeo.publishing.buffer.api.representations.response;

import java.util.List;

public class BufferErrorRepresentation {

    public int code;
    public List<ProfileId> errored_profiles;
    public String message;
    public boolean success;

    public class ProfileId {
        public String profiled_id;
    }
}
