/**
 *
 */
package com.dxc.medxc.persistence.jpa.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Patient;

/**
 * @author amirchev
 */
@Entity
@Table(name = "PATIENT")
public class JpaPatient implements Patient {

    @Id
    // @GeneratedValue
    @Column(name = "PIN", nullable = false, length = 10)
    private String pin;

    @Column(name = "EMAIL", length = 40)
    private String email;

    @Column(name = "NAME", nullable = false, length = 70)
    private String name;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Column(name = "SEX", length = 1)
    private char sex;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    // TODO add cascade, fetch..
    @OneToMany(targetEntity = JpaAppointment.class, mappedBy = "patient")
    private List<Appointment> appointments;

    /**
     * Default constructor
     */
    public JpaPatient() {

    }

    /**
     * @param pin
     *            patient pin
     * @param email
     *            patient email
     * @param name
     *            patient email
     * @param phone
     *            patient phone
     * @param sex
     *            patient sex
     * @param address
     *            patient address
     * @param appointments
     *            patient appointments
     */
    public JpaPatient(final String pin, final String email, final String name, final String phone, final char sex,
            final String address, final List<Appointment> appointments) {
        this.pin = pin;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.address = address;
        this.appointments = appointments == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
    }

    @Override
    public String getPIN() {
        return this.pin.trim();
    }

    @Override
    public String getEmail() {
        return this.email.trim();
    }

    @Override
    public String getName() {
        return this.name.trim();
    }

    @Override
    public String getPhoneNumber() {
        return this.phone.trim();
    }

    @Override
    public char getSex() {
        return this.sex;
    }

    @Override
    public String getAddress() {
        return this.address == null ? null : this.address.trim();
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointments == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
    }
}
