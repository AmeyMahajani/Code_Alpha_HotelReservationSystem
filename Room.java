public class Room {
    private int roomNumber;
    private String guestName; // Name of the guest occupying the room
    private boolean occupied; // Room occupancy status

    // Constructor to initialize the room with its number
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.guestName = null; // Initially, no guest is assigned
        this.occupied = false; // Room is unoccupied by default
    }

    // Getter for room number
    public int getRoomNumber() {
        return roomNumber;
    }

    // Getter for guest name
    public String getGuestName() {
        return guestName;
    }

    // Setter for guest name
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    // Getter for occupancy status
    public boolean isOccupied() {
        return occupied;
    }

    // Setter for occupancy status
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // Overridden toString method for a detailed room description
    @Override
    public String toString() {
        return "Room " + roomNumber + " is " + (occupied ? "occupied by " + guestName : "available");
    }
}
