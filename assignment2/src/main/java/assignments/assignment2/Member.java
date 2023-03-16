package assignments.assignment2;    //package assignment 2

import assignments.assignment1.NotaGenerator;   //import NotaGenerator

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    //instance variables
    private String nama;
    private String noHp;
    private int bonusCounter = 0;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getNama() {   //getter untuk nama
        return nama;
    }

    public void setNama(String nama) {  //setter untuk nama
        this.nama = nama;
    }

    public String getNoHp() {   //getter untuk no hp
        return noHp;
    }

    public void setNoHp(String noHp) {  //setter untuk no hp
        this.noHp = noHp;
    }

    public String getId() { //getter untuk generateId
        return  (NotaGenerator.generateId(nama, noHp));
    }

    public int getBonusCounter() {  //getter untuk bonus counter
        return bonusCounter;
    }

    public void setBonusCounter() { //setter untuk bonus counter
        bonusCounter +=1; 
    }

    public void resetBonusCounter() {   //reset bonus counter
        bonusCounter = 0;
    }
}
