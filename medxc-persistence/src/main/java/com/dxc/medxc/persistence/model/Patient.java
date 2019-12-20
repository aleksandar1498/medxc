/**
 * 
 */
package com.dxc.medxc.persistence.model;

import java.util.List;

/**
 * @author amirchev
 */
public interface Patient {

    /**
     * @return PIN - id of patient
     */
    String getPIN();

    /**
     * @return email of patient
     */
    String getEmail();

    /**
     * @return name of patient
     */
    String getName();

    /**
     * @return patient's phone number
     */
    String getPhoneNumber();

    /**
     * @return patient's sex
     */
    char getSex();

    /**
     * @return patient's address
     */
    String getAddress();
    
    /**
     * @return list of patient's appointments
     */
    List<Appointment> getAppointments();
}
