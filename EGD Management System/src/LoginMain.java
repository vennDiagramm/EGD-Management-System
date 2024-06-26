import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginMain extends JFrame {
    private JPanel Login; // Main panel for login components
    private JLabel User; // Username label
    private JTextField textField1; // Username input field
    private JButton loginButton; // Login button
    private JPasswordField passwordField1; // Password input field
    private JLabel passwordLabel; // Password label
    private JLabel logo;// logo label
    private ImageIcon imageLogo;
    private String username = "admin";
    private String userpass = "bastegwapo123";


    public LoginMain() {
        createUIComponents(); // Initialize UI components
        setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());


        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        // Key listener for pressing Enter in the password field
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }

    // Method to perform login action
    private void performLogin() {
        String user = textField1.getText(); // Get username input
        String password = new String(passwordField1.getPassword()); // Get password input

        // Check if username and password match expected values
        if (username.equals(user) && userpass.equals(password)) {
            JOptionPane.showMessageDialog(this,"Login successful...");
            dispose(); // Dispose the current login frame
            MainMenu mainMenu = new MainMenu(); // Open the main menu
            mainMenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(Login, "Incorrect Credentials"); // Show error message
        }
    }

    // Method to create UI components
    private void createUIComponents() {
        Login = new JPanel(new GridBagLayout()); // Create main panel with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout control

        // Set padding and alignment constraints
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding

        // Initialize logo label and set icon
        logo = new JLabel();
        imageLogo = new ImageIcon("logo.png");

        logo.setIcon(imageLogo);
        logo.setPreferredSize(new Dimension(130, 100)); // Adjust size as needed


        // Username label and text field
        User = new JLabel("Username:");
        textField1 = new JTextField(20);

        // Password label and password field
        passwordLabel = new JLabel("Password:");
        passwordField1 = new JPasswordField(20);

        // Login button
        loginButton = new JButton("Login");

        // Add logo to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3; // Span the logo over three rows
        gbc.anchor = GridBagConstraints.CENTER;
        Login.add(logo, gbc);

        // Add username label and text field
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        Login.add(User, gbc); // Add username label

        gbc.gridx = 2;
        Login.add(textField1, gbc); // Add username text field

        // Add password label and password field
        gbc.gridx = 1;
        gbc.gridy = 1;
        Login.add(passwordLabel, gbc); // Add password label

        gbc.gridx = 2;
        Login.add(passwordField1, gbc); // Add password text field

        // Add login button
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        Login.add(loginButton, gbc); // Add login button centered

        // Customize fonts for components
        User.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField1.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginMain loginMain = new   LoginMain(); // Create instance of LoginMain
                loginMain.setContentPane(loginMain.Login); // Set content pane to the login panel
                loginMain.setTitle("Login Screen"); // Set window title
                loginMain.setSize(520, 220); // Set window size
                loginMain.setLocationRelativeTo(null); // Center window on screen
                loginMain.setVisible(true); // Make window visible
                loginMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
                loginMain.setResizable(false); // Do not allow window resizing
            }
        });
    }
}
