package assignments.assignment2;    //package assignment 2

//import yang diperlukan
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import assignments.assignment1.NotaGenerator;

//public class
public class MainMenu {
    private static final Scanner input = new Scanner(System.in);    //scanner untuk memasukkan input
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");   //format tanggal
    private static Calendar cal = Calendar.getInstance();   //calendar
    private static ArrayList<Member> memberList = new ArrayList<>();    //arraylist untuk menyimpan member
    private static ArrayList<Nota> notaList = new ArrayList<>();    //arraylist untuk menyimpan nota
    private static String tanggalSekarang = fmt.format(cal.getTime());  //set tanggal hari ini
    private static int idNotaCounter = 0;   //counter untuk menghitung id nota

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) { //looping
            printMenu();    //memanggil method printMenu
            System.out.print("Pilihan : ");
            String command = input.nextLine();  //memasukkan input ke variable command
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();   //jika commandnya 1 akan memanggil method handleGenerateUser
                case "2" -> handleGenerateNota();   //jika commandnya 2 akan memanggil method handleGenerateNota
                case "3" -> handleListNota();       //jika commandnya 3 akan memanggil method handleListNota
                case "4" -> handleListUser();       //jika commandnya 4 akan memanggil method handleListUser
                case "5" -> handleAmbilCucian();    //jika commandnya 5 akan memanggil method handleAmbilCucian
                case "6" -> handleNextDay();        //jika commandnya 6 akan memanggil method handleNextDay
                case "0" -> isRunning = false;      //jika commandnya 0 boolean akan diganti jadi false, dan loop berhenti
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");    //selain command diatas maka akan looping
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");    //keluaran saat loop berhenti
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.print("Masukkan nama Anda:\n");
        String nama = input.nextLine(); //memasukkan input ke variabel nama
        System.out.print("Masukkan nomor handphone Anda:\n");
        String nomorHP = input.nextLine();  //memasukkan input ke variabel nomorHP

        /*mengecek validasi dari input nomorHP yang telah dilakukan menggunakan while loop
        while loop akan mengecek satu persatu dari inputan nomorHP, yang dianggap benar adalah 0 sampai 9
        lalu di or jika ada spasi di nomorHP maka juga akan meminta input ulang */
        while (nomorHP.matches("[0-9]+") != true || nomorHP.contains(" ")) {
            System.out.println("Field nomor hp hanya menerima digit");
            nomorHP = input.nextLine();
        }
        Member memberBaru = new Member(nama, nomorHP);  //membuat member baru
        
        if (memberList.size() == 0) {   //jika memberList nya masih kosong akan masuk kesini
            memberList.add(memberBaru); //menambahkan member baru ke memberList
            System.out.println("Berhasil membuat member dengan ID " + memberBaru.getId() + " !");   //keluaran saat berhasil menambahkan member
        } else {    //jika memberList sudah ada isinya, sizenya minimal 1
            boolean memberIdFound = false;  //set boolean untuk mengecek sudah ada atau belum id yang dimasukkan
            for (Member i : memberList) {   //loop memberList satu persatu
                if (i.getId().equals(memberBaru.getId())) { //jika id yang dimasukkan sudah ada
                    memberIdFound = true;   //set boolean true karena id sudah digunakan
                    System.out.println("Member dengan nama " + nama + " dan nomor hp " + nomorHP + " sudah ada!");  //keluarannya
                    break;  //break agar berhenti looping
                }
            }
            if (!memberIdFound) {   //jika booleannya false maka artinya id belum terpakai
                memberList.add(memberBaru); //menambahkan member baru ke memberList
                System.out.println("Berhasil membuat member dengan ID " + memberBaru.getId() + " !");   //keluaran saat berhasil menambahkan member
            }
        }
    }

    private static void handleGenerateNota() {
        // TODO: handle generate nota
        System.out.print("Masukan ID member:\n");
        String checkID = input.nextLine(); //memasukkan input ke variabel checkID
        boolean idFound = false;    //set boolean untuk mengecek sudah ada atau belum id yang dimasukkan
        for (Member i : memberList) {   //loop memberlist satu persatu
            if (i.getId().equals(checkID)) {    //jika id yang dimasukkan sudah ada
                idFound = true; //set boolean true karena id sudah ada

                // variabel yang berisi string kosong untuk mempersiapkan hasil dari input paket
                String paket = "";

                // while loop yang digunakan untuk mengecek kecocokan variabel dengan kondisi if yang dibuat
                while (true) {
                    System.out.print("Masukkan paket laundry:\n");
                    paket = input.nextLine();    // memasukkan input ke variabel paket
                    
                    // kondisi if yang mengecek jika variabel paket sesuai dengan string yang sudah ditentukan
                    if (paket.equalsIgnoreCase("express") || paket.equalsIgnoreCase("fast") || paket.equalsIgnoreCase("reguler")) {
                        break;
                    
                    //pengecekan variabel paket jika isinya adalah "?"
                    } else if (paket.equals("?")) { 
                        NotaGenerator.showPaket();    //memanggil method showPaket yang isinya adalah jenis jenis paket yang disediakan
                    
                    //kondisi jika paket tidak diketahui dan akan me loop kembali
                    } else {    
                        System.out.println("Paket " + paket + " tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
                    }  
                }
                
                System.out.print("Masukkan berat cucian Anda [Kg]:\n");
                String beratStr = input.nextLine(); //memasukkan input ke variabel beratStr
                
                /*mengecek validasi dari input beratStr yang telah dilakukan menggunakan while loop
                while loop akan mengecek satu persatu dari inputan beratStr, yang dianggap benar adalah 0 sampai 9
                lalu di or jika ada spasi di beratStr maka juga akan meminta input ulang
                di or lagi dengan beratStr yang di integerkan dan sama dengan 0*/
                while (beratStr.matches("[0-9]+") != true || beratStr.contains(" ") || Integer.parseInt(beratStr) == 0) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratStr = input.nextLine();
                }
                int beratInt = Integer.parseInt(beratStr);  //mengubah string berat menjadi integer
                Nota notaBaru = new Nota (checkID, paket, beratInt, tanggalSekarang, i);    //membuat nota baru
                notaBaru.setSisaHariPengerjaan();   //set sisa hari pengerjaan dengan memanggil methodnya yang ada di class Nota
                notaBaru.getMember().setBonusCounter(); //set diskon dengan memanggil methodnya yang ada di class member
                
                notaBaru.setIdNota(idNotaCounter);  //set idNota dengan idNotaCounter yang ada caranya dengan memanggil methodnya yang ada di class Nota
                notaList.add(notaBaru); //menambahkan nota baru ke notaList
                idNotaCounter += 1; //idNotaCounter mengalami increment atau penambahan 1

                System.out.println(notaBaru.getNota()); //keluaran notanya
                break;  //menghentikan loop
            }
        }
        if (!idFound) { //jika booleannya false maka artinya id tidak ada
            System.out.println("Member dengan ID " + checkID + " tidak ditemukan!");    //keluarannya
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.println("Terdaftar " +notaList.size()+ " nota dalam sistem.");   //keluaran awal untuk memberitahu ada berapa nota yang terdaftar
        for (Nota i : notaList) {   //loop untuk mengiterasi notaList
            if (i.getIsReady() == false) {  //jika nota belum selesai
                System.out.println("- [" +i.getIdNota()+ "] Status\t: Belum bisa diambil :(");
            } else if (i.getIsReady() == true)  //jika nota sudah selesai
                System.out.println("- [" +i.getIdNota()+ "] Status\t: Sudah dapat diambil!");
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.println("Terdaftar " +memberList.size()+ " member dalam sistem.");   //keluaran awal untuk memberitahu ada berapa user yang terdaftar
        for (Member i : memberList) {   //loop untuk mengiterasi memberList
            System.out.println("- " +i.getId()+ " : " + i.getNama());   //keluaran yang berupa id dan nama member
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        boolean idNotaFound = false;    //set boolean untuk mengecek sudah ada atau belum idNota yang dimasukkan
        Nota removeNota = null; //nota kosong untuk mengambil salah satu nota saat diiterasi
        System.out.print("Masukan ID nota yang akan diambil:\n");
        String checkIdNotaString = input.nextLine();    //memasukkan input ke variabel checkIdNotaString

        /*mengecek validasi dari input checkIdNotaString yang telah dilakukan menggunakan while loop
        while loop akan mengecek satu persatu dari inputan checkIdNotaString, yang dianggap benar adalah 0 sampai 9
        lalu di or jika ada spasi di checkIdNotaString maka juga akan meminta input ulang */
        while (checkIdNotaString.matches("[0-9]+") != true || checkIdNotaString.contains(" ")) {
            System.out.println("ID nota berbentuk angka!");
            checkIdNotaString = input.nextLine();
        }
        int checkIdNotaInt = Integer.parseInt(checkIdNotaString);   //mengubah string checkIdNotaString menjadi integer

        for (Nota i : notaList) {   //loop untuk mengiterasi notaList
            if (i.getIdNota() == checkIdNotaInt) {  //jika id nota yang dimasukkan ada
                if (i.getIsReady()) {   //jika nota sudah selesai, bernilai true
                    idNotaFound = true; //set idNotaFound jadi true
                    removeNota = i; //notaList ke i akan dimasukkan ke variabel removeNota
                    break;  //menghentikan loop
                } else {
                    System.out.println("Nota dengan ID " +checkIdNotaString+ " gagal diambil!");    //keluaran jika nota belum selesai
                    return; //keluar loop
                }
            }
        }
        if (idNotaFound) {  //kalau idNotaFound nya true, berarti nota sudah siap untuk di ambil
            notaList.remove(removeNota);    //pengambilan nota dengan meremovenya dari notaList
            System.out.println("Nota dengan ID " +checkIdNotaString+ " berhasil diambil!");
        } else {    //jika id nota tidak ada dalam notaList
            System.out.println("Nota dengan ID " +checkIdNotaString+ " tidak ditemukan!");
        }
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DATE, 1);    // menambah 1 hari
        tanggalSekarang = fmt.format(cal.getTime());    //memperbarui tanggal menjadi hari berikutnya

        for (Nota i : notaList) {   //loop notaList untuk mengurangi sisa hari pengerjaan dan mengeceknya apakah sudah selesai
            i.kurangSisaHariPengerjaan();   //pengurangan sisa hari pengerjaan
            i.checkSisaHariPengerjaan();    //pengecekan sudah selesai atau belumnya pengerjaan nota
        }
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for (Nota i : notaList) {   //loop notaList untuk memberitahukan saat nota sudah dapat diambil
            if (i.getIsReady()){
                System.out.println("Laundry dengan nota ID " +i.getIdNota()+ " sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {   //method printMenu
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", tanggalSekarang);
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }
}
