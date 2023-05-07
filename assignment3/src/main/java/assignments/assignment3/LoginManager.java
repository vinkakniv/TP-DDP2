package assignments.assignment3;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping from to the appropriate SystemCLI.
     *
     * @param id -> ID of the user who will use SystemCLI.
     * @return SystemCLI object corresponding to ID, null if ID not found.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        return null;
    }

    /**
     * Register a new member with the information provided.
     *
     * @param nama -> Member name.
     * @param noHp -> Members mobile number.
     * @param password -> Member account password.
     * @return Member object that was successfully registered, returns null if it failed to register.
     */
    public Member register(String nama, String noHp, String password) {
        String newId = NotaGenerator.generateId(nama, noHp);
        if (!memberSystem.isMemberExist(newId)) {
            Member newMember = new Member(nama, newId, password);
            memberSystem.addMember(newMember);
            return newMember;
        }
        return null;
    }
}