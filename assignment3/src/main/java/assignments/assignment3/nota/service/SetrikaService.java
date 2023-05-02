package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    //tambahan
    private boolean isSetrikaDone;

    public SetrikaService() {
        this.isSetrikaDone = false;
    }
    //sampe sini

    @Override
    public String doWork() {
        // TODO
        this.isSetrikaDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return this.isSetrikaDone;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long biayaSetrika = 1000 * berat;
        return biayaSetrika;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
