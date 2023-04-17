package com.morgan.carpark.repository;

import com.morgan.carpark.models.Car;

public interface Repository {
    boolean add(Car car);
    boolean remove(Car car);
}
