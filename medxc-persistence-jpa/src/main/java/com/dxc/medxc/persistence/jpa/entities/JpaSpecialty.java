
package com.dxc.medxc.persistence.jpa.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.DocSpec;
import com.dxc.medxc.persistence.model.Specialty;

/**
 * @author atsekov
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Specialty.getIdByName", query = "SELECT a.id FROM JpaSpecialty a WHERE trim(a.name) = :name"),
    @NamedQuery(name = "Specialty.getAllNames", query = "SELECT a.name FROM JpaSpecialty a") })
@Table(name = "SPECIALTY")
public class JpaSpecialty implements Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPEC_ID")
    private int id;

    @Column(name = "SPEC_NAME", nullable = false, length = 50)
    private String name;

    @OneToMany(targetEntity = JpaDocSpec.class, mappedBy = "specialty")
    private List<DocSpec> doctors;

    /**
     * default constructor
     */
    public JpaSpecialty() {

    }

    /**
     * @param name specialty name
     */
    public JpaSpecialty(final String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * @param name    specialty name
     * @param doctors doctors with this specialty
     */
    public JpaSpecialty(final String name, final List<DocSpec> doctors) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final JpaSpecialty other = (JpaSpecialty) obj;
        // TODO fix equals
        return Objects.equals(name, other.name);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name.trim();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setDocSpec(final List<DocSpec> doctors) {
        this.doctors = doctors == null ? new ArrayList<>() : Collections.unmodifiableList(doctors);
    }
}
