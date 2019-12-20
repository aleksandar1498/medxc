/**
 *
 *
 */
package com.dxc.medxc.persistence.jpa.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.Status;

/**
 *
 * @author yyayya
 */

@Entity
@Table(name = "STATUS")
public class JpaStatus implements Status {

    @Id
    @GeneratedValue
    @Column(name = "STATUS_ID", nullable = false)
    private int id;

    @Column(name = "STATUS_NAME", nullable = false, length = 10)
    private String name;

    /**
     * Empty Constructor
     */
    public JpaStatus() {
    }

    /**
     * @param name Name of the status
     */
    public JpaStatus(final String name) {
        Objects.requireNonNull(name);

        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name.trim();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        final JpaStatus statusOther = (JpaStatus) other;

        // TODO fix equals
        return this.name.equals(statusOther.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
