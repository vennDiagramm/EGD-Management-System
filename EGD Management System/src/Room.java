// BASE CLASS FOR ROOM MANAGEMENT
public class Room {
  // Fields to store the room's details
  private int roomNumber;
  private String type;
  private double rent;
  private boolean isAvailable;

  // Constructor to initialize a room with number, type, rent, and availability status
  public Room(int roomNumber, String type, double rent, boolean isAvailable) {
      this.roomNumber = roomNumber;
      this.type = type;
      this.rent = rent;
      this.isAvailable = isAvailable;
  }

  // Getter for room number
  public int getRoomNumber() { 
      return roomNumber; 
  }

  // Setter to update the room number
  public void setRoomNumber(int roomNumber) { 
      this.roomNumber = roomNumber; 
  }

  // Getter for room type
  public String getType() { 
      return type; 
  }

  // Setter to update the room type
  public void setType(String type) { 
      this.type = type; 
  }

  // Getter for room rent
  public double getRent() { 
      return rent; 
  }

  // Setter to update the room rent
  public void setRent(double rent) { 
      this.rent = rent; 
  }

  // Getter to check if the room is available
  public boolean isAvailable() { 
      return isAvailable; 
  }

  // Setter to update the room's availability status
  public void setAvailable(boolean isAvailable) { 
      this.isAvailable = isAvailable; 
  }

  // Overriding the toString method to provide a string representation of the room
  @Override
  public String toString() {
      return "Room Number: " + roomNumber + "\n Type: " + type + "\n Rent: ₱" + rent +
              "\n Status: " + (isAvailable ? "Available" : "Occupied");
  }
}