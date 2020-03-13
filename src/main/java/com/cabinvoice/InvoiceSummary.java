package com.cabinvoice;


public class InvoiceSummary {
    public double averageFare;
    public double totalFare;
    public int numberOfRides;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numberOfRides=numberOfRides;
        this.totalFare=totalFare;
        this.averageFare=this.totalFare/this.numberOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(that.averageFare, averageFare) == 0 &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                numberOfRides == that.numberOfRides;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
