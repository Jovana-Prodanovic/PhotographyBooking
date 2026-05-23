package com.example.base.ui;

import com.example.booking.ui.BookingView;
import com.example.booking.ui.GalerieView;
import com.example.booking.ui.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout implements AfterNavigationObserver
{
    private final H1 viewTitle;

    public MainLayout()
    {
        DrawerToggle toggle = new DrawerToggle();

        viewTitle = new H1();
        viewTitle.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.Margin.NONE
        );

        H2 appName = new H2("Photography Booking");
        appName.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM
        );

        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("Home", HomeView.class, VaadinIcon.HOME.create()));
        nav.addItem(new SideNavItem("Bookings", BookingView.class, VaadinIcon.CALENDAR.create()));
        nav.addItem(new SideNavItem("Gallery", GalerieView.class, VaadinIcon.PICTURE.create()));

        addToDrawer(appName, nav);
        addToNavbar(toggle, viewTitle);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event)
    {
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle()
    {
        Component content = getContent();

        if (content != null && content.getClass().isAnnotationPresent(PageTitle.class))
        {
            return content.getClass().getAnnotation(PageTitle.class).value();
        }

        return "";
    }
}