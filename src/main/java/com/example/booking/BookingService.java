package com.example.booking;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    public void delete(Booking booking) {
        repository.delete(booking);
    }
}
