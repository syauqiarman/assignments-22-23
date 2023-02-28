package assignments.assignment1;

import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        
        String pilihan = "";
        while (!pilihan.equals("0")) {
            printMenu();
            System.out.print("Pilihan: ");
            pilihan = input.next();
            System.out.println("================================");

            if (pilihan.equals("1")) {
                System.out.print("Masukkan nama Anda: ");
                String nama = input.next().toUpperCase();
                System.out.print("Masukkan nomor handphone Anda: ");
                input.nextLine();
                String nomorHP = input.next();
                String id = generateId(nama, nomorHP);
                System.out.println("ID anda: " + id);

            } else if (pilihan.equals("2")) {
                System.out.print("Masukkan nama Anda: ");
                String nama = input.next().toUpperCase();
                System.out.print("Masukkan nomor handphone Anda: ");
                input.nextLine();
                String nomorHP = input.next();
                String id = generateId(nama, nomorHP);
                System.out.print("Masukkan tanggal terima: ");
                String tanggal = input.next();
                
                String paket = "";
                while (true) {
                    System.out.print("Masukkan paket laundry: ");
                    paket = input.next().toLowerCase();
                    
                    if (paket.equals("express") || paket.equals("fast") || paket.equals("reguler")) {
                        System.out.println("masuk");
                        break;
                    } else if (paket.equals("?")) {
                        showPaket();
                    } else {
                        System.out.println("Paket " + paket + " tidak diketahui [ketik ? untuk mencari tahu jenis paket]");
                    }  
                }
                
                int berat = 0;
                System.out.print("Masukkan berat cucian Anda [Kg]: ");
                while (true) {
                    try {
                        berat = input.nextInt();
                        if (berat > 0 && berat < 2) {
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            berat = 2;
                            break;
                        } else if (berat >= 2) {
                            break;
                        } else if (berat <= 0) {
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        } else {
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        } 
                    } catch (Exception e) {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        input.nextLine();
                    }
                }
                System.out.println(generateNota(id, paket, berat, tanggal));

            } else if (pilihan.equals("0")){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        } 
        input.close();
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        String [] perKata = nama.split(" ");
        String kataPertama = perKata[0];
        String namaNomor = kataPertama + "-" + nomorHP;

        int checkSum = 0;
        for (int i = 0; i < namaNomor.length(); i++) {
            char karakter = namaNomor.charAt(i);
            if (karakter >= 'A' && karakter <= 'Z') {
                checkSum += (int) karakter - 'A' + 1;
            } else if (karakter >= '0' && karakter <= '9') {
                checkSum += (int) karakter - '0';
            } else {
                checkSum +=7;
            }
        }
        String newCheckSum = ""+checkSum;
        String hasilId = namaNomor + "-" + newCheckSum.substring(newCheckSum.length()-2);
        return hasilId;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        int biaya = 0;
        if (paket.equals("express")) {
            int durasi = 1;
            biaya = 12000;
        } else if (paket.equals("fast")) {
            int durasi = 2;
            biaya = 10000;
        } else if (paket.equals("reguler")) {
            int durasi = 3;
            biaya = 7000;
        }

        int total = berat * biaya;
        return ("Nota Laundry\nID    : "+ id +"\nPaket : "+ paket +"\nHarga :\n"+ berat +" kg x "+ biaya +" = "+total+"\nTanggal Terima  : "+ tanggalTerima +"\nTanggal Selesai : [tanggalTerima + LamaHariPaket]");
    }
}
