/**
 *
 */
package com.dxc.medxc.persistence.jpa.repository;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.repository.DoctorRepository;

/**
 * @author mhristov2
 */
public class JpaDoctorRepositoryTest extends UnitilsJUnit4 {

    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("medxc-persistence-jpa");

    /**
     *
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Clear.xml", "testFindAppointmentsByDocNumFromToDateInit.xml" })
    public void findAllAppointmentsOfDoctorWithIdTest() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final DoctorRepository doctorRepository = new JpaDoctorRepository(entityManager);

        final String utc = "UTC";
        final String docNum = "0055555555";
        final String from = "2019-10-31";
        final String to = "2019-12-30";
        final String pin = "9812148221";
        final int statusId = 4;
        final int specId = 35;
        final int appIdA = 1;
        final int appIdB = 2;

        final ZonedDateTime fromDate = ZonedDateTime.of(LocalDate.parse(from), LocalTime.of(0, 0, 0, 0),
                ZoneId.of(utc));
        final ZonedDateTime toDate = ZonedDateTime.of(LocalDate.parse(to), LocalTime.of(23, 59, 59, 999_999_999),
                ZoneId.of(utc));

        final Timestamp timeStampA = Timestamp.valueOf("2019-12-22 23:59:00.0");
        final Timestamp timeStampB = Timestamp.valueOf("2019-12-20 23:59:00.0");

        try {
            final List<Appointment> apps = doctorRepository.findAppointmentsByDoctorIdAndFromToDates(docNum, fromDate,
                    toDate);
            assertEquals(2, apps.size());
            assertEquals(timeStampA, apps.get(0).getDate());
            assertEquals(timeStampB, apps.get(1).getDate());
            assertEquals(docNum, apps.get(0).getDocNum().getDocNum());
            assertEquals(docNum, apps.get(1).getDocNum().getDocNum());
            assertEquals(pin, apps.get(0).getPIN().getPIN());
            assertEquals(pin, apps.get(1).getPIN().getPIN());
            assertEquals(specId, apps.get(0).getSpecialty().getID());
            assertEquals(specId, apps.get(1).getSpecialty().getID());
            assertEquals(statusId, apps.get(0).getStatus().getId());
            assertEquals(statusId, apps.get(1).getStatus().getId());
            assertEquals(appIdB, apps.get(0).getId());
            assertEquals(appIdA, apps.get(1).getId());

        } finally {
            entityManager.close();
        }
    }
}
