package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.NotaManager.*;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import static assignments.assignment3.nota.NotaManager.*;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout.

        // Set up main panel.
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    // Method untuk menginisialisasi HOME GUI.
    private void initGUI() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Menginisiasi komponen HOME GUI.
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Calibre", Font.BOLD, 24));
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");
        dateLabel = new JLabel("Hari ini: " + fmt.format(cal.getTime()));

        // Mengatur grid tampilan GUI.
        gbc.gridx = 0;
        mainPanel.add(titleLabel, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(32, 0, 37, 0);
        mainPanel.add(loginButton, gbc);
        gbc.gridy = 10;
        mainPanel.add(registerButton, gbc);
        gbc.gridy = 15;
        mainPanel.add(toNextDayButton, gbc);
        gbc.gridy = 20;
        gbc.insets = new Insets(37, 0, 0, 0);
        mainPanel.add(dateLabel, gbc);

        // Menambahkan action listener untuk tiap komponen.
        registerButton.addActionListener(e -> handleToRegister());
        loginButton.addActionListener(e -> handleToLogin());
        toNextDayButton.addActionListener(e -> handleNextDay());
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.navigateTo("REGISTER");
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.navigateTo("LOGIN");
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        // Menambahkan hari untuk semua daftar nota.
        NotaManager.toNextDay();
        // Menampilkan pesan skip hari.
        dateLabel.setText("Hari ini: " + fmt.format(cal.getTime()));
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini...zzz...", "Sleep well..", JOptionPane.INFORMATION_MESSAGE);
    }
}
