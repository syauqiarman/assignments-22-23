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
    private long totalHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private String tanggalSelesai;
    private boolean isDone;
    private long totalKompensasi;
    static public int totalNota;
    private boolean telat;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota++;
        addService(new CuciService());

        if (paket.equalsIgnoreCase("express")) {
            this.baseHarga = 12000;
            this.sisaHariPengerjaan = 1;
        } else if (paket.equalsIgnoreCase("fast")) {
            this.baseHarga = 10000;
            this.sisaHariPengerjaan = 2;
        } else if (paket.equalsIgnoreCase("reguler")) {
            this.baseHarga = 7000;
            this.sisaHariPengerjaan = 3;
        }
    }

    public void addService(LaundryService service){
        //TODO
        LaundryService[] newServiceList = new LaundryService[services.length + 1]; //membuat array baru yang panjangnya ditambah 1
        for (int i = 0; i < services.length; i++) {  //mengcopy isi dari array notaList ke array yg baru
            newServiceList[i] = services[i];
        }
        newServiceList[services.length] = service;  //mengisi index array yang terakhir dengan objek yang baru ditambahkan
        services = newServiceList; //mengupdate isi dari array notaList dengan isi dari array baru
    }

    public String kerjakan(){
        // TODO
        for(LaundryService service : services) {
            if(service != null  && service.isDone()==false) {
                String a = service.doWork();
                check();
                return "Nota " + this.id + " : " + a;
            }
        }
        this.isDone = true;
        return "Nota " + this.id + " : Sudah selesai.";
    }

    public Boolean check(){
        for(LaundryService service : services) {
            if(service.isDone()){
                continue;
            } else {
                isDone = false;
                return isDone;
            }
        }
        isDone = true;
        return isDone;
    }

    public void toNextDay() {
        // TODO
        this.sisaHariPengerjaan--;
        if (this.sisaHariPengerjaan < 0) {
            if (!isDone) {
                totalKompensasi = (this.sisaHariPengerjaan) * (-1) * 2000;
                telat = true;
            }
        }
    }

    public long calculateHarga(){
        // TODO
        if (telat) {
            totalHarga = (this.baseHarga * this.berat) - totalKompensasi;
        } else {
            totalHarga = this.baseHarga * this.berat;
        }

        if (totalHarga < 0){
            totalHarga = 0;
        }
        
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

    // public void allService() {
    //     for (LaundryService service : this.services) {
    //         outputService += "-" + service.getServiceName() + " @ Rp." + service.getHarga(this.berat) + "\n";
    //     }
    // }

    @Override
    public String toString(){
        // TODO
        // memformat masukan tanggal
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //mengecek kesesuaian format
        LocalDate cekFormat = LocalDate.parse(this.tanggalMasuk, formatTanggal);

        String outputService = "";
        for (LaundryService service : this.services) {
            outputService += "-" + service.getServiceName() + " @ Rp." + service.getHarga(this.berat) + "\n";
        }


        if (paket.equalsIgnoreCase("express")) {
            LocalDate durasiExpress = cekFormat.plusDays(1);    // menambah 1 hari
            tanggalSelesai = durasiExpress.format(formatTanggal);
        } else if (paket.equalsIgnoreCase("fast")) {
            LocalDate durasiFast = cekFormat.plusDays(2);   // menambah 2 hari
            tanggalSelesai = durasiFast.format(formatTanggal);  // menyesuaikan formatnya
        } else if (paket.equalsIgnoreCase("reguler")) {
            LocalDate durasiReguler = cekFormat.plusDays(3); // menambah 3 hari
            tanggalSelesai = durasiReguler.format(formatTanggal);   // menyesuaikan formatnya
        }
        
        //this.allService();
        if (telat) {
            return ("[ID Nota = " + this.id + "]\n"
            + "ID    : " + this.member.getId()
            + "\nPaket : " + this.paket
            + "\nHarga : \n" 
            + this.berat + "kg x " + this.baseHarga + " = " + (this.berat * this.baseHarga)
            + "\ntanggal terima  : " + this.tanggalMasuk
            + "\ntanggal selesai : " + this.tanggalSelesai
            + "\n--- SERVICE LIST ---\n"
            + outputService
            + "Harga Akhir: " + calculateHarga() + " Ada kompensasi keterlambatan " + (this. sisaHariPengerjaan) * (-1) + " * 2000 hari\n");
        } else {
            return ("[ID Nota = " + this.id + "]\n"
            + "ID    : " + this.member.getId()
            + "\nPaket : " + this.paket
            + "\nHarga : \n" 
            + this.berat + "kg x " + this.baseHarga + " = " + (this.berat * this.baseHarga)
            + "\ntanggal terima  : " + this.tanggalMasuk
            + "\ntanggal selesai : " + this.tanggalSelesai
            + "\n--- SERVICE LIST ---\n"
            + outputService
            + "Harga Akhir: " + calculateHarga() + "\n");
        }
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
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
