/**
 *
 */
package com.dxc.medxc.persistence.repository;

import java.time.ZonedDateTime;
import java.util.List;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.DocSpec;
import com.dxc.medxc.persistence.model.Doctor;

/**
 * @author mhristov2
 */
public interface DoctorRepository {
    /**
     * @param id
     *            Id of the doctor whose appointments need to be returned.
     * @param fromDate
     *            The date from which appointments should start to be displayed.
     * @param toDate
     *            The date until appointments should be displayed Any appointments
     *            that that have date equal to <strong>from</strong> or
     *            <strong>to</strong> are included.
     * @return A list containing all appointments between the two dates of a
     *         particular doctor.
     */
    List<Appointment> findAppointmentsByDoctorIdAndFromToDates(String id, ZonedDateTime fromDate, ZonedDateTime toDate);

    /**
     * @param id
     *            The id of the doctor whose specialties are to be returned.
     * @return The specialties of the doctor with the given id.
     */
    List<DocSpec> findAllDoctorSpecialtiesOfDoctorWithId(final String id);

    /**
     * @param id
     *            The id of the doctor whose appointments are to be returned.
     * @return The appointments of the doctor with the given id.
     */
    List<Appointment> findAllAppointmentsOfDoctorWithId(final String id);

    /**
     * @return All the doctors in the system.
     */
    List<Doctor> getAllDoctors();
}
