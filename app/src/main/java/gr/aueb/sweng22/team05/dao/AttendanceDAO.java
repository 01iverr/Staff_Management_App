package gr.aueb.sweng22.team05.dao;

import java.util.List;

import gr.aueb.sweng22.team05.domain.Attendance;
import gr.aueb.sweng22.team05.domain.Worker;

public interface AttendanceDAO {
    /**
     * Deletes all entities
     */
    void removeAll();

    /**
     * Deletes an attendance
     * @param attendance the attendance
     */
    void delete(Attendance attendance);

    /**
     * Returns all attendances
     * @return the attendances
     */
    List<Attendance> findAll();

    /**
     * Returns all attendances for worker
     * @return worker's attendances
     */
    List<Attendance> findAllFor(Worker worker);

    /**
     * Saves an attendance
     * @param attendance the attendance
     * @param worker the worker
     */
    void save(Attendance attendance, Worker worker);

    /**
     * Finds an attendance by id
     * @param id attendance's id
     * @return the attendance found or null
     */
    Attendance find(Long id);

    /**
     * Returns the next available id that can by used on an attendance
     * @return the attendance's id
     */
    Long nextId();
}
