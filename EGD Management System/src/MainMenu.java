import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainMenu extends JFrame {
    private static final String DATA_FILE = "Resources/dorm_data.txt"; // Name of the file where dorm data is stored
    private DormManager dormManager; // Instance of DormManager to handle dorm operations


    public MainMenu() {
        dormManager = new DormManager(); // Initialize the DormManager
        dormManager.loadData(DATA_FILE); // Load the dorm data from the file
        initComponents(); // Initialize the GUI components
    }


    private void initComponents() {
        setTitle("EverGreen Dorm Management System"); // Set the title of the window
        setSize(1000, 768); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        setResizable(false); // Prevent the window from being resized


        // Set the look and feel of the UI to Nimbus if available
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace if setting the look and feel fails
        }

        // Create a tabbed pane to hold different panels
        JTabbedPane tabbedPane = new JTabbedPane();
        RoomsPanel roomsPanel = new RoomsPanel(dormManager); // Panel to manage rooms
        BoardersPanel boardersPanel = new BoardersPanel(dormManager); // Panel to manage boarders
        ReportsPanel reportsPanel = new ReportsPanel(dormManager); // Panel to generate reports

        


        // Add tabs to the tabbed pane
        tabbedPane.addTab("Rooms", roomsPanel);
        tabbedPane.addTab("Boarders", boardersPanel);
        tabbedPane.addTab("Reports", reportsPanel);

        

        // Add the tabbed pane to the content pane of the frame
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Add a window listener to handle actions when the window is closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dormManager.saveData(DATA_FILE); // Save the dorm data to the file
                super.windowClosing(e); // Call the superclass method
            }
        });
    }

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu(); // Create an instance of MainMenu
            mainMenu.setVisible(true); // Make the main menu visible
        });
    }
}
