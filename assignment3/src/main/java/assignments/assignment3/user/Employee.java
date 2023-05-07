package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    
    // Constructor of Employee class.
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
        employeeCount ++;
    }

    /**
     * Create employee ID from employee name with format
     * FIRST_NAME-[number of employees, starting from 0]
     * EXAMPLE: Dek Depe is the first employee created, so the ID is DEK-0.
     *
     * @param nama -> Full name of the employee.
     */
    private static String generateId(String nama) {
        String id = (nama.split(" ")[0] + "-" + employeeCount).toUpperCase();
        return id;
    }
}
