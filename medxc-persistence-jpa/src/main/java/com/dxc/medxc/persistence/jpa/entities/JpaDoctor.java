/**
 *
 */
package com.dxc.medxc.persistence.jpa.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.DocSpec;
import com.dxc.medxc.persistence.model.Doctor;

/**
 * @author yyayya
 */
@Entity
@Table(name = "DOCTOR")
public class JpaDoctor implements Doctor {

    @Id
    @Column(name = "DOC_NUM", nullable = false, length = 10)
    private String docNum;

    @Column(name = "DOC_NAME", nullable = false, length = 70)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 20)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phoneNumber;

    @OneToMany(targetEntity = JpaAppointment.class, mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(targetEntity = JpaDocSpec.class, mappedBy = "doctor")
    private List<DocSpec> specialties;

    /**
     * default constructor
     */
    public JpaDoctor() {

    }

    /**
     * @param docNum
     *            doctor's number
     * @param name
     *            doctor's name
     * @param email
     *            doctor's email
     * @param phoneNumber
     *            doctor's phone number
     * @param specialties
     *            doctor's specialties
     * @param appointments
     *            list of doctor's appointments
     */

    public JpaDoctor(final String docNum, final String name, final String email, final String phoneNumber,
            final List<DocSpec> specialties, final List<Appointment> appointments) {
        Objects.requireNonNull(docNum);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phoneNumber);
        this.docNum = docNum;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialties = specialties;
        this.appointments = appointments;
    }

    @Override
    public int hashCode() {
        return Objects.hash(docNum, email, name, phoneNumber);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final JpaDoctor other = (JpaDoctor) obj;
        // TODO Fix equals
        return Objects.equals(docNum, other.docNum)
                && Objects.equals(email, other.email) && Objects.equals(name, other.name)
                && Objects.equals(phoneNumber, other.phoneNumber);
    }

    @Override
    public String getName() {
        return name.trim();
    }

    @Override
    public String getEmail() {
        return email.trim();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.trim();
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointments == null ? new ArrayList<>() : Collections.unmodifiableList(appointments);
    }

    @Override
    public List<DocSpec> getSpecialties() {
        return specialties == null ? new ArrayList<>() : Collections.unmodifiableList(specialties);
    }

    @Override
    public String getDocNum() {
        return docNum.trim();
    }

    @Override
    public void setDocSpec(final List<DocSpec> specialties) {
        this.specialties = Collections.unmodifiableList(specialties);
    }
}
