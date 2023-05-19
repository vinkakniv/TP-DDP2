package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return "EMPLOYEE";
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
                new JButton("Display list nota"),
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
        if (notaList.length == 0) {
            JOptionPane.showMessageDialog(this,"Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE);
        } else {
            String notaStatus = "";
            for (Nota nota: notaList) {
                notaStatus += nota.getNotaStatus() + "\n";
            }
            JOptionPane.showMessageDialog(this, notaStatus, "List Nota", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        //System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());
        JOptionPane.showMessageDialog(this,String.format("Stand back! %s beginning to nyuci!", loggedInMember.getNama()), "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
        if (notaList.length == 0) {
            JOptionPane.showMessageDialog(this,"nothing to cuci here", "Nyuci result", JOptionPane.ERROR_MESSAGE);
        } else {
            String notaCuci = "";
            for (Nota nota: notaList) {
                notaCuci += nota.kerjakan() + "\n";
            }
            JOptionPane.showMessageDialog(this, notaCuci, "Nyuci Result", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
