package com.faveeo.publishing.buffer.api.representations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.faveeo.publishing.buffer.api.representations.BufferSchedule;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@ToString
public class BufferProfileRepresentation {

    public String avatar;
    public long created_at;
    @JsonProperty("default")
    public boolean _default;
    public String formatted_username;
    public String id;
    public List<BufferSchedule> schedules;
    public String service;
    public String service_id;
    public String service_username;
    public HashMap<String, String> statistics;
    public List<HashMap<String, String>> team_members;
    public String timezone;
    public String user_id;

    public Long followers() {
        if(statistics != null && statistics.containsKey("followers")) {
            return Long.parseLong(statistics.get("followers"));
        } else {
            return null;
        }
    }

}
