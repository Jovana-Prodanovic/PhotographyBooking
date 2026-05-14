package com.example.booking;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @NotBlank
    @Column(name = "customer_name", nullable = false)
    private String customerName = "";

    @NotBlank
    @Column(name = "photographer", nullable = false)
    private String photographer = "";

    @Column(name = "location")
    private String location = "";

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "status")
    private String status = "Pending";

    protected Booking() {}

    public Booking(String customerName, String photographer, LocalDate bookingDate) {
        this.customerName = customerName;
        this.photographer = photographer;
        this.bookingDate = bookingDate;
    }

    public Long getId() { return id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String n) { this.customerName = n; }

    public String getPhotographer() { return photographer; }
    public void setPhotographer(String p) { this.photographer = p; }

    public String getLocation() { return location; }
    public void setLocation(String l) { this.location = l; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate d) { this.bookingDate = d; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !getClass().isAssignableFrom(obj.getClass())) return false;
        if (obj == this) return true;
        Booking other = (Booking) obj;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}