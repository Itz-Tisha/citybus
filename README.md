# 🚌 City Bus Booking System

A full-stack City Bus Booking System built with Java, Spring Boot, and Hibernate, designed to allow users to  book, and manage bus tickets between city locations. Admins can manage routes and bus details, while users can search for available buses and book seats.

## 🛠️ Tech Stack

- Backend: Java, Spring Boot, Spring MVC, Hibernate (DAO-based, no JpaRepository)
- Frontend: HTML, CSS, JavaScript (or React, if added)
- Database: MySQL
- Architecture: MVC + DAO pattern

## 👥 User Roles

### 🔐 Admin
- Add and manage buses
- Define and update routes
- View all bookings

### 👤 Customer
- Search buses by source & destination
- Book tickets
- View ticket history



## 📘 Entities & Relationships

| Entity       | Relationships                                 |
|--------------|-----------------------------------------------|
| `webuser`    | Base user entity                              |
| `Bus`        | One-to-One with `route`, One-to-Many with `ticket`, `passengers`, and `busschedual` |
| `route`      | One-to-One with `Bus`                         |
| `passengers` | One-to-One with `ticket`, Many-to-One with `Bus` |
| `ticket`     | One-to-One with `passengers`, Many-to-One with `Bus` & `route` |
| `busschedual`| Many-to-One with `Bus`, stores date, time, and available seats |


---

## 🔄 Key Functionalities

### ✅ Admin
- Add new buses with routes
- Set seat capacity
- Monitor all bookings

### ✅ User
- Register & log in
- Search for buses by source and destination
- Book tickets (checks real-time seat availability)
- View or cancel booked tickets

