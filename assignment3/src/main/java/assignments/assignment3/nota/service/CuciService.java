package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    //tambahan
    private boolean isCuciDone;

    public CuciService(){
        this.isCuciDone = false;
    }
    //sampe sini

    @Override
    public String doWork() {
        // TODO
        this.isCuciDone = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return this.isCuciDone;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
