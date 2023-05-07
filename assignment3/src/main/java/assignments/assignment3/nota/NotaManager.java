package assignments.assignment3.nota;   //package assignment3.nota

//import yang diperlukan
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");    // memformat masukan tanggal
    public static Calendar cal = Calendar.getInstance();    //calendar
    static public Nota[] notaList = new Nota[0];    //membuat array notaList untuk menyimpan nota keseluruhan

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //TODO: implement skip hari
        cal.add(Calendar.DATE, 1);    // menambah 1 hari
        for (Nota nota : notaList) {    //melakukan nextDay untuk setiap nota
            nota.toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        //TODO: implement add nota
        Nota[] newNotalist = new Nota[notaList.length + 1]; //membuat array baru yang panjangnya ditambah 1
        for (int i = 0; i < notaList.length; i++) {  //mengcopy isi dari array notaList ke array yg baru
            newNotalist[i] = notaList[i];
        }
        newNotalist[notaList.length] = nota;  //mengisi index array yang terakhir dengan objek yang baru ditambahkan
        notaList = newNotalist; //mengupdate isi dari array notaList dengan isi dari array baru
    }
}
