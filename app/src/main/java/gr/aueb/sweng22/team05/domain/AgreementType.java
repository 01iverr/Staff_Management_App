package gr.aueb.sweng22.team05.domain;

public class AgreementType {
    private long id;
    private String name;
    private float salary;
    private int restDays;
    private int sickDays;
    private typeOfAgr agrType;
    private emplType emType;

    public AgreementType(){
    }

    public AgreementType(long ID, String agrName, float sal, typeOfAgr tOA, emplType eT, int restD, int sickD){
        this.id = ID;
        this.name = agrName;
        this.salary = sal;
        this.agrType = tOA;
        this.emType = eT;
        this.restDays = restD;
        this.sickDays = sickD;
    }
      

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getSalary() {
        return this.salary;
    }

    public int getRestDays() {
        return this.restDays;
    }

    public int getSickDays() {
        return this.sickDays;
    }

    public typeOfAgr getAgrType() {
        return this.agrType;
    }

    public emplType getEmType() {
        return this.emType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }  

    public void setSalary(float salary) {
        this.salary = salary;
    }
   
    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

    public void setSickDays(int sickDays) {
        this.sickDays = sickDays;
    }
   
    public void setAgrType(typeOfAgr agrType) {
        this.agrType = agrType;
    } 

    public void setEmType(emplType emType) {
        this.emType = emType;
    }

    public int getTypehours(){
        if(agrType == typeOfAgr.PARTTIME){
            return 4;
        }
        else{
            return 8;
        }
    }

    @Override
    public String toString() {
        return "Agreement Type ("+ this.id + "): " + this.name + ", salary: " + this.salary+ ", restDays: " + this.restDays +", sickDays: " + this.sickDays + 
            ", agrType: " + this.agrType + ", emType: " + this.emType;
    }
    
}
