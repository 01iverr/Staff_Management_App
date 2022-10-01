package gr.aueb.sweng22.team05.domain;

public class Manager extends Worker {

    public Manager(){
        super();
    }

    public Manager(Long id, String name, String lastname, String email,
            long phone, String afm, String username, String password, Department department){
        super(id, name, lastname, email, phone, afm, username, password, department);
    }
}
