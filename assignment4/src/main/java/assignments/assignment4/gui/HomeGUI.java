package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set warna latar belakang frame menggunakan hex
        mainPanel.setBackground(Color.decode("#FEFBE9"));
        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
    // Set up title label
    titleLabel = new JLabel("Selamat Datang di");
    titleLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
    //titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridx = 2;
    gbc.gridy = 0;
    //gbc.gridwidth = 2;
    gbc.insets = new Insets(0, 0, 10, 0);
    mainPanel.add(titleLabel, gbc);

    titleLabel = new JLabel("CuciCuci System!");
    titleLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
    gbc.gridx = 2;
    gbc.gridy = 1;
    //gbc.gridwidth = 2;
    gbc.insets = new Insets(0, 0, 10, 0);
    mainPanel.add(titleLabel, gbc);

    // Set up date label
    dateLabel = new JLabel("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));
    dateLabel.setFont(new Font("Garamond", Font.BOLD, 16));
    dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    mainPanel.add(dateLabel, gbc);
    
    // Buat labelkosong dan atur posisinya di koordinat (1, 1)
    JLabel labelKosong1 = new JLabel("                                                             ");
    gbc.gridx = 1;
    gbc.gridy = 0;
    mainPanel.add(labelKosong1, gbc);

    // Buat labelkosong dan atur posisinya di koordinat (1, 1)
    JLabel labelKosong2 = new JLabel("          ");
    gbc.gridx = 2;
    gbc.gridy = 2;
    mainPanel.add(labelKosong2, gbc);

    // Buat labelkosong dan atur posisinya di koordinat (1, 1)
    JLabel labelKosong3 = new JLabel("          ");
    gbc.gridx = 2;
    gbc.gridy = 3;
    mainPanel.add(labelKosong3, gbc);

    // Buat ImageIcon dari gambar yang ingin dimasukkan
    ImageIcon plantIcon = MainFrame.messagePict("plant.png");
    JLabel plant = new JLabel(plantIcon);
    gbc.gridx = 0;
    gbc.gridy = 6;
    mainPanel.add(plant, gbc);

    // Set up register button
    registerButton = new JButton("Register");
    registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleToRegister();
        }
    });
    gbc.gridx = 2;
    gbc.gridy = 4;
    //gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    MainFrame.buttonThing(registerButton);
    //gbc.insets = new Insets(0, 20, 0, 20);
    mainPanel.add(registerButton, gbc);

    // Set up login button
    loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleToLogin();
        }
    });
    gbc.gridx = 2;
    gbc.gridy = 5;
    MainFrame.buttonThing(loginButton);
    //gbc.insets = new Insets(0, 0, 0, 5);
    mainPanel.add(loginButton, gbc);

    // Set up toNextDay button
    toNextDayButton = new JButton("Next Day");
    toNextDayButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleNextDay();
        }
    });
    gbc.gridx = 2;
    gbc.gridy = 6;
    //gbc.gridwidth = 2;
    MainFrame.buttonThing(toNextDayButton);
    //gbc.insets = new Insets(0, 0, 0, 0);
    mainPanel.add(toNextDayButton, gbc);

    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();    //memanggil method toNextDay yang berada di NotaManager
        dateLabel.setText("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "Next Day", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("relax.png"));
    }

}
