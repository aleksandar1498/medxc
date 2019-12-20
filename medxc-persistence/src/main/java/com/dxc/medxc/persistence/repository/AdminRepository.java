package com.dxc.medxc.persistence.repository;

import java.util.List;

/**
 * Interface for JpaAdminRepository. Includes method used by the admin.
 * 
 * @author atsekov
 */
public interface AdminRepository {

    /**
     * @param docNum
     *            doctor's number
     * @param name
     *            doctor's name
     * @param email
     *            doctor's email
     * @param phoneNumber
     *            doctor's phone number
     * @param specialties
     *            doctor's specialty names
     * @return true if doctor was successfully created and false otherwise.
     */
    boolean createDoctor(String docNum, String name, String email, String phoneNumber, List<String> specialties);

    /**
     * @return names of all the available specialties.
     */
    List<String> getAllSpecialtyNames();
}
