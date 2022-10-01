package gr.aueb.sweng22.team05.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.domain.Attendance;
import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Employee;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Manager;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.domain.emplType;
import gr.aueb.sweng22.team05.domain.leaveType;
import gr.aueb.sweng22.team05.domain.typeOfAgr;
import gr.aueb.sweng22.team05.memorydao.AgreementTypeDAOMemory;

public abstract class Initializer {
    /**
     * Deletes all saved data
     */
    protected abstract void eraseData();

    /**
     * Adds new data
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void prepareData() {

        eraseData();

        // Departments =====================
        Department department1 = new Department(1L, "Business");
        Department department2 = new Department(2L, "HR");
        Department department3 = new Department(3L, "IT");

        // Agreement Types =================
        AgreementTypeDAO agreementTypeDAO = getAgreementTypeDAO();

        AgreementType agrType1 = new AgreementType(1L, "Agreement Type 1", 1000f, typeOfAgr.FULLTIME,
                emplType.PAIDBYSALARY, 5, 7);
        AgreementType agrType2 = new AgreementType(2L, "Agreement Type 2", 900f, typeOfAgr.FULLTIME,
                emplType.PAIDBYWAGE, 4, 8);
        AgreementType agrType3 = new AgreementType(3L, "Agreement Type 3", 1200f, typeOfAgr.PARTTIME,
                emplType.PAIDBYSALARY, 6, 10);
        AgreementType agrType4 = new AgreementType(4L, "Agreement Type 4", 2100f, typeOfAgr.PARTTIME,
                emplType.PAIDBYWAGE, 15, 10);

        agreementTypeDAO.save(agrType2);
        agreementTypeDAO.save(agrType3);
        agreementTypeDAO.save(agrType1);
        agreementTypeDAO.save(agrType4);

        // Agreements ======================
        AgreementDAO agreementDAO = getAgreementDAO();

        Agreement agreement1 = new Agreement(1L, LocalDate.of(2020, 1, 22),
                LocalDate.of(2020, 2, 1), LocalDate.of(2021, 2, 20),
                agrType1, false);
        Agreement agreement2 = new Agreement(2L, LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 20), LocalDate.of(2022, 8, 20),
                agrType2, true);
        Agreement agreement3 = new Agreement(3L, LocalDate.of(2020, 6, 13),
                LocalDate.of(2020, 7, 1), LocalDate.of(2021, 1, 31),
                agrType3, false);
        Agreement agreement4 = new Agreement(4L, LocalDate.of(2021, 6, 4),
                LocalDate.of(2021, 6, 15), LocalDate.of(2022, 8, 15),
                agrType4, true);
        Agreement agreement5 = new Agreement(5L, LocalDate.of(2022, 3, 6),
                LocalDate.of(2022, 3, 10), LocalDate.of(2022, 11, 10),
                agrType1, true);
        Agreement agreement6 = new Agreement(6L, LocalDate.of(2020, 2, 10),
                LocalDate.of(2020, 3, 3), LocalDate.of(2023, 6, 5),
                agrType3, true);

        // Workers =========================
        WorkerDAO workerDAO = getWorkerDAO();

        Worker worker1 = new Employee(1L, "Heather", "Duke", "hduke@heathers.com", 6954325452L,
                "564238952", "heather1", "11111111111111111111", department1);
        Worker worker2 = new Employee(2L, "Zack", "Callison", "cal2000@somewhere.xyz", 6945630028L,
                "453168792", "zack2", "22222222222222222222", department2);
        Worker worker3 = new Manager(3L, "Lin-Manuel", "Miranda", "hduke@company.com", 6945633781L,
                "213465879", "lin3", "33333333333333333333", department3);
        Worker worker4 = new Manager(4L, "Beth", "Wortmen", "berthold@mushroom.gr", 6946588528L,
                "666325986", "beth4", "44444444444444444444", department2);

        workerDAO.save(worker1);
        agreementDAO.save(agreement1, worker1);
        agreementDAO.save(agreement2, worker1);
        workerDAO.save(worker2);
        agreementDAO.save(agreement3, worker2);
        agreementDAO.save(agreement4, worker2);
        workerDAO.save(worker3);
        agreementDAO.save(agreement5, worker3);
        workerDAO.save(worker4);
        agreementDAO.save(agreement6, worker4);

        // Accepted Leaves =================
        AcceptedLeavesDAO acceptedLeavesDAO = getAcceptedLeavesDAO();

        Leave acceptedLeave1 = new Leave(1L,
                LocalDate.of(2020,11,22),
                LocalDate.of(2020,11,25), leaveType.SICK);
        Leave acceptedLeave2 = new Leave(2L,
                LocalDate.of(2021,3,5),
                LocalDate.of(2021,3,9), leaveType.SICK);
        Leave acceptedLeave3 = new Leave(3L,
                LocalDate.of(2021,6,15),
                LocalDate.of(2021,7,18), leaveType.REST);

        Leave acceptedLeave4 = new Leave(4L,
                LocalDate.of(2020,10,7),
                LocalDate.of(2020,10,13), leaveType.SICK);
        Leave acceptedLeave5 = new Leave(5L,
                LocalDate.of(2021,12,22),
                LocalDate.of(2021,12,26), leaveType.REST);
        Leave acceptedLeave6 = new Leave(6L,
                LocalDate.of(2021,9,2),
                LocalDate.of(2021,9,8), leaveType.REST);
        Leave acceptedLeave7 = new Leave(7L,
                LocalDate.of(2021,12,25),
                LocalDate.of(2021,12,26), leaveType.REST);
        Leave acceptedLeave8 = new Leave(7L,
                LocalDate.of(2022,2,3),
                LocalDate.of(2022,2,6), leaveType.SICK);
        Leave acceptedLeave9 = new Leave(7L,
                LocalDate.of(2022,1,16),
                LocalDate.of(2022,1,16), leaveType.SICK);

        Leave acceptedLeave10 = new Leave(10L,
                LocalDate.of(2022,4,14),
                LocalDate.of(2022,4,15), leaveType.SICK);
        Leave acceptedLeave11 = new Leave(11L,
                LocalDate.of(2022,3,31),
                LocalDate.of(2022,4,1), leaveType.REST);

        Leave acceptedLeave16 = new Leave(16L,
                LocalDate.of(2020,5,14),
                LocalDate.of(2020,5,15), leaveType.REST);

        acceptedLeavesDAO.save(acceptedLeave1, worker1);
        acceptedLeavesDAO.save(acceptedLeave2, worker1);
        acceptedLeavesDAO.save(acceptedLeave3, worker1);

        acceptedLeavesDAO.save(acceptedLeave4, worker2);
        acceptedLeavesDAO.save(acceptedLeave5, worker2);
        acceptedLeavesDAO.save(acceptedLeave6, worker2);
        acceptedLeavesDAO.save(acceptedLeave7, worker2);
        acceptedLeavesDAO.save(acceptedLeave8, worker2);
        acceptedLeavesDAO.save(acceptedLeave9, worker2);

        acceptedLeavesDAO.save(acceptedLeave10, worker3);
        acceptedLeavesDAO.save(acceptedLeave11, worker3);

        acceptedLeavesDAO.save(acceptedLeave16, worker4);

        // Leave Requests ==================
        LeaveRequestDAO leaveRequestDAO = getLeaveRequestDAO();

        Leave leaveRequest1 = new Leave(12L,
                LocalDate.of(2022,6,22),
                LocalDate.of(2022,6,23), leaveType.SICK);
        Leave leaveRequest2 = new Leave(13L,
                LocalDate.of(2022,8,5),
                LocalDate.of(2022,8,7), leaveType.REST);
        Leave leaveRequest3 = new Leave(14L,
                LocalDate.of(2022,9,11),
                LocalDate.of(2022,9,20), leaveType.REST);
        Leave leaveRequest4 = new Leave(15L,
                LocalDate.of(2022,7,5),
                LocalDate.of(2022,7,6), leaveType.REST);

        leaveRequestDAO.save(leaveRequest1, worker1);
        leaveRequestDAO.save(leaveRequest2, worker1);
        leaveRequestDAO.save(leaveRequest3, worker3);
        leaveRequestDAO.save(leaveRequest4, worker2);

        // Attendances =====================
        AttendanceDAO attendanceDAO = getAttendanceDAO();

        Attendance attendance1 = new Attendance(1L, LocalDate.of(2020,2,3),
                LocalTime.of(9,0), LocalTime.of(17,0));
        Attendance attendance2 = new Attendance(2L, LocalDate.of(2020,2,4),
                LocalTime.of(9,0), LocalTime.of(17,0));
        Attendance attendance3 = new Attendance(3L, LocalDate.of(2021,3,4),
                LocalTime.of(10,0), LocalTime.of(18,0));
        Attendance attendance4 = new Attendance(4L, LocalDate.of(2021,3,5),
                LocalTime.of(10,0), LocalTime.of(19,0));
        Attendance attendance5 = new Attendance(5L, LocalDate.of(2021,3,6),
                LocalTime.of(10,0), LocalTime.of(18,0));

        Attendance attendance6 = new Attendance(6L, LocalDate.of(2020,10,10),
                LocalTime.of(9,0), LocalTime.of(13,0));
        Attendance attendance7 = new Attendance(7L, LocalDate.of(2020,10,11),
                LocalTime.of(9,0), LocalTime.of(13,0));
        Attendance attendance8 = new Attendance(8L, LocalDate.of(2021,12,2),
                LocalTime.of(10,0), LocalTime.of(14,0));

        Attendance attendance9 = new Attendance(9L, LocalDate.of(2022,5,2),
                LocalTime.of(7,0), LocalTime.of(15,0));
        Attendance attendance10 = new Attendance(10L, LocalDate.of(2022,5,3),
                LocalTime.of(7,0), LocalTime.of(15,0));
        Attendance attendance11 = new Attendance(11L, LocalDate.of(2022,5,4),
                LocalTime.of(7,0), LocalTime.of(15,0));

        Attendance attendance12 = new Attendance(12L, LocalDate.of(2022,6,12),
                LocalTime.of(9,0), LocalTime.of(13,0));

        attendanceDAO.save(attendance1, worker1);
        attendanceDAO.save(attendance2, worker1);
        attendanceDAO.save(attendance3, worker1);
        attendanceDAO.save(attendance4, worker1);
        attendanceDAO.save(attendance5, worker1);

        attendanceDAO.save(attendance6, worker2);
        attendanceDAO.save(attendance7, worker2);
        attendanceDAO.save(attendance8, worker2);

        attendanceDAO.save(attendance9, worker3);
        attendanceDAO.save(attendance10, worker3);
        attendanceDAO.save(attendance11, worker3);

        attendanceDAO.save(attendance12, worker4);

    }

    /**
     * Returns Accepted Leaves DAO
     * @return accepted leaves DAO
     */
    public abstract AcceptedLeavesDAO getAcceptedLeavesDAO();

    /**
     * Returns Agreement DAO
     * @return agreement DAO
     */
    public abstract AgreementDAO getAgreementDAO();

    /**
     * Returns Agreement Type DAO
     * @return agreement Type DAO
     */
    public abstract AgreementTypeDAO getAgreementTypeDAO();

    /**
     * Returns Attendance DAO
     * @return attendance DAO
     */
    public abstract AttendanceDAO getAttendanceDAO();

    /**
     * Returns Leave Request DAO
     * @return leave request DAO
     */
    public abstract LeaveRequestDAO getLeaveRequestDAO();

    /**
     * Returns Worker DAO
     * @return worker DAO
     */
    public abstract WorkerDAO getWorkerDAO();

}
