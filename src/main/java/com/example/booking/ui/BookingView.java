package com.example.booking.ui;

import com.example.base.ui.MainLayout;
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

@Route(value = "booking", layout = MainLayout.class)
@PageTitle("Buchungen")
public class BookingView extends VerticalLayout
{
    private final BookingService service;
    private final Grid<Booking> grid = new Grid<>(Booking.class, false);

    private final TextField kundenname = new TextField("Kundenname");
    private final TextField fotograf = new TextField("Fotograf");
    private final TextField ort = new TextField("Ort");
    private final DatePicker buchungsdatum = new DatePicker("Buchungsdatum");
    private final Button speichernButton = new Button("Speichern");

    public BookingView(BookingService service)
    {
        this.service = service;

        grid.addColumn(Booking::getCustomerName).setHeader("Kunde");
        grid.addColumn(Booking::getPhotographer).setHeader("Fotograf");
        grid.addColumn(Booking::getLocation).setHeader("Ort");
        grid.addColumn(Booking::getBookingDate).setHeader("Datum");
        grid.addColumn(Booking::getStatus).setHeader("Status");

        speichernButton.addClickListener(e -> speichereBuchung());

        HorizontalLayout formular = new HorizontalLayout(
                kundenname, fotograf, ort, buchungsdatum, speichernButton
        );

        add(formular, grid);
        aktualisiereGrid();
    }

    private void speichereBuchung()
    {
        Booking buchung = new Booking(
                kundenname.getValue(),
                fotograf.getValue(),
                buchungsdatum.getValue()
        );
        buchung.setLocation(ort.getValue());
        service.save(buchung);
        aktualisiereGrid();
        leereFormular();
    }

    private void aktualisiereGrid()
    {
        grid.setItems(service.findAll());
    }

    private void leereFormular()
    {
        kundenname.clear();
        fotograf.clear();
        ort.clear();
        buchungsdatum.clear();
    }
}