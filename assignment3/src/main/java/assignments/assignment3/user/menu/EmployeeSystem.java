package assignments.assignment3.user.menu;  //package assignment3.user.menu

//import yang diperlukan
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
            case 1: //jika choicenya 1 akan memanggil method employeeKerja
                employeeKerja();
                break;
            case 2: //jika choicenya 2 akan memanggil method displayNotaList
                displayNotaList();
                break;
            case 3: //jika choicenya 3 akan mengubah logout menjadi true dan akan keluar loop
                logout = true;
                break;
            default:
                System.out.println("Invalid choice, please try again.");    //selain choice diatas akan looping lagi
                break;
        }
        return logout;  //mengembalikan logout
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

    private void employeeKerja () { //method untuk employee melakukan kerja nya
        System.out.println("Stand back! " + loginMember.getNama() + " beginning to nyuci!");
        for (Nota nota : notaList) {    //mengerjakan semua nota yang ada sesuai servicenya
            System.out.println(nota.kerjakan());
        }
    }

    private void displayNotaList() {    //method untuk employee melihat status dari nota yang ada
        for (Nota nota : notaList) {
            System.out.println(nota.getNotaStatus());
        }
    }

    //METHOD TAMBAHAN TP4
    public void addEmployee(Employee[] employees) {
        Member[] result = new Member[employees.length + memberList.length];
     
     
        System.arraycopy(memberList, 0, result, 0, memberList.length);
        System.arraycopy(employees, 0, result, memberList.length, employees.length);
     
        memberList = result;
     }
     
}