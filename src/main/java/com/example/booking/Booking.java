package com.example.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Photographer is required")
    private String photographer;

    @NotNull(message = "Booking date is required")
    @Future(message = "Booking date must be in the future")
    private LocalDate bookingDate;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Status is required")
    private String status;

    public Booking()
    {
    }

    public Booking(String customerName, String photographer, LocalDate bookingDate)
    {
        this.customerName = customerName;
        this.photographer = photographer;
        this.bookingDate = bookingDate;
        this.location = "Wien";
        this.status = "Pending";
    }

    public Long getId()
    {
        return id;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getPhotographer()
    {
        return photographer;
    }

    public void setPhotographer(String photographer)
    {
        this.photographer = photographer;
    }

    public LocalDate getBookingDate()
    {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate)
    {
        this.bookingDate = bookingDate;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}