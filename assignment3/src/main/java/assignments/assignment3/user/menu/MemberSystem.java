package assignments.assignment3.user.menu;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.Nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Process user's choice according to the specific menu.
     *
     * @param choice -> user's choice
     * @return true if user logs out.
     */
    @Override
    protected boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                // Generate a new laundry nota and add it to the current user's nota list.
                String paket = NotaGenerator.getPaket();
                int berat = NotaGenerator.getBerat();
                String tanggal = fmt.format(cal.getTime());
                Nota nota = new Nota(loginMember, berat, paket, tanggal);
                loginMember.addNota(nota);
                // Ask user if they want to add any extra services.
                setrikaCucian(nota);
                antarCucian(nota);
                System.out.println("Nota berhasil dibuat!\n");
                break;
            case 2:
                // Display the details of the user's laundry nota.
                Nota[] daftarNota = loginMember.getNotaList();
                if (daftarNota.length > 0) {
                    for (Nota memo : daftarNota) {
                        System.out.println(memo);
                    }
                } 
                break;
            case 3:
                return true;
        }
        return false;
    }


    /**
     * Ask user if they want to add ironing service to their laundry.
     * 
     * @param nota -> the current laundry invoice.
     */
    public void setrikaCucian(Nota nota) {
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami? Hanya tambah 1000 / kg :0");
        System.out.print("[Ketik x untuk tidak mau]: ");
        if (!in.nextLine().equals("x")) {
            LaundryService setrikaService = new SetrikaService();
            nota.addService(setrikaService);
        }
    }

    /**
     * Ask user if they want to add delivery service to their laundry.
     * 
     * @param nota -> the current laundry nota.
     */
    public void antarCucian(Nota nota) {
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan! Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        if (!in.nextLine().equals("x")) {
            LaundryService antarService = new AntarService();
            nota.addService(antarService);
        }
    }

    /**
     * Displays specific menu for regular Members.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Adding a new Member to the system.
     *
     * @param member -> Adding a new Member to the system.
     */
    public void addMember(Member member) {
        Member[] newMemberList = new Member[memberList.length + 1];
        System.arraycopy(memberList, 0, newMemberList, 0, memberList.length);
        newMemberList[memberList.length] = member;
        memberList = newMemberList;
    }
}