package com.dxc.medxc.services;

import java.util.List;

import com.dxc.medxc.dto.DoctorDTO;

/**
 * Interface for admin services
 *
 * @author atsekov
 */
public interface AdminService {

    /**
     * Temporary - just to test post services.
     *
     * @param newDoctor
     *            New doctor that is going to be created.
     * @return A list containing all appointments between the two dates
     */
    boolean createDoctor(DoctorDTO newDoctor);

    /**
     * @return List of all available specialty names
     */
    List<String> getAllSpecialtyNames();
}
