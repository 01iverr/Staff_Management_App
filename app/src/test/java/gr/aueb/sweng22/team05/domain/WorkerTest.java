package gr.aueb.sweng22.team05.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkerTest {
    Worker worker;

    @Before
    public void setUp() throws Exception {
        worker = new Worker();
    }

//    @Test
//    public void addAttendance() {
//        Assert.assertEquals(0, worker.getCalendar().size());
//        Attendance at = new Attendance();
//        worker.addAttendance(at);
//        Assert.assertNotEquals(0, worker.getCalendar().size());
//    }
//
//    @Test
//    public void addRemoveAttendance() {
//        Attendance at = new Attendance();
//        worker.addAttendance(at);
//        worker.removeAttendance(at);
//        Assert.assertEquals(0, worker.getCalendar().size());
//    }
//
//    @Test
//    public void addAgreement() {
//        Agreement agr = new Agreement();
//        worker.addAgreement(agr);
//        Assert.assertEquals(agr, worker.getLastAgreement());
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void getLastAgreementEmpty() {
//        worker.getLastAgreement();
//    }
//
//    @Test
//    public void getAgreement2() {
//        Agreement agr1 = new Agreement();
//        worker.addAgreement(agr1);
//        Agreement agr2 = new Agreement();
//        worker.addAgreement(agr2);
//        Agreement agr3 = new Agreement();
//        worker.addAgreement(agr3);
//        Assert.assertEquals(agr2, worker.getAgreement(2));
//    }

    @Test
    public void getIdEmpty() {
        Assert.assertEquals(0L, worker.getId());
    }

    @Test
    public void setId() {
        worker.setId(19L);
        Assert.assertEquals(19L, worker.getId());
    }

    @Test
    public void getFirstNameEmpty() {
        Assert.assertNull(worker.getFirstName());
    }

    @Test
    public void setFirstName() {
        worker.setFirstName("Stacy");
        Assert.assertEquals("Stacy", worker.getFirstName());
    }

    @Test
    public void getLastNameEmpty() {
        Assert.assertNull(worker.getLastName());
    }

    @Test
    public void setLastName() {
        worker.setLastName("Brook");
        Assert.assertEquals("Brook", worker.getLastName());
    }

    @Test
    public void getEmailEmpty() {
        Assert.assertNull(worker.getEmail());
    }

    @Test
    public void setEmail() {
        worker.setEmail("random@gmail.com");
        Assert.assertEquals("random@gmail.com",worker.getEmail());
    }

    @Test
    public void getPhoneNumberEmpty() {
        Assert.assertEquals(0,worker.getPhoneNumber());
    }

    @Test
    public void setPhoneNumber() {
        worker.setPhoneNumber(1234567891);
        Assert.assertEquals(1234567891,worker.getPhoneNumber());
    }

    @Test
    public void getAFMEmpty() {
        Assert.assertNull(worker.getAFM());
    }

    @Test
    public void setAFM() {
        worker.setAFM("123456");
        Assert.assertEquals("123456",worker.getAFM());
    }

    @Test
    public void getUsernameEmpty() {
        Assert.assertNull(worker.getUsername());
    }

    @Test
    public void setUsername() {
        worker.setUsername("u123456789");
        Assert.assertEquals("u123456789",worker.getUsername());
    }

    @Test
    public void getPasswordEmpty() {
        Assert.assertNull(worker.getPassword());
    }

    @Test
    public void setPassword() {
        worker.setPassword("44444");
        Assert.assertEquals("44444",worker.getPassword());
    }

    @Test
    public void getDeparmentEmpty() {
        Assert.assertNull(worker.getDepartment());
    }

    @Test
    public void setDeparment() {
        Department dep = new Department();
        worker.setDepartment(dep);
        Assert.assertEquals(dep,worker.getDepartment());
    }

//    @Test
//    public void checkDate() {
//        Assert.assertFalse(worker.checkDate(2001,10,4));
//    }

    @Test
    public void verify() {
        worker.setUsername("aaa");
        worker.setPassword("123455");
        Assert.assertTrue(worker.verify("aaa", "123455"));
    }

    @Test
    public void testToString() {
        Agreement agr=new Agreement();
        Department dep = new Department(1L, "Delivery");
        Worker work=new  Worker(123L, "Lakhs","Mastromanwlis", "takhs@gmail.com", 6934567890L, "123456789", "usernamee", "password123", dep);
        Assert.assertEquals("Lakhs Mastromanwlis ID: 123 email: takhs@gmail.com Phone Number: 6934567890 AFM 123456789 Department (1): Delivery",work.toString());
    }

}