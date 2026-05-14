package com.example.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Test
    public void tasks_are_stored_in_the_database_with_the_current_timestamp() {
        var now = Instant.now();
        var desc = "Do this";
        var due = LocalDate.of(2025, 2, 7);
        bookingService.createTask(desc, due);

        var task = bookingService.list(PageRequest.ofSize(1)).get(0);
        assertThat(task.getDescription().equals(desc));
        assertThat(task.getDueDate().equals(due));
        assertThat(task.getCreationDate().isAfter(now));
    }

    @Test
    public void tasks_are_validated_before_they_are_stored() {
        assertThatThrownBy(() -> bookingService.createTask("X".repeat(Booking.DESCRIPTION_MAX_LENGTH + 1), null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
