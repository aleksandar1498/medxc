package com.dxc.medxc.persistence.jpa.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author astefanov2
 */
@Embeddable
public class TestCompositeKey implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6551179603999138478L;

    @Column(name = "TTYPE_ID", nullable = false)
    private int testTypeId;

    @Column(name = "APP_ID", nullable = false)
    private int appointmentId;

    /**
     *
     */
    public TestCompositeKey() {

    }

    /**
     *
     * @param testTypeId Id of the test type
     * @param appointmentId Id of the appointment
     */
    public TestCompositeKey(final int testTypeId, final int appointmentId) {
        this.testTypeId = testTypeId;
        this.appointmentId = appointmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.testTypeId), Integer.valueOf(this.appointmentId));
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        final TestCompositeKey testCompositeKey = (TestCompositeKey) other;

        return Objects.equals(Integer.valueOf(this.testTypeId), Integer.valueOf(testCompositeKey.testTypeId))
                && Objects.equals(Integer.valueOf(this.appointmentId), Integer.valueOf(testCompositeKey.appointmentId));
    }

    /**
     * @return the testTypeId
     */
    public int getTestTypeId() {
        return this.testTypeId;
    }

    /**
     * @return the appointmentId
     */
    public int getAppointmentId() {
        return this.appointmentId;
    }

}
