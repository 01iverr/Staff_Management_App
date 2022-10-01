package gr.aueb.sweng22.team05.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.dao.WorkerDAO;

public class WorkerDAOMemory implements WorkerDAO {
    protected static ArrayList<Worker> entities = new ArrayList<Worker>();
    protected static Long departmentNextId = 0L;

    /**
     * Deletes all entities
     */
    public void removeAll(){
        entities.removeAll(entities);
        departmentNextId = 0L;
    }

    /**
     * Deletes a worker
     * @param entity the worker
     */
    public void delete(Worker entity) {
        entities.remove(entity);
    }

    /**
     * Returns all workers
     * @return the workers
     */
    public List<Worker> findAll() {
        ArrayList<Worker> result = new ArrayList<Worker>();
        result.addAll(entities);
        return result;
    }

    /**
     * Saves a worker
     * @param entity the worker
     */
    public void save(Worker entity) {
        // increment departmentNextId if needed
        if (entity.getDepartment()!=null) {
            boolean departmentAlreadyExists = false;
            for (Worker worker : entities) {
                String workerName = worker.getDepartment().getName();
                String entityName = entity.getDepartment().getName();
                if (workerName.equals(entityName)) {
                    departmentAlreadyExists = true;
                }
            }
            if (!departmentAlreadyExists) {
                departmentNextId++;
            }
        }

        // save entity
        entities.add(entity);
    }

    /**
     * Finds a worker by id
     * @param id the worker's id
     * @return the worker found or null
     */
    public Worker findById(Long id) {
        for(Worker worker : entities)
            if(worker.getId() == id)
                return worker;

        return null;
    }

    /**
     * Finds a worker by AFM
     * @param afm the worker's afm
     * @return the worker found or null
     */
    public Worker findByAFM(String afm) {
        for(Worker worker : entities)
            if(worker.getAFM().equals(afm))
                return worker;

        return null;
    }

    /**
     * Returns the next available id that can by used on a worker
     * @return the worker's id
     */
    @Override
    public Long nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }

    /**
     * Returns the next available id that can by used on a department
     * @return the department's id
     */
    @Override
    public Long departmentNextId(){
        return ++departmentNextId;
    }
}
