/**
 *
 */
package com.dxc.medxc.persistence.model;

import java.sql.Timestamp;

/**
 * @author astefanov2
 */
public interface Test {

    /**
     * @return the id of the test referring type
     */
    int getTestTypeId();

    /**
     * @return the id of the referring appointment
     */
    int getAppointmentId();

    /**
     * @return the test type of the current test
     */
    TestType getTestType();

    /**
     * @return the appointment by which this test has been created
     */
    Appointment getAppointment();

    /**
     * @return the resulting value of the test
     */
    Double getVal();

    /**
     * @return the description/characteristics of the test
     */
    String getDescription();

    /**
     * @return the day until which the test has to be performed
     */
    Timestamp getDueDate();

    /**
     * @return the day in which the Test is performed
     */
    Timestamp getTestDate();
}
