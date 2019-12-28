
/**
 *
 */
package com.dxc.medxc.persistence.jpa.entities;

import java.util.Objects;

import javax.persistence.*;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Record;

/**
 * SELECT * FROM APP_RECORD INNER JOIN APPOINTMENT ON APPOINTMENT.APP_ID = APP_RECORD.APP_ID WHERE APPOINTMENT.PIN = '8211125555';
 * @author yyayya
 */
@Entity
@Table(name = "APP_RECORD")
@NamedQuery(name = JpaRecord.RECORDS_BY_PATIENT_PIN,
        query = "SELECT r FROM JpaRecord r INNER JOIN JpaAppointment a ON a.id = r.appointment.id WHERE a.patient.pin =:id")
public class JpaRecord implements Record {

    public static final String RECORDS_BY_PATIENT_PIN = "recordsRelatedToPatient";
    @Id
    private int id;

    @MapsId
    @OneToOne(targetEntity = JpaAppointment.class)
    @JoinColumn(name = "APP_ID")
    private Appointment appointment;

    @Column(name = "ANAMNESIS", length = 200)
    private String anamnesis;

    @Column(name = "OBSERVATION", length = 200)
    private String observation;

    @Column(name = "THERAPY", length = 200)
    private String therapy;

    /**
     * Default constructor
     */
    public JpaRecord() {

    }

    /**
     * @param appointment
     *            appointment when record was made
     * @param anamnesis
     *            anamnesis
     * @param observation
     *            observation
     * @param therapy
     *            ordered therapy
     */
    public JpaRecord(final Appointment appointment, final String anamnesis, final String observation,
            final String therapy) {
        this.id = appointment.getId();
        this.appointment = appointment;
        this.anamnesis = anamnesis;
        this.observation = observation;
        this.therapy = therapy;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getAnamnesis() {
        return this.anamnesis;
    }

    @Override
    public String getObservation() {
        return this.observation;
    }

    @Override
    public String getTherapy() {
        return this.therapy;
    }

    @Override
    public Appointment getAppointment() {
        return this.appointment;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(appointment, anamnesis, observation, therapy);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final JpaRecord other = (JpaRecord) obj;
        return /* Objects.equals(appointment, other.appointment) && */Objects.equals(anamnesis, other.anamnesis)
                && Objects.equals(observation, other.observation) && Objects.equals(therapy, other.therapy);
    }
}
