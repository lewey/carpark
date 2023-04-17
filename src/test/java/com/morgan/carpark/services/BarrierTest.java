package com.morgan.carpark.services;

import com.morgan.carpark.models.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarrierTest {

    private final Car car1 = new Car("REG1");

    @Test
    @DisplayName("Barrier should be able to admit a car to the car park")
    public void testAdmit(){
        final Barrier barrier = new Barrier();
        barrier.queue(car1);

        final Car admittedCar = barrier.admit();
        assertEquals(car1, admittedCar);
    }

    @Test
    @DisplayName("Barrier should be able to queue multiple cars")
    public void testQueueMultipleCars(){
        final Car car2 = new Car("REG2");
        final Barrier barrier = new Barrier();

        barrier.queue(car1);
        barrier.queue(car2);

        final Car admittedCar = barrier.admit();
        assertEquals(car1, admittedCar);
    }

}
