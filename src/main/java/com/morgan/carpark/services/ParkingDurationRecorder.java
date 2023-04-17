package com.morgan.carpark.services;

import com.morgan.carpark.models.Car;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingDurationRecorder implements DurationRecorder {
    private final Map<Car, Instant> durations = new HashMap<>();
    @Override
    public void captureEntryTime(Car car, Instant entryTime) {
        durations.put(car, entryTime);
    }

    @Override
    public Duration captureDuration(Car car, Instant exitTime) {
        return Optional.ofNullable(durations.remove(car))
                .map(checkInTime -> Duration.between(checkInTime, exitTime))
                .orElseGet(() -> Duration.ofHours(0));
    }
}