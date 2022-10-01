package gr.aueb.sweng22.team05.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Worker;

public class AgreementDAOMemory implements AgreementDAO {
    protected static ArrayList<Agreement> agreements = new ArrayList<Agreement>();
    protected static ArrayList<Worker> workers = new ArrayList<Worker>();

    /**
     * Deletes all entities
     */
    public void removeAll(){
        agreements.removeAll(agreements);
        workers.removeAll(workers);
    }

    /**
     * Deletes an agreement
     * @param agreement the agreement
     */
    public void delete(Agreement agreement) {
        int i = agreements.indexOf(agreement);
        agreements.remove(i);
        workers.remove(i);
    }

    /**
     * Returns all agreements
     * @return the agreements
     */
    public List<Agreement> findAll() {
        ArrayList<Agreement> result = new ArrayList<Agreement>();
        result.addAll(agreements);
        return result;
    }

    /**
     * Returns all available agreements for worker
     * @param worker the worker
     * @return the agreements
     */
    public List<Agreement> findAllFor(Worker worker) {
        ArrayList<Agreement> result = new ArrayList<Agreement>();
        for (int i = 0; i<workers.size(); i++){
            if (workers.get(i)==worker){
                result.add(agreements.get(i));
            }
        }
        return result;
    }

    /**
     * Saves an agreement
     * @param agreement the agreement
     * @param worker the worker
     */
    public void save(Agreement agreement, Worker worker) {
        agreements.add(agreement);
        workers.add(worker);
    }

    /**
     * Find an agreement by id
     * @param id the agreement's id
     * @return the agreement found, or null
     */
    public Agreement find(Long id) {
        for(Agreement Agreement : agreements)
            if(Agreement.getId() == id)
                return Agreement;

        return null;
    }

    /**
     * Returns the next available id that can by used on an agreement
     * @return the agreement's id
     */
    @Override
    public Long nextId() {
        return (agreements.size() > 0 ? agreements.get(agreements.size()-1).getId()+1 : 1);
    }
}
