package assignments.assignment4.gui.member.employee;    //package assignment4.gui.member.employee

//import yang diperlukan
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {  //implementasi kelas EmployeeSystemGUI yang merupakan turunan dari kelas AbstractMemberGUI
    public static final String KEY = "EMPLOYEE";
    //konstruktor menerima parameter systemCLI untuk menginisialisasi objek EmployeeSystemGUI
    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    //getter
    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        return new JButton[]{   //button yang diperlukan
            new JButton("It's nyuci time"),
            new JButton("Display List Nota")
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
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        String displayNotaText = "";    //string kosong
        if (NotaManager.notaList.length == 0) { //kalau panjang listnya 0, masuk kesini dan mengeluarkan error message
            JOptionPane.showMessageDialog(this, "Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
        } else {    //selain itu masuk sini
            for (Nota nota : NotaManager.notaList) {    //loop pernota dan ditambahkan ke displayNotaText
                displayNotaText += nota.getNotaStatus() + "\n";
            }
            //menampilkan information message
            JOptionPane.showMessageDialog(this, displayNotaText, "List Nota", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("stickynotes.png"));
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        //menampilkan information message mengenai pekerjaan yang dikerjakan
        JOptionPane.showMessageDialog(this, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!", "Nyuci Time", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("laundrymachine.png"));
        String kerja = "";  //string kosong
        if (NotaManager.notaList.length == 0) { //jika panjang listnya 0, masuk sini dan mengeluarkan error message
            JOptionPane.showMessageDialog(this, "Nothing to cuci here", "Nyuci Results", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
        } else {    //selain itu masuk sini
            for (Nota nota : NotaManager.notaList) {    //mengerjakan semua nota yang ada sesuai servicenya dan menambahkan string kerja
                kerja += nota.kerjakan() + "\n";
            }
            //mengeluarkan pesan information message
            JOptionPane.showMessageDialog(this, kerja, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("clean.png"));
        }
    }
}
