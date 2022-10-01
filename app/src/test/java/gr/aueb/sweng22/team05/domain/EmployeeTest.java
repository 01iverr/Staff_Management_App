package gr.aueb.sweng22.team05.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeeTest {

//    @Test
//    public void addAcceptedLeaves() {
//        Employee emp = new Employee();
//        Leave l = new Leave();
//        emp.addAcceptedLeaves(l);
//        Assert.assertNotNull(emp.getAcceptedLeaves(0));
//    }
//
//    @Test
//    public void getAcceptedLeaves() {
//        Employee emp = new Employee();
//        Leave l = new Leave();
//        emp.addAcceptedLeaves(l);
//        Assert.assertEquals(l, emp.getAcceptedLeaves(0));
//    }
//
//    @Test
//    public void removeLeaveRequests() {
//        Employee emp = new Employee();
//        Leave l = new Leave();
//        emp.addLeaveRequest(l);
//        Assert.assertTrue(emp.removeLeaveRequests(l));
//    }
//
//    @Test
//    public void leaveRequest() {
//        Employee emp = new Employee();
//        AgreementType agrtype= new AgreementType();
//        agrtype.setAgrType(typeOfAgr.FULLTIME);
//        agrtype.setRestDays(50);
//        Agreement agr = new Agreement();
//        agr.setActive(true);
//        agr.setAgrType(agrtype);
//        LocalDate st = LocalDate.of(2022, 4, 2);
//        LocalDate end = LocalDate.of(2022, 4, 2);
//        Leave l = new Leave(10L, st, end, leaveType.REST);
//        emp.addAcceptedLeaves(l);
//        emp.addAgreement(agr);
//        Assert.assertTrue(emp.leaveRequest(10, 5, 2022, 12, 5, 2022, leaveType.REST));
//    }
//
//    @Test
//    public void hasRemainingDays() {
//        Employee emp = new Employee();
//        AgreementType agrtype= new AgreementType();
//        agrtype.setAgrType(typeOfAgr.FULLTIME);
//        agrtype.setRestDays(50);
//        Agreement agr = new Agreement();
//        agr.setActive(true);
//        agr.setAgrType(agrtype);
//        LocalDate st = LocalDate.of(2022, 4, 2);
//        LocalDate end = LocalDate.of(2022, 4, 2);
//        Leave l = new Leave(10L, st, end, leaveType.REST);
//        emp.addAcceptedLeaves(l);
//        emp.addAgreement(agr);
//        Assert.assertTrue(emp.hasRemainingDays(2022, leaveType.REST));
//    }
//
//    @Test
//    public void daysOfLeave() {
//        Employee emp = new Employee();
//        LocalDate st = LocalDate.of(2022, 4, 2);
//        LocalDate end = LocalDate.of(2022, 4, 12);
//        Leave l = new Leave(10L, st, end, leaveType.REST);
//        emp.addAcceptedLeaves(l);
//        Assert.assertEquals(11, emp.daysOfLeave(0));
//    }
//
//    @Test
//       public void calcPay() {
//        Employee emp=new Employee();
//        AgreementType agrtype= new AgreementType();
//       agrtype.setAgrType(typeOfAgr.PARTTIME);
//       agrtype.setSalary(1000f);
//       agrtype.setEmType(emplType.PAIDBYSALARY);
//
//       Agreement agr = new Agreement(12345678L, LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 30), agrtype, true);
//       agr.setAgrType(agrtype);
//
//
//        Attendance att1 = new Attendance(1L, LocalDate.of(2022, 5, 26), LocalTime.of(9,0),LocalTime.of(13,0));
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 27), LocalTime.of(9,0),LocalTime.of(13,0));
//        Leave leave1 = new Leave(1L, LocalDate.of(2022, 5, 28),LocalDate.of(2022, 5, 28), leaveType.SICK);
//        Leave leave2 = new Leave(2L, LocalDate.of(2022, 5, 29),LocalDate.of(2022, 5, 29), leaveType.REST);
//
//        emp.addAgreement(agr);
//        emp.addAttendance(att1);
//        emp.addAttendance(att2);
//        emp.addAcceptedLeaves(leave1);
//        emp.addAcceptedLeaves(leave2);
//
//        Assert.assertEquals(100f, emp.calcPay(5,2022), 0.00001);
//
//    }
//
//
//    @Test
//    public void calcPay2() {
//        Employee emp=new Employee();
//        AgreementType agrtype= new AgreementType();
//        agrtype.setAgrType(typeOfAgr.PARTTIME);
//        agrtype.setSalary(10f);
//        agrtype.setEmType(emplType.PAIDBYWAGE);
//
//        Agreement agr = new Agreement(12345678L, LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 30), agrtype, true);
//        agr.setAgrType(agrtype);
//
//
//        Attendance att1 = new Attendance(1L, LocalDate.of(2022, 5, 26), LocalTime.of(9,0),LocalTime.of(13,0));
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 27), LocalTime.of(9,0),LocalTime.of(13,0));
//        Leave leave1 = new Leave(1L, LocalDate.of(2022, 5, 28),LocalDate.of(2022, 5, 28), leaveType.SICK);
//        Leave leave2 = new Leave(2L, LocalDate.of(2022, 5, 29),LocalDate.of(2022, 5, 31), leaveType.REST);
//
//        emp.addAgreement(agr);
//        emp.addAttendance(att1);
//        emp.addAttendance(att2);
//        emp.addAcceptedLeaves(leave1);
//        emp.addAcceptedLeaves(leave2);
//
//        Assert.assertEquals(220f, emp.calcPay(5,2022), 0.00001);
//
//    }
//
//
//    @Test
//    public void calcPay3() {
//        Employee emp=new Employee();
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
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 27), LocalTime.of(9,0),LocalTime.of(19,0));
//        Leave leave1 = new Leave(1L, LocalDate.of(2022, 5, 28),LocalDate.of(2022, 5, 28), leaveType.SICK);
//        Leave leave2 = new Leave(2L, LocalDate.of(2022, 5, 29),LocalDate.of(2022, 5, 29), leaveType.REST);
//
//        emp.addAgreement(agr);
//        emp.addAttendance(att1);
//        emp.addAttendance(att2);
//        emp.addAcceptedLeaves(leave1);
//        emp.addAcceptedLeaves(leave2);
//
//        Assert.assertEquals(125f, emp.calcPay(5,2022), 0.00001);
//
//    }
//
//    @Test
//    public void calcPay4() {
//        Employee emp=new Employee();
//        AgreementType agrtype= new AgreementType();
//        agrtype.setAgrType(typeOfAgr.PARTTIME);
//        agrtype.setSalary(10f);
//        agrtype.setEmType(emplType.PAIDBYWAGE);
//
//        Agreement agr = new Agreement(12345678L, LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 30), agrtype, true);
//        agr.setAgrType(agrtype);
//
//
//        Attendance att1 = new Attendance(1L, LocalDate.of(2022, 5, 26), LocalTime.of(9,0),LocalTime.of(17,0));
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 27), LocalTime.of(9,0),LocalTime.of(13,0));
//        Leave leave1 = new Leave(1L, LocalDate.of(2022, 5, 28),LocalDate.of(2022, 5, 28), leaveType.SICK);
//        Leave leave2 = new Leave(2L, LocalDate.of(2022, 5, 29),LocalDate.of(2022, 5, 31), leaveType.REST);
//
//        emp.addAgreement(agr);
//        emp.addAttendance(att1);
//        emp.addAttendance(att2);
//        emp.addAcceptedLeaves(leave1);
//        emp.addAcceptedLeaves(leave2);
//
//        Assert.assertEquals(270f, emp.calcPay(5,2022), 0.00001);
//
//    }
//
//    @Test
//    public void calcPay5() {
//        Employee emp = new Employee();
//        AgreementType agrtype = new AgreementType();
//        agrtype.setAgrType(typeOfAgr.PARTTIME);
//        agrtype.setSalary(1000f);
//        agrtype.setEmType(emplType.PAIDBYSALARY);
//
//        Agreement agr = new Agreement(12345678L, LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 01), LocalDate.of(2022, 5, 06), agrtype, true);
//        agr.setAgrType(agrtype);
//
//
//        Attendance att1 = new Attendance(1L, LocalDate.of(2022, 5, 1), LocalTime.of(9, 0), LocalTime.of(13, 0));
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 2), LocalTime.of(9, 0), LocalTime.of(13, 0));
//        Leave leave1 = new Leave(1L, LocalDate.of(2022, 5, 3), LocalDate.of(2022, 5, 3), leaveType.SICK);
//        Leave leave2 = new Leave(2L, LocalDate.of(2022, 5, 4), LocalDate.of(2022, 5, 4), leaveType.REST);
//
//        emp.addAgreement(agr);
//        emp.addAttendance(att1);
//        emp.addAttendance(att2);
//        emp.addAcceptedLeaves(leave1);
//        emp.addAcceptedLeaves(leave2);
//
//        Assert.assertEquals(100f, emp.calcPay(5, 2022), 0.00001);
//    }
}