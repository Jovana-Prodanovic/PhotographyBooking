package com.example.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Booking {

    @Id
    private Long id;
    private String customerName;
    private String photographer;
    private String location;
    private LocalDate bookingDate;
    private String status;

    private static final AtomicLong sequence = new AtomicLong(1);

    public Booking() {
    }

    public Booking(String customerName, String photographer, LocalDate bookingDate) {
        setId();
        setCustomerName(customerName);
        setPhotographer(photographer);
        setBookingDate(bookingDate);
        setLocation("");
        setStatus("Pending");
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = sequence.getAndIncrement();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new BookingException("Customer name must not be empty");
        }
        this.customerName = customerName;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        if (photographer == null || photographer.isBlank()) {
            throw new BookingException("Photographer must not be empty");
        }
        this.photographer = photographer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null) {
            this.location = "";
        } else {
            this.location = location;
        }
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        if (bookingDate == null) {
            throw new BookingException("Booking date must not be empty");
        }
        if (bookingDate.isBefore(LocalDate.now())) {
            throw new BookingException("Booking date must not be in the past");
        }
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new BookingException("Status must not be empty");
        }
        if (!status.equals("Pending") && !status.equals("Confirmed") && !status.equals("Cancelled")) {
            throw new BookingException("Status must be Pending, Confirmed or Cancelled");
        }
        this.status = status;
    }
}