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

    public SystemCLI getSystem(String id){
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        return null;
    }

    public Member register(String nama, String noHp, String password) {
        String id = NotaGenerator.generateId(nama, noHp);
        if(memberSystem.isMemberExist(id)){
            return null;
        }

        Member member = new Member(nama, id, password);
        memberSystem.addMember(member);
        return member;
    }
}
