package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout.
        this.loginManager = loginManager;

        // Set up main panel.
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    // Method untuk menginisialisasi LOGIN GUI.
    private void initGUI() {
        // Menginisiasi komponen LOGIN GUI.
        idLabel = new JLabel("Masukkan ID Anda:");
        passwordLabel = new JLabel("Masukkan password Anda:");
        idTextField  = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        backButton = new JButton("Kembali");

        // Mengatur grid dan menambahkan komponen GUI pada main panel.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 20;
        gbc.weighty = 2;
        gbc.insets = new Insets(15, 0, 15, 0);
        mainPanel.add(idLabel, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 0, 15, 0);
        mainPanel.add(idTextField, gbc);
        gbc.gridy = 4;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridy = 6;
        gbc.insets = new Insets(15, 0, 15, 0);
        mainPanel.add(passwordField, gbc);
        gbc.gridy = 8;
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(loginButton, gbc);
        gbc.gridy = 10;
        mainPanel.add(backButton, gbc);

        // Menambahkan action listener pada button.
        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo("HOME");
        // Set text field kosong.
        idTextField.setText("");
        passwordField.setText("");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String id = idTextField.getText();
        String password = passwordField.getText();
        MainFrame.getInstance().login(id, password);
        // Set text field kosong.
        idTextField.setText("");
        passwordField.setText("");
    }
}
