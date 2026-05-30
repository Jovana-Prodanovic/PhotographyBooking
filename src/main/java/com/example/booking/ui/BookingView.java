package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.example.booking.Booking;
import com.example.booking.BookingService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "booking", layout = MainLayout.class)
@PageTitle("Bookings")
public class BookingView extends VerticalLayout
{
    private final Grid<Booking> grid = new Grid<Booking>(Booking.class, true);
    private final BookingService bookingService;

    public BookingView(@Autowired BookingService bookingService)
    {
        this.bookingService = bookingService;

        setSpacing(true);
        setSizeFull();

        grid.setSizeFull();

        add(grid);
        reload();
    }

    private void reload()
    {
        grid.setItems(bookingService.findAll());
    }
}