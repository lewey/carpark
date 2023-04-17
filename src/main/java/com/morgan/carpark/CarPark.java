package com.morgan.carpark;

import com.morgan.carpark.models.Car;
import com.morgan.carpark.models.ParkingCharge;
import com.morgan.carpark.models.ParkingStatus;
import com.morgan.carpark.repository.Repository;
import com.morgan.carpark.services.Barrier;
import com.morgan.carpark.services.DurationRecorder;

import java.time.Duration;
import java.time.Instant;

public class CarPark {

    private final Repository repository;
    private final DurationRecorder durationRecorder;
    private final Barrier barrier;
    public CarPark(Repository repository, DurationRecorder durationRecorder, Barrier barrier){
        this.repository = repository;
        this.durationRecorder = durationRecorder;
        this.barrier = barrier;
    }

    public ParkingStatus park(Car car){
        barrier.queue(car);
        final boolean isParked = repository.add(barrier.admit());

        if(isParked){
            durationRecorder.captureEntryTime(car, Instant.now());
            return ParkingStatus.Parked;
        }else{
            return ParkingStatus.Refused;
        }
    }

    public ParkingCharge leave(Car car){
        repository.remove(car);
        final Duration lengthOfStay = durationRecorder.captureDuration(car, Instant.now());
        final double parkingCharge = calculateParkingCharge(lengthOfStay);
        return new ParkingCharge(parkingCharge);
    }

    private double calculateParkingCharge(Duration parkingDuration){
        return parkingDuration.toHours() * 2;
    }
}