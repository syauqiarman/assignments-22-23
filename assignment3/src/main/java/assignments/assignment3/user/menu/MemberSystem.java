package assignments.assignment3.user.menu;  //package assignment3.user.menu

//import yang diperlukan
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.service.AntarService;
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
            case 1 :    //jika choicenya 1 akan memanggil method generateNota
                generateNota();
                break;
            case 2 :    //jika choicenya 2 akan memanggil method displayNota
                displayNota();
                break;
            case 3 :    //jika choicenya 3 akan mengubah logout menjadi true dan akan keluar loop
                logout = true;
                break;
            default:
                System.out.println("Invalid choice, please try again.");    //selain choice diatas akan looping lagi
                break;
        }
        return logout;  //mengembalikan logout
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

        if (berat < 2) {    //jika berat kurang dari 2
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;  //berat diubah menjadi 2
        }

        Nota notaBaru = new Nota(loginMember, berat, paket, fmt.format(cal.getTime())); //membuat nota baru
        ((Member)loginMember).addNota(notaBaru);    //menambahkan nota ke array notaList di Member yang sifatnya perorangan
        NotaManager.addNota(notaBaru);  //menambahkan nota ke array notaList di NotaManager yang sifatnya keseluruhan

        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String pilihanSetrika = in.nextLine();  // memasukkan input ke variabel pilihanSetrika

        if (!pilihanSetrika.equalsIgnoreCase("x")) {    //jika pilihanSetrika bukan "x" akan masuk kesini
            LaundryService setrika = new SetrikaService();  //membuat SetrikaService baru
            notaBaru.addService(setrika);   //menambahkan service setrika ke nota
        }

        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
        System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String pilihanAntar = in.nextLine();    // memasukkan input ke variabel pilihanAntar

        if (!pilihanAntar.equalsIgnoreCase("x")) {  //jika pilihanAntar bukan "x" akan masuk kesini
            LaundryService antar = new AntarService();  //membuat AntarService baru
            notaBaru.addService(antar); //menambahkan service antar ke nota
        }

        System.out.println("Nota berhasil dibuat!");
    }

    private void displayNota () {   //method untuk member melihat nota miliknya
        for (Nota nota : loginMember.getNotaList()) {
            System.out.println(nota); 
        }
    }
}