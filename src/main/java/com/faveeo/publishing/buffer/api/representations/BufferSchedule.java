package com.faveeo.publishing.buffer.api.representations;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BufferSchedule {

    public List<String> days;
    public List<String> times;

}
