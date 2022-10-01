package gr.aueb.sweng22.team05.domain;
import java.time.LocalDate;

public class Agreement {
    private long id;
    private LocalDate hireDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private AgreementType agrType;
    private Boolean active;

    public Agreement(){
    }

    public Agreement (long ID, LocalDate h_date, LocalDate s_Date, LocalDate e_Date, AgreementType agreement, Boolean act){
        this.id = ID;
        this.hireDate = h_date;
        this.startDate = s_Date;
        this.endDate = e_Date;
        this.agrType = agreement;
        this.active = act;
    }

    public long getId() { return this.id; } 

    public LocalDate getHireDate() { return this.hireDate; }

    public LocalDate getStartDate() { return this.startDate; }

    public LocalDate getEndDate() { return this.endDate; }

    public AgreementType getAgrType() { return this.agrType; }

    public Boolean getActive() { return this.active; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public void setId(long id) { this.id = id; }

    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public void setAgrType(AgreementType agr_type) { this.agrType = agr_type; }

    public void setActive(Boolean actv) { this.active = actv; }

    @Override
    public String toString() {
        return "Agreement id: " + this.id + ", hired " + this.hireDate + " starts " + this.startDate + " ends " + this.endDate + " and has " + this.agrType;
    }
}
