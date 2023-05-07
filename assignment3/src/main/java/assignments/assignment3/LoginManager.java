package assignments.assignment3;    //package assignment 3

//import yang diperlukan
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){ //jika role nya member masuk kesini
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){   //jika role nya employee masuk kesini
            return employeeSystem;
        }
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {
        // TODO
        String tempId = NotaGenerator.generateId(nama, noHp);   //pembuatan Id dengan memanggil generateId yang ada di TP1
        if(!memberSystem.isMemberExist(tempId)) {   //pengecekan apakah member sudah ada atau belum
            Member memberBaru = new Member(nama, tempId, password); //membuat member baru
            memberSystem.addMember(memberBaru); //menambahkan member ke dalam array memberList dengan memanggil method addMember di memberSystem
            return memberBaru;  //mengembalikan memberBaru
        }
        return null;    //jika member sudah ada maka akan mengembalikan null
    }
}