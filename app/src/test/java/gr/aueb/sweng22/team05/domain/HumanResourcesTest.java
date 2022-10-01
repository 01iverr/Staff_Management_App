//package gr.aueb.sweng22.team05.domain;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//public class HumanResourcesTest {
//    HumanResources hr;
//
//    @Before
//    public void setUp() throws Exception {
//        hr = new HumanResources();
//    }
//
//    @Test
//    public void createWorkerManager() {
//        hr.createWorker("manager","John","Green","jgreen@gmail.com",12345,"123456",new Department(123l,"a"),new Agreement());
//        Assert.assertNotEquals(0, hr.getWorkerList().size());
//        Assert.assertEquals(Manager.class, hr.getWorkerList().get(0).getClass());
//    }
//
//    @Test
//    public void createWorkerEmployee() {
//        hr.createWorker("employee","John","Green","jgreen@gmail.com",12345,"123456",new Department(123l,"a"),new Agreement());
//        Assert.assertNotEquals(0, hr.getWorkerList().size());
//        Assert.assertEquals(Employee.class, hr.getWorkerList().get(0).getClass());
//    }
//
//    @Test
//    public void editWorkerName() {
//        hr.createWorker("manager","John","Green","jgreen@gmail.com",12345,"123456",new Department(123l,"a"),new Agreement());
//        hr.editWorker("123456","name", "Dave");
//        Assert.assertEquals("Dave", hr.getWorkerList().get(0).getFirstName());
//    }
//
//    @Test
//    public void addDeleteWorker() {
//        hr.createWorker("employee","John","Green","jgreen@gmail.com",12345,"123456",new Department(123l,"a"),new Agreement());
//        hr.deleteWorker("123456");
//        Assert.assertEquals(0, hr.getWorkerList().size());
//    }
//
//    @Test
//    public void createDepartment() {
//        hr.createDepartment(10,"new_dep");
//        Assert.assertEquals(1, hr.departmentList.size());
//    }
//
//    @Test
//    public void deleteDepartment() {
//        hr.createDepartment(10,"new_dep");
//        hr.createDepartment(20,"new_dep2");
//        hr.createDepartment(30,"new_dep3");
//        hr.deleteDepartment(20);
//        Assert.assertEquals(2,hr.departmentList.size());
//    }
//
//    @Test
//    public void createAgreement() {
//        LocalDate today= LocalDate.of(2017, 1, 13);   ;
//        LocalDate yesterday = today.minusDays(1);
//        LocalDate tomorrow = yesterday.plusDays(2);
//        AgreementType agrT = new AgreementType();
//        Agreement testAgr = hr.createAgreement(10, yesterday, today, tomorrow, agrT, true);
//        Assert.assertNotNull(testAgr);
//    }
//
//    @Test
//    public void createAgreementType() {
//        LocalDate today= LocalDate.of(2017, 1, 13);   ;
//        LocalDate yesterday = today.minusDays(1);
//        LocalDate tomorrow = yesterday.plusDays(2);
//        AgreementType testAgrT = hr.createAgreementType(1L, "newAgr",48.5f, typeOfAgr.PARTTIME, emplType.PAIDBYSALARY, 20, 10);
//        Assert.assertNotNull(testAgrT);
//    }
//
//    @Test
//    public void calculatePay() {
//        Assert.assertEquals(0, hr.calculatePay(5,2022).size());
//
//        AgreementType agrtype= new AgreementType();
//        agrtype.setAgrType(typeOfAgr.FULLTIME);
//        agrtype.setSalary(1000f);
//        agrtype.setEmType(emplType.PAIDBYSALARY);
//
//        Agreement agr = new Agreement(12345678L, LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 25), LocalDate.of(2022, 5, 30), agrtype, true);
//        agr.setAgrType(agrtype);
//
//        hr.createWorker("employee", "John", "Smith", "jsmith@gmail.com", 123456789, "123456", new Department(123l,"a"), agr);
//
//        Attendance att1 = new Attendance(1L, LocalDate.of(2022, 5, 26), LocalTime.of(9,0),LocalTime.of(19,0));
//        Attendance att2 = new Attendance(2L, LocalDate.of(2022, 5, 27), LocalTime.of(9,0),LocalTime.of(17,0));
//        Leave leave1 = new Leave(1L, LocalDate.of(2022, 5, 28),LocalDate.of(2022, 5, 28), leaveType.SICK);
//        Leave leave2 = new Leave(2L, LocalDate.of(2022, 5, 29),LocalDate.of(2022, 5, 29), leaveType.REST);
//
//        hr.editWorker("123456", "attendance", att1);
//        hr.editWorker("123456", "attendance", att2);
//
//        ((Employee)hr.getWorkerList().get(0)).addAcceptedLeaves(leave1);
//        ((Employee)hr.getWorkerList().get(0)).addAcceptedLeaves(leave2);
//
//        Assert.assertEquals(1, hr.calculatePay(5,2022).size());
//    }
//}