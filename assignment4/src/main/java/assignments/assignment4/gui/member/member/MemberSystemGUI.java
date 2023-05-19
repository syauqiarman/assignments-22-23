package assignments.assignment4.gui.member.member;  //package assignment4.gui.member.member

//import yang diperlukan
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {    //implementasi kelas MemberSystemGUI yang merupakan turunan dari kelas AbstractMemberGUI
    public static final String KEY = "MEMBER";
    //konstruktor menerima parameter systemCLI untuk menginisialisasi objek MemberSystemGUI
    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    //getter
    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        return new JButton[]{   //button yang diperlukan
            new JButton("Saya ingin laundry"),
            new JButton("Lihat detail nota saya")
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{    //actionlistener yang diperlukan
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        // TODO
        JTextArea detailNotaTextArea = new JTextArea(); //set jtextarea
        detailNotaTextArea.setEditable(false);  //set agar non editable
        if (loggedInMember.getNotaList().length == 0) { //kalau panjang listnya 0, masuk kesini
            detailNotaTextArea.setText("Belum pernah laundry di CuciCuci, hiks :'(");
        } else {    //selain itu masuk sini
            for (Nota nota : loggedInMember.getNotaList()) {    //loop pernota dan ditambahkan ke detailNotaTextArea
                detailNotaTextArea.append(nota.toString() + "\n");
            }
        }
        JScrollPane detailNotaScrollPane = new JScrollPane(detailNotaTextArea); //buat agar bisa di scroll
        detailNotaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //scroll vertikal
        detailNotaScrollPane.setPreferredSize(new Dimension(400, 300)); //set size
        //mengeluarkan pesan information message
        JOptionPane.showMessageDialog(this, detailNotaScrollPane, "Detail Nota", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("stickynotes.png"));
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // TODO
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);  //menampilkan panel yang dituju
    }

}
