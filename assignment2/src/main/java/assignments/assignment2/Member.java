package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

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
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getId() {
        return  (NotaGenerator.generateId(nama, noHp));
    }

    public int getBonusCounter() {
        return bonusCounter;
    }

    public void setBonusCounter() {
        bonusCounter +=1; 
    }

    public void resetBonusCounter() {
        if (bonusCounter >= 3) {
            bonusCounter = 0;
        }  
    }
}
