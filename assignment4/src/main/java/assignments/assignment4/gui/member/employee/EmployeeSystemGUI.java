package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


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
        return new JButton[]{
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
        return new ActionListener[]{
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
        //JTextField displayNotaText = new JTextField();
        String displayNotaText = "";
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
        } else {
            for (Nota nota : NotaManager.notaList) {
                displayNotaText += nota.getNotaStatus() + "\n";
            }
            JOptionPane.showMessageDialog(this, displayNotaText, "List Nota", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("stickynotes.png"));
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        JOptionPane.showMessageDialog(this, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!", "Nyuci Time", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("laundrymachine.png"));
        String kerja = "";
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "Nothing to cuci here", "Nyuci Results", JOptionPane.ERROR_MESSAGE, MainFrame.messagePict("cry.png"));
        } else {
            for (Nota nota : NotaManager.notaList) {    //mengerjakan semua nota yang ada sesuai servicenya
                kerja += nota.kerjakan() + "\n";
            }
            JOptionPane.showMessageDialog(this, kerja, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE, MainFrame.messagePict("clean.png"));
        }
    }
}
