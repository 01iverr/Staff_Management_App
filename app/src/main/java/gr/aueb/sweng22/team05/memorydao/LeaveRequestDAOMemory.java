package gr.aueb.sweng22.team05.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public class LeaveRequestDAOMemory implements LeaveRequestDAO {
    protected static ArrayList<Leave> leaves = new ArrayList<>();
    protected static ArrayList<Worker> workers = new ArrayList<>();

    /**
     * Deletes all entities
     */
    public void removeAll(){
        leaves.removeAll(leaves);
        workers.removeAll(workers);
    }

    /**
     * Deletes a leave request
     * @param leave the leave request
     */
    public void delete(Leave leave) {
        int i = leaves.indexOf(leave);
        leaves.remove(i);
        workers.remove(i);
    }

    /**
     * Returns all leave requests
     * @return the leave requests
     */
    public List<Leave> findAll() {
        ArrayList<Leave> result = new ArrayList<Leave>();
        result.addAll(leaves);
        return result;
    }

    /**
     * Returns all leave requests for worker
     * @param worker the worker
     * @return the worker's leave request
     */
    public List<Leave> findAllFor(Worker worker) {
        ArrayList<Leave> result = new ArrayList<Leave>();
        for (int i = 0; i<workers.size(); i++){
            if (workers.get(i)==worker){
                result.add(leaves.get(i));
            }
        }
        return result;
    }

    /**
     * Returns all leave requests for department
     * @param department the department
     * @return the leave requests filed by the department's workers
     */
    @Override
    public List<Leave> findAllFor(String department) {
        ArrayList<Leave> result = new ArrayList<Leave>();
        for (int i=0; i<leaves.size(); i++){
            String workerDepartment = workers.get(i).getDepartment().getName();
            if (workerDepartment.equals(department)){
                result.add(leaves.get(i));
            }
        }
        return result;
    }

    /**
     * Returns worker that placed request
     * @param leaveRequest the leave request
     * @return worker that placed the request
     */
    @Override
    public Worker findWorkerFor(Leave leaveRequest) {
        int index = leaves.indexOf(leaveRequest);
        return workers.get(index);
    }

    /**
     * Saves a leave request
     * @param leave the leave request
     * @param worker the worker
     */
    public void save(Leave leave, Worker worker) {
        leaves.add(leave);
        workers.add(worker);
    }

    /**
     * Finds a leave request by id
     * @param id the leave request's id
     * @return the leave request found or null
     */
    public Leave find(Long id) {
        for(Leave agrType : leaves)
            if(agrType.getId() == id)
                return agrType;

        return null;
    }

    /**
     * Get total number of leave requests
     * @return number of leave requests
     */
    @Override
    public int size() {
        return leaves.size();
    }
}
