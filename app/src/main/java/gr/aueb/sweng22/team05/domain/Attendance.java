package gr.aueb.sweng22.team05.domain;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Arrival/Leave from place of work
 */
public class Attendance {

    private long id;
    private LocalDate date;
    private LocalTime arrival;
    private LocalTime leave;

    public Attendance(){
    }

    public Attendance(Long id, LocalDate date, LocalTime ar, LocalTime le){
        this.id = id;
        this.date = date;
        this.arrival = ar;
        this.leave = le;
    }

    /** Getters and Setters */

    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getArrival() {
        return this.arrival;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    public LocalTime getLeave() {
        return this.leave;
    }

    public void setLeave(LocalTime leave) {
        this.leave = leave;
    }


}
