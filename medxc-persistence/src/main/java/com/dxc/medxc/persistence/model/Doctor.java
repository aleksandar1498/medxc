/**
 *
 */
package com.dxc.medxc.persistence.model;

import java.util.List;

/**
 * @author yyayya
 */
public interface Doctor {

    /**
     * @return doctor's number
     */
    String getDocNum();

    /**
     * @return doctor's name
     */
    String getName();

    /**
     * @return doctor's email
     */
    String getEmail();

    /**
     * @return doctor's phone number
     */
    String getPhoneNumber();

    /**
     * @return list of doctors's appointments
     */
    List<Appointment> getAppointments();

    /**
     * @return specialties
     */
    List<DocSpec> getSpecialties();

    /**
     * @param specialties
     *            specialties of the doctor
     */
    void setDocSpec(final List<DocSpec> specialties);

}
