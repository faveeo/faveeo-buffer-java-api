package com.faveeo.publishing.buffer.api.representations;

import java.util.List;

public class BufferSchedule {

    public final List<String> days;
    public final List<String> times;

    public BufferSchedule(List<String> days, List<String> times) {
        this.days = days;
        this.times = times;
    }
}
