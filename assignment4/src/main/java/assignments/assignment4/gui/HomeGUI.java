package assignments.assignment4.gui;    //package assignment4.gui

//import yang diperlukan
import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;
import static assignments.assignment3.nota.NotaManager.toNextDay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGUI extends JPanel {   //implementasi kelas HomeGUI yang merupakan turunan dari kelas JPanel
    //datafield yang digunakan
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel titleLabel1;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout

        // Set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#FEFBE9"));   //set warna latar belakang frame menggunakan hex
        initGUI();  //memanggil method initGUI
        add(mainPanel, BorderLayout.CENTER);    //menambahkan mainPanel kedalam kontainer utama menggunakan layout BorderLayout dengan posisi CENTER
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // Set up title label
        GridBagConstraints gbc = new GridBagConstraints();  //set up gbc
        titleLabel = new JLabel("Selamat Datang di");
        titleLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(titleLabel, gbc);
        
        // Set up title label 1
        titleLabel1 = new JLabel("CuciCuci System!");
        titleLabel1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(titleLabel1, gbc);

        // Set up date label
        dateLabel = new JLabel("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));
        dateLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(dateLabel, gbc);
        
        // Set up label kosong untuk merapihkan panel
        JLabel labelKosong1 = new JLabel("                                                             ");
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(labelKosong1, gbc);

        JLabel labelKosong2 = new JLabel("          ");
        gbc.gridx = 2;
        gbc.gridy = 2;
        mainPanel.add(labelKosong2, gbc);

        JLabel labelKosong3 = new JLabel("          ");
        gbc.gridx = 2;
        gbc.gridy = 3;
        mainPanel.add(labelKosong3, gbc);

        //buat ImageIcon dari gambar yang ingin dimasukkan
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
                handleToRegister(); //saat ditekan akan memanggil method handleToRegister
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MainFrame.buttonThing(registerButton);  //setting tampilan button
        mainPanel.add(registerButton, gbc);

        // Set up login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToLogin();    //saat ditekan akan memanggil method handleToLogin
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 5;
        MainFrame.buttonThing(loginButton); //setting tampilan button
        mainPanel.add(loginButton, gbc);

        // Set up toNextDay button
        toNextDayButton = new JButton("Next Day");
        toNextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextDay();    //saat ditekan akan memanggil method handleNextDay
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 6;
        MainFrame.buttonThing(toNextDayButton); //setting tampilan button
        mainPanel.add(toNextDayButton, gbc);
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);    //menampilkan panel yang dituju
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);   //menampilkan panel yang dituju
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();    //memanggil method toNextDay yang berada di NotaManager
        dateLabel.setText("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));    //memperbarui label tanggal
        //menampilkan information message
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "Next Day", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("relax.png"));
    }

}
