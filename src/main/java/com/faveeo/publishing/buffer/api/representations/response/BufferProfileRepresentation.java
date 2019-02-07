package com.faveeo.publishing.buffer.api.representations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.faveeo.publishing.buffer.api.representations.BufferSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BufferProfileRepresentation {

    public String avatar;
    public long created_at;
    @JsonProperty("default") //NON-NLS
    public boolean _default;
    public String formatted_username;
    public String id;
    public List<BufferSchedule> schedules;
    public String service;
    public String service_id;
    public String service_username;
    public Map<String, String> statistics;
    public List<HashMap<String, String>> team_members;
    public String timezone;
    public String user_id;

    public Optional<Long> followers() {
        if (statistics != null && statistics.containsKey("followers")) { //NON-NLS
            return Optional.of(Long.parseLong(statistics.get("followers"))); //NON-NLS
        } else {
            return Optional.empty();
        }
    }

}
