package com.example.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private BookingService service;

    @Test
    public void testFillTestData() {
        service.fillTestData(10);
        System.out.println(service);
    }
}
