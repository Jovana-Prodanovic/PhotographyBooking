package com.example.booking;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        ArrayList<Booking> copy = new ArrayList<Booking>(repository.findAll());
        return copy;
    }

    public void save(Booking booking)
    {
        repository.save(booking);
    }

    public void delete(Booking booking)
    {
        repository.delete(booking);
    }

    public void fillTestData(int anz)
    {
        Faker faker = new Faker();

        String[] photographers = {"Anna Müller", "Lukas Bauer", "Sofia Reiter", "Max Huber", "Elena König"};
        String[] locations = {"Wien", "Graz", "Salzburg", "Innsbruck", "Linz"};
        String[] statuses = {"Pending", "Confirmed", "Cancelled"};

        repository.deleteAll();

        ArrayList<Booking> bookings = new ArrayList<Booking>();

        for (int i = 0; i < anz; i++)
        {
            Booking b = new Booking();

            b.setId();
            b.setCustomerName(faker.name().fullName());
            b.setPhotographer(photographers[faker.number().numberBetween(0, photographers.length)]);
            b.setLocation(locations[faker.number().numberBetween(0, locations.length)]);
            b.setBookingDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 60)));
            b.setStatus(statuses[faker.number().numberBetween(0, statuses.length)]);

            bookings.add(b);
        }

        repository.saveAll(bookings);
    }

    @Override
    public String toString()
    {
        return repository.findAll().stream()
                .map(Booking::toString)
                .collect(Collectors.joining("\n"));
    }
}

