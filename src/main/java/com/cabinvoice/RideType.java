package com.cabinvoice;

public enum RideType {
    NORMAL(10,1,5),PREMIUM(15,2,20);

    public final double costPerKm;
    public final int costPerMin;
    public final double minimumFare;

    RideType(double costPerKm, int costPerMin, double minimumFare) {
        this.costPerKm = costPerKm;
        this.costPerMin = costPerMin;
        this.minimumFare = minimumFare;
    }
}
