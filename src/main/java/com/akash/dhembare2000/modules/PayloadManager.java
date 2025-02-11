package com.akash.dhembare2000.modules;

import com.akash.dhembare2000.pojos.Booking;
import com.akash.dhembare2000.pojos.BookingDates;
import com.akash.dhembare2000.pojos.BookingResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class PayloadManager {
    // Serialization and Deserialization
    Gson gson;

    // Converting the JAVA object to String
    // {}
    public String createPayloadBookingAsString(){
        Booking booking= new Booking();
        booking.setFirstname("Akash");
        booking.setLastname("Dhembare");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates= new BookingDates();
        bookingDates.setCheckin("2025-01-01");
        bookingDates.setCheckout("2025-02-02");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        // Java object -> JSON String (bytestream) -> serialization
        gson= new Gson();
        String jsonStringPayload = gson.toJson(booking);
        System.out.println(jsonStringPayload);

        return jsonStringPayload;
    }

    public String createPayloadBookingAsStringFaker() {
        Faker faker= new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-01-01");
        bookingDates.setCheckout("2025-02-02");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        String jsonStringPayload=gson.toJson(booking);
        System.out.println(jsonStringPayload);

        return jsonStringPayload;
    }

    public BookingResponse bookingResponseJava(String responseString){
        gson= new Gson();
        BookingResponse bookingResponse= gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
}
