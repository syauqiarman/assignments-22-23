package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String checkID;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int idNota;
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
        return (NotaGenerator.generateNota(checkID, paket, berat, checkID, member)) ;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
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
}
