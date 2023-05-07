package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment1.NotaGenerator;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    //int idNotaCounter = 0;
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        switch (choice) {
            case 1 :
                generateNota();
                break;
            case 2 :
                displayNota();
                break;
            case 3 :
                logout = true;
                break;
            default:
                System.out.println("Invalid choice, please try again.");    //gatau pesannya apa
                break;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        Member[] newMemberList = new Member[memberList.length + 1]; //membuat array baru yang panjangnya ditambah 1
        for (int i = 0; i < memberList.length; i++) {  //mengcopy isi dari array memberList ke array yg baru
            newMemberList[i] = memberList[i];
        }
        newMemberList[memberList.length] = member;  //mengisi index array yang terakhir dengan objek yang baru ditambahkan
        memberList = newMemberList; //mengupdate isi dari array notaList dengan isi dari array baru
    }

    private void generateNota () {
        // variabel yang berisi string kosong untuk mempersiapkan hasil dari input paket
        String paket = "";

        System.out.print("Masukkan paket laundry:\n");
        NotaGenerator.showPaket();    //memanggil method showPaket yang isinya adalah jenis jenis paket yang disediakan

        // while loop yang digunakan untuk mengecek kecocokan variabel dengan kondisi if yang dibuat
        while (true) {
            paket = in.nextLine();    // memasukkan input ke variabel paket
            
            // kondisi if yang mengecek jika variabel paket sesuai dengan string yang sudah ditentukan
            if (paket.equalsIgnoreCase("express") || paket.equalsIgnoreCase("fast") || paket.equalsIgnoreCase("reguler")) {
                break;
            //pengecekan variabel paket jika isinya adalah "?"
            } else if (paket.equals("?")) { 
                NotaGenerator.showPaket();    //memanggil method showPaket yang isinya adalah jenis jenis paket yang disediakan
            //kondisi jika paket tidak diketahui dan akan me loop kembali
            } else {    
                System.out.println("Paket " + paket + " tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
            }  
        }

        System.out.print("Masukkan berat cucian Anda [Kg]:\n");
        String beratStr = in.nextLine(); //memasukkan input ke variabel beratStr
        
        /*mengecek validasi dari input beratStr yang telah dilakukan menggunakan while loop
        while loop akan mengecek satu persatu dari inputan beratStr, yang dianggap benar adalah 0 sampai 9
        lalu di or jika ada spasi di beratStr maka juga akan meminta input ulang
        di or lagi dengan beratStr yang di integerkan dan sama dengan 0*/
        while (beratStr.matches("[0-9]+") != true || beratStr.contains(" ") || Integer.parseInt(beratStr) == 0) {
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            beratStr = in.nextLine();
        }
        int berat = Integer.parseInt(beratStr);  //mengubah string berat menjadi integer

        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;  //berat akan diubah menjadi 2
        }

        Nota notaBaru = new Nota(loginMember, berat, paket, fmt.format(cal.getTime()));
        //notaBaru.setIdNota(idNotaCounter);
        //idNotaCounter++;
        ((Member)loginMember).addNota(notaBaru);
        NotaManager.addNota(notaBaru);
        //LaundryService cuci = new CuciService();
        //notaBaru.addService(cuci);
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String pilihanSetrika = in.nextLine();

        if (!pilihanSetrika.equalsIgnoreCase("x")) {
            LaundryService setrika = new SetrikaService();
            notaBaru.addService(setrika);
        }

        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
        System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String pilihanAntar = in.nextLine();

        if (!pilihanAntar.equalsIgnoreCase("x")) {
            // TODO
            LaundryService antar = new AntarService();
            notaBaru.addService(antar);
        }
        //notaBaru.allService();
        System.out.println("Nota berhasil dibuat!");
    }

    private void displayNota () {
        for (Nota nota : loginMember.getNotaList()) {
            System.out.println(nota); 
        }
    }
}