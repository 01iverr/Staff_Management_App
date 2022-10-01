package gr.aueb.sweng22.team05.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.domain.Attendance;
import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Employee;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.domain.leaveType;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementTypeDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AttendanceDAOMemory;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

public class DAOtest {
    Initializer dataHelper;

    private AcceptedLeavesDAO acceptedLeavesDAO;
    private AgreementDAO agreementDAO;
    private AgreementTypeDAO agreementTypeDAO;
    private AttendanceDAO attendanceDAO;
    private LeaveRequestDAO leaveRequestDAO;
    private WorkerDAO workerDAO;

    private static final int INITIAL_ACCEPTED_LEAVE_COUNT = 12;
    private static final int INITIAL_ACCEPTED_LEAVE_COUNT_FIRST_WORKER = 3;
    private static final int INITIAL_AGREEMENT_COUNT = 6;
    private static final int INITIAL_AGREEMENT_COUNT_FIRST_WORKER = 2;
    private static final int INITIAL_AGREEMENT_TYPE_COUNT = 4;
    private static final int INITIAL_ATTENDANCE_COUNT = 12;
    private static final int INITIAL_ATTENDANCE_COUNT_FIRST_WORKER = 5;
    private static final int INITIAL_LEAVE_REQUEST_COUNT = 4;
    private static final int INITIAL_LEAVE_REQUEST_COUNT_FIRST_WORKER = 2;
    private static final int INITIAL_WORKER_COUNT = 4;
    private static final int INITIAL_DEPARTMENT_COUNT = 3;
    private static final int INITIAL_LEAVE_REQUEST_COUNT_IT_DEPARTMENT = 1;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        acceptedLeavesDAO = new AcceptedLeavesDAOMemory();
        agreementDAO = new AgreementDAOMemory();
        agreementTypeDAO = new AgreementTypeDAOMemory();
        attendanceDAO = new AttendanceDAOMemory();
        leaveRequestDAO = new LeaveRequestDAOMemory();
        workerDAO = new WorkerDAOMemory();
    }

    @After
    public void cleanUp(){
        dataHelper.eraseData();
    }

    /**====================================================
     * Search for existent and non existent ===============
     * entries in every DAO                 ===============
     */
    @Test
    public void findExistingAcceptedLeave(){
        Assert.assertNotNull(acceptedLeavesDAO.find(2L));
    }

    @Test
    public void findNonExistingAcceptedLeave(){
        Assert.assertNull(acceptedLeavesDAO.find(132L));
    }

    @Test
    public void findExistingAgreement(){
        Assert.assertNotNull(agreementDAO.find(2L));
    }

    @Test
    public void findNonExistingAgreement(){
        Assert.assertNull(agreementDAO.find(825L));
    }

    @Test
    public void findExistingAgreementType(){
        Assert.assertNotNull(agreementTypeDAO.find(1L));
    }

    @Test
    public void findNonExistingAgreementType(){
        Assert.assertNull(agreementTypeDAO.find(644L));
    }

    @Test
    public void findExistingAttendance(){
        Assert.assertNotNull(attendanceDAO.find(1L));
    }

    @Test
    public void findNonExistingAttendance(){
        Assert.assertNull(attendanceDAO.find(465L));
    }

    @Test
    public void findExistingLeaveRequest(){
        Assert.assertNotNull(leaveRequestDAO.find(13L));
    }

    @Test
    public void findNonExistingLeaveRequest(){
        Assert.assertNull(leaveRequestDAO.find(7651L));
    }

    @Test
    public void findExistingWorkerById(){
        Assert.assertNotNull(workerDAO.findById(1L));
    }

    @Test
    public void findNonExistingWorkerById(){
        Assert.assertNull(workerDAO.findById(3432L));
    }

    @Test
    public void findExistingWorkerByAFM(){
        Assert.assertNotNull(workerDAO.findByAFM("564238952"));
    }

    @Test
    public void findNonExistingWorkerByAFM(){
        Assert.assertNull(workerDAO.findByAFM("12"));
    }

    @Test
    public void findExistingWorkerConfirmName(){
        Worker worker = workerDAO.findById(1L);
        Assert.assertEquals("Heather", worker.getFirstName());
    }

    /**====================================================
     * Count entries (total, or for single worker) ========
     */
    @Test
    public void listAllAcceptedLeaves(){
        Assert.assertEquals(INITIAL_ACCEPTED_LEAVE_COUNT, acceptedLeavesDAO.findAll().size());
    }

    @Test
    public void listAllAcceptedLeavesForWorker(){
        Worker worker = workerDAO.findById(1L);
        Assert.assertEquals(INITIAL_ACCEPTED_LEAVE_COUNT_FIRST_WORKER, acceptedLeavesDAO.findAllFor(worker).size());
    }

    @Test
    public void listAllAgreementsForWorker(){
        Worker worker = workerDAO.findById(1L);
        Assert.assertEquals(INITIAL_AGREEMENT_COUNT_FIRST_WORKER, agreementDAO.findAllFor(worker).size());
    }

    @Test
    public void listAllAgreementTypes(){
        Assert.assertEquals(INITIAL_AGREEMENT_TYPE_COUNT, agreementTypeDAO.findAll().size());
    }

    @Test
    public void listAllAttendancesForWorker(){
        Worker worker = workerDAO.findById(1L);
        Assert.assertEquals(INITIAL_ATTENDANCE_COUNT_FIRST_WORKER, attendanceDAO.findAllFor(worker).size());
    }

    @Test
    public void listAllLeaveRequestsForWorker(){
        Worker worker = workerDAO.findById(1L);
        Assert.assertEquals(INITIAL_LEAVE_REQUEST_COUNT_FIRST_WORKER, leaveRequestDAO.findAllFor(worker).size());
    }

    @Test
    public void listAllWorkers(){
        Assert.assertEquals(INITIAL_WORKER_COUNT, workerDAO.findAll().size());
    }

    @Test
    public void listAllDepartments(){
        Assert.assertEquals(INITIAL_DEPARTMENT_COUNT, workerDAO.departmentNextId()-1);
    }

    @Test
    public void listAllLeaveRequests(){
        Assert.assertEquals(INITIAL_LEAVE_REQUEST_COUNT, leaveRequestDAO.size());
    }

    @Test
    public void listAllLeaveRequestsForITDepartment(){
        List<Leave> allLeaveRequestsForIT = leaveRequestDAO.findAllFor("IT");
        Assert.assertEquals(INITIAL_LEAVE_REQUEST_COUNT_IT_DEPARTMENT, allLeaveRequestsForIT.size());
    }

    /**====================================================
     * Save - Delete items ================================
     */

    /**
     * Save new worker
     */
    @Test
    public void saveWorker(){
        Worker worker = new Employee(workerDAO.nextId(), "James", "Lewin", "jlew@secret.com", 6945852365L,
                "324215658", "j"+workerDAO.nextId(), "0Pp15bJo&91fmAhUTlyo", new Department());
        workerDAO.save(worker);

        Assert.assertEquals(INITIAL_WORKER_COUNT+1, workerDAO.findAll().size());
        Assert.assertNotNull(workerDAO.findById(worker.getId()));
        Assert.assertTrue(workerDAO.findAll().contains(worker));
    }

    /**
     * Delete worker
     */
    @Test
    public void deleteWorker(){
        List<Worker> allWorkers = workerDAO.findAll();
        Worker worker = allWorkers.get(0);
        workerDAO.delete(worker);
        allWorkers = workerDAO.findAll();
        Assert.assertEquals(INITIAL_WORKER_COUNT-1, allWorkers.size());
        Assert.assertNull(workerDAO.findById(worker.getId()));
    }

    /**
     * Save Accepted Leave
     */
    @Test
    public void saveAcceptedLeave(){
        Leave acceptedLeave = new Leave((long) (INITIAL_ACCEPTED_LEAVE_COUNT + INITIAL_LEAVE_REQUEST_COUNT + 1),
                LocalDate.of(2015,11,22),
                LocalDate.of(2015,11,25), leaveType.SICK);
        acceptedLeavesDAO.save(acceptedLeave, workerDAO.findById(1L));

        Assert.assertEquals(INITIAL_ACCEPTED_LEAVE_COUNT+1, acceptedLeavesDAO.findAll().size());
        Assert.assertNotNull(acceptedLeavesDAO.find(acceptedLeave.getId()));
        Assert.assertTrue(acceptedLeavesDAO.findAll().contains(acceptedLeave));
    }

    /**
     * Delete accepted leave
     */
    @Test
    public void deleteAcceptedLeave(){
        List<Leave> allItems = acceptedLeavesDAO.findAll();
        Leave item = allItems.get(0);
        acceptedLeavesDAO.delete(item);
        allItems = acceptedLeavesDAO.findAll();
        Assert.assertEquals(INITIAL_ACCEPTED_LEAVE_COUNT-1, allItems.size());
        Assert.assertNull(acceptedLeavesDAO.find(item.getId()));
    }

    /**
     * Save Agreement
     */
    @Test
    public void saveAgreement(){
        Agreement agreement = new Agreement();
        agreement.setId(agreementDAO.nextId());
        agreementDAO.save(agreement, workerDAO.findAll().get(0));

        Assert.assertEquals(INITIAL_AGREEMENT_COUNT+1, agreementDAO.findAll().size());
        Assert.assertNotNull(agreementDAO.find(agreement.getId()));
        Assert.assertTrue(agreementDAO.findAll().contains(agreement));
    }

    /**
     * Delete Agreement
     */
    @Test
    public void deleteAgreement(){
        List<Agreement> allItems = agreementDAO.findAll();
        Agreement item = allItems.get(0);
        agreementDAO.delete(item);
        allItems = agreementDAO.findAll();
        Assert.assertEquals(INITIAL_AGREEMENT_COUNT-1, allItems.size());
        Assert.assertNull(agreementDAO.find(item.getId()));
    }

    /**
     * Save Agreement Type
     */
    @Test
    public void saveAgreementType(){
        AgreementType agreementType = new AgreementType();
        agreementType.setId(agreementTypeDAO.nextId());
        agreementTypeDAO.save(agreementType);

        Assert.assertEquals(INITIAL_AGREEMENT_TYPE_COUNT+1, agreementTypeDAO.findAll().size());
        Assert.assertNotNull(agreementTypeDAO.find(agreementType.getId()));
        Assert.assertTrue(agreementTypeDAO.findAll().contains(agreementType));
    }

    /**
     * Delete Agreement Type
     */
    @Test
    public void deleteAgreementType(){
        List<AgreementType> allItems = agreementTypeDAO.findAll();
        AgreementType item = allItems.get(0);
        agreementTypeDAO.delete(item);
        allItems = agreementTypeDAO.findAll();
        Assert.assertEquals(INITIAL_AGREEMENT_TYPE_COUNT-1, allItems.size());
        Assert.assertNull(agreementTypeDAO.find(item.getId()));
    }

    /**
     * Save Attendance
     */
    @Test
    public void saveAttendance(){
        Attendance attendance = new Attendance();
        attendance.setId(attendanceDAO.nextId());
        attendanceDAO.save(attendance, workerDAO.findAll().get(0));

        Assert.assertEquals(INITIAL_ATTENDANCE_COUNT+1, attendanceDAO.findAll().size());
        Assert.assertNotNull(attendanceDAO.find(attendance.getId()));
        Assert.assertTrue(attendanceDAO.findAll().contains(attendance));
    }

    /**
     * Delete Attendance
     */
    @Test
    public void deleteAttendance(){
        List<Attendance> allItems = attendanceDAO.findAll();
        Attendance item = allItems.get(0);
        attendanceDAO.delete(item);
        allItems = attendanceDAO.findAll();
        Assert.assertEquals(INITIAL_ATTENDANCE_COUNT-1, allItems.size());
        Assert.assertNull(attendanceDAO.find(item.getId()));
    }

    /**
     * Save Leave Request
     */
    @Test
    public void saveALeaveRequest(){
        Leave leaveRequest = new Leave();
        leaveRequest.setId((long) (INITIAL_ACCEPTED_LEAVE_COUNT + INITIAL_LEAVE_REQUEST_COUNT + 1));
        leaveRequestDAO.save(leaveRequest, workerDAO.findAll().get(0));

        Assert.assertEquals(INITIAL_LEAVE_REQUEST_COUNT+1, leaveRequestDAO.findAll().size());
        Assert.assertNotNull(leaveRequestDAO.find(leaveRequest.getId()));
        Assert.assertTrue(leaveRequestDAO.findAll().contains(leaveRequest));
    }

    /**
     * Delete Leave Request
     */
    @Test
    public void deleteLeaveRequest(){
        List<Leave> allItems = leaveRequestDAO.findAll();
        Leave item = allItems.get(0);
        leaveRequestDAO.delete(item);
        allItems = leaveRequestDAO.findAll();
        Assert.assertEquals(INITIAL_LEAVE_REQUEST_COUNT-1, allItems.size());
        Assert.assertNull(leaveRequestDAO.find(item.getId()));
    }

}
