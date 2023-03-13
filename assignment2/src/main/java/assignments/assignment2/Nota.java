package assignments.assignment2;

import static assignments.assignment1.NotaGenerator.*;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String checkID;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int idNota;

    public Nota(String checkID, String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this. checkID = checkID;
        this. paket = paket;
        this. berat = berat;
        this. tanggalMasuk = tanggalMasuk;
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
        return (generateNota(checkID, paket, berat, tanggalMasuk)) ;
    }
    public int getIdNota() {
        return idNota;
    }
    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }
}
