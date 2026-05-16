package com.example.base.ui;

import com.example.booking.ui.BookingView;
import com.example.booking.ui.GalerieView;
import com.example.booking.ui.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Photography Booking 📸");
        title.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.NONE
        );

        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("🏠 Startseite", HomeView.class));
        nav.addItem(new SideNavItem("📅 Buchungen", BookingView.class));
        nav.addItem(new SideNavItem("🖼️ Galerie", GalerieView.class));

        addToDrawer(nav);
        addToNavbar(toggle, title);
    }
}