package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
        // TODO
        // Set up name label and text field
        nameLabel = new JLabel("Masukkan nama Anda: ");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 5);
        mainPanel.add(nameLabel, gbc);

        nameTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        mainPanel.add(nameTextField, gbc);

        // Set up phone label and text field
        phoneLabel = new JLabel("Masukkan nomor handphone Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 5);
        mainPanel.add(phoneLabel, gbc);

        phoneTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        mainPanel.add(phoneTextField, gbc);

        // Set up password label and password field
        passwordLabel = new JLabel("Masukkan password Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 5);
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(passwordField, gbc);

        // Set up register button
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(registerButton, gbc);

        // Set up back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(backButton, gbc);

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        String nameString = nameTextField.getText();
        String phoneString = phoneTextField.getText();
        String passwordString = new String(passwordField.getPassword());

        if (nameString.isEmpty() || phoneString.isEmpty() || passwordString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field diatas wajib diisi", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (phoneString.matches("[0-9]+") != true || phoneString.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
            return;
        }

        Member registeredMember = loginManager.register(nameString, phoneString, passwordString);  //mendaftarkan member baru dengan parameter nama, noHp, dan password yang sudah dimasukkan
        if(registeredMember == null){   //jika member sudah ada maka member baru tidak bisa membuat akun
            JOptionPane.showMessageDialog(this, "User dengan nama " + nameString + " dan nomor hp " + phoneString + " sudah ada!", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        } else {
            JOptionPane.showMessageDialog(this, "Berhasil membuat user dengan ID " + registeredMember.getId() + "!", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        }
    }
}
