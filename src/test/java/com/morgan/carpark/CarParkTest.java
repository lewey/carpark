package com.morgan.carpark;


import com.morgan.carpark.models.Car;
import com.morgan.carpark.models.ParkingCharge;
import com.morgan.carpark.models.ParkingStatus;
import com.morgan.carpark.repository.Repository;
import com.morgan.carpark.services.Barrier;
import com.morgan.carpark.services.DurationRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class CarParkTest {

    private final Repository repository = mock(Repository.class);
    private final DurationRecorder durationRecorder = mock(DurationRecorder.class);
    private final Barrier barrier = mock(Barrier.class);

    private final Car car = new Car("REG1");

    @BeforeEach
    public void resetMocks(){
        reset(repository, durationRecorder, barrier);
    }

    @Test
    @DisplayName("car park should park a single vehicle when a space is available")
    public void testParkSingleCar(){
        final CarPark carPark = new CarPark(repository, durationRecorder, barrier);
        when(barrier.admit()).thenReturn(car);
        when(repository.add(car)).thenReturn(true);

        final ParkingStatus parkingStatus = carPark.park(car);
        assertEquals(ParkingStatus.Parked, parkingStatus);
        verify(durationRecorder).captureEntryTime(eq(car), any(Instant.class));

    }

    @Test
    @DisplayName("car park should refuse to park when there are no available spaces")
    public void testParkRefuseSingleCar(){
        final CarPark carPark = new CarPark(repository, durationRecorder, barrier);
        when(barrier.admit()).thenReturn(car);
        when(repository.add(car)).thenReturn(false);

        final ParkingStatus parkingStatus = carPark.park(car);
        assertEquals(ParkingStatus.Refused, parkingStatus);
        verify(durationRecorder, never()).captureEntryTime(any(), any());

    }

    @Test
    @DisplayName("car park should return a parking charge for 1.5 hours parking duration and removes the car")
    public void testLeaveReturnParkingCharge(){
        final CarPark carPark = new CarPark(repository, durationRecorder, barrier);
        when(barrier.admit()).thenReturn(car);
        when(durationRecorder.captureDuration(eq(car), any(Instant.class))).thenReturn(Duration.ofMinutes(90));
        final ParkingCharge parkingCharge = carPark.leave(car);

        assertEquals(2.0, parkingCharge.getCharge());
        verify(repository).remove(car);
    }
}
