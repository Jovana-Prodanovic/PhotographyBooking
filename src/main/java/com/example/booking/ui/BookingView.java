package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.example.booking.Booking;
import com.example.booking.BookingException;
import com.example.booking.BookingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "booking", layout = MainLayout.class)
@PageTitle("Bookings")
public class BookingView extends VerticalLayout
{
    private final Button buttonAdd = new Button("Add booking");
    private final Button buttonRemoveAll = new Button("Remove all bookings");
    private final Button buttonAdd10 = new Button("Add 10 bookings");
    private final Button buttonAddWrong = new Button("Add WRONG booking");

    private final Grid<Booking> grid = new Grid<>(Booking.class, false);

    private final BookingService bookingService;

    public BookingView(@Autowired BookingService bookingService)
    {
        this.bookingService = bookingService;

        setSpacing(true);
        setSizeFull();

        HorizontalLayout buttons = new HorizontalLayout(buttonAdd, buttonRemoveAll, buttonAdd10, buttonAddWrong);
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
                        new Button("Edit booking", e -> addEditBooking(booking)))
                .setHeader("Edit")
                .setSortable(false);

        grid.addComponentColumn(booking ->
                        new Button("Delete", e -> removeSelected(booking.getId())))
                .setHeader("Delete")
                .setSortable(false);

        grid.addComponentColumn(booking ->
                        new Button("One more", e -> oneMoreDay(booking.getId())))
                .setHeader("One more")
                .setSortable(false);

        buttonAdd.addClickListener(b -> addEditBooking(null));
        buttonRemoveAll.addClickListener(b -> removeAllBookings());
        buttonAdd10.addClickListener(b -> add10Bookings());
        buttonAddWrong.addClickListener(b -> addWrongBooking());

        add(buttons, grid);

        reload();
    }

    private void addEditBooking(Booking existingBooking)
    {
        Booking booking;
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(existingBooking == null ? "New Booking" : "Edit Booking");

        if (existingBooking != null)
        {
            booking = existingBooking;
        }
        else
        {
            booking = new Booking();
        }

        TextField customerName = new TextField("Customer name");
        TextField photographer = new TextField("Photographer");
        DatePicker bookingDate = new DatePicker("Booking date");

        ComboBox<String> location = new ComboBox<>("Location");
        location.setItems("Wien", "Graz", "Salzburg", "Innsbruck", "Linz");

        ComboBox<String> status = new ComboBox<>("Status");
        status.setItems("Pending", "Confirmed", "Cancelled");

        BeanValidationBinder<Booking> binder =
                new BeanValidationBinder<>(Booking.class);

        binder.forField(customerName).bind("customerName");
        binder.forField(photographer).bind("photographer");
        binder.forField(bookingDate).bind("bookingDate");
        binder.forField(location).bind("location");
        binder.forField(status).bind("status");

        binder.setBean(booking);

        VerticalLayout formLayout = new VerticalLayout(
                customerName,
                photographer,
                bookingDate,
                location,
                status
        );

        Button saveButton = new Button("OK", event -> {
            if (binder.validate().isOk())
            {
                bookingService.add(booking);
                reload();
                dialog.close();
                Notification.show(existingBooking == null ? "Booking saved" : "Booking updated");
            }
            else
            {
                Notification.show("Check your input");
            }
        });

        Button cancelButton = new Button("Cancel", event -> dialog.close());

        dialog.add(formLayout);
        dialog.getFooter().add(cancelButton, saveButton);

        dialog.open();
    }

    private void removeAllBookings()
    {
        bookingService.removeAll();
        reload();
    }

    private void add10Bookings()
    {
        bookingService.fillTestData(10);
        reload();
    }

    private void addWrongBooking()
    {
        try
        {
            bookingService.addWrongBooking();
            reload();
        }
        catch (Exception e)
        {
            Notification.show("Wrong booking could not be saved");
        }
    }

    private void removeSelected(Long bookingId)
    {
        try
        {
            bookingService.removeBooking(bookingId);
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
        buttonRemoveAll.setEnabled(!bookingService.findAll().isEmpty());
    }
}