/**
 *
 */
package com.dxc.medxc.persistence.jpa.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.dxc.medxc.persistence.jpa.entities.JpaAppointment;
import com.dxc.medxc.persistence.jpa.entities.JpaPatient;
import com.dxc.medxc.persistence.jpa.entities.JpaRecord;
import com.dxc.medxc.persistence.jpa.entities.JpaSpecialty;
import com.dxc.medxc.persistence.jpa.entities.JpaTest;
import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Patient;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.model.Specialty;
import com.dxc.medxc.persistence.model.Test;
import com.dxc.medxc.persistence.repository.PatientRepository;

/**
 * @author yyayya
 */
public class JpaPatientRepository implements PatientRepository {
    @SuppressWarnings("nls")
    private final static String APPOINTMENT_PATIENT_FIELD = "patient"; //$NON-NLS-1$
    private final static String SPECIALTY_FIELD = "specialty"; //$NON-NLS-1$
    private final static String NAME_FIELD = "name"; //$NON-NLS-1$
    private final static String PIN_FIELD = "pin"; //$NON-NLS-1$
    private final static String APPOINTMENT_FIELD = "appointment"; //$NON-NLS-1$
    private final static String ID_FIELD = "id"; //$NON-NLS-1$

    private final EntityManager manager;

    /**
     * Constructor for the Repository
     *
     * @param manager
     */
    public JpaPatientRepository(final EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Patient> getAllPatients() {

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Patient> criteriaQuery = cb.createQuery(Patient.class);
        final Root<JpaPatient> from = criteriaQuery.from(JpaPatient.class);
        criteriaQuery.select(from);
        return this.manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Appointment> getAllAppointmentsByIdAndSpecialty(final String id, final String specialty) {

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Appointment> criteriaQuery = cb.createQuery(Appointment.class);
        final Root<JpaAppointment> from = criteriaQuery.from(JpaAppointment.class);

        final Expression<String> path = from.get(SPECIALTY_FIELD).<String>get(NAME_FIELD);
        final Expression<String> t2 = cb.trim(' ', path);

        criteriaQuery.select(from).where(cb.and(
                cb.equal(from.get(APPOINTMENT_PATIENT_FIELD).<String>get(PIN_FIELD), id), cb.equal(t2, specialty)));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Appointment> getAllAppointmentsById(final String id) {

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Appointment> criteriaQuery = cb.createQuery(Appointment.class);
        final Root<JpaAppointment> from = criteriaQuery.from(JpaAppointment.class);

        criteriaQuery.select(from).where(cb.equal(from.get(APPOINTMENT_PATIENT_FIELD).<String>get(PIN_FIELD), id));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Specialty> getAllSpecialtiesByAppointments(final String idPat) {

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Specialty> criteriaQuery = cb.createQuery(Specialty.class);
        final Root<JpaSpecialty> from = criteriaQuery.from(JpaSpecialty.class);

        final Subquery<JpaAppointment> subquery = criteriaQuery.subquery(JpaAppointment.class);
        final Root<JpaAppointment> fromSub = subquery.from(JpaAppointment.class);

        subquery.select(fromSub)
                .where(cb.and(cb.equal(fromSub.get(APPOINTMENT_PATIENT_FIELD).<String>get(PIN_FIELD), idPat),
                        cb.equal(fromSub.get(SPECIALTY_FIELD).<Integer>get(ID_FIELD), from.get(ID_FIELD))));

        criteriaQuery.select(from).where(cb.exists(subquery));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    /*
     * @SuppressWarnings("nls")
     * @Override public List<Specialty> getAllSpecialties() { return new
     * ArrayList<>(List.of(new JpaSpecialty("Neurologist"), new
     * JpaSpecialty("Cardiologist"))); }
     */
    @Override
    public List<Record> getAllRecordsById(final String id) {
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Record> criteriaQuery = cb.createQuery(Record.class);
        final Root<JpaRecord> recFrom = criteriaQuery.from(JpaRecord.class);

        criteriaQuery.select(recFrom).where(
                cb.equal(recFrom.get(APPOINTMENT_FIELD).get(APPOINTMENT_PATIENT_FIELD).<String>get(PIN_FIELD),
                        id));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Test> getAllTestsById(final String id) {

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Test> criteriaQuery = cb.createQuery(Test.class);
        final Root<JpaTest> testFrom = criteriaQuery.from(JpaTest.class);

        criteriaQuery.select(testFrom).where(
                cb.equal(testFrom.get(APPOINTMENT_FIELD).get(APPOINTMENT_PATIENT_FIELD).<String>get(PIN_FIELD), id));

        return manager.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public List<Test> getTestsByIdAfterDueDate(final String id) {

        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Test> criteriaQuery = cb.createQuery(Test.class);
        final Root<JpaTest> testRoot = criteriaQuery.from(JpaTest.class);

        criteriaQuery.select(testRoot)
                .where(cb.and(
                        cb.equal(testRoot.get(APPOINTMENT_FIELD).get(APPOINTMENT_PATIENT_FIELD).<String>get(PIN_FIELD),
                                id),
                        cb.greaterThanOrEqualTo(testRoot.get("dueDate"), new Timestamp(System.currentTimeMillis())))); //$NON-NLS-1$

        return manager.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public Boolean registerPatient(final String pin, final String email, final String name, final String phone,
            final char sex, final String address, final List<Appointment> appointments) {
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Patient> criteriaQuery = cb.createQuery(Patient.class);
        final Root<JpaPatient> from = criteriaQuery.from(JpaPatient.class);
        final Expression<String> expr = cb.trim(' ', from.get(PIN_FIELD));
        criteriaQuery.select(from).where(cb.equal(expr, pin));

        if (!manager.createQuery(criteriaQuery).getResultList().isEmpty()) {
            return Boolean.FALSE;
        }

        final Patient patient = new JpaPatient(pin, email, name, phone, sex, address, null);
        manager.persist(patient);
        return Boolean.TRUE;
    }
}
