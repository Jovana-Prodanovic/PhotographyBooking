package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.example.booking.Booking;
import com.example.booking.BookingException;
import com.example.booking.BookingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "booking", layout = MainLayout.class)
@PageTitle("Bookings")
public class BookingView extends VerticalLayout
{
    private final Button buttonRemoveAll = new Button("Remove all bookings");
    private final Button buttonAdd10 = new Button("Add 10 bookings");
    private final Button buttonAddWrong = new Button("Add WRONG booking");

    private final Grid<Booking> grid = new Grid<Booking>(Booking.class, false);

    private final BookingService bookingService;

    public BookingView(@Autowired BookingService bookingService)
    {
        this.bookingService = bookingService;

        setSpacing(true);
        setSizeFull();

        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAll, buttonAdd10, buttonAddWrong);
        buttons.setSpacing(true);

        grid.setSizeFull();

        grid.addColumn(Booking::getId)
                .setHeader("ID")
                .setSortable(true);

        grid.addColumn(Booking::getBookingDate)
                .setHeader("Booking date")
                .setSortable(true);

        grid.addColumn(Booking::getCustomerName)
                .setHeader("Customer")
                .setSortable(true);

        grid.addColumn(Booking::getPhotographer)
                .setHeader("Photographer")
                .setSortable(true);

        grid.addColumn(Booking::getLocation)
                .setHeader("Location")
                .setSortable(true);

        grid.addColumn(Booking::getStatus)
                .setHeader("Status")
                .setSortable(true);

        grid.addComponentColumn(booking -> {
                    Checkbox cb = new Checkbox("Confirmed".equals(booking.getStatus()));
                    cb.setReadOnly(true);
                    return cb;
                })
                .setHeader("Confirmed")
                .setSortable(true)
                .setComparator(booking -> "Confirmed".equals(booking.getStatus()));

        grid.addComponentColumn(booking ->
                        new Button("Delete", e -> removeSelected(booking.getId())))
                .setHeader("Delete")
                .setSortable(false);

        grid.addComponentColumn(booking ->
                        new Button("One more", e -> oneMoreDay(booking.getId())))
                .setHeader("One more")
                .setSortable(false);

        buttonRemoveAll.addClickListener(b -> removeAllBookings());
        buttonAdd10.addClickListener(b -> add10Bookings());
        buttonAddWrong.addClickListener(b -> addWrongBooking());

        add(buttons, grid);

        buttonRemoveAll.setEnabled(!bookingService.findAll().isEmpty());

        reload();
    }

    private void removeAllBookings()
    {
        bookingService.removeAll();
        buttonRemoveAll.setEnabled(false);
        reload();
    }

    private void add10Bookings()
    {
        bookingService.fillTestData(10);
        buttonRemoveAll.setEnabled(true);
        reload();
    }

    private void addWrongBooking()
    {
        try
        {
            bookingService.addWrongBooking();
            reload();
        }
        catch (BookingException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void removeSelected(Long bookingId)
    {
        try
        {
            bookingService.removeBooking(bookingId);
            buttonRemoveAll.setEnabled(!bookingService.findAll().isEmpty());
            reload();
        }
        catch (BookingException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void oneMoreDay(Long bookingId)
    {
        try
        {
            bookingService.oneMoreDay(bookingId);
            reload();
        }
        catch (BookingException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void reload()
    {
        grid.setItems(bookingService.findAll());
    }
}