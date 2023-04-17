package com.morgan.carpark.services;

import com.morgan.carpark.models.Car;

import java.util.LinkedList;
import java.util.Queue;

public class Barrier {
    private final Queue<Car> queue = new LinkedList<>();

    public boolean queue(Car car){
        return queue.offer(car);
    }

    public Car admit(){
        return queue.poll();
    }
}
