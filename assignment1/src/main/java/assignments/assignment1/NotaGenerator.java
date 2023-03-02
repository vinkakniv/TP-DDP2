// NAMA: VINKA ALREZKY AS
// NPM: 2206820200
// KELAS: DDP2-D
// TP-01


package assignments.assignment1;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner inp = new Scanner(System.in);

    // Method main, the main program runs here.
    public static void main(String[] args) {
        String choice;
        do {
            printMenu();  // display menu

            // Get user input for menu choice
            System.out.print("Pilihan : "); 
            choice = inp.nextLine();
            switch (choice) {
                case "0":
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break;
                case "1":
                    System.out.println("================================");

                    // Get user input for name and mobile number
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
        }  while (!choice.equals("0"));  // Program stops executing
        inp.close();
    }


    /**
     * Prompts the user to enter the laundry package and checks if the input is valid.
     * @return the chosen laundry package.
     */
    public static String getLaundryPackage(Scanner inp){
        String chosenPackage;
        while (true) {
            System.out.println("Masukkan paket laundry: ");
            chosenPackage = inp.nextLine();
            String chosenPackageLower = chosenPackage.toLowerCase();
            if (chosenPackage.equals("?")) {
                showPaket();
                continue;
            } else if (!(chosenPackageLower.equals("reguler") || chosenPackageLower.equals("fast") || chosenPackageLower.equals("express"))) {
                System.out.println("Paket " + chosenPackage + " tidak diketahui");
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                continue;
            } break;
        } return chosenPackage;
    }


    /**
     * Prompts the user to enter the laundry weight and checks if the input is valid.
     * @return the laundry weight in kg.
     */
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
            } else if ((inputNumValidation(weightString)) && (weight >= 2)) break;
        } return weight;
    }


    /**
     * Prompts the user to enter the mobile number and checks if the input is valid.
     * @return the string of mobile number.
     */
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


    // Checks if a given input is a positive number.
    public static boolean inputNumValidation(String input) {
        try {
            double value = Double.parseDouble(input);
            return value > 0; // input is valid
        } catch (NumberFormatException e) {
            return false; // input is not valid
        }
    }

    
    // Method to display menu in NotaGenerator.
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    
    // Method to display packages.
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

   /**
     * Method for creating an ID from name and phone number.
     *
     * @return String ID in the format of [FIRSTNAME]-[phoneNumber]-[2digitChecksum].
     */
    public static String generateId(String personName, String phoneNumber) {
        // Get the first name from the input name string and convert it to uppercase.
        String firstName = personName.split("\\s+")[0].toUpperCase();

        // Concatenate the first name and phone number to form the initial ID string.
        String idString = firstName + "-" + phoneNumber;

        // Define the alphabet for calculating the checksum and initialize the total checksum to zero.
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int totalChecksum = 0;

        // Loop through each character in the ID string and calculate the checksum.
        for (int i = 0; i < idString.length(); i++) {
            char c = idString.charAt(i);

            // If the character is a digit, add its value to the total checksum.
            if (Character.isDigit(c)) {
                int digitValue = Character.getNumericValue(c);
                totalChecksum += digitValue;

            // If the character is a letter, add its value (based on its position in the alphabet) to the total checksum.
            } else if (Character.isLetter(c)) {
                int charValue = alphabet.indexOf(Character.toUpperCase(c)) + 1;
                totalChecksum += charValue;

            // If the character is not a digit or a letter, add 7 to the total checksum.
            } else {
                totalChecksum += 7;
            }
        }

        // Concatenate the total checksum (formatted to 2 digits) to the ID string and return the final ID.
        return idString + "-" + String.format("%02d", totalChecksum);
    }


    /**
     * Method for creating memo.
     * @return string memo with the following format:
     *         <p>ID    : [id]
     *         <p>Paket : [thePackage]
     *         <p>Harga :
     *         <p>[theWeight] kg x [price] = [totalPrice]
     *         <p>Tanggal Terima  : [receiptDate]
     *         <p>Tanggal Selesai : [receiptDate + duration]
     */
    public static String generateNota(String id, String thePackage, int theWeight, String receiptDate){
        LocalDate receiptDateParsed = LocalDate.parse(receiptDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int price = 0;
        int duration = 0;
        if (thePackage.toLowerCase().equals("reguler")) {
            price = 7000;
            duration = 3;
        } else if (thePackage.toLowerCase().equals("fast")) {
            price = 10000;
            duration = 2;
        } else if (thePackage.toLowerCase().equals("express")) {
            price = 12000;
            duration = 1;
        }
        int totalPrice = theWeight * price;
        LocalDate dateOfCompletion = receiptDateParsed.plusDays(duration);
        String dateOfCompletionFormatted = dateOfCompletion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String memo = String.format("ID    : %s\nPaket : %s\nHarga :\n%d kg x %d = %d\nTanggal Terima  : %s\nTanggal Selesai : %s", 
        id, thePackage, theWeight, price, totalPrice, receiptDate, dateOfCompletionFormatted);
        return memo;
    }
}
