import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Hotel {
    private String name;
    private Room[] rooms;

    // Database credentials
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel_reservation_db";
    static final String JDBC_USER = "USER";  // Replace with your DB username
    static final String JDBC_PASS = "1234567890";  // Replace with your DB password

    // Constructor to initialize the hotel name and its rooms
    public Hotel(String name, Room[] rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    // Method to check in a guest
    public void checkIn() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter room number to check in: ");
        
        // Validate room number input
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid room number.");
            sc.nextLine(); // Clear invalid input
            return;
        }
        int roomNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Database connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            String query = "SELECT occupied FROM rooms WHERE room_number = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, roomNumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    boolean occupied = rs.getBoolean("occupied");
                    if (occupied) {
                        System.out.println("Room " + roomNumber + " is already occupied.");
                    } else {
                        System.out.print("Enter guest name: ");
                        String guestName = sc.nextLine();

                        // Update room status in the database
                        String updateSQL = "UPDATE rooms SET guest_name = ?, occupied = ? WHERE room_number = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateSQL)) {
                            updatePstmt.setString(1, guestName);
                            updatePstmt.setBoolean(2, true);
                            updatePstmt.setInt(3, roomNumber);
                            updatePstmt.executeUpdate();

                            System.out.println("Guest " + guestName + " successfully checked into room " + roomNumber);
                        }
                    }
                } else {
                    System.out.println("Invalid room number. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during check-in: " + e.getMessage());
        }
    }

    // Method to check out a guest
    public void checkOut() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter room number to check out: ");
        
        // Validate room number input
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid room number.");
            sc.nextLine(); // Clear invalid input
            return;
        }
        int roomNumber = sc.nextInt();

        // Database connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            String query = "SELECT occupied, guest_name FROM rooms WHERE room_number = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, roomNumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    boolean occupied = rs.getBoolean("occupied");
                    String guestName = rs.getString("guest_name");

                    if (!occupied) {
                        System.out.println("Room " + roomNumber + " is already available.");
                    } else {
                        // Update room status in the database
                        String updateSQL = "UPDATE rooms SET guest_name = NULL, occupied = ? WHERE room_number = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateSQL)) {
                            updatePstmt.setBoolean(1, false);
                            updatePstmt.setInt(2, roomNumber);
                            updatePstmt.executeUpdate();

                            System.out.println("Guest " + guestName + " successfully checked out from room " + roomNumber);
                        }
                    }
                } else {
                    System.out.println("Invalid room number. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during check-out: " + e.getMessage());
        }
    }

    // Method to display available rooms
    public void displayAvailableRooms() {
        // Database connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            String query = "SELECT room_number FROM rooms WHERE occupied = FALSE";
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println("Available Rooms:");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int roomNumber = rs.getInt("room_number");
                    System.out.println("Room " + roomNumber);
                }
                if (!found) {
                    System.out.println("No rooms are currently available.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error while fetching available rooms: " + e.getMessage());
        }
    }
}
