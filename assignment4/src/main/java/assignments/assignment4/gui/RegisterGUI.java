package assignments.assignment4.gui;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import static assignments.assignment1.NotaGenerator.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout
        this.loginManager = loginManager;

        // Set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    // Method untuk menginisialisasi REGISTER GUI.
    private void initGUI() {
        // Menginisiasi komponen REGISTER GUI
        nameLabel = new JLabel("Masukkan nama Anda:");
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        passwordLabel = new JLabel("Masukkan password Anda");

        nameTextField = new JTextField(10);
        phoneTextField = new JTextField(10);
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        backButton = new JButton("Kembali");

        // Mengatur grid dan menambahkan tiap komponen ke main panel.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 20;
        gbc.weighty = 2;
        mainPanel.add(nameLabel, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(nameTextField, gbc);
        gbc.gridy = 4;
        mainPanel.add(phoneLabel, gbc);
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(phoneTextField, gbc);
        gbc.gridy = 8;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridy = 10;
        gbc.insets = new Insets(10, 0, 15, 0);
        mainPanel.add(passwordField, gbc);
        gbc.gridy = 12;
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(registerButton, gbc);
        gbc.gridy = 14;
        mainPanel.add(backButton, gbc);

        // Menambahkan action listener pada komponen REGISTER GUI.
        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.navigateTo("HOME");

    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // Menampilkan information dialog untuk empty field.
        if (phoneTextField.getText().equals("") || nameTextField.getText().equals("") || passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field diatas wajib diisi", "Empty field", JOptionPane.ERROR_MESSAGE);
        }
        // Menampilkan information dialog untuk input nomor hp tidak valid.
        if (!NotaGenerator.isNumeric(phoneTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
        }
        // Input valid.
        else {
            String name = nameTextField.getText();
            String phoneNo = phoneTextField.getText();
            String password = passwordField.getText();
            // Mendaftarkan member baru ke sistem.
            Member registeredMember = loginManager.register(name, phoneNo ,password);
            if(registeredMember == null){
                // Jika member yang akan didaftarkan sudah ada.
                JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada!", name, phoneNo), "Registration Failed", JOptionPane.ERROR_MESSAGE);
                MainFrame.getInstance().navigateTo("HOME");
                nameTextField.setText("");
                phoneTextField.setText("");
                passwordField.setText("");
                return;
            }
            // Member berhasil didaftarkan.
            JOptionPane.showMessageDialog(this, String.format("Berhasil membuat user dengan ID %s", NotaGenerator.generateId(name, phoneNo)), "Registration Succesful", JOptionPane.INFORMATION_MESSAGE);
            MainFrame.getInstance().navigateTo("HOME");
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
        }
    }
}
