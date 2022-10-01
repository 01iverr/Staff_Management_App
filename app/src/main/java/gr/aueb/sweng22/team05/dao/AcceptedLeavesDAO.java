package gr.aueb.sweng22.team05.dao;

import java.util.List;

import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public interface AcceptedLeavesDAO {
    /**
     * Deletes all entities
     */
    void removeAll();

    /**
     * Deletes an accepted Leave
     * @param leave the leave
     */
    void delete(Leave leave);

    /**
     * Returns all accepted leaves
     * @return the leaves
     */
    List<Leave> findAll();

    /**
     * Returns all accepted leaves for a worker
     * @return worker's accepted leaves
     */
    List<Leave> findAllFor(Worker worker);

    /**
     * Saves an accepted leave
     * @param leave the leave
     * @param worker the worker
     */
    void save(Leave leave, Worker worker);

    /**
     * Finds the accepted leave based on id
     * @param id the accepted leave's id
     * @return the accepted leave or null
     */
    Leave find(Long id);

    /**
     * Get total number of accepted leaves
     * @return number of accepted leave
     */
    int size();
}
