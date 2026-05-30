package com.example.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
public class Booking implements Cloneable
{
    @Id
    private Long id;
    private String customerName;
    private String photographer;
    private String location;
    private LocalDate bookingDate;
    private String status;

    private static final AtomicLong sequence = new AtomicLong(1000);
    private static final String[] allowedStatuses = {"Pending", "Confirmed", "Cancelled"};

    public Booking()
    {
    }

    public Booking(String customerName, String photographer, LocalDate bookingDate)
    {
        setId();
        setCustomerName(customerName);
        setPhotographer(photographer);
        setBookingDate(bookingDate);
        setStatus("Pending");
        setLocation("");
    }

    public Booking(Long id, String customerName, String photographer, String location, LocalDate bookingDate, String status)
    {
        setId(id);
        setCustomerName(customerName);
        setPhotographer(photographer);
        setLocation(location);
        setBookingDate(bookingDate);
        setStatus(status);
    }

    public void setId()
    {
        this.id = sequence.getAndIncrement();
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setCustomerName(String customerName)
    {
        if (customerName == null || customerName.isBlank())
        {
            throw new BookingException("Customer name must not be empty");
        }

        this.customerName = customerName;
    }

    public void setPhotographer(String photographer)
    {
        if (photographer == null || photographer.isBlank())
        {
            throw new BookingException("Photographer must not be empty");
        }

        this.photographer = photographer;
    }

    public void setLocation(String location)
    {
        if (location == null)
        {
            this.location = "";
        }
        else
        {
            this.location = location;
        }
    }

    public void setBookingDate(LocalDate bookingDate)
    {
        if (bookingDate == null)
        {
            throw new BookingException("Booking date must not be empty");
        }

        if (bookingDate.isBefore(LocalDate.now()))
        {
            throw new BookingException("Booking date must not be in the past");
        }

        this.bookingDate = bookingDate;
    }

    public void setStatus(String status)
    {
        boolean valid = false;

        for (String s : allowedStatuses)
        {
            if (s.equals(status))
            {
                valid = true;
                break;
            }
        }

        if (valid == false)
        {
            throw new BookingException("Status must be: Pending, Confirmed or Cancelled");
        }

        this.status = status;
    }

    @Override
    public Booking clone()
    {
        return new Booking(id, customerName, photographer, location, bookingDate, status);
    }
}