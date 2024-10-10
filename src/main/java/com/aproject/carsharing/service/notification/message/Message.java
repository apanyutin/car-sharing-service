package com.aproject.carsharing.service.notification.message;

import com.aproject.carsharing.model.Payment;
import com.aproject.carsharing.model.Rental;

public class Message {

    public static String getRentalMessageForCustomer(Rental rental) {
        return String.format("Dear %s, you've just rented %s from %tF to %tF",
                rental.getUser().getFirstName(),
                rental.getCar().getBrand() + " " + rental.getCar().getModel(),
                rental.getRentalDate(),
                rental.getReturnDate());
    }

    public static String getSuccessfulPaymentMessageForCustomer(Payment payment) {
        return String.format("Payment of %f usd until %tF has been accepted",
                payment.getAmountToPay(),
                payment.getRental().getReturnDate());
    }
}
