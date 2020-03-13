package com.cabinvoice;


public class InvoiceService {
    private static double MINIMUM_COST_PER_KILOMETER;
    private static int COST_PER_TIME;
    private static double MINIMUM_FARE;
    private RideRepository rideRepository;


    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(RideType rideEnum, double distance, int time) {
        setValue(rideEnum);
        double premiumTotalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(premiumTotalFare, MINIMUM_FARE);
    }

    private void setValue(RideType rideEnum) {
        MINIMUM_COST_PER_KILOMETER=rideEnum.costPerKm;
        COST_PER_TIME=rideEnum.costPerMin;
        MINIMUM_FARE=rideEnum.minimumFare;
    }

    public InvoiceSummary addRides(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.addRides(rideRepository.getRides(userId));
    }

//    public double calculatePremiumFare(double distance, int time) {
//        double premiumTotalFare = distance * MINIMUM_PREMIUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
//        return Math.max(premiumTotalFare, MINIMUM_PREMIUM_FARE);
//    }
//
//    public InvoiceSummary addPremiumRides(Ride[] rides) {
//        double totalFare = 0;
//        for (Ride ride : rides) {
//            totalFare += this.calculatePremiumFare(ride.distance, ride.time);
//        }
//        return new InvoiceSummary(rides.length, totalFare);
//    }
}
