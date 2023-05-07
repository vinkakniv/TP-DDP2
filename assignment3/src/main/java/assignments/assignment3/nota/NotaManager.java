package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips to the next day and updates all relevant Nota entries.
     */
    public static void toNextDay(){
        cal.add(Calendar.DATE, 1);
        for (Nota nota : notaList) {
            nota.toNextDay();
        }
    }

    /**
     * Adds a new Nota object to the NotaList array.
     *
     * @param nota The Nota object to be added.
     */
    public static void addNota(Nota nota) {
        Nota[] newNotaList = new Nota[notaList.length + 1];
        System.arraycopy(notaList, 0, newNotaList, 0, notaList.length);
        newNotaList[newNotaList.length - 1] = nota;
        notaList = newNotaList;
    }
}
