package gr.aueb.sweng22.team05.dao;

import java.util.List;

import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public interface LeaveRequestDAO {
    /**
     * Deletes all entities
     */
    void removeAll();

    /**
     * Deletes a leave request
     * @param leave the leave request
     */
    void delete(Leave leave);

    /**
     * Returns all leave requests
     * @return the leave requests
     */
    List<Leave> findAll();

    /**
     * Returns all leave requests for worker
     * @param worker the worker
     * @return the worker's leave request
     */
    List<Leave> findAllFor(Worker worker);

    /**
     * Returns all leave requests for department
     * @param department the department
     * @return the leave requests filed by the department's workers
     */
    List<Leave> findAllFor(String department);

    /**
     * Returns worker that placed request
     * @param leaveRequest the leave request
     * @return worker that placed the request
     */
    Worker findWorkerFor(Leave leaveRequest);

    /**
     * Saves a leave request
     * @param leave the leave request
     * @param worker the worker
     */
    void save(Leave leave, Worker worker);

    /**
     * Finds a leave request by id
     * @param id the leave request's id
     * @return the leave request found or null
     */
    Leave find(Long id);

    /**
     * Get total number of leave requests
     * @return number of leave requests
     */
    int size();
}
