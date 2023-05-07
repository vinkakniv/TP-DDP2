package assignments.assignment3.user.menu;

import assignments.assignment3.user.Member;
import java.util.Scanner;

public abstract class SystemCLI {
    protected Member[] memberList = new Member[0];
    protected Member loginMember;
    protected Scanner in;

    /**
     * Authenticate the user with the given ID and password and start the user session.
     * Will stop if logout or ID / Password is incorrect.
     *
     * @param in -> Scanner object to read input.
     * @param inputId -> User ID to be authenticated.
     * @param inputPassword -> User password to be authenticated.
     */
    public void login(Scanner in, String inputId, String inputPassword){
        Member authMember = authUser(inputId, inputPassword);
        if (authMember != null) {
            this.in = in;
            System.out.println("Login successful!\n");
            run(in, authMember);
            return;
        }
        System.out.println("Invalid ID or password.\n");
    };

    /**
     * Starts a user session and handles input.
     *
     * @param in -> Scanner object to read input.
     * @param member -> Member objects that use the system.
     */
    public void run(Scanner in, Member member){
        loginMember = member;
        boolean logout = false;
        while (!logout) {
            displayMenu();
            int choice = in.nextInt();
            in.nextLine();
            logout = processChoice(choice);
        }
        loginMember = null;
        System.out.println("Logging out...\n");
    }

    /**
     * Checks all users with the given ID and password.
     *
     * @param id -> User ID to be authenticated.
     * @param pass -> User password to be authenticated.
     * @return  Authenticated member object, null if authentication failed.
     */
    public Member authUser(String id, String pass) {
        for (Member user : memberList) {
            if (!user.getId().equals(id)) {
                continue;
            }
            if(user.login(id, pass)){
                return user;
            }
            return null;
        }
        return null;
    };

    /**
     * Checks if there is a Member with the given ID.
     *
     * @param id -> ID to check.
     * @return true if there is a member with the given ID, false otherwise.
     */
    public boolean isMemberExist(String id){
        for (Member member:
                memberList) {
            if(member.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * Displays the main menu for users using the system.
     */
    protected void displayMenu(){
        System.out.printf("Login as : %s\nSelamat datang %s!\n\n", loginMember.getId(), loginMember.getNama());
        displaySpecificMenu();
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }

    /**
     * Process choices from users who use the system according to their roles.
     *
     * @param choice -> user's choice.
     * @return true if user log.
     */
    protected abstract boolean processChoice(int choice);

    /**
     * Displays specific menu according to the class that inherited it.
     */
    protected abstract void displaySpecificMenu();
}