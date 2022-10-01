package gr.aueb.sweng22.team05.dao;

import java.util.List;

import gr.aueb.sweng22.team05.domain.AgreementType;

public interface AgreementTypeDAO {
    /**
     * Deletes all entities
     */
    void removeAll();

    /**
     * Delete an agreement type.
     * @param entity the agreement type
     */
    void delete(AgreementType entity);

    /**
     * Returns all agreement types
     * @return the agreement types
     */
    List<AgreementType> findAll();

    /**
     * Saves an agreement type
     * @param entity the agreement type
     */
    void save(AgreementType entity);

    /**
     * Finds the agreement type by id
     * @param id the agreement type's id
     * @return the agreement type found or null
     */
    AgreementType find(Long id);

    /**
     * Returns the next available id that can by used on an agreement type
     * @return the agreement type's id
     */
    Long nextId();
}
