package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "galerie", layout = MainLayout.class)
@PageTitle("Galerie")
public class GalerieView extends VerticalLayout {
    public GalerieView() {
        add(new H1("🖼️ Galerie"));
        add(new H2("Unsere besten Aufnahmen"));
        add(new Paragraph("Hochzeit 💍"));
        add(new Paragraph("Portrait 👤"));
        add(new Paragraph("Events 🎉"));
        add(new Paragraph("Natur 🌿"));
    }
}
