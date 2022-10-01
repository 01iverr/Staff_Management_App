package gr.aueb.sweng22.team05.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.dao.AgreementTypeDAO;
import gr.aueb.sweng22.team05.domain.AgreementType;

public class AgreementTypeDAOMemory implements AgreementTypeDAO {
    protected static ArrayList<AgreementType> entities = new ArrayList<AgreementType>();

    /**
     * Deletes all entities
     */
    public void removeAll(){
        entities.removeAll(entities);
    }

    /**
     * Delete an agreement type.
     * @param entity the agreement type
     */
    public void delete(AgreementType entity) {
        entities.remove(entity);
    }

    /**
     * Returns all agreement types
     * @return the agreement types
     */
    public List<AgreementType> findAll() {
        ArrayList<AgreementType> result = new ArrayList<AgreementType>();
        result.addAll(entities);
        return result;
    }

    /**
     * Saves an agreement type
     * @param entity the agreement type
     */
    public void save(AgreementType entity) {
        entities.add(entity);
    }

    /**
     * Finds the agreement type by id
     * @param id the agreement type's id
     * @return the agreement type found or null
     */
    public AgreementType find(Long id) {
        for(AgreementType agrType : entities)
            if(agrType.getId() == id)
                return agrType;

        return null;
    }

    /**
     * Returns the next available id that can by used on an agreement type
     * @return the agreement type's id
     */
    @Override
    public Long nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
