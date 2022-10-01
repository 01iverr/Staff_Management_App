package gr.aueb.sweng22.team05.domain;

public class Worker {
    protected long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected long phoneNumber;
    protected String AFM;
    protected String username;
    protected String password;
    protected Department department;

    public Worker(){
    }

    public Worker(long id, String firstName, String lastName, String email, long phoneNumber,
                  String AFM, String username, String password, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.AFM = AFM;
        this.username = username;
        this.password = password;
        this.department = department;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    } 

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAFM() {
        return this.AFM;
    }

    public void setAFM(String AFM) {
        this.AFM = AFM;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean verify(String usern, String passw){
        if (this.username==usern && this.password==passw){
            return true;
        }
        return false;
    }   

    @Override
    public String toString(){
        return firstName+" "+lastName+" ID: "+id +" email: "+email+" Phone Number: "+phoneNumber+" AFM "+AFM+" "+department ;
    }

}
