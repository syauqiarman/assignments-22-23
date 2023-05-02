package assignments.assignment3.nota;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

//import yang diperlukan
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services  = new LaundryService[0];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private String tanggalSelesai;
    private boolean isDone;
    private long totalKompensasi;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;

        // memformat masukan tanggal
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //mengecek kesesuaian format
        LocalDate cekFormat = LocalDate.parse(this.tanggalMasuk, formatTanggal);

        if (paket.equalsIgnoreCase("express")) {
            this.baseHarga = 12000;
            this.sisaHariPengerjaan = 1;
            LocalDate durasiExpress = cekFormat.plusDays(1);    // menambah 1 hari
            tanggalSelesai = durasiExpress.format(formatTanggal);
        } else if (paket.equalsIgnoreCase("fast")) {
            this.baseHarga = 10000;
            this.sisaHariPengerjaan = 2;
            LocalDate durasiFast = cekFormat.plusDays(2);   // menambah 2 hari
            tanggalSelesai = durasiFast.format(formatTanggal);  // menyesuaikan formatnya
        } else if (paket.equalsIgnoreCase("reguler")) {
            this.baseHarga = 7000;
            this.sisaHariPengerjaan = 3;
            LocalDate durasiReguler = cekFormat.plusDays(3); // menambah 3 hari
            tanggalSelesai = durasiReguler.format(formatTanggal);   // menyesuaikan formatnya
        }
    }

    public void addService(LaundryService service){
        //TODO
        // for (int i = 0; i < services.length; i++) {
        //     if (services[i] == null) {
        //         services[i] = service;
        //         break;
        //     }
        // }
        addArray(service);
        // if (service.getServiceName().equals("Cuci")) {
        //     addArray(service);
        // } else if (service.getServiceName().equals("Antar")) {
        //     addArray(service);
        // } else if (service.getServiceName().equals("Setrika")) {
        //     services[3] = service;
        // }
    }

    public String kerjakan(){
        // TODO
        for(LaundryService service : services) {
            if(service != null  && service.isDone()==false) {
                return service.doWork();
            }
        }
        this.isDone = true;
        return "Sudah selesai.";
    }
    public void toNextDay() {
        // TODO
        this.sisaHariPengerjaan--;
        if (this.sisaHariPengerjaan <= 0) {
            if (!isDone) {
                totalKompensasi = this.sisaHariPengerjaan * (-1) * 2000;
            }
        }
    }

    public long calculateHarga(){
        // TODO
        long totalHarga = this.baseHarga *this.berat;
        for (LaundryService service : services) {
            if (service != null) {
                totalHarga += service.getHarga(this.berat);
            }
        }
        return totalHarga;
    }

    public String getNotaStatus(){
        // TODO
        if (isDone) {
            return "Nota " + this.id + " : Sudah selesai.";
        } else {
            return "Nota " + this.id + " : Belum selesai.";
        }
    }

    public void addArray(LaundryService service) {
        // TODO
        LaundryService[] newServiceList = new LaundryService[services.length + 1]; //membuat array baru yang panjangnya ditambah 1
        for (int i = 0; i < services.length; i++) {  //mengcopy isi dari array notaList ke array yg baru
            newServiceList[i] = services[i];
        }
        newServiceList[services.length] = service;  //mengisi index array yang terakhir dengan objek yang baru ditambahkan
        services = newServiceList; //mengupdate isi dari array notaList dengan isi dari array baru
    }

    @Override
    public String toString(){
        // TODO
        return "";
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public int getIdNota() {    //getter untuk IdNota
        return id;
    }

    public long getBaseHarga() {
        return baseHarga;
    }

    public void setIdNota(Integer id) { //setter untuk idNota
        this.id = id; 
    }
}
