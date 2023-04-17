package com.morgan.carpark.repository;

import com.morgan.carpark.models.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingSpacesTest {

    private Car car1 = new Car("REG1");
    private Car car2 = new Car("REG2");

    @Test
    @DisplayName("park should park a car if a space is available")
    public void testParkShouldParkCar(){
        final int parkingSpacesCapacity = 1;
        final ParkingSpaces parkingSpaces = new ParkingSpaces(parkingSpacesCapacity);
        final boolean parkingResult = parkingSpaces.add(car1);

        assertTrue(parkingResult);
    }

    @Test
    @DisplayName("park should be able to park multiple cars")
    public void testParkShouldParkMultipleCars(){
        final int parkingSpacesCapacity = 2;
        final ParkingSpaces parkingSpaces = new ParkingSpaces(parkingSpacesCapacity);
        final boolean parkingResult1 = parkingSpaces.add(car1);
        final boolean parkingResult2 = parkingSpaces.add(car2);

        assertTrue(parkingResult1);
        assertTrue(parkingResult2);
    }


    @Test
    @DisplayName("park should not park a car if a space is not available")
    public void testParkShouldNotParkCar(){
        final int parkingSpacesCapacity = 1;
        final ParkingSpaces parkingSpaces = new ParkingSpaces(parkingSpacesCapacity);
        parkingSpaces.add(car1);
        final boolean parkingResult = parkingSpaces.add(car2);

        assertFalse(parkingResult);
    }

    @Test
    @DisplayName("remove should allow a car parking space to become available")
    public void testRemoveShouldFreeSpace(){
        final int parkingSpacesCapacity = 1;
        final ParkingSpaces parkingSpaces = new ParkingSpaces(parkingSpacesCapacity);
        parkingSpaces.add(car1);
        parkingSpaces.remove(car1);
        final boolean parkingResult = parkingSpaces.add(car2);

        assertTrue(parkingResult);
    }

    @Test
    @DisplayName("remove should indicate false if a car was not parked")
    public void testRemoveNoCar(){
        final int parkingSpacesCapacity = 1;
        final ParkingSpaces parkingSpaces = new ParkingSpaces(parkingSpacesCapacity);
        parkingSpaces.add(car1);
        final boolean parkingResult = parkingSpaces.add(car2);

        assertFalse(parkingResult);
    }
}
