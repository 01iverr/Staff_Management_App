package gr.aueb.sweng22.team05.memorydao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.dao.AttendanceDAO;
import gr.aueb.sweng22.team05.domain.Attendance;
import gr.aueb.sweng22.team05.domain.Worker;

public class AttendanceDAOMemory implements AttendanceDAO {
    protected static ArrayList<Attendance> attendances = new ArrayList<Attendance>();
    protected static ArrayList<Worker> workers = new ArrayList<Worker>();

    /**
     * Deletes all entities
     */
    public void removeAll(){
        attendances.removeAll(attendances);
        workers.removeAll(workers);
    }

    /**
     * Deletes an attendance
     * @param attendance the attendance
     */
    public void delete(Attendance attendance) {
        int i = attendances.indexOf(attendance);
        attendances.remove(i);
        workers.remove(i);
    }

    /**
     * Returns all attendances
     * @return the attendances
     */
    public List<Attendance> findAll() {
        ArrayList<Attendance> result = new ArrayList<Attendance>();
        result.addAll(attendances);
        return result;
    }

    /**
     * Returns all attendances for worker
     * @return worker's attendances
     */
    public List<Attendance> findAllFor(Worker worker) {
        ArrayList<Attendance> result = new ArrayList<Attendance>();
        for (int i = 0; i<workers.size(); i++){
            if (workers.get(i)==worker){
                result.add(attendances.get(i));
            }
        }
        return result;
    }

    /**
     * Saves an attendance
     * @param attendance the attendance
     * @param worker the worker
     */
    public void save(Attendance attendance, Worker worker) {
        attendances.add(attendance);
        workers.add(worker);
    }

    /**
     * Finds an attendance by id
     * @param id attendance's id
     * @return the attendance found or null
     */
    public Attendance find(Long id) {
        for(Attendance Attendance : attendances)
            if(Attendance.getId() == id)
                return Attendance;

        return null;
    }

    /**
     * Returns the next available id that can by used on an attendance
     * @return the attendance's id
     */
    @Override
    public Long nextId() {
        return (attendances.size() > 0 ? attendances.get(attendances.size()-1).getId()+1 : 1);
    }
}
