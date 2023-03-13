package assignments.assignment1;
// import package yang diperlukan
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//public class
public class NotaGenerator {
    //scanner agar bisa memasukkan input
    private static final Scanner input = new Scanner(System.in);
    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        // variabel yang berisi string kosong untuk mempersiapkan hasil dari input pilihan
        String pilihan = "";

        //while loop jika input yang dimasukkan bukan "0" akan masuk loop
        while (!pilihan.equals("0")) {
            printMenu();    //memanggil method printMenu yang isinya ucapan selamat datang dan pilihan opsi
            System.out.print("Pilihan: ");
            pilihan = input.nextLine(); //memasukkan input ke variabel pilihan
            System.out.println("================================");

            //jika pilihannya "1" akan masuk kesini
            if (pilihan.equals("1")) {
                System.out.print("Masukkan nama Anda:\n");
                String nama = input.nextLine(); //memasukkan input ke variabel nama
                System.out.print("Masukkan nomor handphone Anda:\n");
                String nomorHP = input.nextLine();  //memasukkan input ke variabel nomorHP
                
                /*mengecek validasi dari input nomorHP yang telah dilakukan menggunakan while loop
                while loop akan mengecek satu persatu dari inputan nomorHP, yang dianggap benar adalah 0 sampai 9
                lalu di or jika ada spasi di nomorHP maka juga akan meminta input ulang */
                while (nomorHP.matches("[0-9]+") != true || nomorHP.contains(" ")) {
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                String id = generateId(nama, nomorHP);  //memanggil method generateId dan memasukkannya sebagai variabel id
                System.out.println("ID anda: " + id);   //print hasil dari id yang telah dibuat
            
            // jika pilihannya "2" akan masuk kesini
            } else if (pilihan.equals("2")) { 
                System.out.print("Masukkan nama Anda:\n");
                String nama = input.nextLine(); // memasukkan input ke variabel nama
                System.out.print("Masukkan nomor handphone Anda:\n");
                String nomorHP = input.nextLine();  //memasukkan input ke variabel nomorHP
                
                /*mengecek validasi dari input nomorHP yang telah dilakukan menggunakan while loop
                while loop akan mengecek satu persatu dari inputan nomorHP, yang dianggap benar adalah 0 sampai 9
                lalu di or jika ada spasi di nomorHP maka juga akan meminta input ulang */
                while (nomorHP.matches("[0-9]+") != true || nomorHP.contains(" ")) {
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                String id = generateId(nama, nomorHP);  //memanggil method generateId dan memasukkannya sebagai variabel id
                System.out.print("Masukkan tanggal terima:\n");
                String tanggal = input.nextLine();  //memasukkan input ke variabel tanggal
                
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
                
                /*mengecek validasi dari input beratStr yang telah dilakukan menggunakan while loop
                while loop akan mengecek satu persatu dari inputan beratStr, yang dianggap benar adalah 0 sampai 9
                lalu di or jika ada spasi di beratStr maka juga akan meminta input ulang
                di or lagi dengan beratStr yang di integerkan dan sama dengan 0*/
                while (beratStr.matches("[0-9]+") != true || beratStr.contains(" ") || Integer.parseInt(beratStr) == 0) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratStr = input.nextLine();
                }
                int beratInt = Integer.parseInt(beratStr);  //mengubah string berat menjadi integer
                System.out.println(generateNota(id, paket, beratInt, tanggal)); //memanggil method generate nota dan di print

            //jika pilihannya "0" maka loop akan berhenti karena memenuhi kondisi while dan mengucapkan terima kasih
            } else if (pilihan.equals("0")){    
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
            
            // selain kondisi diatas maka akan print perintah tidak dikenal dan meminta input kembali
            } else {    
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        } 
        //menutup input
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
    public static void showPaket() {
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
        
        String [] perKata = nama.split(" ");    // split parameter nama berdasarkan spasi
        String kataPertama = perKata[0].toUpperCase();  // mengambil index ke 0 dan di uppercase
        String namaNomor = kataPertama + "-" + nomorHP; // membuat variabel baru yang isinya [kata pertama nama]-[nomor hp]

        // variable yang berisi 0 untuk mempersiapkan hasil dari perhitungan
        int checkSum = 0;

        // for loop untuk melakukan penambahan masing masing karakter
        for (int i = 0; i < namaNomor.length(); i++) {
            
            // mengambil karakter per karakter dari variabel namaNomor
            char karakter = namaNomor.charAt(i);

            // jika karakter tersebut abjad A-Z maka akan masuk sini
            if (karakter >= 'A' && karakter <= 'Z') {
                checkSum += (int) karakter - 'A' + 1;   // melakukan perhitungan checkSum
            
            // jika karakter tersebut angka 0-9 maka akan masuk sini
            } else if (karakter >= '0' && karakter <= '9') {
                checkSum += (int) karakter - '0';   // melakukan perhitungan checkSum
            
            // selain kondisi diatas maka akan ditambahkan 7
            } else {
                checkSum +=7;
            }
        }

        // mengubah integer checkSum menjadi string
        String newCheckSum = ""+checkSum;

        // jika panjang dari checkSum nya 1 maka akan ditambahkan 0 di depan
        if (newCheckSum.length() == 1) {
            newCheckSum = "0" + newCheckSum;
        }
        // membuat variabel hasilId untuk menyimpan id yang sudah dibuat
        String hasilId = namaNomor + "-" + newCheckSum.substring(newCheckSum.length()-2);   // checksum yang diambil adalah 2 digit terakhir
        return hasilId; // mereturn variabel hasilId
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
        
        // jika parameter berat yang masuk <2 maka masuk sini
        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;  //berat akan diubah menjadi 2
        }

        //  variable yang berisi 0 untuk mempersiapkan hasil dari perhitungan
        int biaya = 0;
        // variabel kosong untuk mempersiapkan hasil dari operasi tanggal
        String tanggalSelesai = "";

        // memformat masukan tanggal
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //mengecek kesesuaian format
        LocalDate cekFormat = LocalDate.parse(tanggalTerima, formatTanggal);

        // kondisi jika parameter paket isinya "express"
        if (paket.equals("express")) {
            LocalDate durasiExpress = cekFormat.plusDays(1);    // menambah 1 hari
            tanggalSelesai = durasiExpress.format(formatTanggal);   // menyesuaikan formatnya
            biaya = 12000;  // biaya yang diperlukan
        
        // kondisi jika parameter paket isinya "fast"
        } else if (paket.equals("fast")) {
            LocalDate durasiFast = cekFormat.plusDays(2);   // menambah 2 hari
            tanggalSelesai = durasiFast.format(formatTanggal);  // menyesuaikan formatnya
            biaya = 10000;  // biaya yang diperlukan

        // kondisi jika parameter paket isinya "reguler"
        } else if (paket.equals("reguler")) {
            LocalDate durasiReguler = cekFormat.plusDays(3); // menambah 3 hari
            tanggalSelesai = durasiReguler.format(formatTanggal);   // menyesuaikan formatnya
            biaya = 7000;   // biaya yang diperlukan
        }

        int total = berat * biaya;  // menghitung total harga yang harus dibayar
        System.out.println("Berhasil menambahkan nota!");
        //System.out.println("ID Nota =" + idNota);
        
        // mereturn format dari keluaran nota
        return ("ID    : "+ id 
        +"\nPaket : "+ paket 
        +"\nHarga :\n"+ berat 
        +" kg x "+ biaya 
        +" = "+ total 
        +  "\nTanggal Terima  : "+ tanggalTerima 
        +"\nTanggal Selesai : " + tanggalSelesai + 
        "\nStatus      	: [apaan dah tuh]");
    }
}
