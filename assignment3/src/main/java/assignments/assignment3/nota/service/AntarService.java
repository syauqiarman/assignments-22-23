package assignments.assignment3.nota.service;   //package assignment3.nota.service

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean isAntarDone;

    public AntarService(){
        this.isAntarDone = false;
    }

    @Override
    public String doWork() {    //proses pengerjaan
        // TODO
        this.isAntarDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {   //nilai isDone
        // TODO
        return this.isAntarDone;
    }

    @Override
    public long getHarga(int berat) {   //harga servicenya
        // TODO
        long biayaAntar = 0;    //membuat variabel biayaAntar
        
        //akan menghitung harga service dimana 4kg pertama seharga 2000 dan 1kg berikutnya seharga 500
        berat -= 4; //pengecekan dengan mengurangi berat dengan 4
        if (berat > 0) {    //jika hasilnya lebih dari 0 masuk sini
            biayaAntar = 2000 + (berat * 500);
        } else {    //selain itu masuk sini
            biayaAntar = 2000;
        }
        return biayaAntar;  //mengembalikan biayaAntar
    }

    @Override
    public String getServiceName() {    //nama servicenya
        return "Antar";
    }
}
