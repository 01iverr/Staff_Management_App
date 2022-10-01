package gr.aueb.sweng22.team05.dao;

import java.util.List;

import gr.aueb.sweng22.team05.domain.Worker;

public interface WorkerDAO {
    /**
     * Deletes all entities
     */
    void removeAll();

    /**
     * Deletes a worker
     * @param entity the worker
     */
    void delete(Worker entity);

    /**
     * Returns all workers
     * @return the workers
     */
    List<Worker> findAll();

    /**
     * Saves a worker
     * @param entity the worker
     */
    void save(Worker entity);

    /**
     * Finds a worker by id
     * @param id the worker's id
     * @return the worker found or null
     */
    Worker findById(Long id);

    /**
     * Finds a worker by AFM
     * @param afm the worker's afm
     * @return the worker found or null
     */
    Worker findByAFM(String afm);

    /**
     * Returns the next available id that can by used on a worker
     * @return the worker's id
     */
    Long nextId();

    /**
     * Returns the next available id that can by used on a department
     * @return the department's id
     */
    Long departmentNextId();
}
