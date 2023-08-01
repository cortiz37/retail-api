package com.retail.provider.converter;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public abstract class DateConverter {

    public static Date toDate(Timestamp timestamp) {
        return new Date(timestamp.getSeconds() * 1000);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault())
            .toInstant());
    }

    public static Timestamp toTimestamp(Date date) {
        Instant instant = date.toInstant();
        return Timestamp.newBuilder()
            .setSeconds(instant.getEpochSecond())
            .setNanos(instant.getNano())
            .build();
    }
}
