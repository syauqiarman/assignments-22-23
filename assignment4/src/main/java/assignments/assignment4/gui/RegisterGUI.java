package assignments.assignment4.gui;    //package assignment4.gui

//import yang diperlukan
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {   //implementasi kelas RegisterGUI yang merupakan turunan dari kelas JPanel
    //datafield yang digunakan
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JPanel pictPanel;
    private JPanel contentPanel;
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
        ImageIcon anjingIcon = MainFrame.messagePict("anjing1.png");
        JLabel anjing = new JLabel(anjingIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 60);
        pictPanel.add(anjing, gbc);

        // Set up content panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.decode("#FEFBE9"));
        mainPanel.add(contentPanel, BorderLayout.WEST);

        // Set up name label
        nameLabel = new JLabel("Masukkan nama Anda: ");
        nameLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 5);
        contentPanel.add(nameLabel, gbc);

        // Set up name textfield
        nameTextField = new JTextField(30);
        nameTextField.setFont(new Font("Garamond", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        contentPanel.add(nameTextField, gbc);

        // Set up phone label
        phoneLabel = new JLabel("Masukkan nomor handphone Anda: ");
        phoneLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 5);
        contentPanel.add(phoneLabel, gbc);

        // Set up phone textfield
        phoneTextField = new JTextField(30);
        phoneTextField.setFont(new Font("Garamond", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        contentPanel.add(phoneTextField, gbc);

        // Set up password label
        passwordLabel = new JLabel("Masukkan password Anda: ");
        passwordLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 5);
        contentPanel.add(passwordLabel, gbc);

        // Set up password field
        passwordField = new JPasswordField(30);
        passwordField.setFont(new Font("Garamond", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPanel.add(passwordField, gbc);

        // Set up register button
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();   //saat ditekan akan memanggil method handleRegister
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 5);
        MainFrame.buttonThing(registerButton);  //setting tampilan button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(registerButton, gbc);

        // Set up back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();   //saat ditekan akan memanggil method handleBack
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 5);
        MainFrame.buttonThing(backButton);  //setting tampilan button
        contentPanel.add(backButton, gbc);

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        //set semua textfield dan passwordfield jadi kosong
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);    //menampilkan panel yang dituju
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        //mengambil semua isi dari textfield dan passwordfield
        String nameString = nameTextField.getText();
        String phoneString = phoneTextField.getText();
        String passwordString = new String(passwordField.getPassword());

        if (nameString.isEmpty() || phoneString.isEmpty() || passwordString.isEmpty()) {    //jika ada yang kosong maka akan mengeluarkan error message
            JOptionPane.showMessageDialog(this, "Semua field diatas wajib diisi", "Empty Field", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("mad.png"));
            return;
        }

        if (phoneString.matches("[0-9]+") != true || phoneString.contains(" ")) {   //jika nomor telepon tidak valid akan mengeluarkan error message
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("mad.png"));
            phoneTextField.setText(""); //set phone textfield jadi kosong
            return;
        }

        Member registeredMember = loginManager.register(nameString, phoneString, passwordString);  //mendaftarkan member baru dengan parameter nama, noHp, dan password yang sudah dimasukkan
        if(registeredMember == null){   //jika member sudah ada maka member baru tidak bisa membuat akun, lalu mengeluarkan error message
            JOptionPane.showMessageDialog(this, "User dengan nama " + nameString + " dan nomor hp " + phoneString + " sudah ada!", "Registration Failed", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
            //set semua textfield dan passwordfield jadi kosong
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);    //menampilkan panel yang dituju
        } else {    //jika berhasil, mengeluarkan information message
            JOptionPane.showMessageDialog(this, "Berhasil membuat user dengan ID " + registeredMember.getId() + "!", "Registration Successful", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("love.png"));
            //set semua textfield dan passwordfield jadi kosong
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);    //menampilkan panel yang dituju
        }
    }
}
