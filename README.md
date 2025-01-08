# Code_Alpha_HotelReservationSystem

# Hotel Reservation System

## Overview
The **Hotel Reservation System** is a Java-based console application designed to manage hotel room bookings effectively. It integrates with a MySQL database to track room occupancy and guest information dynamically, providing a robust solution for hotel management operations.

---

## Features
- **Room Management**:
  - Check-in and check-out functionality for guests.
  - Tracks room occupancy status in real-time.

- **Database Integration**:
  - Uses MySQL to store and retrieve room and guest information.
  - Ensures persistent data storage for hotel operations.

- **Interactive Console Interface**:
  - Provides a user-friendly, menu-driven system for efficient management.
  - Displays available rooms dynamically.

---

## How It Works
1. **Room Management**:
   - The user can check in guests by providing room numbers and guest names.
   - Guests can check out, releasing the room for future use.

2. **Database Operations**:
   - Room details (number, occupancy status, and guest name) are stored in the database.
   - Data retrieval ensures real-time updates for availability and occupancy.

3. **Dynamic Updates**:
   - Displays available rooms based on current database entries.
   - Handles invalid room numbers and duplicate bookings gracefully.

---

## Prerequisites
1. **Java**: Ensure JDK 8 or higher is installed.
2. **MySQL**: A running MySQL server is required.
3. **JDBC Driver**: MySQL Connector/J must be added to the project.

---

## Database Setup
1. **Create Database**:
   ```sql
   CREATE DATABASE hotel_reservation_db;
   ```

2. **Create Table**:
   ```sql
   USE hotel_reservation_db;

   CREATE TABLE rooms (
       room_number INT PRIMARY KEY,
       guest_name VARCHAR(100),
       occupied BOOLEAN DEFAULT FALSE
   );
   ```

3. **Insert Sample Data**:
   ```sql
   INSERT INTO rooms (room_number, guest_name, occupied) VALUES
   (101, NULL, FALSE),
   (102, NULL, FALSE),
   (103, NULL, FALSE),
   (104, NULL, FALSE),
   (105, NULL, FALSE),
   (106, NULL, FALSE);
   ```

---

## How to Run
1. Clone the repository from GitHub:
   ```bash
   git clone https://github.com/AmeyMahajani/Code_Alpha_HotelReservationSystem.git
   ```bash
   git clone https://github.com/AmeyMahajani/Code_Alpha_HotelReservationSystem.git
   ```
2. Set up the MySQL database using the instructions above.
3. Update database credentials in the code:
   ```java
   static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel_reservation_db?useSSL=false&serverTimezone=UTC";
   static final String JDBC_USER = "YOUR_USERNAME";
   static final String JDBC_PASS = "YOUR_PASSWORD";
   ```
4. Compile and run the Java program:
   ```bash
   javac HotelReservationSystem.java
   java HotelReservationSystem
   ```

---

## Skills Demonstrated
- **Java Programming**: Designed and implemented a robust console-based system.
- **JDBC Integration**: Managed database connections and queries using JDBC.
- **Database Management**: Created and manipulated MySQL databases for real-time operations.
- **Problem Solving**: Built solutions for dynamic room management.

---

## Acknowledgments
Special thanks to **CodeAlpha Technology** for providing this opportunity to enhance my programming and database management skills.

