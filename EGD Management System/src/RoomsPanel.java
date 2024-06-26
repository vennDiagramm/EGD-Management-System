import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomsPanel extends JPanel {
    private DormManager dormManager;
    private JTextArea roomList;
    private JComboBox<String> roomNumberComboBox;
    private JComboBox<String> typeComboBox;
    private JTextField rentField;
    private JCheckBox availabilityCheck;
    private ImageIcon pic;
    private JLabel picture;
    


    public RoomsPanel(DormManager manager) {
        this.dormManager = manager;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Text area to display the list of rooms
        roomList = new JTextArea();
        roomList.setEditable(false); // Make the text area read-only
        roomList.setFont(new Font("Arial", Font.BOLD, 14));
        add(new JScrollPane(roomList), BorderLayout.CENTER); // Add the text area to the center of the panel

        // Controls panel on the left side
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally


        // Room Number label and combo box
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Room Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] roomNumbers = generateRoomNumbers(); // Generate room numbers
        roomNumberComboBox = new JComboBox<>(roomNumbers); // Create a combo box for room number selection
        controlPanel.add(roomNumberComboBox, gbc); // Add combo box for room number

        // Room Type label and combo box
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JLabel("Type:"), gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        typeComboBox = new JComboBox<>(new String[]{"Air-Conditioned", "Non AC"});
        controlPanel.add(typeComboBox, gbc);

        // Rent label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(new JLabel("Rent:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        rentField = new JTextField(10); // Text field to input the rent
        controlPanel.add(rentField, gbc);

        // Availability label and checkbox
        gbc.gridx = 0;
        gbc.gridy = 3;
        controlPanel.add(new JLabel("Availability:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        availabilityCheck = new JCheckBox("Available"); // Checkbox for availability
        controlPanel.add(availabilityCheck, gbc);

        // Buttons panel for various actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add Room");
        JButton updateButton = new JButton("Update Room");
        JButton deleteButton = new JButton("Delete Room");
        JButton viewAllRoomsButton = new JButton("View All Rooms");
        JButton searchButton = new JButton("Search");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewAllRoomsButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        controlPanel.add(buttonPanel, gbc); // Add button panel to the control panel

        //Picture Panel
    
        picture = new JLabel();     
        pic = new ImageIcon("ads.png");

        picture.setIcon(pic);
        picture.setPreferredSize(new Dimension(430, 260));
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        controlPanel.add(picture,gbc);
        add(controlPanel, BorderLayout.WEST);

        // Search Button
        gbc.gridx = 0;
        gbc.gridy = 6;
        controlPanel.add(searchButton, gbc); // Save space

        add(controlPanel, BorderLayout.WEST); // Add control panel to the west side of the main panel

        // Add button actions with anonymous inner classes
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addRoom(); // Call method to add a room
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRoom(); // Call method to update a room
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRoom(); // Call method to delete a room
            }
        });

        viewAllRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllRooms(); // Show all rooms in a JOptionPane
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSearchDialog(); // Show search dialog
            }
        });

        updateRoomList(); // Initial update of the room list display
    }

    // Generate room numbers as a String array
    private String[] generateRoomNumbers() {
        String[] roomNumbers = new String[20];
        int index = 0;
        for (int i = 101; i <= 110; i++) {
            roomNumbers[index++] = String.valueOf(i);
        }
        for (int i = 200; i <= 209; i++) {
            roomNumbers[index++] = String.valueOf(i);
        }
        return roomNumbers;
    }

    // Add a new room based on the user input
    private void addRoom() throws Exception {
        try {
            Room room = getRoomFromInput(); // Get room details from input fields
            if (room != null) {
                dormManager.addRoom(room.getRoomNumber(), room.getType(), room.getRent(), room.isAvailable());
                updateRoomList(); // Update the room list display
                clearInputFields(); // Clear the input fields
                showMessage("Room added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update an existing room based on the user input
    private void updateRoom() {
        try {
            Room room = getRoomFromInput(); // Get room details from input fields
            if (room != null) {
                dormManager.updateRoom(room.getRoomNumber(), room.getType(), room.getRent(), room.isAvailable());
                updateRoomList(); // Update the room list display
                clearInputFields(); // Clear the input fields
                showMessage("Room updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete a room based on the selected room number
    private void deleteRoom() {
        try {
            int roomNumber = Integer.parseInt((String) roomNumberComboBox.getSelectedItem());
            dormManager.deleteRoom(roomNumber); // Delete room from dorm manager
            updateRoomList(); // Update the room list display
            clearInputFields(); // Clear the input fields
            showMessage("Room deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            showMessage("Invalid input! Please enter a valid room number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            showMessage("Room number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Get room details from input fields
    private Room getRoomFromInput() {
        String roomNumberText = (String) roomNumberComboBox.getSelectedItem();
        String rentText = rentField.getText().trim();
        if (roomNumberText.isEmpty() || rentText.isEmpty()) {
            showMessage("All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int roomNumber = Integer.parseInt(roomNumberText);
        String type = (String) typeComboBox.getSelectedItem();
        double rent = Double.parseDouble(rentText);
        boolean isAvailable = availabilityCheck.isSelected();

        if (rent <= 0) {
            showMessage("Rent must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Room(roomNumber, type, rent, isAvailable);
    }

    // Update the room list display in the text area
    private void updateRoomList() {
        StringBuilder builder = new StringBuilder();
        for (Room room : dormManager.viewRooms()) {
            builder.append("Room ").append(room.getRoomNumber()).append(": ")
                    .append(room.getType()).append(", ₱").append(room.getRent())
                    .append(", ").append(room.isAvailable() ? "Available" : "Occupied")
                    .append("\n");
        }
        roomList.setText(builder.toString());
    }

    // Show all rooms in a JOptionPane
    private void showAllRooms() {
        updateRoomList();
        JOptionPane.showMessageDialog(this, roomList.getText(), "All Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show a message dialog with the specified message, title, and message type
    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // Clear input fields
    private void clearInputFields() {
        roomNumberComboBox.setSelectedIndex(0);
        rentField.setText("");
        typeComboBox.setSelectedIndex(0);
        availabilityCheck.setSelected(false);
    }

    // Show search dialog to search for a room
    private void showSearchDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        // Room Number dropdown
        panel.add(new JLabel("Room Number:"));
        JComboBox<String> searchRoomNumberComboBox = new JComboBox<>(generateRoomNumbers());
        panel.add(searchRoomNumberComboBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Search Room",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selectedRoomNumber = (String) searchRoomNumberComboBox.getSelectedItem();

            ArrayList<Room> searchResults = new ArrayList<>();
            if (selectedRoomNumber != null && !selectedRoomNumber.isEmpty()) {
                try {
                    int roomNumber = Integer.parseInt(selectedRoomNumber);
                    Room room = dormManager.findRoomByNumber(roomNumber);
                    if (room != null) {
                        searchResults.add(room);
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid room number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!searchResults.isEmpty()) {
                displaySearchResults("Room Search Results", searchResults); // Display search results
            } else {
                showMessage("No matching room found!", "Search Room", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Display search results in a message dialog
    private void displaySearchResults(String title, ArrayList<Room> results) {
        StringBuilder builder = new StringBuilder();
        for (Room room : results) {
            builder.append(room.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, builder.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }
}
