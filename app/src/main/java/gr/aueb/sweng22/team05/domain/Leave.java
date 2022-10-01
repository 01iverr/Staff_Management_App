package gr.aueb.sweng22.team05.domain;
import java.time.LocalDate;

public class Leave {
    private long id;
    private LocalDate start;
    private LocalDate end;
    private leaveType type;

    public Leave(){
    }

    public Leave(Long id, LocalDate start, LocalDate end, leaveType type) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public leaveType getType() {
        return this.type;
    }

    public void setType(leaveType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Leave ("+ this.type + "): from " + this.start + " to " + this.end;
    }

}
