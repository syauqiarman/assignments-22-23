package assignments.assignment4;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.gui.HomeGUI;
import assignments.assignment4.gui.LoginGUI;
import assignments.assignment4.gui.RegisterGUI;
import assignments.assignment4.gui.member.AbstractMemberGUI;
import assignments.assignment4.gui.member.Loginable;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.CreateNotaGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame{
    private static MainFrame instance;
    private final Loginable[] loginablePanel;
    private final MemberSystem memberSystem = new MemberSystem();
    private final EmployeeSystem employeeSystem = new EmployeeSystem();
    private final CardLayout cards = new CardLayout();
    private final JPanel mainPanel = new JPanel(cards);
    private final LoginManager loginManager = new LoginManager(employeeSystem, memberSystem);
    private final HomeGUI homeGUI = new HomeGUI();
    private final RegisterGUI registerGUI = new RegisterGUI(loginManager);
    private final LoginGUI loginGUI = new LoginGUI(loginManager);
    private final EmployeeSystemGUI employeeSystemGUI = new EmployeeSystemGUI(employeeSystem);
    private final MemberSystemGUI memberSystemGUI = new MemberSystemGUI(memberSystem);
    private final CreateNotaGUI createNotaGUI = new CreateNotaGUI(memberSystemGUI);

    private MainFrame(){
        super("CuciCuciSystem");
//        TODO: uncomment code dibawah ini setelah kamu implmentasikan addEmployee pada EmployeeSystem.
//        // for context dari 2 employee baru ini : https://ristek.link/karyawan-baru-cucicuci
//        employeeSystem.addEmployee(new Employee[]{
//                new Employee("delta Epsilon Huha Huha", "ImplicitDiff"),
//                new Employee("Regret", "FansBeratKanaArima")
//        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 432);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        loginablePanel = new Loginable[]{
                employeeSystemGUI,
                memberSystemGUI,
        };
        initGUI();
        cards.show(mainPanel, HomeGUI.KEY);
        add(mainPanel);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        mainPanel.add(homeGUI, HomeGUI.KEY);
        mainPanel.add(registerGUI, RegisterGUI.KEY);
        mainPanel.add(loginGUI, LoginGUI.KEY);
        mainPanel.add(employeeSystemGUI, EmployeeSystemGUI.KEY);
        mainPanel.add(memberSystemGUI, MemberSystemGUI.KEY);
        mainPanel.add(createNotaGUI, CreateNotaGUI.KEY);
    }

    /**
     * Method untuk mendapatkan instance MainFrame.
     * Instance Class MainFrame harus diambil melalui method ini agar menjamin hanya terdapat satu Frame pada program.
     *
     * @return instance dari class MainFrame
     * */
    public static MainFrame getInstance(){
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    /**
     * Method untuk pergi ke panel sesuai dengan yang diberikan pada param.
     *
     * @param page -> key dari halaman yang diinginkan.
     * */
    public void navigateTo(String page){
        // TODO
        cards.show(mainPanel, page);
    }

    /**
     * Method untuk login pada sistem.
     * Jika gagal login akan mengembalikan boolean false dan jika berhasil login: <p>
     * - return boolean true <p>
     * - menampilkan halaman yang sesuai <p>
     *
     * @param id -> ID dari pengguna
     * @param password -> password dari pengguna
     * @return boolean yang menandakan apakah login berhasil atau gagal.
     * */
    public boolean login(String id, String password){
        for (Loginable panel:
                loginablePanel) {
            // TODO
            if (panel.login(id, password)) {
                navigateTo(panel.getPageName());
            }
        }
        return false;
    }


    /**
     * Method untuk logout dari sistem, kemudian menampilkan halaman Home.
     * */
    public void logout(){
        for (Loginable panel:
                loginablePanel) {
            panel.logout();
        }
        navigateTo(HomeGUI.KEY);
    }

    public static void buttonThing(JButton button) {
        button.setBackground(Color.decode("#183A1D"));
        button.setFont(new Font("Garamond", Font.BOLD, 15));
        // Set warna teks JButton
        button.setForeground(Color.decode("#E1EEDD"));
        // Hapus stroke di sekitar JButton
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#183A1D")), new EmptyBorder(5, 5, 5, 5)));
        // Mengatur kursor menjadi clickable saat menyentuh button
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Tambahkan pendengar mouse untuk mengubah warna saat kursor berada di wilayah button
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.decode("#E1EEDD")); // Ubah warna saat kursor masuk ke button
                button.setForeground(Color.decode("#183A1D"));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.decode("#183A1D")); // Kembalikan warna saat kursor keluar dari button
                button.setForeground(Color.decode("#E1EEDD"));
            }
        });
    }

    public static ImageIcon messagePict(String fileName) {
        String property = System.getProperty("user.dir") + "/assignment4/src/main/java/assignments/assignment4/gui/properties/" + fileName;
        return new ImageIcon(property);
    }

    public static void main(String[] args) {
        // menampilkan GUI kalian.
        // Jika ingin tau lebih lanjut mengapa menggunakan SwingUtilities.invokeLater
        // silakan akses https://stackoverflow.com/questions/6567870/what-does-swingutilities-invokelater-do
        // Tapi in general kalian tidak usah terlalu overthinking line ini selain fungsi utamanya adalah menampilkan GUI
        SwingUtilities.invokeLater(MainFrame::getInstance);
    }
}
