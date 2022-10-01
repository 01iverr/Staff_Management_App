package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

public class LeaveTest {
    Leave leave;

    @Before
    public void setUp() {
        leave = new Leave();
    }

    @Test
    public void getIdEmpty() {
        Assert.assertEquals(0L, leave.getId());
    }

    @Test
    public void setId() {
        leave.setId(5L);
        Assert.assertEquals(5L, leave.getId());
    }

    @Test
    public void getStartEmpty() {
        Assert.assertNull(leave.getStart());
    }

    @Test
    public void setStart() {
        LocalDate today = LocalDate.of(2021, 8, 2);
        leave.setStart(today);
        Assert.assertEquals(today, leave.getStart());
    }

    @Test
    public void getEndEmpty() {
        Assert.assertNull(leave.getEnd());
    }

    @Test
    public void setEnd() {
        LocalDate today = LocalDate.of(2021, 9, 15);
        leave.setEnd(today);
        Assert.assertEquals(today, leave.getEnd());
    }

    @Test
    public void getTypeEmpty() {
        Assert.assertNull(leave.getType());
    }

    @Test
    public void setType() {
        leave.setType(leaveType.REST);
        Assert.assertEquals(leaveType.REST, leave.getType());
    }

    @Test
    public void testToString() {
        LocalDate begin = LocalDate.of(2021, 8, 5);
        LocalDate end = LocalDate.of(2021, 8, 10);
        leave.setStart(begin);
        leave.setEnd(end);
        leave.setType(leaveType.SICK);
        Assert.assertEquals("Leave (SICK): from 2021-08-05 to 2021-08-10", leave.toString());
    }
}