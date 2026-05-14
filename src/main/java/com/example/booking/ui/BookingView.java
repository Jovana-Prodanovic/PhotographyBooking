package com.example.booking.ui;

import com.example.booking.Booking;
import com.example.booking.BookingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("booking")
@PageTitle("Photography Booking")
public class BookingView extends VerticalLayout {

    private final BookingService service;
    private final Grid<Booking> grid = new Grid<>(Booking.class, false);

    private final TextField customerName = new TextField("Customer Name");
    private final TextField photographer = new TextField("Photographer");
    private final TextField location = new TextField("Location");
    private final DatePicker bookingDate = new DatePicker("Booking Date");
    private final Button saveBtn = new Button("Save");

    public BookingView(BookingService service) {
        this.service = service;

        grid.addColumn(Booking::getCustomerName).setHeader("Customer");
        grid.addColumn(Booking::getPhotographer).setHeader("Photographer");
        grid.addColumn(Booking::getLocation).setHeader("Location");
        grid.addColumn(Booking::getBookingDate).setHeader("Date");
        grid.addColumn(Booking::getStatus).setHeader("Status");

        saveBtn.addClickListener(e -> saveBooking());

        HorizontalLayout form = new HorizontalLayout(
                customerName, photographer, location, bookingDate, saveBtn
        );

        add(form, grid);
        refreshGrid();
    }

    private void saveBooking() {
        Booking booking = new Booking(
                customerName.getValue(),
                photographer.getValue(),
                bookingDate.getValue()
        );
        booking.setLocation(location.getValue());
        service.save(booking);
        refreshGrid();
        clearForm();
    }

    private void refreshGrid() {
        grid.setItems(service.findAll());
    }

    private void clearForm() {
        customerName.clear();
        photographer.clear();
        location.clear();
        bookingDate.clear();
    }
}
