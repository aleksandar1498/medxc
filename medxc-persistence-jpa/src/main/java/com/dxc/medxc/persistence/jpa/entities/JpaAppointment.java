/**
 *
 */
package com.dxc.medxc.persistence.jpa.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Doctor;
import com.dxc.medxc.persistence.model.Patient;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.model.Specialty;
import com.dxc.medxc.persistence.model.Status;
import com.dxc.medxc.persistence.model.Test;

/**
 * Appointment entity
 *
 * @author yyayya
 */
@Entity
@Table(name = "APPOINTMENT")
public class JpaAppointment implements Appointment {
    @Id
    @GeneratedValue
    @Column(name = "APP_ID", nullable = false)
    private int id;

    @Column(name = "APP_DATE_TIME", nullable = false)
    private Timestamp date;

    @ManyToOne(targetEntity = JpaStatus.class)
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @ManyToOne(targetEntity = JpaSpecialty.class)
    @JoinColumn(name = "SPEC_ID")
    private Specialty specialty;

    @ManyToOne(targetEntity = JpaPatient.class)
    @JoinColumn(name = "PIN")
    private Patient patient;

    @ManyToOne(targetEntity = JpaDoctor.class)
    @JoinColumn(name = "DOC_NUM")
    private Doctor doctor;

    @OneToMany(targetEntity = JpaTest.class, mappedBy = "appointment")
    private List<Test> tests;

    @OneToOne(targetEntity = JpaRecord.class, mappedBy = "appointment")
    private Record record;

    /**
     * Needed by JPA
     */
    public JpaAppointment() {

    }

    /**
     * @param status
     *            Status of the appointment
     * @param patient
     *            The patient who scheduled the appointment
     * @param doctor
     *            The doctor the appointment is scheduled with
     * @param date
     *            Date of the appointment
     */
    public JpaAppointment(final Status status, final Patient patient, final Doctor doctor, final Timestamp date) {
        Objects.requireNonNull(status);
        Objects.requireNonNull(patient);
        Objects.requireNonNull(doctor);
        Objects.requireNonNull(date);

        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
        this.date = (Timestamp) date.clone();
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Patient getPIN() {
        return patient;
    }

    @Override
    public Doctor getDocNum() {
        return doctor;

    }

    @Override
    public Timestamp getDate() {
        return (Timestamp) date.clone();

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Patient getPatient() {
        return patient;
    }

    @Override
    public Specialty getSpecialty() {
        return specialty;
    }

    @Override
    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public List<Test> getTests() {
        final List<Test> newTests = new ArrayList<>();
        newTests.addAll(this.tests);
        return newTests;
    }

    @Override
    public Record getRecord() {
        return record;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        if (this == other) {
            return true;
        }

        final JpaAppointment appointmentOther = (JpaAppointment) other;

        // TODO fix equals

        return this.date.equals(appointmentOther.getDate()) && this.status.equals(appointmentOther.getStatus())
                && this.specialty.equals(appointmentOther.getSpecialty())
                && this.patient.equals(appointmentOther.getPatient())
                && this.doctor.equals(appointmentOther.getDoctor()) && this.record.equals(appointmentOther.getRecord())
                && this.tests.equals(appointmentOther.getTests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.date, this.status, this.specialty, this.patient, this.doctor, this.patient,
                this.record, this.tests);
    }

}
