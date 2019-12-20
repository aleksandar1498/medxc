package com.dxc.medxc.services;

import java.util.List;

import com.dxc.medxc.dto.AppointmentDTO;
import com.dxc.medxc.dto.DoctorDTO;

/**
 * Interface for the doctor services
 *
 * @author atsekov
 */
public interface DoctorService {

    /**
     * Method to get all the doctors
     *
     * @return A list of all doctors
     */
    List<DoctorDTO> getAllDoctors();

    /**
     * @author mhristov2 Method to view a doctor's appointments between two dates
     * @param id
     *            The id of the doctor whose appointments are to be viewed
     * @param from
     *            The date from which appointments should be displayed
     * @param to
     *            The date until appointments should be displayed Any appointments
     *            that that have date equal to <strong>from</strong> or
     *            <strong>to</strong> are included
     * @return A list containing all appointments between the two dates
     */
    List<AppointmentDTO> viewAppointments(final String id, String from, String to);

    // /**
    // * Temporary - just to test post services.
    // *
    // * @param doctor
    // * - New doctor data.
    // * @return A list containing all appointments between the two dates
    // */
    // List<DoctorDTO> createDoctor(DoctorDTO newDoctor);
}
