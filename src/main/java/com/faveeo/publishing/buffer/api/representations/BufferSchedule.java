package com.faveeo.publishing.buffer.api.representations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferSchedule {

    public List<String> days;
    public List<String> times;

}
