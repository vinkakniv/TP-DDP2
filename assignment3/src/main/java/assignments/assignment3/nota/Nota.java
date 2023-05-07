package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.CuciService;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = {new CuciService()};
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    private int totalHari;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.id = totalNota;
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = NotaGenerator.toHariPaket(paket);
        this.baseHarga = NotaGenerator.toHargaPaket(paket)*berat;
        totalNota++;
    }

    public void addService(LaundryService service){
        LaundryService[] newServices = new LaundryService[services.length + 1];
        System.arraycopy(services, 0, newServices, 0, services.length);
        newServices[services.length] = service;
        services = newServices;
    }

    public String kerjakan(){
        for (int i = 0; i < services.length; i++) {
            if (!services[i].isDone()) {
                if (services[i].equals(services[services.length-1])) {
                    this.isDone = true;
                }
                return "Nota " + this.id + " : " + services[i].doWork();
            }
        }
        return "Nota " + this.id + " : Sudah selesai.";
    }

    public void toNextDay() {
        if (!isDone()) {
            this.totalHari++;
        } 
        this.sisaHariPengerjaan--;
    }

    public long calculateHarga(){
        long totalHarga = this.baseHarga;
        for (LaundryService service : services) {
            totalHarga += service.getHarga(berat);
        }
        if (totalHari >  NotaGenerator.toHariPaket(paket)) {
            totalHarga = totalHarga - (2000*(totalHari-NotaGenerator.toHariPaket(paket)));
        }
        if (totalHarga <= 0) {
            return 0;
        }
        return totalHarga;
    }

    public String getNotaStatus(){
        if (isDone) {
            return "Nota " + this.id + " : Sudah selesai.";
        }
        return "Nota " + this.id + " : Belum selesai.";
    }

    @Override
    public String toString(){
        String notaStr = String.format("[ID Nota = %d]\n", this.id);
        notaStr += NotaGenerator.generateNota(member.getId(), this.paket, this.berat, this.tanggalMasuk, false) + "\n";
        notaStr += "--- SERVICE LIST ---\n";
        for (LaundryService service : services) {
            notaStr += "-" + service.getServiceName() + " @ Rp." + service.getHarga(berat) + "\n";
        }
        long totalPrice = this.calculateHarga();
        notaStr += String.format("Harga Akhir: %,d", totalPrice).replaceAll(",", "");
        if (totalHari >  NotaGenerator.toHariPaket(paket)) {
            notaStr += " Ada kompensasi keterlambatan " + (totalHari - NotaGenerator.toHariPaket(paket)) + " * 2000 hari";
        }
        notaStr += "\n";
        return notaStr;
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

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }

    public int getId() {
        return id;
    }

    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
