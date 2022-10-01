package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ManagerTest {
    Manager manager;

    @Before
    public void setUp() {
        manager = new Manager();
    }

//    @Test(expected = NullPointerException.class)
//    public void handleLeaveNotAccept() {
//        Employee employee = new Employee();
//        Leave leave = new Leave();
//        manager.handleLeave(employee, leave, false);
//        employee.getAcceptedLeaves(0);
//    }
//
//    @Test
//    public void handleLeaveAccept(){
//        Employee employee = new Employee();
//        Leave leave = new Leave(1L, LocalDate.of(2050, 06, 26), LocalDate.of(2050, 06, 30), leaveType.REST);
//        employee.addLeaveRequest(leave);
//
//        Assert.assertEquals(1, employee.getLeaveRequests().size());
//
//        manager.handleLeave(employee, leave, true);
//
//        Assert.assertEquals(0, employee.getLeaveRequests().size());
//        Assert.assertNotNull(employee.getAcceptedLeaves(0));
//    }
//
//    @Test
//    public void calcPay() {
//        Manager emp=new Manager();
//        AgreementType agrtype= new AgreementType();
//        agrtype.setAgrType(typeOfAgr.FULLTIME);
//        agrtype.setSalary(1000f);
//        agrtype.setEmType(emplType.PAIDBYSALARY);
//
//        Agreement agr = new Agreement(12345678L, LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 30), agrtype, true);
//        agr.setAgrType(agrtype);
//
//
//        Attendance att1 = new Attendance(1L, LocalDate.of(2022, 5, 26), LocalTime.of(9,0),LocalTime.of(19,0));
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 27), LocalTime.of(9,0),LocalTime.of(17,0));
//        emp.addAgreement(agr);
//        emp.addAttendance(att1);
//        emp.addAttendance(att2);
//
//
//        Assert.assertEquals(92.5f, emp.calcPay(5,2022), 0.00001);
//    }
}