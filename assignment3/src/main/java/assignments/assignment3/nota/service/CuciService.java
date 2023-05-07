package assignments.assignment3.nota.service;   //package assignment3.nota.service

public class CuciService implements LaundryService{
    private boolean isCuciDone;

    public CuciService(){
        this.isCuciDone = false;
    }

    @Override
    public String doWork() {    //proses pengerjaan
        // TODO
        this.isCuciDone = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {   //nilai isDone
        // TODO
        return this.isCuciDone;
    }

    @Override
    public long getHarga(int berat) {   //harga servicenya
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {    //nama servicenya
        return "Cuci";
    }
}
