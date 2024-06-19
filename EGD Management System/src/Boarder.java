public class Boarder {
    // Fields to store the boarder's details
    private String name;
    private String id;
    private String contactInfo;
    private Room assignedRoom;

    // Constructor to initialize a boarder with name, ID, contact info, and assigned room
    public Boarder(String name, String id, String contactInfo, Room assignedRoom) {
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
        this.assignedRoom = assignedRoom;
    }

    // Getter for boarder's name
    public String getName() {
        return name;
    }

    // Getter for boarder's ID
    public String getId() {
        return id;
    }

    // Getter for boarder's contact info
    public String getContactInfo() {
        return contactInfo;
    }

    // Getter for the room assigned to the boarder
    public Room getAssignedRoom() {
        return assignedRoom;
    }

    // Setter to update the boarder's name
    public void setName(String name) {
        this.name = name;
    }

    // Setter to update the boarder's contact info
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Setter to update the room assigned to the boarder
    public void setAssignedRoom(Room assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    // Overriding the toString method to provide a string representation of the boarder
    @Override
    public String toString() {
        return "Boarder Name: " + name + "\n ID: " + id + "\n Contact: " + contactInfo +
               "\n Assigned Room: " + (assignedRoom != null ? assignedRoom.getRoomNumber() : "None");
    }
}