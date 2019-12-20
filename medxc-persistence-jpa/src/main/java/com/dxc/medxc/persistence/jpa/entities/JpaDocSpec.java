package com.dxc.medxc.persistence.jpa.entities;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.DocSpec;
import com.dxc.medxc.persistence.model.Doctor;
import com.dxc.medxc.persistence.model.Specialty;

/**
 * @author lnikolaeva
 */
@Entity
@Table(name = "DOC_SPEC")
public class JpaDocSpec implements DocSpec {
    @EmbeddedId
    private DocSpecCompositeKey docSpecKey;

    @Column(name = "DATE")
    private Timestamp date;

    @Column(name = "START_HOUR")
    private Timestamp startHour;

    @Column(name = "NUM_APPOINT", length = 2)
    private int numAppoint;

    @Column(name = "DUR_APPOINT", length = 3)
    private int durAppoint;

    @ManyToOne(targetEntity = JpaDoctor.class)
    @MapsId("docNum")
    @JoinColumn(name = "DOC_NUM")
    private Doctor doctor;

    @ManyToOne(targetEntity = JpaSpecialty.class)
    @MapsId("specId")
    @JoinColumn(name = "SPEC_ID")
    private Specialty specialty;

    /**
     * default constructor
     */
    public JpaDocSpec() {

    }

    /**
     * @param doctor
     *            doctor
     * @param specialty
     *            doctor's specialty
     * @param date
     *            date
     * @param startHour
     *            doctor's start working hour
     * @param numAppoint
     *            number of appointments per day
     * @param durAppoint
     *            duration of appointment
     */
    public JpaDocSpec(final Doctor doctor, final Specialty specialty, final Timestamp date, final Timestamp startHour,
            final int numAppoint, final int durAppoint) {
        this.docSpecKey = new DocSpecCompositeKey(doctor.getDocNum(), specialty.getID(), 0);
        this.date = date;
        this.startHour = startHour;
        this.numAppoint = numAppoint;
        this.durAppoint = durAppoint;

    }

    /**
     * @param docSpecKey
     *            composite key of the doctor
     * @param date
     *            date doctor's of schedule
     * @param startHour
     *            starting hour of the date
     * @param numAppoint
     *            number of appointments for the date
     * @param durAppoint
     *            the duration of appointment for the date
     * @param doctor
     *            doctor
     * @param specialty
     *            doctor's specialty
     */
    public JpaDocSpec(final DocSpecCompositeKey docSpecKey, final Timestamp date, final Timestamp startHour,
            final int numAppoint, final int durAppoint, final JpaDoctor doctor, final JpaSpecialty specialty) {

        super();

        Objects.requireNonNull(docSpecKey);
        Objects.requireNonNull(date);
        Objects.requireNonNull(startHour);
        Objects.requireNonNull(Integer.valueOf(numAppoint));
        Objects.requireNonNull(Integer.valueOf(durAppoint));
        Objects.requireNonNull(doctor);
        Objects.requireNonNull(specialty);


        this.docSpecKey = docSpecKey;
        this.date = (Timestamp)date.clone();
        this.startHour = (Timestamp)startHour.clone();
        this.numAppoint = numAppoint;
        this.durAppoint = durAppoint;
        this.doctor = doctor;
        this.specialty = specialty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, docSpecKey, doctor, Integer.valueOf(durAppoint), Integer.valueOf(numAppoint), specialty, startHour);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final JpaDocSpec other = (JpaDocSpec) obj;
        return Objects.equals(date, other.date) && Objects.equals(docSpecKey, other.docSpecKey)
                && Objects.equals(doctor, other.doctor) && durAppoint == other.durAppoint
                && numAppoint == other.numAppoint && Objects.equals(specialty, other.specialty)
                && Objects.equals(startHour, other.startHour);
    }

    @Override
    public Specialty getSpecialty() {
        return specialty;
    }

}
