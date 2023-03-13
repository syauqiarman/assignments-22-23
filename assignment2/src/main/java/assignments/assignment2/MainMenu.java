package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Member> memberList = new ArrayList<>();
    private static ArrayList<Nota> notaList = new ArrayList<>();
    private static String tanggalSekarang = fmt.format(cal.getTime());

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.print("Masukkan nama Anda:\n");
                String nama = input.nextLine(); //memasukkan input ke variabel nama
                System.out.print("Masukkan nomor handphone Anda:\n");
                String nomorHP = input.nextLine();  //memasukkan input ke variabel nomorHP
                
                while (nomorHP.matches("[0-9]+") != true || nomorHP.contains(" ")) {
                    System.out.println("Field nomor hp hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                Member memberBaru = new Member(nama, nomorHP);
                if (memberList.size() == 0) {
                    memberList.add(memberBaru);
                    System.out.println("Berhasil membuat member dengan ID " + memberBaru.getId() + " !");
                } else {
                    for (int i = 0; i < memberList.size();i++) {
                        if (memberList.get(i).getId().equals(memberBaru.getId())) {
                            System.out.println("Member dengan nama " + nama + " dan nomor hp " + nomorHP + " sudah ada!");
                        } else {
                            memberList.add(memberBaru);
                            System.out.println("Berhasil membuat member dengan ID " + memberBaru.getId() + " !");
                            
                        }
                        break;
                    }
                }
                System.out.println(memberList);  //CEEEKKKK
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.print("Masukan ID member: ");
        String checkID = input.nextLine();
        for (int i = 0; i < memberList.size();i++) {
            if (memberList.get(i).getId().equals(checkID)) {
                // variabel yang berisi string kosong untuk mempersiapkan hasil dari input paket dan awal
                String paket = "";
                String awal = "";

                // while loop yang digunakan untuk mengecek kecocokan variabel dengan kondisi if yang dibuat
                while (true) {
                    System.out.print("Masukkan paket laundry:\n");
                    awal = input.nextLine();    // memasukkan input ke variabel awal untuk jaga-jaga bila input yang dimasukkan tidak sesuai kriteria
                    paket = awal.toLowerCase(); // variabel awal di lowercase untuk pengecekan di kondisi if
                    
                    // kondisi if yang mengecek jika variabel paket sesuai dengan string yang sudah ditentukan
                    if (paket.equals("express") || paket.equals("fast") || paket.equals("reguler")) {
                        break;
                    
                    //pengecekan variabel paket jika isinya adalah "?"
                    } else if (paket.equals("?")) { 
                        showPaket();    //memanggil method showPaket yang isinya adalah jenis jenis paket yang disediakan
                    
                    //kondisi jika paket tidak diketahui dan akan me loop kembali
                    } else {    
                        System.out.println("Paket " + awal + " tidak diketahui [ketik ? untuk mencari tahu jenis paket]");
                    }  
                }
                
                System.out.print("Masukkan berat cucian Anda [Kg]:\n");
                String beratStr = input.nextLine(); //memasukkan input ke variabel beratStr
                
                while (beratStr.matches("[0-9]+") != true || beratStr.contains(" ") || Integer.parseInt(beratStr) == 0) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratStr = input.nextLine();
                }
                int beratInt = Integer.parseInt(beratStr);  //mengubah string berat menjadi integer
                Nota notaBaru = new Nota (checkID, paket, beratInt, tanggalSekarang);
                notaList.add(notaBaru);
            } else {
                System.out.println("Member dengan ID " + checkID + "tidak ditemukan!");
            }
            break;
        }
        System.out.println(notaList);
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.println("Terdaftar " +notaList.size()+ " nota dalam sistem.");
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.println("Terdaftar " +memberList.size()+ " member dalam sistem.");
        for (int i = 0; i < memberList.size();i++) {
            System.out.println("- " +memberList.get(i).getId()+ " : " + memberList.get(i).getNama());
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.print("Masukan ID nota yang akan diambil: ");
        String checkIdNotaString = input.nextLine();
        while (checkIdNotaString.matches("[0-9]+") != true || checkIdNotaString.contains(" ")) {
            System.out.println("ID nota berbentuk angka!");
            checkIdNotaString = input.nextLine();
        }
        int checkIdNotaInt = Integer.parseInt(checkIdNotaString);
        //tinggal bikin idnota, ngecek ada ngga idnota nya, ngecek udah beres blm lewat tanggal
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DATE, 1);    // menambah 1 hari
        tanggalSekarang = fmt.format(cal.getTime());
        System.out.println(tanggalSekarang);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        System.out.println("Laundry dengan nota ID [BERAPA] sudah dapat diambil!");
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
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
