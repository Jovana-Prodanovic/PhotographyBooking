package com.example.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(min = 3, max = 50, message = "Customer name must be between 3 and 50 characters")
    private String customerName;

    @NotBlank(message = "Photographer is required")
    @Size(min = 3, max = 50, message = "Photographer must be between 3 and 50 characters")
    private String photographer;

    @NotNull(message = "Booking date is required")
    @Future(message = "Booking date must be in the future")
    private LocalDate bookingDate;

    @NotBlank(message = "Location is required")
    @Size(min = 2, max = 40, message = "Location must be between 2 and 40 characters")
    private String location;

    @NotBlank(message = "Status is required")
    @Size(min = 3, max = 20, message = "Status must be between 3 and 20 characters")
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