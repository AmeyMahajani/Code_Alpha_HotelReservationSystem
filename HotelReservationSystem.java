import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {

        // Array of Room objects to hold all the rooms of the hotel
        Room[] rooms = {
            new Room(101), new Room(102),
            new Room(103), new Room(104),
            new Room(105), new Room(106)
        };

        // Hotel object initialized with a name and rooms
        Hotel hotel = new Hotel("Hotel Regenta", rooms);

        // Scanner for getting input from the user
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("\n===== Welcome to Hotel Regenta Management System =====");
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. View Available Rooms");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Validate menu input
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                sc.next(); // Consume invalid input
                continue;
            }

            int choice = sc.nextInt();

            // Perform action based on user's choice
            switch (choice) {
                case 1:
                    hotel.checkIn(); // Call check-in method
                    break;
                case 2:
                    hotel.checkOut(); // Call check-out method
                    break;
                case 3:
                    hotel.displayAvailableRooms(); // Display available rooms
                    break;
                case 4:
                    System.out.println("Thank you for using Hotel Regenta Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-4).");
            }
        }
    }
}
