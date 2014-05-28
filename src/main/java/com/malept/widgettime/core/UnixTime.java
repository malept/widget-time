package com.malept.widgettime.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnixTime {
    private long timestamp;

    public UnixTime() {
        // Jackson deserialization
    }

    public UnixTime(long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty
    public long getTimestamp() {
        return this.timestamp;
    }
}
