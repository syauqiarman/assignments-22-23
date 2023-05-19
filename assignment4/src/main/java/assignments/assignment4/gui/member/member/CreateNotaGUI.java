package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private JPanel mainPanel;
    private boolean antarSelected;
    private boolean setrikaSelected;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        mainPanel.setBackground(Color.decode("#FEFBE9"));
        setBackground(Color.decode("#FEFBE9"));
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
        paketLabel = new JLabel("Paket Laundry: ");
        paketLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(75, 15, 5, 5);
        mainPanel.add(paketLabel, gbc);

        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        paketComboBox.setBackground(Color.decode("#FEFBE9"));
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(75, 5, 5, 5);
        mainPanel.add(paketComboBox, gbc);

        // Set up password label and password field
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaket();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(75, 5, 5, 5);
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        MainFrame.buttonThing(showPaketButton);
        mainPanel.add(showPaketButton, gbc);

        beratLabel = new JLabel("Berat Cucian (Kg): ");
        beratLabel.setFont(new Font("Garamond", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 15, 5, 0);
        mainPanel.add(beratLabel, gbc);

        beratTextField = new JTextField(6);
        beratTextField.setFont(new Font("Garamond", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        mainPanel.add(beratTextField, gbc);

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / Kg)");
        setrikaCheckBox.setFont(new Font("Garamond", Font.BOLD, 16));
        setrikaCheckBox.setBackground(Color.decode("#FEFBE9"));
        setrikaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                if (source.isSelected()) {
                    setrikaSelected = true;
                } else {
                    setrikaSelected = false;
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 15, 0, 0);
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(setrikaCheckBox, gbc);

        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4 Kg pertama, kemudian 500 / Kg)");
        antarCheckBox.setFont(new Font("Garamond", Font.BOLD, 16));
        antarCheckBox.setBackground(Color.decode("#FEFBE9"));
        antarCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                if (source.isSelected()) {
                    antarSelected = true;
                } else {
                    antarSelected = false;
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 15, 0, 0);
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(antarCheckBox, gbc);

        createNotaButton = new JButton("Buat Nota");
        createNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.gridwidth = 3;
        
        MainFrame.buttonThing(createNotaButton);
        mainPanel.add(createNotaButton, gbc);

        // Set up back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.gridwidth = 3;
        MainFrame.buttonThing(backButton);
        mainPanel.add(backButton, gbc);
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
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("stickynotes.png"));
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        String selectedPaket = (String) paketComboBox.getSelectedItem();
        String beratStr = beratTextField.getText();
        

        if (beratStr.matches("[0-9]+") != true || beratStr.contains(" ") || Integer.parseInt(beratStr) == 0) {
            JOptionPane.showMessageDialog(this, "Berat cucian harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("mad.png"));
            beratTextField.setText("");
            return;
        } 
        int berat = Integer.parseInt(beratStr);  //mengubah string berat menjadi integer
        
        if (berat < 2) {    //jika berat kurang dari 2
            JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Berat Information", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("happy.png"));
            berat = 2;  //berat diubah menjadi 2
        }
        Nota notaBaru = new Nota(memberSystemGUI.getLoggedInMember(), berat, selectedPaket, fmt.format(cal.getTime())); //membuat nota baru
        ((Member)memberSystemGUI.getLoggedInMember()).addNota(notaBaru);    //menambahkan nota ke array notaList di Member yang sifatnya perorangan
        NotaManager.addNota(notaBaru);  //menambahkan nota ke array notaList di NotaManager yang sifatnya keseluruhan
        
        if (setrikaSelected) {    //jika pilihanSetrika bukan "x" akan masuk kesini
            LaundryService setrika = new SetrikaService();  //membuat SetrikaService baru
            notaBaru.addService(setrika);   //menambahkan service setrika ke nota
        }

        if (antarSelected) {  //jika pilihanAntar bukan "x" akan masuk kesini
            LaundryService antar = new AntarService();  //membuat AntarService baru
            notaBaru.addService(antar); //menambahkan service antar ke nota
        }

        JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("love.png"));
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0); // Mengosongkan (uncheck) checkbox
        antarCheckBox.setSelected(false); // Mengosongkan (uncheck) checkbox
        setrikaCheckBox.setSelected(false); // Mengosongkan (uncheck) checkbox
        setrikaSelected = false;
        antarSelected = false;
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0); // Mengosongkan (uncheck) checkbox
        antarCheckBox.setSelected(false); // Mengosongkan (uncheck) checkbox
        setrikaCheckBox.setSelected(false); // Mengosongkan (uncheck) checkbox
        setrikaSelected = false;
        antarSelected = false;
    }
}
