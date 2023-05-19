package assignments.assignment4.gui.member.member;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;
import static assignments.assignment4.MainFrame.mainPanel;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;
        String[] daftarPaket = {"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(daftarPaket);
        // Set up main panel.
        initGUI();
    }

    // Method untuk menginisialisasi CreateNota GUI.
    private void initGUI() {
        // Mengatur tiap komponen GUI pada main panel.
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        paketLabel = new JLabel("Paket laundry:");
        add(paketLabel, gbc);

        gbc.gridx = 5;
        add(paketComboBox, gbc);

        gbc.gridx = 7;
        gbc.insets = new Insets(20, 3, 0, 0);
        showPaketButton = new JButton("Show Paket");

        add(showPaketButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(6, 0, 0, 0);
        beratLabel = new JLabel("Berat Cucian (Kg)");
        add(beratLabel, gbc);

        gbc.gridx = 5;
        beratTextField = new JTextField();
        add(beratTextField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        add(setrikaCheckBox, gbc);

        gbc.gridy = 3;
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama, kemudian 500/kg)");
        add(antarCheckBox, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        createNotaButton = new JButton("Buat Nota");
        add(createNotaButton, gbc);

        gbc.gridy = 5;
        backButton = new JButton("Kembali");
        add(backButton, gbc);

        showPaketButton.addActionListener(e -> showPaket());
        createNotaButton.addActionListener(e -> createNota());
        backButton.addActionListener(e -> handleBack());

    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        String beratInput = beratTextField.getText();
        // Iterasi selamat input berat tidak valid.
        while (!NotaGenerator.isNumeric(beratInput) || Integer.parseInt(beratInput) < 1 || beratInput == null) {
            JOptionPane.showMessageDialog(this, "Berat cucian harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
            beratInput = beratTextField.getText();
        }
        int berat = Integer.parseInt(beratInput);
        if (berat < 2) {
            JOptionPane.showMessageDialog(this,"Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
            berat = 2;
        }
        String paket = (String) paketComboBox.getSelectedItem();
        // Membuat nota.
        Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, paket, fmt.format(cal.getTime()));
        // Menambahkan service dan menambahkan nota pada daftar nota.
        if (setrikaCheckBox.isSelected()) nota.addService(new SetrikaService());
        if (antarCheckBox.isSelected()) nota.addService(new AntarService());
        NotaManager.addNota(nota);
        memberSystemGUI.getLoggedInMember().addNota(nota);
        // Menampilkan information massage berhasil dan set komponen menjadi default.
        JOptionPane.showMessageDialog(this,"Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        paketComboBox.setSelectedIndex(0);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo("MEMBER");
        // Set komponen kembali default.
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        paketComboBox.setSelectedIndex(0);
    }
}
