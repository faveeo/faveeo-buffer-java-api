package com.faveeo.publishing.buffer.api.representations;

import lombok.ToString;

import java.util.List;

@ToString
public class BufferSchedule {

    public final List<String> days;
    public final List<String> times;

    /**
     * Instantiates a new Buffer schedule.
     *
     * @param days  the days
     * @param times the times
     */
    public BufferSchedule(List<String> days, List<String> times) {
        this.days = days;
        this.times = times;
    }
}
