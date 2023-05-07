package assignments.assignment3.nota.service;   //package assignment3.nota.service

public class SetrikaService implements LaundryService{
    //tambahan
    private boolean isSetrikaDone;

    public SetrikaService() {
        this.isSetrikaDone = false;
    }
    //sampe sini

    @Override
    public String doWork() {    //proses pengerjaan
        // TODO
        this.isSetrikaDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {   //nilai isDone
        // TODO
        return this.isSetrikaDone;
    }

    @Override
    public long getHarga(int berat) {   //harga servicenya
        // TODO
        long biayaSetrika = 1000 * berat;   //harga servicenya per kg 1000
        return biayaSetrika;    //mengembalikan biayaSetrika
    }

    @Override
    public String getServiceName() {    //nama servicenya
        return "Setrika";
    }
}
