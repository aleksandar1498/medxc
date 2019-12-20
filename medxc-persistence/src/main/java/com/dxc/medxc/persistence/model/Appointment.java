/**
 *
 */
package com.dxc.medxc.persistence.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yyayya
 */
public interface Appointment {

    /**
     * @return Status of the appointment
     */
    Status getStatus();

    /**
     * @return Id of the appointment
     */
    int getId();

    /**
     * @return date of the appointment
     */
    Timestamp getDate();

    /**
     * @return Id of the patient
     */
    Patient getPIN();

    /**
     * @return Specialty of the appointment
     */
    Specialty getSpecialty();

    /**
     * @return Id of the doctor
     */
    Doctor getDocNum();

    /**
     * @return Ordered tests from the appointment
     */
    List<Test> getTests();

    /**
     * @return the record created for this appointment
     */
    Record getRecord();

    /**
     * @return the patient for whom this appointment is registered
     */
    Patient getPatient();

    /**
     * @return the patient that perform the visitation
     */
    Doctor getDoctor();
}
