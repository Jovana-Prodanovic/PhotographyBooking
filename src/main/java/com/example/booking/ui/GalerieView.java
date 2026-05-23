package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "galerie", layout = MainLayout.class)
@PageTitle("Gallery")
public class GalerieView extends VerticalLayout
{

    private VerticalLayout createCard(String title, String description, String subInfo)
    {
        H2 categoryTitle = new H2(title);
        categoryTitle.getStyle().set("font-family", "Georgia, serif").set("margin", "0 0 10px 0");

        Paragraph text = new Paragraph(description);
        text.getStyle().set("font-family", "Georgia, serif").set("color", "#555").set("margin", "0");

        Paragraph info = new Paragraph(subInfo);
        info.getStyle().set("font-family", "Georgia, serif").set("color", "#888").set("margin", "0");

        VerticalLayout card = new VerticalLayout(categoryTitle, text, info);
        card.setWidth("350px");
        card.setPadding(true);
        card.setSpacing(false);
        card.setDefaultHorizontalComponentAlignment(Alignment.CENTER);


        card.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px")
                .set("text-align", "center")
                .set("background-color", "#fcfcfc");

        return card;
    }

    public GalerieView()
    {
        setSpacing(false);
        add(HomeView.getHeader());


        VerticalLayout cat1 = createCard("Wedding", "Elegant wedding photography for unforgettable memories.", "Perfect for ceremonies, couples and celebrations.");
        VerticalLayout cat2 = createCard("Portrait", "Professional portraits with style and personality.", "Ideal for individuals, couples and families.");
        VerticalLayout cat3 = createCard("Events", "Creative event photography for every special occasion.", "Beautiful moments captured with care and precision.");
        VerticalLayout cat4 = createCard("Nature", "Natural outdoor sessions with soft light and atmosphere.", "A wonderful choice for unique and timeless images.");


        FlexLayout cardsLayout = new FlexLayout(cat1, cat2, cat3, cat4);
        cardsLayout.setWidthFull();
        cardsLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        cardsLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(cardsLayout);


        Paragraph info = new Paragraph("All gallery sessions can be customized to your specific needs – feel free to contact our studio for special requests.");
        info.setWidth("100%");
        info.getStyle()
                .set("font-family", "Georgia, serif")
                .set("text-align", "center")
                .set("color", "#555")
                .set("margin-top", "20px");
        add(info);
    }
}

