package com.dxc.medxc.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DTO class
 *
 * @author atsekov
 */
public final class DoctorDTO {

    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final boolean active;
    private final List<SpecialtyDTO> specialties;
    private final List<AppointmentDTO> appointments;

    /**
     * Empty constructor for DoctorDTO
     */
    public DoctorDTO() {
        id = null;
        name = null;
        email = null;
        phoneNumber = null;
        active = false;
        specialties = null;
        appointments = null;
    }

    /**
     * @param id
     *            String. The id of the doctor.
     * @param name
     *            String. The name of the doctor.
     * @param email
     *            String. The email of the doctor.
     * @param phoneNumber
     *            String. The phone number of the doctor.
     * @param active
     *            boolean. The active status of the doctor.
     * @param specialties
     *            list of specialties. The specialties that the doctor has. The
     * @param appointments
     *            doctor's appointments
     */
    public DoctorDTO(final String id, final String name, final String email, final String phoneNumber,
            final boolean active, final List<SpecialtyDTO> specialties, final List<AppointmentDTO> appointments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.specialties = specialties == null ? new ArrayList<>() : Collections.unmodifiableList(specialties);
        this.appointments = appointments == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
    }

    /**
     * @return String parameter id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return String parameter name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return String parameter email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return String parameter phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return boolean parameter active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return list of specialties that the doctor has
     */
    public List<SpecialtyDTO> getSpecialties() {
        return specialties == null ? new ArrayList<>() : Collections.unmodifiableList(specialties);
    }

    /**
     * @return list of appointments that the doctor has
     */
    public List<AppointmentDTO> getAppointments() {
        return specialties == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
    }
}
