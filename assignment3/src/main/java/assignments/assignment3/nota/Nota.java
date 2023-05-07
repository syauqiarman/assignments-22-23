package assignments.assignment3.nota;   //package assignment3.nota

//import yang diperlukan
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;

//import yang diperlukan
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services  = new LaundryService[0]; //membuat array yang menyimpan service apa saja yang digunakan
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
    private int telatBerapaHari;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota++;  //pemberian idNota
        addService(new CuciService());  //membuat CuciService baru dan menambahkannya ke array services

        //set baseHarga dan sisaHariPengerjaan sesuai jenis paket yang diambil
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

    public void addService(LaundryService service){ //method addService untuk menambahkan service ke array services
        //TODO
        LaundryService[] newServiceList = new LaundryService[services.length + 1]; //membuat array baru yang panjangnya ditambah 1
        for (int i = 0; i < services.length; i++) {  //mengcopy isi dari array notaList ke array yg baru
            newServiceList[i] = services[i];
        }
        newServiceList[services.length] = service;  //mengisi index array yang terakhir dengan objek yang baru ditambahkan
        services = newServiceList; //mengupdate isi dari array notaList dengan isi dari array baru
    }

    public String kerjakan(){   //method untuk employee mengerjakan service yang ada
        // TODO
        for(LaundryService service : services) {
            if(service != null  && service.isDone()==false) {
                String a = service.doWork();    // employee mulai mengerjakan pekerjaannya
                checkIsDone();    //mengecek boolean dengan memanggil method checkIsDone
                return "Nota " + this.id + " : " + a;   //mengembalikan string dengan format nota, id nota, dan status pengerjaan
            }
        }
        this.isDone = true;
        return "Nota " + this.id + " : Sudah selesai."; //mengembalikan string dengan format nota, id nota, dan pemberitahuan sudah selesai
    }

    public Boolean checkIsDone(){   //method untuk mengecek status masing-masing services
        for(LaundryService service : services) {
            if(service.isDone()){   //kalau sudah selesai, continue
                continue;
            } else {    //jika belum isDone nilainya false
                isDone = false;
                return isDone;
            }
        }
        isDone = true;  //jika sudah semua isDone jadi true
        return isDone;
    }

    public void toNextDay() {   //menghandle hari berikutnya
        // TODO
        this.sisaHariPengerjaan--;  //akan mengurangi sisa hari pengerjaan
        if (this.sisaHariPengerjaan < 0) {  //jika sisaHariPengerjaan kurang dari 0 akan masuk sini
            if (!isDone) {  //jika service nya masih belum selesi juga akan ada kompensasi
                totalKompensasi = (this.sisaHariPengerjaan) * (-1) * 2000;  //penghitungan kompensasi
                telat = true;   //variabel boolean telat yang menandakan nota tersebut telat
            }
        }
    }

    public long calculateHarga(){   //menghandle kalkulasi harga
        // TODO
        if (telat) {    //jika telat maka harga total akan dikurangi kompensasi
            totalHarga = (this.baseHarga * this.berat) - totalKompensasi;
        } else {    //jika tidak maka harga totalnya normal
            totalHarga = this.baseHarga * this.berat;
        }
        
        for (LaundryService service : services) {   //penambahan total harga berdasarkan service yang digunakan
            if (service != null) {
                totalHarga += service.getHarga(this.berat); //memanggil getharga masing-masing services
            }
        }

        if (totalHarga < 0){    //kalau sudah sekian hari belum dikerjakan hingga total harga bernilai negatif, maka total harga diperbarui jadi 0
            totalHarga = 0;
        }
        return totalHarga;
    }

    public String getNotaStatus(){  //method untuk melihat status nota
        // TODO
        if (isDone) {
            return "Nota " + this.id + " : Sudah selesai.";
        } else {
            return "Nota " + this.id + " : Belum selesai.";
        }
    }

    @Override
    public String toString(){
        // TODO
        String outputService = "";
        for (LaundryService service : this.services) {  //manambahkan string dengan apa saja services yang digunakan beserta harganya
            outputService += "-" + service.getServiceName() + " @ Rp." + service.getHarga(this.berat) + "\n";
        }
        
        /*jika belum selesai juga maka akan masuk sini untuk penghitungan berapa hari terlambatnya, 
        dan jika baru dikerjakan saat sudah telat sekian hari, maka variable telatBerapaHari tidak akan bertambah lagi*/
        if (!isDone) { 
            telatBerapaHari = this. sisaHariPengerjaan * (-1);
        }

        // memformat masukan tanggal
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //mengecek kesesuaian format
        LocalDate cekFormat = LocalDate.parse(this.tanggalMasuk, formatTanggal);

        //set tanggalSelesai dari paket
        if (paket.equalsIgnoreCase("express")) {
            LocalDate durasiExpress = cekFormat.plusDays(1);    // menambah 1 hari
            tanggalSelesai = durasiExpress.format(formatTanggal);   // menyesuaikan formatnya
        } else if (paket.equalsIgnoreCase("fast")) {
            LocalDate durasiFast = cekFormat.plusDays(2);   // menambah 2 hari
            tanggalSelesai = durasiFast.format(formatTanggal);  // menyesuaikan formatnya
        } else if (paket.equalsIgnoreCase("reguler")) {
            LocalDate durasiReguler = cekFormat.plusDays(3); // menambah 3 hari
            tanggalSelesai = durasiReguler.format(formatTanggal);   // menyesuaikan formatnya
        }
        
        if (telat) {    //jika telat keluarannya ini
            return ("[ID Nota = " + this.id + "]\n"
            + "ID    : " + this.member.getId()
            + "\nPaket : " + this.paket
            + "\nHarga : \n" 
            + this.berat + "kg x " + this.baseHarga + " = " + (this.berat * this.baseHarga)
            + "\ntanggal terima  : " + this.tanggalMasuk
            + "\ntanggal selesai : " + this.tanggalSelesai
            + "\n--- SERVICE LIST ---\n"
            + outputService
            + "Harga Akhir: " + calculateHarga() + " Ada kompensasi keterlambatan " + telatBerapaHari + " * 2000 hari\n");
        } else {    //jika tidak telat keluarannya ini
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

    public int getIdNota() { 
        return id;
    }

    public long getBaseHarga() {
        return baseHarga;
    }
}
