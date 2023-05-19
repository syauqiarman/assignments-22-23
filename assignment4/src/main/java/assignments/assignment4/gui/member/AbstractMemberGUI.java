package assignments.assignment4.gui.member; //package assignment4.gui.member

//import yang diperlukan
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//implementasi kelas AbstractMemberGUI yang merupakan turunan dari kelas JPanel dan mengimplementasikan interface Loginable.
public abstract class AbstractMemberGUI extends JPanel implements Loginable{
    //datafield yang digunakan
    private JLabel welcomeLabel;
    private JLabel loggedInAsLabel;
    private JPanel northPanel;
    protected Member loggedInMember;
    private final SystemCLI systemCLI;

    public AbstractMemberGUI(SystemCLI systemCLI) {
        super(new BorderLayout());  // Setup layout
        this.systemCLI = systemCLI;
        setBackground(Color.decode("#FEFBE9")); // Set up background
        
        // Set up north panel
        northPanel = new JPanel(new GridBagLayout());
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        northPanel.setBackground(Color.decode("#FEFBE9"));
        add(northPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();  //set up gbc
        // Set up image
        ImageIcon emberIcon = MainFrame.messagePict("ember.png");
        JLabel ember = new JLabel(emberIcon);
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        northPanel.add(ember, gbc);

        // Set up welcome label
        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Garamond", Font.BOLD, 30));
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        northPanel.add(welcomeLabel, gbc);

        // Set up logged in as label
        loggedInAsLabel = new JLabel("", SwingConstants.CENTER);
        loggedInAsLabel.setFont(new Font("Garamond", Font.PLAIN, 16));
        add(loggedInAsLabel, BorderLayout.SOUTH);

        // Initialize buttons panel
        JPanel buttonsPanel = initializeButtons();  //memanggil method yg menginisiasi button
        buttonsPanel.setBackground(Color.decode("#FEFBE9"));
        add(buttonsPanel, BorderLayout.CENTER);
    }

    /**
     * Membuat panel button yang akan ditampilkan pada Panel ini.
     * Buttons dan ActionListeners akan disupply oleh method createButtons() & createActionListeners() respectively.
     * <p> Feel free to make any changes. Be creative and have fun!
     *
     * @return JPanel yang di dalamnya berisi Buttons.
     * */
    protected JPanel initializeButtons() {
        JButton[] buttons = createButtons();    //pembuatan button dan dikumpulkan di array buttons
        ActionListener[] listeners = createActionListeners();   //pembuatan actionlistener dan dikumpulkan di array listeners
        //pengecekan buttons dan listeners
         if (buttons.length != listeners.length) {
             throw new IllegalStateException("Number of buttons and listeners must be equal.");
         }

        JPanel buttonsPanel = new JPanel(new GridBagLayout());  //buttons panel dibuat
        GridBagConstraints gbc = new GridBagConstraints();  //set up gbc
        //memposisikan button
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.insets = new Insets(5, 250, 5, 250);

        for (int i = 0; i < buttons.length; i++) {  //loop per button
            JButton button = buttons[i];
            MainFrame.buttonThing(button);  //setting tampilan button
            button.addActionListener(listeners[i]); //setting actionlistenernya
            buttonsPanel.add(button, gbc);
        }

        // Set up logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().logout();   //saat ditekan akan memanggil method logout di mainframe
            }
        });
        MainFrame.buttonThing(logoutButton);    //setting tampilan button
        buttonsPanel.add(logoutButton, gbc);
        return buttonsPanel;
    }

    /**
     * Method untuk login pada panel.
     * <p>
     * Method ini akan melakukan pengecekan apakah ID dan passowrd yang telah diberikan dapat login
     * pada panel ini. <p>
     * Jika bisa, member akan login pada panel ini, method akan:<p>
     *  - mengupdate Welcome & LoggedInAs label.<p>
     *  - mengupdate LoggedInMember sesuai dengan instance pemilik ID dan password.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     * */
    public boolean login(String id, String password) {
        // TODO
        Member authMember = systemCLI.authUser(id, password);   //mengambil member yang terdaftar dengan memanggil method authUser
        if (authMember != null) {   //jika ada masuk sini
            loggedInMember = authMember;
            welcomeLabel.setText("Welcome, " + loggedInMember.getNama());   //set welcome label
            loggedInAsLabel.setText("Logged in as: "+ loggedInMember.getId());  //set logged in as label
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method untuk logout pada panel ini.
     * Akan mengubah loggedInMemberMenjadi null.
     * */
    public void logout() {
        //set semua textfield jadi kosong
        welcomeLabel.setText("");
        loggedInAsLabel.setText("");
        loggedInMember = null;  //logged in membernya di null kan agar bisa dipakai lagi
    }

    /**
     * Method ini mensupply buttons apa saja yang akan dimuat oleh panel ini.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     * Harus diimplementasikan sesuai class yang menginherit class ini.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    protected abstract JButton[] createButtons();

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons().
     * Harus diimplementasikan sesuai class yang menginherit class ini.
     *
     * @return Array of ActionListener.
     * */
    protected abstract ActionListener[] createActionListeners();
}
