package gr.aueb.sweng22.team05.domain;

public class Employee extends Worker {
    public Employee(){
        super();
    }

    public Employee(long id, String firstName, String lastName, String email, long phoneNumber,
                    String AFM, String username, String password, Department department) {
        super(id, firstName,lastName,email,phoneNumber,AFM,username,password,department);
    }

}
