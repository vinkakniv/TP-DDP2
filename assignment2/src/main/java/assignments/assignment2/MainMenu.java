package assignments.assignment2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import static assignments.assignment1.NotaGenerator.*;


public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Member> daftarMember = new ArrayList<Member>();
    private static HashMap<String, Nota> daftarNota = new HashMap<String, Nota>();
    private static Member member;
    private static Nota nota;
    private static Random random = new Random();

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    // Method to handle generate user
    private static void handleGenerateUser() {
        // Generate user
        // Prompt user for name 
        System.out.println("Masukan nama Anda: ");
        String inputNama = input.nextLine();
        // Validate and get user's mobile number
        String nomorHp = getMobileNumber(input);
        // Check if member already exists in daftarMember
        boolean memberExists = false;
        for (Member m : daftarMember) {
            if (m.getId().equals(generateId(inputNama, nomorHp))) {
                memberExists = true;
                break;
            }
        }
        // If member exists, print error message. Otherwise, add member to list and print success message
        if (memberExists) {
            System.out.println("Member dengan nama " + inputNama + " dan nomor hp " + nomorHp + " sudah ada!");
        } else {
            member = new Member(inputNama, nomorHp);
            daftarMember.add(member);
            String memberId = member.getId();
            System.out.println("Berhasil membuat member dengan ID " + memberId + "!");
        }
    }


    // Method to handle generate nota
    private static void handleGenerateNota() {
        // Prompt user for member's id
        System.out.println("Masukan ID member: ");
        String inputId = input.nextLine();
        // Check if member already exists in daftarMember
        boolean tidakAda = false; 
        for (Member m : daftarMember) {
            if (inputId.equals(m.getId())) {
                // Member exist in daftarMember
                tidakAda = true; 
                // Validate and get user's selected package & laundry weight
                String paketCucian = getLaundryPackage(input); 
                System.out.println("Masukkan berat cucian Anda [Kg]: ");
                int beratCucian = getLaundryWeight(input);
                System.out.println("Berhasil menambahkan nota!");
                // Get id nota 
                String idNota = getIdNote();
                System.out.printf("[ID Nota = %s] \n", idNota);
                String tanggalMasuk = fmt.format(cal.getTime());
                // Create a nota object, add to daftarNota
                nota = new Nota(m, paketCucian, beratCucian, tanggalMasuk);
                daftarNota.put(idNota, nota);
                // Increment the bonus counter from member's class
                m.setBonusCounter(m.getBonusCounter()+1);
                // Print nota summary
                nota.createNota();
            }
        }
        // If member not available, print error message
        if (!tidakAda) {
                System.out.println("Member dengan ID " + inputId + " tidak ditemukan!");
        } 
    }

    // Method to create a unique nota id that is different for each nota object
    private static String getIdNote() {
        int randomNumber;
        String uniqueIdNote;
        do {
            randomNumber = random.nextInt(daftarNota.size() + 1);  // Give random number
            uniqueIdNote = String.valueOf(randomNumber);
        } while (daftarNota.containsKey(uniqueIdNote)); 
        return uniqueIdNote;
    }


    // Method to show the daftarNota
    private static void handleListNota() {
        System.out.println("Terdaftar " + daftarNota.size() + " nota dalam sistem.");
        if(daftarNota.size() != 0) {
            // Iterate over all laundry notes in the daftarNota map
            for (String key : daftarNota.keySet()) {
                nota = daftarNota.get(key);
                System.out.println(String.format("- [%s] Status\t: %s", key, nota.getStatus()));
            }
        }
    }

    // Method to show the daftarMember
    private static void handleListUser() {
        System.out.println("Terdaftar " + daftarMember.size() + " member dalam sistem.");
        if(daftarMember.size() != 0) {
            for (Member m : daftarMember) {
                System.out.printf("- %s : %s%n", m.getId(), m.getNama());
            }
        }
    }

    // Method to handle user to get the laundry
    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil: ");
        while (true) {
            // Prompt user for nota id
            String inputIdNota = input.nextLine();
            if (!isNumeric(inputIdNota)|| Integer.parseInt(inputIdNota) < 0) {
                // Input invalid
                System.out.println("ID nota berbentuk angka!");
                continue;
            } else {
                // Check if the given nota id is already in daftarNota
                Boolean notaTerdaftar = false;
                for (String key : daftarNota.keySet()) {
                    if (key.equals(inputIdNota)) {
                        // Nota exist in daftarNota
                        notaTerdaftar = true;
                        nota = daftarNota.get(key);
                        // Check if nota can be taken or not yet
                        if (!nota.getIsReady()) System.out.println("Nota dengan ID " + inputIdNota +" gagal diambil!");
                        else if (nota.getIsReady()) {
                            System.out.println("Nota dengan ID " + inputIdNota + " berhasil diambil!");
                            daftarNota.remove(inputIdNota);  
                        }
                        break;
                    }
                }
                // If nota not available, print error message
                if (!notaTerdaftar) {
                    System.out.println("Nota dengan ID " + inputIdNota + " tidak ditemukan!");
                    break;
                }
            } break;
        }
        
    }

    // Method to handle next day prompt 
    private static void handleNextDay() {
        // Add date
        cal.add(Calendar.DATE, 1);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        // Iterate over all laundry notes in the daftarNota map
        for (HashMap.Entry<String, Nota> entry : daftarNota.entrySet()) {
            String notaId = entry.getKey();
            Nota nota = entry.getValue();
            if (nota.getSisaHariPengerjaan() > 0) {
                // Decrement the remaining days if it's not 0
                nota.setSisaHariPengerjaan(nota.getSisaHariPengerjaan() - 1);
            }
            nota.setIsReady(nota.getSisaHariPengerjaan());
            if (nota.getIsReady()) {
                // Notify user
                System.out.println("Laundry dengan nota ID " + notaId +" sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    // Method to print menu
    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}

/* Special thanks to:   - Lidwina Eurora Firsta Nobella (2206083615) as a collaborator.
                        
References that i used for TP2:
    Y. D. Liang: Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition. Pearson.
    https://www.geeksforgeeks.org/classes-objects-java/amp/
    https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
    https://www.w3schools.com/java/java_encapsulation.asp
*/ 
