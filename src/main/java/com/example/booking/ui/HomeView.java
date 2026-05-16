package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Startseite")
public class HomeView extends VerticalLayout
{
    public HomeView()
    {
        H1 titel = new H1("Startseite");

        Paragraph beschreibung = new Paragraph(
                "Willkommen! Dies ist die Startseite deiner Anwendung für Fotobuchungen."
        );

        Section karte1 = erstelleKarte(
                "Buchungen",
                "Hier kannst du die Buchungsseite öffnen und Reservierungen verwalten."
        );

        Section karte2 = erstelleKarte(
                "Übersicht",
                "Hier findest du einen Überblick über die wichtigsten Informationen der Anwendung."
        );

        FormLayout raster = new FormLayout();
        raster.setWidthFull();
        raster.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("700px", 2)
        );
        raster.add(karte1, karte2);

        Button buchungButton = new Button("Buchungen öffnen");
        buchungButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("booking"))
        );

        setSizeFull();
        setWidthFull();
        setPadding(true);
        setSpacing(true);
        add(titel, beschreibung, raster, buchungButton);
    }

    private Section erstelleKarte(String titel, String text)
    {
        Section karte = new Section();

        H1 kartenTitel = new H1(titel);
        kartenTitel.getStyle().set("font-size", "20px");
        kartenTitel.getStyle().set("margin", "0");

        Paragraph kartenText = new Paragraph(text);
        kartenText.getStyle().set("margin", "0");

        VerticalLayout inhalt = new VerticalLayout(kartenTitel, kartenText);
        inhalt.setPadding(false);
        inhalt.setSpacing(true);

        karte.add(inhalt);
        karte.getStyle().set("padding", "16px");
        karte.getStyle().set("border", "1px solid #dcdcdc");
        karte.getStyle().set("border-radius", "12px");
        karte.getStyle().set("background", "white");
        karte.getStyle().set("box-sizing", "border-box");
        karte.setWidthFull();

        return karte;
    }
}