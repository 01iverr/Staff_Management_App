package gr.aueb.sweng22.team05.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public class AcceptedLeavesDAOMemory implements AcceptedLeavesDAO {
    protected static ArrayList<Leave> leaves = new ArrayList<Leave>();
    protected static ArrayList<Worker> workers = new ArrayList<Worker>();

    /**
     * Deletes all entities
     */
    public void removeAll(){
        leaves.removeAll(leaves);
        workers.removeAll(workers);
    }

    /**
     * Deletes an accepted Leave
     * @param leave the leave
     */
    public void delete(Leave leave) {
        int i = leaves.indexOf(leave);
        leaves.remove(i);
        workers.remove(i);
    }

    /**
     * Returns all accepted leaves
     * @return the leaves
     */
    public List<Leave> findAll() {
        ArrayList<Leave> result = new ArrayList<Leave>();
        result.addAll(leaves);
        return result;
    }

    /**
     * Returns all accepted leaves for a worker
     * @return worker's accepted leaves
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
     * Saves an accepted leave
     * @param leave the leave
     * @param worker the worker
     */
    public void save(Leave leave, Worker worker) {
        leaves.add(leave);
        workers.add(worker);
    }

    /**
     * Finds the accepted leave based on id
     * @param id the accepted leave's id
     * @return the accepted leave or null
     */
    public Leave find(Long id) {
        for(Leave agrType : leaves)
            if(agrType.getId() == id)
                return agrType;

        return null;
    }

    /**
     * Get total number of accepted leaves
     * @return number of accepted leave
     */
    @Override
    public int size() {
        return leaves.size();
    }
}
