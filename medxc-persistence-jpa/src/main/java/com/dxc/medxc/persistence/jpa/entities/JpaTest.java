package com.dxc.medxc.persistence.jpa.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Test;
import com.dxc.medxc.persistence.model.TestType;

/**
 * @author astefanov2
 */
@Entity
@Table(name = "APP_TEST")
public class JpaTest implements Test {

    @EmbeddedId
    private TestCompositeKey testId;

    @ManyToOne(targetEntity = JpaAppointment.class)
    @MapsId("appointmentId")
    @JoinColumn(name = "APP_ID")
    private Appointment appointment;

    @ManyToOne(targetEntity = JpaTestType.class)
    @MapsId("testTypeId")
    @JoinColumn(name = "TTYPE_ID")
    private TestType testType;

    @Column(name = "VAL")
    private Double val;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DUE_DATE")
    private Timestamp dueDate;

    @Column(name = "TEST_DATE")
    private Timestamp testDate;

    /**
     * needed by JPA
     */
    public JpaTest() {
    }

    /**
     * @param testId      is the id for the test , it is a composite key
     * @param appointment in which this test has been created
     * @param testType    of the current test
     * @param val         is the result of the test
     * @param description of the test
     * @param dueDate     Date until which the test should be performed
     * @param testDate    Date in which the test is performed
     */
    public JpaTest(final TestCompositeKey testId, final Appointment appointment, final JpaTestType testType,
            final Double val, final String description, final Timestamp dueDate, final Timestamp testDate) {
        this.testId = testId;

        this.appointment = appointment;
        this.testType = testType;
        this.val = val;
        this.description = description;
        this.dueDate = (Timestamp) dueDate.clone();
        this.testDate = (Timestamp) testDate.clone();
    }

    /**
     * @param testType Type of the test
     * @param dueDate  Due date of the test
     */
    public JpaTest(final TestType testType, final Timestamp dueDate) {
        this.testType = testType;
        this.dueDate = (Timestamp) dueDate.clone();
    }

    @Override
    public int getTestTypeId() {
        return testId.getTestTypeId();
    }

    @Override
    public int getAppointmentId() {
        return testId.getAppointmentId();
    }

    @Override
    public Double getVal() {
        return this.val;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Timestamp getDueDate() {
        return (Timestamp) this.dueDate.clone();
    }

    @Override
    public Timestamp getTestDate() {
        if (testDate == null) {
            return null;
        }
        return (Timestamp) this.testDate.clone();
    }

    @Override
    public TestType getTestType() {
        return testType;
    }

    @Override
    public Appointment getAppointment() {
        return appointment;
    }

}
