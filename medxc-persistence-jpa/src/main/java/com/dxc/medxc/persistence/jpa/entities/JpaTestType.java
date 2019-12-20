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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.medxc.persistence.model.Test;
import com.dxc.medxc.persistence.model.TestType;

/**
 * @author astefanov2
 */
@Entity
@Table(name = "TESTTYPE")
public class JpaTestType implements TestType {

    @Id
    @GeneratedValue
    @Column(name = "TTYPE_ID", nullable = false)
    private int id;

    @Column(name = "TEST_NAME", nullable = false)
    private String name;

    @Column(name = "REF_LIMITS")
    private String refLimits;

    @OneToMany(targetEntity = JpaTest.class, mappedBy = "testType")
    private List<Test> tests;

    /**
     * Needed by JPA
     */
    public JpaTestType() {
    }

    /**
     * @param id        the id of the test
     * @param name      the name of the test
     * @param refLimits the referent limits for the test
     * @param tests     tests created of this type
     */
    public JpaTestType(final int id, final String name, final String refLimits) {
        Objects.requireNonNull(Integer.valueOf(id));
        Objects.requireNonNull(name);
        Objects.requireNonNull(refLimits);

        this.id = id;
        this.name = name;
        this.refLimits = refLimits;
        // this.tests = t_tests;
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
    public String getRefLimits() {
        return this.refLimits;
    }

    @Override
    public List<Test> getTests() {
        return tests == null ? new ArrayList<>() : Collections.unmodifiableList(tests);
    }

}
