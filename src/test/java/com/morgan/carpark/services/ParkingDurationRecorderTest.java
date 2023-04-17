package com.morgan.carpark.services;

import com.morgan.carpark.models.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingDurationRecorderTest {

    @Test
    @DisplayName("ParkingDurationRecorder should create a Duration based upon creating and removing a record")
    public void testCaptureDuration(){
        final ParkingDurationRecorder parkingDurationRecorder = new ParkingDurationRecorder();
        final Instant checkInTime = Instant.parse("2023-03-16T09:00:00.00Z");
        final Instant checkOutTime = checkInTime.plus(Duration.ofHours(1));
        final Car car = new Car("Reg1");

        parkingDurationRecorder.captureEntryTime(car, checkInTime);
        final Duration duration = parkingDurationRecorder.captureDuration(car, checkOutTime);
        assertEquals(1, duration.toHours());
    }

    @Test
    @DisplayName("A car record is removed from capturing a duration")
    public void testX(){
        final ParkingDurationRecorder parkingDurationRecorder = new ParkingDurationRecorder();
        final Car car = new Car("Reg1");
        parkingDurationRecorder.captureEntryTime(car, Instant.now());
        parkingDurationRecorder.captureEntryTime(car, Instant.now());
    }


    @Test
    @DisplayName("ParkingDurationRecorder should return a duration of 0 when a car record does not exist")
    public void testNoStoredCar(){
        final ParkingDurationRecorder parkingDurationRecorder = new ParkingDurationRecorder();
        final Instant checkOutTime = Instant.parse("2023-03-16T09:00:00.00Z");
        final Car car = new Car("Reg1");

        parkingDurationRecorder.captureEntryTime(car, checkOutTime);
        final Duration duration = parkingDurationRecorder.captureDuration(car, checkOutTime);
        assertEquals(0, duration.toHours());
    }
}
