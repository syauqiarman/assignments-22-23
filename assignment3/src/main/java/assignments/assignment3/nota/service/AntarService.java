package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    //tambahan
    private boolean isAntarDone;

    public AntarService(){
        this.isAntarDone = false;
    }
    //sampe sini

    @Override
    public String doWork() {
        // TODO
        this.isAntarDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return this.isAntarDone;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long biayaAntar = 0;
        berat -= 4;
        if (berat > 0) {
            biayaAntar = 2000 + (berat * 500);
        } else {
            biayaAntar = 2000;
        }
        return biayaAntar;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
