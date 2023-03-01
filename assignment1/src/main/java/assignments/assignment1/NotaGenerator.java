package assignments.assignment1;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner inp = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        //inp.close();
        String choice;
        do {
            printMenu();
            System.out.print("Pilihan : "); 
            choice = inp.nextLine();
            switch (choice) {
                case "0":
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break;
                case "1":
                    System.out.println("================================");
                    System.out.println("Masukkan nama Anda: ");
                    String name1 = inp.nextLine();
                    String mobileNumber1 = getMobileNumber(inp);
                    String id1 = generateId(name1, mobileNumber1);
                    System.out.println("ID Anda : " + id1);
                    break;
                case "2":
                    System.out.println("================================");
                    System.out.println("Masukkan nama Anda:");
                    String name2 = inp.nextLine();
                    String mobileNumber2 = getMobileNumber(inp);
                    String id2 = generateId(name2, mobileNumber2);
                    System.out.println("Masukkan tanggal terima: ");
                    String dateOfReceipt = inp.nextLine();
                    String laundryPackage = getLaundryPackage(inp);
                    System.out.println("Masukkan berat cucian Anda [Kg]: ");
                    int laundryWeight = getLaundryWeight(inp);
                    String note = generateNota(id2, laundryPackage, laundryWeight, dateOfReceipt);
                    System.out.println(note);
                    break;
                default:
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                    break;
                }
        }  while (!choice.equals("0")); 
        inp.close();
    }

    public static String getLaundryPackage(Scanner inp){
        String thePackage;
        while (true) {
            System.out.println("Masukkan paket laundry: ");
            thePackage = inp.nextLine();
            String thePackageLower = thePackage.toLowerCase();
            if (thePackage.equals("?")) {
                showPaket();
                continue;
            }
            else if (!(thePackageLower.equals("reguler") || thePackageLower.equals("fast") || thePackageLower.equals("express"))) {
                System.out.println("Paket " + thePackage + " tidak diketahui");
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                continue;
            }
            break;
        }
        return thePackage;
    }

    public static int getLaundryWeight(Scanner inp) {
        String weightString;
        int weight;
        while (true) {
            weightString = inp.nextLine();
            weight = Integer.parseInt(weightString);
            if (!inputNumValidation(weightString)) {
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                continue; // input is not valid, continue loop 
            } else if ((inputNumValidation(weightString)) && (weight < 2)) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                weight = 2;
            } else if ((inputNumValidation(weightString)) && (weight >= 2)) 
                break;
        } return weight;
    }
    public static String getMobileNumber(Scanner inp) {
        String mobileNumber; 
        while (true) {
            System.out.println("Masukkan nomor handphone Anda: ");
            mobileNumber = inp.nextLine();
            if (!inputNumValidation(mobileNumber)) {
                System.out.println("Nomor hp hanya menerima digit");
                continue; // input is not valid, continue loop
            } break;
        } return mobileNumber;
    }

    public static boolean inputNumValidation(String input) {
        try {
            double value = Double.parseDouble(input);
            return value > 0; // input is valid
        } catch (NumberFormatException e) {
            return false; // input is not valid
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    
    }
    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        String firstName = nama.split("\\s+")[0].toUpperCase();
        String checksum = firstName + "-" + nomorHP;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int totalValue = 0;
        for (int i = 0; i < checksum.length(); i++) {
            char c = checksum.charAt(i);
            if (Character.isDigit(c)) {
                int digitValue = Character.getNumericValue(c);
                totalValue += digitValue;
            } else if (Character.isLetter(c)) {
                int charValue = alphabet.indexOf(Character.toUpperCase(c)) + 1;
                totalValue += charValue;
            } else {
                totalValue += 7;
            }
        }
        return  checksum + "-" + String.format("%02d", totalValue);
    }
    

        
    

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        int totalHarga;
        LocalDate tanggalTerimaParsed = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate tanggalSelesai;
        int price = 0;
        int duration = 0;
        if (paket.toLowerCase().equals("reguler")) {
            price = 7000;
            duration = 3;
        } else if (paket.toLowerCase().equals("fast")) {
            price = 10000;
            duration = 2;
        } else if (paket.toLowerCase().equals("express")) {
            price = 12000;
            duration = 1;
        }
        totalHarga = price * berat;
        tanggalSelesai = tanggalTerimaParsed.plusDays(duration);
        String formattedTanggalSelesai = tanggalSelesai.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nota = String.format("ID    : %s\nPaket : %s\nHarga :\n%d kg x %d = %d\nTanggal Terima  : %s\nTanggal Selesai : %s", 
        id, paket, berat, price, totalHarga, tanggalTerima, formattedTanggalSelesai);
        return nota;
    }
}
