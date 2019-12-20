/**
 *
 */
package com.dxc.medxc.persistence.repository;

import java.util.List;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Patient;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.model.Specialty;
import com.dxc.medxc.persistence.model.Test;

/**
 * @author yyayya
 */
public interface PatientRepository {

    /**
     * @param id        Id of the patient whose appointments need to be returned
     * @param specialty Specialty of the appointments
     * @return List of the appointments of the patient
     */
    List<Appointment> getAllAppointmentsByIdAndSpecialty(String id, String specialty);

    /**
     * @param id Id of the patient whose appointments need to be returned
     * @return List of the appointments of the patient
     */
    List<Appointment> getAllAppointmentsById(String id);

    /**
     *
     * @param id Id of the Patient
     * @return List of specialties for which the patient has an appointment
     */
    List<Specialty> getAllSpecialtiesByAppointments(final String id);

    /**
     * @return All doctor specialties
     */
    // List<Specialty> getAllSpecialties();

    /**
     * @return List all patients
     */
    List<Patient> getAllPatients();

    /**
     * @param id Id of the patient whose records need to be returned
     * @return all records assigned to patient
     */
    List<Record> getAllRecordsById(String id);

    /**
     * @param id Id of the patient whose tests need to be returned
     * @return all tests assigned to patient
     */
    List<Test> getAllTestsById(String id);

    /**
     * @param id of the patient
     * @return list of tests yet to be performed
     */
    List<Test> getTestsByIdAfterDueDate(String id);

    /**
     * @param pin          The personal ID of the patient
     * @param email        The email of the patient
     * @param name         The name of the patient
     * @param phone        The phone number of the patient
     * @param sex          The sex of the patient
     * @param address      The address of the patient
     * @param appointments The appointments of the patient
     * @return true if the patient is successfully registered, false otherwise
     */
    Boolean registerPatient(final String pin, final String email, final String name, final String phone, final char sex,
            final String address, final List<Appointment> appointments);

}
