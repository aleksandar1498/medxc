/**
 *
 */
package com.dxc.medxc.persistence.jpa.repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dxc.medxc.persistence.jpa.entities.JpaAppointment;
import com.dxc.medxc.persistence.jpa.entities.JpaDocSpec;
import com.dxc.medxc.persistence.jpa.entities.JpaDoctor;
import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.DocSpec;
import com.dxc.medxc.persistence.model.Doctor;
import com.dxc.medxc.persistence.repository.DoctorRepository;

/**
 * @author mhristov2
 */
public class JpaDoctorRepository implements DoctorRepository {
    private final static String DOCTOR_FIELD = "doctor"; //$NON-NLS-1$
    private final static String DATE_FIELD = "date"; //$NON-NLS-1$
    private final static String DOCNUM_FIELD = "docNum"; //$NON-NLS-1$

    private final EntityManager manager;

    /**
     * @param manager The manager of the repository
     */
    public JpaDoctorRepository(final EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @return All the doctors in the system.
     */
    @Override
    public List<Doctor> getAllDoctors() {
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Doctor> criteriaQuery = cb.createQuery(Doctor.class);
        final Root<JpaDoctor> from = criteriaQuery.from(JpaDoctor.class);
        criteriaQuery.select(from);
        return this.manager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * @param id The id of the doctor whose appointments are to be returned.
     * @return The appointments of the doctor with the given id.
     */
    @Override
    public List<Appointment> findAllAppointmentsOfDoctorWithId(final String id) {
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Appointment> criteriaQuery = cb.createQuery(Appointment.class);
        final Root<JpaAppointment> from = criteriaQuery.from(JpaAppointment.class);

        criteriaQuery.select(from).where(cb.equal(from.get(DOCTOR_FIELD).<String>get(DOCNUM_FIELD), id));
        criteriaQuery.orderBy(cb.desc(from.get(DATE_FIELD)));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * @param id The id of the doctor whose specialties are to be returned.
     * @return The specialties of the doctor with the given id.
     */
    @Override
    public List<DocSpec> findAllDoctorSpecialtiesOfDoctorWithId(final String id) {
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<DocSpec> criteriaQuery = cb.createQuery(DocSpec.class);
        final Root<JpaDocSpec> from = criteriaQuery.from(JpaDocSpec.class);

        criteriaQuery.select(from).where(cb.equal(from.get(DOCTOR_FIELD).<String>get(DOCNUM_FIELD), id));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Appointment> findAppointmentsByDoctorIdAndFromToDates(final String id, final ZonedDateTime fromDate,
            final ZonedDateTime toDate) {

        final Timestamp fromTimestamp = Timestamp.valueOf(fromDate.toLocalDateTime());
        final Timestamp toTimestamp = Timestamp.valueOf(toDate.toLocalDateTime());

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Appointment> criteriaQuery = cb.createQuery(Appointment.class);
        final Root<JpaAppointment> from = criteriaQuery.from(JpaAppointment.class);

        criteriaQuery.select(from).where(cb.and(cb.equal(from.get(DOCTOR_FIELD).<String>get(DOCNUM_FIELD), id),
                cb.between(from.get(DATE_FIELD), fromTimestamp, toTimestamp)));
        criteriaQuery.orderBy(cb.desc(from.get(DATE_FIELD)));

        // cb.greaterThanOrEqualTo(from.get("date"), fromTimestamp),
        // cb.lessThanOrEqualTo(from.get("date"), toTimestamp)

        return manager.createQuery(criteriaQuery).getResultList();
    }
}
