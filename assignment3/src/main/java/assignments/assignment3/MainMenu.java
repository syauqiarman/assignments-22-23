package assignments.assignment3;    //package assignment 3

//import yang diperlukan
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

import java.util.Scanner;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MainMenu {
    private final Scanner in;   //scanner agar bisa memasukkan input
    private final LoginManager loginManager;    //variable LoginManager

    /**
     * Entry point for the CuciCuci System application.
     *
     * @param args command line arguments, bisa kalian ignore.
     */
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new Scanner(System.in), new LoginManager(new EmployeeSystem(), new MemberSystem()));
        mainMenu.run();
    }

    public MainMenu(Scanner in, LoginManager loginManager) {
        this.in = in;
        this.loginManager = loginManager;
    }

    /**
     * Menjalankan main menu.
     */
    public void run() {
        boolean exit = false;   // set boolean jadi false
        while (!exit) { //looping yang akan berhenti jika case 4 dipilih
            displayMenu();  //memanggil method displayMenu
            int choice = in.nextInt();  //memasukkan input pilihan ke variable choice
            in.nextLine();
            switch (choice) {
                case 1 -> login();  //jika choicenya 1 akan memanggil method login
                case 2 -> register();   //jika choicenya 2 akan memanggil method register
                case 3 -> toNextDay();  //jika choicenya 2 akan memanggil method toNextDay
                case 4 -> exit = true;  //jika choicenya 4 boolean akan diganti jadi true, dan loop berhenti
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.\n"); //selain choice diatas maka akan looping
            }
        }

        in.close(); //menutup scanner
    }

    /**
     * Skips ke hari selanjutnya dan mengupdate sistem.
     */
    private void toNextDay() {
        System.out.println("Kamu tidur hari ini... zzz...\n");
        NotaManager.toNextDay();    //memanggil method toNextDay yang berada di NotaManager
    }

    /**
     * Mendaftarkan user pada sistem.
     */
    void register() {
        System.out.println("Masukan nama Anda: ");
        String nama = in.nextLine();    //memasukkan input ke variabel nama
        System.out.println("Masukan nomor handphone Anda: ");
        String noHp = in.nextLine();    //memasukkan input ke variabel noHp

        /*mengecek validasi dari input noHp yang telah dilakukan menggunakan while loop
        while loop akan mengecek satu persatu dari inputan noHp, yang dianggap benar adalah 0 sampai 9
        lalu di or jika ada spasi di noHp maka juga akan meminta input ulang */
        while (noHp.matches("[0-9]+") != true || noHp.contains(" ")) {
            System.out.println("Field nomor hp hanya menerima digit");
            noHp = in.nextLine();
        }

        System.out.println("Masukan password Anda: ");
        String password = in.nextLine();    //memasukkan input ke variabel password

        Member registeredMember = loginManager.register(nama, noHp, password);  //mendaftarkan member baru dengan parameter nama, noHp, dan password yang sudah dimasukkan
        if(registeredMember == null){   //jika member sudah ada maka member baru tidak bisa membuat akun
            System.out.printf("User dengan nama %s dan nomor hp %s sudah ada!\n\n", nama, noHp);
            return;
        }
        System.out.printf("Berhasil membuat user dengan ID %s!\n\n", registeredMember.getId()); //jika member tidak ada, maka member baru bisa membuat akun
    }

    /**
     * Meminta user untuk login dan memulai SystemCLI yang sesuai.
     */
    private void login() {
        System.out.print("Masukan ID Anda: ");
        String inputId = in.nextLine(); //memasukkan input ke variabel inputId
        System.out.print("Masukan password Anda: ");
        String inputPassword = in.nextLine();   //memasukkan input ke variabel inputPassword
        SystemCLI systemCLI = loginManager.getSystem(inputId);  //mengecek apakah Id tersebut ada dan apakah rolenya dengan memanggil method getSystem
        if(systemCLI == null){  //jika id tadi tidak ditemukan maka akan masuk sini
            System.out.println("ID atau password invalid.\n");
            return;
        }
        systemCLI.login(in, inputId, inputPassword);    //jika id dan passwordnya sesuai maka akan memanggil method login di SystemCLI
    }

    /**
     * Menampilkan menu
     */
    private void displayMenu() {
        System.out.println("Selamat datang di CuciCuci System!");
        System.out.printf("Sekarang tanggal %s\n", fmt.format(cal.getTime()));
        System.out.println("1. Login");
        System.out.println("2. Register Member");
        System.out.println("3. Tidur (Skip hari)");
        System.out.println("4. Exit");
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }
}