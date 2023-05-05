package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        switch (choice) {
            case 1:
                // sementara [intinya panggil method] nyuciMenu();
                employeeKerja();
                break;
            case 2:
                displayNotaList();
                break;
            case 3:
                logout = true;
                System.out.println("Logout successful!");
                break;
            default:
                System.out.println("Invalid choice, please try again.");    //ngga tau apa pesannya
                break;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    private void employeeKerja () {
        System.out.println("Stand back! Depram beginning to nyuci!");
        for (Nota nota : notaList) {
            System.out.println(nota.kerjakan());
        }
    }

    private void displayNotaList() {
        for (Nota nota : notaList) {
            System.out.println(nota.getNotaStatus());
        }
    }
}