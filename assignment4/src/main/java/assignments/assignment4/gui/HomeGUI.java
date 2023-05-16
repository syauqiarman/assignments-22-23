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
    titleLabel = new JLabel("Selamat Datang di CuciCuci System!");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(0, 0, 10, 0);
    mainPanel.add(titleLabel, gbc);

    // Set up date label
    dateLabel = new JLabel("Today is ");
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridy = 1;
    mainPanel.add(dateLabel, gbc);

    // Set up register button
    registerButton = new JButton("Register");
    registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleToRegister();
        }
    });
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(20, 0, 0, 5);
    mainPanel.add(registerButton, gbc);

    // Set up login button
    loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleToLogin();
        }
    });
    gbc.gridx = 1;
    gbc.insets = new Insets(20, 5, 0, 0);
    mainPanel.add(loginButton, gbc);

    // Set up toNextDay button
    toNextDayButton = new JButton("Next Day");
    toNextDayButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleNextDay();
        }
    });
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(20, 0, 0, 0);
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
    }
}
