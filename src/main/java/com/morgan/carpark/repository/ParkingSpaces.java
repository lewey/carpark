package com.morgan.carpark.repository;

import com.morgan.carpark.models.Car;

import java.util.HashSet;
import java.util.Set;

public class ParkingSpaces implements Repository {

    private final Set<Car> spaces;
    private final int totalNumberOfSpaces;

    public ParkingSpaces(int totalAmountOfSpaces){
       this.spaces = new HashSet<>(totalAmountOfSpaces);
       this.totalNumberOfSpaces = totalAmountOfSpaces;
    }

    public boolean add(Car car) {
       if(spacesAvailable()){
           spaces.add(car);
           return true;
       }else{
           return false;
       }
    }

    public boolean remove(Car car){
        return spaces.remove(car);
    }

    private boolean spacesAvailable(){
        return spaces.size() < totalNumberOfSpaces;
    }
}
