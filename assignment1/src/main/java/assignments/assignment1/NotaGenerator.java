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
                    // Prompt user for name 
                    System.out.println("Masukkan nama Anda: ");
                    String name1 = inp.nextLine();
                    // Validate and get user's mobile number
                    String mobileNumber1 = getMobileNumber(inp);
                    // Generate an ID based on user's name and mobile number
                    String id1 = generateId(name1, mobileNumber1);
                    System.out.println("ID Anda : " + id1);
                    break; // Return to the main menu
                case "2":
                    System.out.println("================================");
                    // Prompt user for name 
                    System.out.println("Masukkan nama Anda:");
                    String name2 = inp.nextLine();
                    // Validate and get user's mobile number
                    String mobileNumber2 = getMobileNumber(inp); 
                    // Generate an ID based on user's name and mobile number
                    String id2 = generateId(name2, mobileNumber2); 
                    // Prompt user for date of receipt
                    System.out.println("Masukkan tanggal terima: ");
                    String dateOfReceipt = inp.nextLine();
                    // Validate and get user's selected package & laundry weight
                    String laundryPackage = getLaundryPackage(inp); 
                    System.out.println("Masukkan berat cucian Anda [Kg]: ");
                    int laundryWeight = getLaundryWeight(inp); 
                    // Generate a note based on user's id, selected package, laundry weight & date of receipt
                    String note = generateNota(id2, laundryPackage, laundryWeight, dateOfReceipt); 
                    System.out.println(note); 
                    break; // Return to the main menu
                default:
                    /*  If inputs other than 0, 1, or 2 are entered, 
                    the program will print a message and return to the main menu */
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                    break; 
                }
        }  while (!choice.equals("0"));  // Program stops executing.
        inp.close();
    }


    // Method to display menu in NotaGenerator.
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }


    // Checks if a given input is a positive number.
    public static boolean inputNumValidation(String input) {
        try {
            double value = Double.parseDouble(input);
            return value > 0; // input is valid.
        } catch (NumberFormatException e) {
            return false; // input is not valid
        }
    }


    /**
     * Prompts the user to enter the mobile number and checks if the input is valid.
     * @return the string of mobile number.
     */
    public static String getMobileNumber(Scanner inp) {
        String mobileNumber;  // Declare mobile number variable in string.
        while (true) {
            System.out.println("Masukkan nomor handphone Anda: "); // Print prompt massage.
            mobileNumber = inp.nextLine(); 
            if (!inputNumValidation(mobileNumber)) {
                System.out.println("Nomor hp hanya menerima digit");
                continue; // input is not valid, continue loop.
            } break;
        } return mobileNumber;
    }


    /**
     * Prompts the user to enter the laundry package and checks if the input is valid.
     * @return the chosen laundry package.
     */
    public static String getLaundryPackage(Scanner inp){
        String chosenPackage; // Declare package variable in string.
        while (true) {
            System.out.println("Masukkan paket laundry: "); // Print prompt massage.
            chosenPackage = inp.nextLine();
            String chosenPackageLower = chosenPackage.toLowerCase(); // Convert to lower case.
            if (chosenPackage.equals("?")) {
                showPaket(); // Show list of package if the input is "?".
                continue; 
            } else if (!(chosenPackageLower.equals("reguler") || chosenPackageLower.equals("fast") || chosenPackageLower.equals("express"))) {
                System.out.println("Paket " + chosenPackage + " tidak diketahui");
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                continue; // Input is not valid, continue loop.
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
                continue; // Input is not valid, continue loop .
            } else if ((inputNumValidation(weightString)) && (weight < 2)) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                weight = 2; // If the laundry weight less than 2 kg, it will rounded up to 2 kg.
            } else if ((inputNumValidation(weightString)) && (weight >= 2)) break; // input valid.
        } return weight;
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
        // Convert receipt date to LocalDate and use format "dd/MM/yyyy"
        LocalDate receiptDateParsed = LocalDate.parse(receiptDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // Declare price and duration as integer
        int price = 0;
        int duration = 0;
        if (thePackage.toLowerCase().equals("reguler")) {
            // If the chosen package is reguler, set price = 7000 and duration = 3 days
            price = 7000;
            duration = 3;
        } else if (thePackage.toLowerCase().equals("fast")) {
            // If the chosen package is fast, set price = 10000 and duration = 2 days
            price = 10000;
            duration = 2;
        } else if (thePackage.toLowerCase().equals("express")) {
            // If the chosen package is express, set price = 12000 and duration = 1 day
            price = 12000;
            duration = 1;
        }
        // Calculate total price 
        int totalPrice = theWeight * price;
        // Get the date of completion using Localdate plus days
        LocalDate dateOfCompletion = receiptDateParsed.plusDays(duration);
        String dateOfCompletionFormatted = dateOfCompletion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // Use string formatting, to get the memo with correct format.
        String memo = String.format("ID    : %s\nPaket : %s\nHarga :\n%d kg x %d = %d\nTanggal Terima  : %s\nTanggal Selesai : %s", 
        id, thePackage, theWeight, price, totalPrice, receiptDate, dateOfCompletionFormatted);
        return memo; 
    }
}

/* Special thanks to:   - Lidwina Eurora Firsta Nobella (2206083615) as a collaborator.
                        - Juan Maxwell Tanaya (2206820352) to help me solve the error.
                        
References that i used for TP2:
    Y. D. Liang: Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition. Pearson.
    https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
    https://www.geeksforgeeks.org/localdate-plusdays-method-in-java-with-examples/
    https://stackoverflow.com/questions/28177370/how-to-format-localdate-to-string

*/ 