package assignments.assignment2;


import static assignments.assignment1.NotaGenerator.*;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;



public class Nota {
    // Attributes for Nota's class
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady = false;
 
    

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // Constructors for Nota's class
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
    }


        /**
     * Method for creating nota.
     * Print summary with the following format:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [harga] = [totalHarga] **= [Diskon] (if if it meets the requirements)
     *         <p>Tanggal Terima  : [receiptDate]
     *         <p>Tanggal Selesai : [receiptDate + duration]
     *         <p>Status      	:     
     */
    public void createNota() {
        // Get price and duration based from the chosen package
        int harga = getHargaPaket(paket);
        int durasi = getHariPaket(paket);
        sisaHariPengerjaan = durasi; // Set remaining days
        // Get date of receipt and date of completion
        LocalDate tanggalTerimaParsed = LocalDate.parse(tanggalMasuk, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate tanggalTenggat = tanggalTerimaParsed.plusDays(durasi);
        String tanggalSelesai = tanggalTenggat.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // If bonusCounter equals 3, a discount will be applied and a description will be added in the next line.
        // Otherwise, the program proceeds to the next line without applying any discount or adding any description.
        String extra = "";
        if (member.getBonusCounter() == 3) {
            extra = " = " + (berat*harga)/2 + " (Discount member 50%!!!)";
        }
        String totalHargaStr = String.valueOf(berat*harga) + extra;
        // Print the nota's summary
        System.out.printf("ID    : %s\nPaket : %s\nHarga :\n%d kg x %d = %s\nTanggal Terima  : %s\nTanggal Selesai : %s", 
        member.getId(), paket, berat, harga, totalHargaStr, tanggalMasuk, tanggalSelesai);
        System.out.println("\nStatus      	: " + getStatus());
    }


    // Getter methods

    public String getPaket() {
        return paket;
    }

    public Member getMember() {
        return member;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public String getStatus() {
        if (isReady) return "Sudah dapat diambil!";
        else {
            return "Belum bisa diambil :(";
        }
    }


    // Setter methods

    public void setSisaHariPengerjaan(int sisaHariPengerjaan) {
        this.sisaHariPengerjaan = sisaHariPengerjaan;
    }

    public void setIsReady(int sisaHariPengerjaan) {
        if (sisaHariPengerjaan == 0) {
            this.isReady = true;
        } else {
            this.isReady = false;
        }
    }
    

    





}
