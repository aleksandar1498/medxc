/**
 *
 */
package com.dxc.medxc.persistence.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    @DataSet({ "Clear.xml", "AppointmentsDataInput.xml" })
    public void testGetAllAppointmentsById() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PatientRepository patientRepo = new JpaPatientRepository(entityManager);

        try {
            final List<Appointment> apps = patientRepo.getAllAppointmentsById(PIN);
            assertEquals(2, apps.size());

            final String patId = PIN;
            final String docId = "0055555555";
            final int specId = 35;
            final int statId = 4;
            final int[] recId = { 1, 2 };

            for (int i = 0; i < apps.size(); i++) {
                assertEquals(apps.get(i).getStatus().getId(), statId);
                assertEquals(apps.get(i).getPatient().getPIN(), patId);
                assertEquals(apps.get(i).getDoctor().getDocNum(), docId);
                assertEquals(apps.get(i).getSpecialty().getID(), specId);
                assertEquals(apps.get(i).getRecord().getId(), recId[i]);
                assertEquals(apps.get(i).getDate(), Timestamp.valueOf("2019-12-20 23:59:00.0"));
            }
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    @DataSet({ "Clear.xml", "AppointmentsDataInput.xml" })
    public void testGetAllAppointmentsByIdAndSpecialty() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PatientRepository patientRepo = new JpaPatientRepository(entityManager);

        try {
            final List<Appointment> apps = patientRepo.getAllAppointmentsByIdAndSpecialty(PIN,
                    "Family Practice");
            assertEquals(2, apps.size());

            final String patId = PIN;
            final String docId = "0055555555";
            final int specId = 35;
            final int statId = 4;
            final int[] recId = { 1, 2 };

            for (int i = 0; i < apps.size(); i++) {
                assertEquals(apps.get(i).getStatus().getId(), statId);
                assertEquals(apps.get(i).getPatient().getPIN(), patId);
                assertEquals(apps.get(i).getDoctor().getDocNum(), docId);
                assertEquals(apps.get(i).getSpecialty().getID(), specId);
                assertEquals(apps.get(i).getRecord().getId(), recId[i]);
                assertEquals(apps.get(i).getDate(), Timestamp.valueOf("2019-12-20 23:59:00.0"));
            }
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Clear.xml", "RecordsDataInput.xml" })
    public void testGetRecordsById() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PatientRepository patientRepo = new JpaPatientRepository(entityManager);

        final List<Record> records = new ArrayList<>() {
            {
                add(new JpaRecord(new JpaAppointment(), "Amnesis", "B", "C"));
                add(new JpaRecord(new JpaAppointment(), "Amnesis", "B", "C"));
            }
        };

        try {
            final List<Record> recs = patientRepo.getAllRecordsById(PIN);
            assertEquals(records.size(), recs.size());
            assertEquals(records, recs);
        } finally {
            entityManager.close();
        }
    }

    /**
     * method for testing
     */
    @SuppressWarnings("static-method")
    @Test
    @DataSet({ "Clear.xml", "TestsByPatientId.xml" })
    public void testFindAllTests() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PatientRepository patientRepo = new JpaPatientRepository(entityManager);
        try {
            final List<com.dxc.medxc.persistence.model.Test> testsResult = patientRepo.getAllTestsById("8211125555");
            assertNotNull(testsResult);
            // assertEquals(1, testsResult.size());

        } finally {
            entityManager.close();

        }
    }
}
