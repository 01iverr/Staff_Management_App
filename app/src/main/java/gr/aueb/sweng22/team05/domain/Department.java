package gr.aueb.sweng22.team05.domain;

public class Department {
    private long id;
    private String name;

    public Department(){
    }

    public Department(long ID, String depName){
        this.id = ID;
        this.name = depName;
    }

    public long getId() { return this.id; }

    public String getName() { return this.name; }

    public void setId(long newId){ this.id = newId; }

    public void setName(String dep_name) { this.name = dep_name; }

    @Override
    public String toString() {
        return "Department ("+ this.id + "): " + this.name;
    }

}
