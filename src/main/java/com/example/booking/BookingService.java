package com.example.booking;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookingService
{
    private final BookingRepository repository;

    public BookingService(BookingRepository repository)
    {
        this.repository = repository;

        if (repository.count() == 0)
        {
            fillTestData(50);
        }
    }

    public ArrayList<Booking> findAll()
    {
        return new ArrayList<>(repository.findAll());
    }

    public Booking add(Booking booking)
    {
        return repository.save(booking);
    }

    public void removeAll()
    {
        repository.deleteAll();
    }

    public void removeBooking(Long bookingId)
    {
        if (bookingId == null)
        {
            throw new BookingException("No Booking ID!");
        }

        if (!repository.existsById(bookingId))
        {
            throw new BookingException("Booking with the ID " + bookingId + " not found!");
        }

        repository.deleteById(bookingId);
    }

    public void oneMoreDay(Long bookingId)
    {
        if (bookingId == null)
        {
            throw new BookingException("No Booking ID!");
        }

        Optional<Booking> booking = repository.findById(bookingId);

        if (booking.isEmpty())
        {
            throw new BookingException("Booking not found!");
        }
        else
        {
            Booking b = booking.get();
            b.setBookingDate(b.getBookingDate().plusDays(1));
            repository.save(b);
        }
    }

    public void addWrongBooking()
    {
        Booking booking = new Booking(
                "A",
                "X",
                LocalDate.now().minusDays(5)
        );

        booking.setLocation("");
        booking.setStatus("P");

        repository.saveAndFlush(booking);
    }

    public void fillTestData(int anz)
    {
        Faker faker = new Faker();

        String[] photographers = {"Anna Müller", "Lukas Bauer", "Sofia Reiter", "Max Huber", "Elena König"};
        String[] locations = {"Wien", "Graz", "Salzburg", "Innsbruck", "Linz"};
        String[] statuses = {"Pending", "Confirmed", "Cancelled"};

        for (int i = 0; i < anz; i++)
        {
            Booking booking = new Booking();
            booking.setCustomerName(faker.name().fullName());
            booking.setPhotographer(photographers[faker.number().numberBetween(0, photographers.length)]);
            booking.setBookingDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 60)));
            booking.setLocation(locations[faker.number().numberBetween(0, locations.length)]);
            booking.setStatus(statuses[faker.number().numberBetween(0, statuses.length)]);

            repository.save(booking);
        }
    }

    @Override
    public String toString()
    {
        return repository.findAll()
                .stream()
                .map(Booking::toString)
                .reduce("", (a, b) -> a + b + "\n");
    }
}

