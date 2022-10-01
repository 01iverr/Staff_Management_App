package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceTest {
    Attendance attendance;

    @Before
    public void setUp() {
        attendance = new Attendance();
    }

    @Test
    public void getIdEmpty() {
        Assert.assertEquals(0L, attendance.getId());
    }

    @Test
    public void getDateEmpty() {
        Assert.assertNull(attendance.getDate());
    }

    @Test
    public void getArrivalEmpty() {
        Assert.assertNull(attendance.getArrival());
    }

    @Test
    public void getLeaveEmpty() {
        Assert.assertNull(attendance.getLeave());
    }

    @Test
    public void setId() {
        attendance.setId(5L);
        Assert.assertEquals(5L, attendance.getId());
    }

    @Test
    public void setDate() {
        LocalDate today = LocalDate.of(2020, 6, 22);
        attendance.setDate(today);
        Assert.assertEquals(today, attendance.getDate());
    }

    @Test
    public void setArrival() {
        LocalTime time = LocalTime.of(9,54);
        attendance.setArrival(time);
        Assert.assertEquals(time, attendance.getArrival());
    }

    @Test
    public void setLeave() {
        LocalTime time = LocalTime.of(17,2);
        attendance.setLeave(time);
        Assert.assertEquals(time, attendance.getLeave());
    }
}