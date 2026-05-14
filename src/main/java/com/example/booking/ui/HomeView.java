package com.example.booking.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("Home - Photography Booking")
public class HomeView extends VerticalLayout {
    public HomeView() {
        add(new H1("📸 Photography Booking"));
        add(new H2("Willkommen!"));
        add(new Paragraph("Buche deinen professionellen Fotografen noch heute!"));
    }
}
