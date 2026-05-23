# Photography Booking 📸

##Java Abschlussprojekt (Vaadin) + Daten Bank 

### Beschreibung
Photography Booking ist eine moderne Webanwendung zur Verwaltung von Fotografie-Buchungen.
Kunden können professionelle Fotografen finden, Termine buchen und ihre Reservierungen verwalten.

### Technologien
- Java 25
- Spring Boot 4.0
- Vaadin Flow 25
- PostgreSQL (Docker)
- Maven

### Funktionen
- 🏠 **Home** — Startseite mit Firmeninfo und Services
- 📅 **Bookings** — Buchungen erstellen und verwalten
- 🖼️ **Gallery** — Fotografie-Kategorien und Übersicht

### Projektstruktur

src/main/java/com/example/
├── base/ui/
│   └── MainLayout.java
├── booking/
│   ├── Booking.java
│   ├── BookingRepository.java
│   ├── BookingService.java
│   └── ui/
│       ├── HomeView.java
│       ├── BookingView.java
│       └── GalerieView.java
└── Application.java

### Datenbank
PostgreSQL läuft in einem Docker-Container:
```bash
docker run --name photographybooking-postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=photographybooking \
  -p 5433:5432 -d postgres
```

### Anwendung starten
1. Docker Desktop starten
2. PostgreSQL Container starten
3. Anwendung in IntelliJ ausführen
4. Browser öffnen: http://localhost:8080

### Autorin
Jovana Prodanovic — 4BKIF — HTL Spengergasse Wien
