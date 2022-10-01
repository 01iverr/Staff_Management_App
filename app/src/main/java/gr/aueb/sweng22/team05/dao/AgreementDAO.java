package gr.aueb.sweng22.team05.dao;

import java.util.List;

import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public interface AgreementDAO {
    /**
     * Deletes all entities
     */
    void removeAll();

    /**
     * Deletes an agreement
     * @param agreement the agreement
     */
    void delete(Agreement agreement);

    /**
     * Returns all agreements
     * @return the agreements
     */
    List<Agreement> findAll();

    /**
     * Returns all available agreements for worker
     * @param worker the worker
     * @return the agreements
     */
    List<Agreement> findAllFor(Worker worker);

    /**
     * Saves an agreement
     * @param agreement the agreement
     * @param worker the worker
     */
    void save(Agreement agreement, Worker worker);

    /**
     * Find an agreement by id
     * @param id the agreement's id
     * @return the agreement found, or null
     */
    Agreement find(Long id);

    /**
     * Returns the next available id that can by used on an agreement
     * @return the agreement's id
     */
    Long nextId();
}
