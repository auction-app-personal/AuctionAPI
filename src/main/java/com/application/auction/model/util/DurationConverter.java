package com.application.auction.model.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        if (duration == null) {
            return null;
        }
        return duration.toHoursPart() + ":" + duration.toMinutesPart()+":"+duration.toSecondsPart();
    }

    @Override
    public Duration convertToEntityAttribute(String intervalString) {
        if (intervalString == null || intervalString.isEmpty()) {
            return null;
        }
        return Duration.parse(intervalString);
    }
}