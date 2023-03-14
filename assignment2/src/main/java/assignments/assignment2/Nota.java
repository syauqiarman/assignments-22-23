package assignments.assignment2;

import assignments.assignment1.NotaGenerator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String checkID;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int idNota;
    private static int tempIdNota;
    private Member member;
    private int sisaHariPengerjaan;
    private boolean isReady = false;

    public Nota(String checkID, String paket, int berat, String tanggalMasuk, Member member) {
        // TODO: buat constructor untuk class ini
        this.checkID = checkID;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.member = member;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getCheckID() {
        return checkID;
    }

    public void setCheckID(String checkID) {
        this.checkID = checkID;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(Integer berat) {
        this.berat = berat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getNota() {
        return (generateNota(checkID, paket, berat, tanggalMasuk, member)) ;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
        tempIdNota = idNota;
    }

    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }

    public void setSisaHariPengerjaan() {
        // kondisi jika parameter paket isinya "express"
        if (paket.equals("express")) {
            sisaHariPengerjaan = 1;
        // kondisi jika parameter paket isinya "fast"
        } else if (paket.equals("fast")) {
            sisaHariPengerjaan = 2;
        // kondisi jika parameter paket isinya "reguler"
        } else if (paket.equals("reguler")) {
            sisaHariPengerjaan = 3;
        }
    }

    public void kurangSisaHariPengerjaan () {
        this.sisaHariPengerjaan -= 1;
    }

    public void checkSisaHariPengerjaan () {
        if (sisaHariPengerjaan <= 0) {
            this.isReady = true;
        }
    }
    
    public Member getMember() {
        return member;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima, Member member){
        // TODO: Implement generate nota sesuai soal.
        
        // jika parameter berat yang masuk <2 maka masuk sini
        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;  //berat akan diubah menjadi 2
        }

        //  variable yang berisi 0 untuk mempersiapkan hasil dari perhitungan
        int biaya = 0;
        int total = 0;
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
        if (member.getBonusCounter() == 3) {
            total = berat * biaya * 50 / 100;  // menghitung total harga yang harus dibayar dengan diskon
            
        } else {
            total = berat * biaya; // menghitung total harga yang harus dibayar
        }
        
        System.out.println("Berhasil menambahkan nota!");
        
        System.out.println("[ID Nota = " +tempIdNota+ "]");
        
        // mereturn format dari keluaran nota
        if (member.getBonusCounter() == 3) {
            member.resetBonusCounter();
            return ("ID    : "+ id 
            +"\nPaket : "+ paket 
            +"\nHarga :\n"+ berat 
            +" kg x "+ biaya 
            +" = "+ total + 
            "(Discount member 50%!!!)\nTanggal Terima  : "+ tanggalTerima 
            +"\nTanggal Selesai : " + tanggalSelesai + 
            "\nStatus      	: Belum bisa diambil :(");
            
        } else {
            return ("ID    : "+ id 
            +"\nPaket : "+ paket 
            +"\nHarga :\n"+ berat 
            +" kg x "+ biaya 
            +" = "+ total 
            +  "\nTanggal Terima  : "+ tanggalTerima 
            +"\nTanggal Selesai : " + tanggalSelesai + 
            "\nStatus      	: Belum bisa diambil :(");
        }

    }
}
