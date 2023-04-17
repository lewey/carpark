package com.morgan.carpark.models;

public class ParkingCharge {
    private final double charge;

    public ParkingCharge(double cost){
        this.charge = cost;
    }

    public double getCharge() {
        return charge;
    }
}
