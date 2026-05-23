package com.example.booking.ui;

import com.example.base.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Home")
public class HomeView extends VerticalLayout
{
    public HomeView()
    {
        setSpacing(true);
        setPadding(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("padding-left", "80px").set("padding-right", "40px");

        add(getHeader());

        Image img = new Image("images/Logo.png", "Photography Logo");
        img.setWidth("180px");
        img.setHeight("180px");
        img.getStyle()
                .set("border-radius", "50%")
                .set("flex-shrink", "0");

        Paragraph description = new Paragraph(
                "We connect you with professional photographers for every occasion. " +
                        "Whether it is a wedding, portrait session, family celebration or special event, " +
                        "our platform helps you find the right photographer in a simple and elegant way. " +
                        "Every great story deserves to be told through beautiful images. " +
                        "Our photographers are passionate professionals who know how to capture " +
                        "the emotions, details and atmosphere of your special moments. " +
                        "From intimate portraits to grand celebrations, we offer a wide range of " +
                        "photography services tailored to your needs and budget."
        );
        description.setWidth("550px");
        description.getStyle()
                .set("font-family", "Georgia, serif")
                .set("font-size", "1.05rem")
                .set("line-height", "1.8")
                .set("color", "#555")
                .set("text-align", "left")
                .set("margin-left", "30px");

        HorizontalLayout content = new HorizontalLayout(img, description);
        content.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        content.setWidthFull();
        content.getStyle().set("max-width", "800px").set("margin-top", "20px");
        add(content);

        VerticalLayout servicesLayout = new VerticalLayout();
        servicesLayout.setSpacing(false);
        servicesLayout.setPadding(false);
        servicesLayout.getStyle()
                .set("max-width", "800px")
                .set("margin-top", "30px")
                .set("align-self", "center");

        H2 servicesTitle = new H2("Our Services");
        servicesTitle.getStyle()
                .set("font-family", "Georgia, serif")
                .set("color", "#2c2c2c")
                .set("margin-bottom", "10px");
        servicesLayout.add(servicesTitle);

        Paragraph service1 = new Paragraph("📷  Wedding Photography — Capture the magic of your special day.");
        Paragraph service2 = new Paragraph("👤  Portrait Sessions — Professional headshots and personal portraits.");
        Paragraph service3 = new Paragraph("🎉  Event Photography — Birthdays, graduations, corporate events.");
        Paragraph service4 = new Paragraph("🌿  Nature & Travel — Stunning landscapes and travel memories.");
        Paragraph service5 = new Paragraph("👶  Family & Newborn — Precious moments with your loved ones.");
        Paragraph service6 = new Paragraph("🏢  Corporate Photography — Professional images for your business.");

        for (Paragraph p : new Paragraph[]{service1, service2, service3, service4, service5, service6}) {
            p.getStyle()
                    .set("font-size", "16px")
                    .set("color", "#444")
                    .set("margin", "6px 0");
            servicesLayout.add(p);
        }
        add(servicesLayout);

        VerticalLayout whyLayout = new VerticalLayout();
        whyLayout.setSpacing(false);
        whyLayout.setPadding(false);
        whyLayout.getStyle()
                .set("max-width", "800px")
                .set("margin-top", "20px")
                .set("align-self", "center");

        H2 whyTitle = new H2("Why Choose Us?");
        whyTitle.getStyle()
                .set("font-family", "Georgia, serif")
                .set("color", "#2c2c2c")
                .set("margin-bottom", "10px");
        whyLayout.add(whyTitle);

        Paragraph why1 = new Paragraph("⭐  Experienced professionals with years of expertise.");
        Paragraph why2 = new Paragraph("💼  Easy online booking — fast and simple.");
        Paragraph why3 = new Paragraph("🎯  Tailored packages for every budget.");
        Paragraph why4 = new Paragraph("📸  High quality photos delivered within 7 days.");
        Paragraph why5 = new Paragraph("🌟  Trusted by hundreds of happy clients across Vienna.");

        for (Paragraph p : new Paragraph[]{why1, why2, why3, why4, why5}) {
            p.getStyle()
                    .set("font-size", "16px")
                    .set("color", "#444")
                    .set("margin", "6px 0");
            whyLayout.add(p);
        }
        add(whyLayout);

        H3 name = new H3("Photography Booking Studio");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");

        name.getStyle().set("color", "#555").set("font-weight", "400");
        street.getStyle().set("color", "#555").set("font-weight", "400");
        city.getStyle().set("color", "#555").set("font-weight", "400");

        HorizontalLayout address = new HorizontalLayout(name, street, city);
        address.getStyle().set("gap", "40px").set("margin-top", "20px");
        add(address);
    }

    public static Component getHeader()
    {
        H1 companyName = new H1("Perfect Moments");
        companyName.getStyle()
                .set("font-family", "Georgia, serif")
                .set("font-size", "3.5rem")
                .set("font-weight", "300")
                .set("letter-spacing", "0.1em")
                .set("color", "#2c2c2c")
                .set("margin", "20px 0 0 0");

        H2 subName = new H2("... capture your perfect moment ...");
        subName.getStyle()
                .set("font-family", "Georgia, serif")
                .set("font-style", "italic")
                .set("font-weight", "300")
                .set("font-size", "1.2rem")
                .set("color", "#888")
                .set("margin", "5px 0 30px 0");

        VerticalLayout headerLayout = new VerticalLayout(companyName, subName);
        headerLayout.setSpacing(false);
        headerLayout.setPadding(false);
        headerLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return headerLayout;
    }
}