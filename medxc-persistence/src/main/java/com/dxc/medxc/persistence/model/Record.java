/**
 *
 */
package com.dxc.medxc.persistence.model;

/**
 * @author yyayya
 */
public interface Record {
    /**
     * @return Id of the record
     */
    int getId();

    /**
     * @return anamnesis from record
     */
    String getAnamnesis();

    /**
     * @return observation from record
     */
    String getObservation();

    /**
     * @return assigned therapy from record
     */
    String getTherapy();

    /**
     * @return appointment when the record was made
     */
    Appointment getAppointment();
}
