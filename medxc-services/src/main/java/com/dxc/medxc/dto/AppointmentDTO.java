/**
 *
 */
package com.dxc.medxc.dto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data Transfer Object class.
 *
 * @author amirchev
 */
public final class AppointmentDTO {
    private ZonedDateTime date;
    private String status;
    private String specialty;
    private RecordDTO record;
    private DoctorDTO doctor;
    private PatientDTO patient;
    private List<TestDTO> tests;

    /**
     * Empty constructor
     */
    public AppointmentDTO() {

    }

    /**
     * @param date      Date of the appointment
     * @param status    Status of the appointment
     * @param specialty Specialty id of the appointment
     * @param record    Record of the appointment
     * @param doctor    Doctor of the appointment
     * @param patient   Patient of the appointment
     * @param tests     Tests of the appointment
     */
    public AppointmentDTO(final ZonedDateTime date, final String status, final String specialty, final RecordDTO record,
            final DoctorDTO doctor, final PatientDTO patient, final List<TestDTO> tests) {
        this.date = date;
        this.status = status;
        this.specialty = specialty;
        this.record = record;
        this.doctor = doctor;
        this.patient = patient;
        this.tests = Collections.unmodifiableList(tests);
    }

    /**
     * @return Date of the appointment
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * @return Status of the appointment
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return Specialty of the appointment
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * @return Doctor of the appointment
     */
    public DoctorDTO getDoctor() {
        return doctor;
    }

    /**
     * @return Patient of the appointment
     */
    public PatientDTO getPatient() {
        return patient;
    }

    /**
     * @return Record of the appointment
     */
    public RecordDTO getRecord() {
        return record;
    }

    /**
     * @return the tests
     */
    public List<TestDTO> getTests() {
        return tests == null ? new ArrayList<>() : Collections.unmodifiableList(tests);
    }

}
