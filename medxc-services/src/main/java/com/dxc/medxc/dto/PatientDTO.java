/**
 *
 */
package com.dxc.medxc.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DTO Class
 *
 * @author amirchev
 */
public final class PatientDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private char sex;
    private String address;
    private List<AppointmentDTO> appointments;
    /// contains records and tests! has get methods
    private PatientHistoryDTO patientHistory;

    /**
     * @param id
     *            The id of the patient
     * @param name
     *            The name of the patient
     * @param email
     *            The email of the patient
     * @param phoneNumber
     *            The phone number of the patient
     * @param sex
     *            The sex of the patient ('F' - Female, 'M' - Male, 'O' - Other, 'U'
     *            - Undefined)
     * @param address
     *            The address of the patient
     * @param appointments
     *            A list with appointments for the patient
     */

    public PatientDTO(final String id, final String name, final String email, final String phoneNumber, final char sex,
            final String address, final List<AppointmentDTO> appointments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.address = address;
        this.appointments = appointments == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
        this.patientHistory = new PatientHistoryDTO();
    }

    /**
     *
     */
    public PatientDTO() {
    }

    /**
     * @param id
     *            The id of the patient
     * @param name
     *            The name of the patient
     * @param email
     *            The email of the patient
     * @param phoneNumber
     *            The phone number of the patient
     * @param history
     *            The history of the patient in the hospital
     */
    @SuppressWarnings("nls")
    public PatientDTO(final String id, final String name, final String email, final String phoneNumber,
            final PatientHistoryDTO history) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = 'U';
        this.address = "";
        this.appointments = new ArrayList<>();
        this.patientHistory = history;
    }

    /**
     ** @param id
     *            The id of the patient*
     * @param name
     *            The name of the patient*
     * @param email
     *            The email of the patient*
     * @param phoneNumber
     *            The phone number of the patient
     */
    @SuppressWarnings("nls")
    public PatientDTO(final String id, final String name, final String email, final String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = 'U';
        this.address = "";
        appointments = new ArrayList<>();
        this.patientHistory = new PatientHistoryDTO();
    }

    /**
     * @param appointment
     *            Appointment which is to be added
     */
    public void addAppointment(final AppointmentDTO appointment) {
        this.appointments.add(appointment);
    }

    /**
     * @return string parameter id
     */
    public String getId() {
        return id;
    }

    /**
     * @return string parameter name
     */
    public String getName() {
        return name;
    }

    /**
     * @return string parameter email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return string parameter phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return sex of the patient ('F' - Female, 'M' - Male, 'O' - Other, 'U' -
     *         Undefined)
     */
    public char getSex() {
        return sex;
    }

    /**
     * @return The address of the patient
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the appointments
     */
    public List<AppointmentDTO> getAppointments() {
        return appointments == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
    }

    /**
     * @return patient history
     */
    public PatientHistoryDTO getPatientHistory() {
        return patientHistory;
    }

}
