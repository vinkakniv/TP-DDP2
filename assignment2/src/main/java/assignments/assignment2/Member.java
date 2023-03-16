package assignments.assignment2;

import static assignments.assignment1.NotaGenerator.*;


public class Member {
    // Attributes for Member's class
    private int bonusCounter = 0;
    private String nama;
    private String noHp;
    private String id;

    public Member(String nama, String noHp) {
        // Constructor for Member's class
        this.nama = nama;
        this.noHp = noHp;
        this.id = generateId(nama, noHp); // 
    }

    // Getter for member's id
    public String getId(){
        return id;
    }

    // Getter for member's bonus counter
    public int getBonusCounter(){
        return bonusCounter;
    }

    // Getter for member's name
    public String getNama(){
        return nama;
    }

    // Getter for member's mobile number
    public String getNoHp(){
        return noHp;
    }
    
    // Setter for bonus counter
    public void setBonusCounter(int bonusCounter) {
        if (bonusCounter > 3) {
            this.bonusCounter = 0; // if bonus counter == 3, reset to 0
        } else {
            this.bonusCounter = bonusCounter;
        }
    }
    
}
