package assignments.assignment3.user.menu;  //package assignment3.user.menu

//import yang diperlukan
import assignments.assignment3.user.Member;
import java.util.Scanner;

public abstract class SystemCLI {
    protected Member[] memberList = new Member[0];  //membuat array memberList untuk menyimpan member
    protected Member loginMember;
    protected Scanner in;

    /**
     * Otentikasi pengguna dengan ID dan password yang diberikan dan memulai sesi pengguna.
     * Akan berhenti jika logout atau ID / Password salah.
     *
     * @param in -> Scanner object untuk membaca input.
     * @param inputId -> ID user yang akan diautentikasi.
     * @param inputPassword -> password user yang akan diautentikasi.
     */
    public void login(Scanner in, String inputId, String inputPassword){
        Member authMember = authUser(inputId, inputPassword);   //mengambil member yang terdaftar dengan memanggil method authUser

        if (authMember != null) {   //jika sudah ketemu akan masuk kesini
            this.in = in;
            System.out.println("Login successful!");
            run(in, authMember);    //akan memanggil method run
            return;
        }

        System.out.println("Invalid ID or password.\n");
    };

    /**
     * Memulai sesi pengguna dan menangani input.
     *
     * @param in -> Scanner object untuk membaca input.
     * @param member -> Member object yang menggunakan sistem.
     */
    public void run(Scanner in, Member member){
        loginMember = member;
        boolean logout = false;
        while (!logout) {   //looping sesuai kondisi boolean logout
            displayMenu();  //memanggil method displayMenu
            int choice = in.nextInt();  //memasukkan input berupa pilihan ke variabel choice
            in.nextLine();
            logout = processChoice(choice); // memanggil method processChoice
        }
        loginMember = null; //mengosongkan variabel loginMember
        System.out.println("Logging out...\n");
    }

    /**
     * Mengecek semua user dengan ID dan password yang diberikan.
     *
     * @param id -> ID pengguna yang akan diautentikasi.
     * @param pass -> password pengguna untuk mengautentikasi.
     * @return  Member object yang diautentikasi, null jika autentikasi gagal.
     */
    public Member authUser(String id, String pass) {
        for (Member user : memberList) {    //melakukan for each loop memberList
            if (!user.getId().equals(id)) { //jika idnya tidak sesuai akan lanjut
                continue;
            }
            if(user.login(id, pass)){   //jika id sama akan memanggil method login di Member
                return user;    //mengembailkan user
            }
            return null;
        }
        return null;
    };

    /**
     * Memeriksa apakah ada Member dengan ID yang diberikan.
     *
     * @param id -> ID yang akan diperiksa.
     * @return true jika ada member dengan ID yang diberikan, false jika tidak.
     */
    public boolean isMemberExist(String id){
        for (Member member : memberList) {  //for each loop untuk memberList
            if(member.getId().equals(id)){  //jika id sudah ada maka akan mereturn true
                return true;
            }
        }
        return false;   //jika id belum digunakan maka akan return false
    }

    /**
     * Displays main menu untuk user yang menggunakan sistem.
     */
    protected void displayMenu(){
        System.out.printf("\nLogin as : %s\nSelamat datang %s!\n\n", loginMember.getId(), loginMember.getNama());
        displaySpecificMenu();  //memanggil method displaySpecificMenu sesuai dengan role member yang login
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }

    /**
     * Memproses pilihan dari pengguna yang menggunakan sistem sesuai dengan rolesnya.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    protected abstract boolean processChoice(int choice);

    /**
     * Displays specific menu sesuai class yang menginheritnya.
     */
    protected abstract void displaySpecificMenu();
}