package assignments.assignment4.gui;    //package assignment4.gui

//import yang diperlukan
import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment3.user.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {  //implementasi kelas LoginrGUI yang merupakan turunan dari kelas JPanel
    //datafield yang digunakan
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JPanel pictPanel;
    private JPanel contentPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout
        this.loginManager = loginManager;

        // Set up main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#FEFBE9"));
        initGUI();  //memanggil method initGUI
        add(mainPanel, BorderLayout.CENTER);    //menambahkan mainPanel kedalam kontainer utama menggunakan layout BorderLayout dengan posisi CENTER
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        GridBagConstraints gbc = new GridBagConstraints();  //set up gbc
        // Set up pict panel
        pictPanel = new JPanel(new GridBagLayout());
        pictPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pictPanel.setBackground(Color.decode("#FEFBE9"));
        mainPanel.add(pictPanel, BorderLayout.EAST);

        // Set up image
        ImageIcon anjingIcon = MainFrame.messagePict("anjing2.png");
        JLabel anjing = new JLabel(anjingIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 40);
        pictPanel.add(anjing, gbc);

        // Set up content panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.decode("#FEFBE9"));
        mainPanel.add(contentPanel, BorderLayout.WEST);

        // Set up id label
        idLabel = new JLabel("Masukkan ID Anda: ");
        idLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 5);
        contentPanel.add(idLabel, gbc);

        // Set up id textfield
        idTextField = new JTextField(30);
        idTextField.setFont(new Font("Garamond", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        contentPanel.add(idTextField, gbc);

        // Set up password label
        passwordLabel = new JLabel("Masukkan password Anda: ");
        passwordLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 5);
        contentPanel.add(passwordLabel, gbc);

        // Set up password field
        passwordField = new JPasswordField(30);
        passwordField.setFont(new Font("Garamond", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(passwordField, gbc);

        // Set up login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();  //saat ditekan akan memanggil method handleLogin
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 5);
        MainFrame.buttonThing(loginButton); //setting tampilan button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(loginButton, gbc);

        // Set up back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();   //saat ditekan akan memanggil method handleBack
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 0, 5);
        MainFrame.buttonThing(backButton);  //setting tampilan button
        gbc.gridwidth = 2;
        contentPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        //set semua textfield dan passwordfield jadi kosong
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);    //menampilkan panel yang dituju
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        //mengambil semua isi dari textfield dan passwordfield
        String idString = idTextField.getText();
        String passwordString = new String(passwordField.getPassword());

        SystemCLI systemCLI = loginManager.getSystem(idString);  //mengecek apakah Id tersebut ada dan apakah rolenya dengan memanggil method getSystem
        if(systemCLI == null){  //jika id tadi tidak ditemukan maka akan masuk sini, lalu mengeluarkan error message
            JOptionPane.showMessageDialog(this, "ID atau password invalid!", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
            //set semua textfield dan passwordfield jadi kosong
            idTextField.setText("");
            passwordField.setText("");
            return;
        } else {    //selain itu masuk sini untuk dicek lagi
            Member member = systemCLI.authUser(idString, passwordString);   //mengecek semua user
            if (member == null) {   //kalau null akan mengeluarkan error message
                JOptionPane.showMessageDialog(this, "Invalid ID or Password!", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
                //set semua textfield dan passwordfield jadi kosong
                idTextField.setText("");
                passwordField.setText("");
            }
            else {  //kalau ada masuk sini
                MainFrame.getInstance().login(idString, passwordString); //jika id dan passwordnya sesuai maka akan memanggil method login di mainframe
                //set semua textfield dan passwordfield jadi kosong
                idTextField.setText("");
                passwordField.setText("");
            }
        }
        
    }
}
