package com.cabinvoice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceGenerator;

    @Before
    public void setUp() throws Exception {
        invoiceGenerator = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL,distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL,distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.PREMIUM,2.0, 5),
                new Ride(RideType.NORMAL,0.1, 1)};
        InvoiceSummary summary = invoiceGenerator.addRides(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId="a@b.com";
        Ride[] rides = {new Ride(RideType.NORMAL,2.0, 5), new Ride(RideType.NORMAL,0.1, 1)};
        invoiceGenerator.addRides(userId,rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(RideType.PREMIUM,distance,time);
        Assert.assertEquals(40.0,fare,0.0);
    }

    @Test
    public void givenPremiumLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance=0.1;
        int time=1;
        double fare = invoiceGenerator.calculateFare(RideType.PREMIUM,distance, time);
        Assert.assertEquals(20.0,fare,0.0);
    }

    @Test
    public void givenPremiumMultipleRides_ShouldReturnInvoiceSummary() {
       Ride[] rides = { new Ride(RideType.PREMIUM,2.0,5),
               new Ride(RideType.PREMIUM,0.1,1)};
        InvoiceSummary invoiceSummary = invoiceGenerator.addRides(rides);
        InvoiceSummary premiumExpectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(premiumExpectedInvoiceSummary,invoiceSummary);
    }
}
