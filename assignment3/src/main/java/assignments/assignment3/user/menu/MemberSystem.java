package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    int idNotaCounter = 0;
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
        System.out.println("Masukan paket laundry: ");
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
        String paket = in.nextLine();

        System.out.println("Masukan berat cucian anda [Kg]: ");
        int berat = in.nextInt();
        in.nextLine();

        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;  //berat akan diubah menjadi 2
        }

        Nota notaBaru = new Nota(loginMember, berat, paket, fmt.format(cal.getTime()));
        notaBaru.setIdNota(idNotaCounter);
        idNotaCounter++;
        ((Member)loginMember).addNota(notaBaru);
        NotaManager.addNota(notaBaru);
        LaundryService cuci = new CuciService();
        notaBaru.addService(cuci);
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.println("[Ketik x untuk tidak mau]: ");
        String pilihanSetrika = in.nextLine();

        if (!pilihanSetrika.equalsIgnoreCase("x")) {
            LaundryService setrika = new SetrikaService();
            notaBaru.addService(setrika);
        }

        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
        System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.println("[Ketik x untuk tidak mau]: ");
        String pilihanAntar = in.nextLine();

        if (!pilihanAntar.equalsIgnoreCase("x")) {
            // TODO
            LaundryService antar = new AntarService();
            notaBaru.addService(antar);
        }

        System.out.println("Nota berhasil dibuat!");
    }

    private void displayNota () {
        //Member member = (Member) loginMember;
        for (Nota nota : loginMember.getNotaList()) {
            System.out.println("[ID Nota = " + nota.getIdNota() + "]");
            System.out.println("ID    : " + loginMember.getId());
            System.out.println("Paket : " + nota.getPaket());
            System.out.println("Harga : \n" 
            + nota.getBerat() + "kg x " + nota.getBaseHarga() + " = " + (nota.getBerat()*nota.getBaseHarga()));
            System.out.println("tanggal terima  : " + nota.getTanggalMasuk());
            System.out.println("tanggal selesai : " + nota.getTanggalSelesai());
            System.out.println("--- SERVICE LIST ---");
            for (LaundryService service : nota.getServices()) {
                System.out.println("-" + service.getServiceName() + " @ Rp." + service.getHarga(nota.getBerat()));
            }
            System.out.println("Harga Akhir: " + nota.calculateHarga() + "\n");
            
        }
    }
}