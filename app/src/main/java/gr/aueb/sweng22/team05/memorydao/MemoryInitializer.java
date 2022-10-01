package gr.aueb.sweng22.team05.memorydao;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.AgreementTypeDAO;
import gr.aueb.sweng22.team05.dao.AttendanceDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.domain.Attendance;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public class MemoryInitializer extends Initializer {

    /**
     * Deletes all saved data
     */
    @Override
    protected void eraseData() {
        getWorkerDAO().removeAll();
        getAcceptedLeavesDAO().removeAll();
        getAgreementDAO().removeAll();
        getAgreementTypeDAO().removeAll();
        getAttendanceDAO().removeAll();
        getLeaveRequestDAO().removeAll();
    }

    /**
     * Returns Accepted Leaves DAO
     * @return accepted leaves DAO
     */
    @Override
    public AcceptedLeavesDAO getAcceptedLeavesDAO() {
        return new AcceptedLeavesDAOMemory();
    }

    /**
     * Returns Agreement DAO
     * @return agreement DAO
     */
    @Override
    public AgreementDAO getAgreementDAO() {
        return new AgreementDAOMemory();
    }

    /**
     * Returns Agreement Type DAO
     * @return agreement Type DAO
     */
    @Override
    public AgreementTypeDAO getAgreementTypeDAO() {
        return new AgreementTypeDAOMemory();
    }

    /**
     * Returns Attendance DAO
     * @return attendance DAO
     */
    @Override
    public AttendanceDAO getAttendanceDAO() {
        return new AttendanceDAOMemory();
    }

    /**
     * Returns Leave Request DAO
     * @return leave request DAO
     */
    @Override
    public LeaveRequestDAO getLeaveRequestDAO() {
        return new LeaveRequestDAOMemory();
    }

    /**
     * Returns Worker DAO
     * @return worker DAO
     */
    @Override
    public WorkerDAO getWorkerDAO() {
        return new WorkerDAOMemory();
    }
}
