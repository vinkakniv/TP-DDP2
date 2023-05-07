package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Constructs a new EmployeeSystem object and registers Employees in CuciCuci.
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     *  Process the employee's choice in the system according to the specific menu.
     *
     * @param choice -> the user's choice.
     * @return true if user logout.
     */
    @Override
    protected boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                // Loop through each nota in the notaList for employees doing the laundry
                System.out.println("Stand back! " + loginMember.getNama() + " beginning to nyuci!");
                for (Nota nota : notaList) {
                    System.out.println(nota.kerjakan());
                }
                System.out.println(" ");
                break;
            case 2:
                // Loop through each nota in the notaList and display its status
                for (Nota nota : notaList) {
                    System.out.println(nota.getNotaStatus());
                }
                System.out.println(" ");
                break;
            case 3:
                return true;
        }
        return false;
    }

    /**
     * Displays specific menu for Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}