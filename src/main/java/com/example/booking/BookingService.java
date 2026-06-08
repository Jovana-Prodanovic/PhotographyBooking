package com.example.booking;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class BookingService
{
    private final BookingRepository repository;

    public BookingService(BookingRepository repository)
    {
        this.repository = repository;
    }

    public ArrayList<Booking> findAll()
    {
        return new ArrayList<Booking>(repository.findAll());
    }

    public void save(Booking booking)
    {
        repository.save(booking);
    }

    public void delete(Booking booking)
    {
        repository.delete(booking);
    }

    public void removeAll()
    {
        repository.deleteAll();
    }

    public void fillTestData(int anz)
    {
        Faker faker = new Faker();

        String[] photographers = {"Anna Müller", "Lukas Bauer", "Sofia Reiter", "Max Huber", "Elena König"};
        String[] locations = {"Wien", "Graz", "Salzburg", "Innsbruck", "Linz"};
        String[] statuses = {"Pending", "Confirmed", "Cancelled"};

        for (int i = 0; i < anz; i++)
        {
            Booking booking = new Booking(
                    faker.name().fullName(),
                    photographers[faker.number().numberBetween(0, photographers.length)],
                    LocalDate.now().plusDays(faker.number().numberBetween(1, 60))
            );

            booking.setLocation(locations[faker.number().numberBetween(0, locations.length)]);
            booking.setStatus(statuses[faker.number().numberBetween(0, statuses.length)]);

            repository.save(booking);
        }
    }

    public void addWrongBooking()
    {
        Booking booking = new Booking(
                "Wrong Customer",
                "Anna Müller",
                LocalDate.now().minusDays(5)
        );

        booking.setLocation("Wien");

        repository.save(booking);
    }

    public void removeBooking(Long bookingId)
    {
        Booking booking;

        if (bookingId == null)
        {
            throw new BookingException("No Booking ID!");
        }

        booking = repository.findById(bookingId).orElse(null);

        if (booking == null)
        {
            throw new BookingException("Booking with the ID " + bookingId + " not found!");
        }

        repository.delete(booking);
    }

    public void oneMoreDay(Long bookingId)
    {
        Booking booking;

        if (bookingId == null)
        {
            throw new BookingException("No Booking ID!");
        }

        booking = repository.findById(bookingId).orElse(null);

        if (booking == null)
        {
            throw new BookingException("Booking with the ID " + bookingId + " not found!");
        }

        booking.setBookingDate(booking.getBookingDate().plusDays(1));
        repository.save(booking);
    }
}

