/**
 *
 */
package com.dxc.medxc.services;

import java.util.List;

import com.dxc.medxc.dto.AppointmentDTO;
import com.dxc.medxc.dto.PatientDTO;
import com.dxc.medxc.dto.PatientHistoryDTO;
import com.dxc.medxc.dto.RecordDTO;
import com.dxc.medxc.dto.SpecialtyDTO;
import com.dxc.medxc.dto.TestDTO;

/**
 * @author astefanov2
 */
public interface PatientService {
    /**
     * @param id of the patient
     * @return the list of user registered records
     */
    List<RecordDTO> getAllMedicalRecords(final String id);

    /*
     * /**
     *
     * @param id If of the patient
     *
     * @return list of the patients appointments
     */
    // List<AppointmentDTO> getAllAppointmentsBy(final String id);*/

    /**
     * @param id        Id of the patient
     * @param specialty Specialty of the patient
     * @return Appointments of the patient
     */
    List<AppointmentDTO> getAllAppointmentsByIdAndSpecialty(final String id, final String specialty);

    /**
     *
     * @param id ID of the patient
     * @return List of all appointments the patient has
     */
    List<AppointmentDTO> getAllAppointmentsById(final String id);

    /**
     *
     * @param id Id of the patient
     * @return List of the specialties that the patient has appointments of
     */
    List<SpecialtyDTO> getAllSpecialtiesByAppointments(final String id);

    /**
     * @return the registered medical records for the patient
     */
    List<PatientDTO> getAllPatients();

    /**
     * Method to get patient history by patient id
     *
     * @param id The id of the patient to view history
     * @return an object of type PatientHistoryDTO which contains a list or records
     *         and a list of tests
     */
    PatientHistoryDTO viewPatientHistory(final String id);

    /**
     * @param newPatient A new patient DTO
     * @return true if the patient is successfully registered and false otherwise.
     */
    Boolean registerPatient(PatientDTO newPatient);

    /**
     * @param id
     * @return list of DTO tests
     */
    List<TestDTO> getAllTestsById(String id);

    /**
     * @param id
     * @return list of DTO tests
     */
    List<TestDTO> getTestsByIdAfterDueDate(String id);
}
