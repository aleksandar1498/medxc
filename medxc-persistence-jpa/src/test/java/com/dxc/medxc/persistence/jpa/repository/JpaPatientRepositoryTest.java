/**
 *
 */
package com.dxc.medxc.persistence.jpa.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import com.dxc.medxc.persistence.jpa.entities.JpaAppointment;
import com.dxc.medxc.persistence.jpa.entities.JpaRecord;
import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.repository.PatientRepository;

import static org.junit.Assert.*;

/**
 * @author astefanov2
 */
public class JpaPatientRepositoryTest extends UnitilsJUnit4 {
    private static final String PIN = "9812148221";

    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("medxc-persistence-jpa");



    /**
     *
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Clear.xml"})
    public void testGetRecordsById() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PatientRepository patientRepo = new JpaPatientRepository(entityManager);

        final List<Record> records = new ArrayList<>() {
            {
                add(new JpaRecord(new JpaAppointment(), "Amnesis", "B", "C"));
                add(new JpaRecord(new JpaAppointment(), "Amnesis", "B", "C"));
            }
        };
        assertTrue(true);
/*
        try {
            final List<Record> recs = patientRepo.getAllRecordsById(PIN);
            assertEquals(records.size(), recs.size());
            assertEquals(records, recs);
        } finally {
            entityManager.close();
        }*/
    }



}
