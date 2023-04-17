package com.morgan.carpark.services;

import com.morgan.carpark.models.Car;

import java.time.Duration;
import java.time.Instant;

public interface DurationRecorder {
    void captureEntryTime(Car car, Instant entryTime);
    Duration captureDuration(Car car, Instant exitTime);
}
