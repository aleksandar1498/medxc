package com.dxc.medxc.implementation;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.converters.ObjectConverter;
import com.dxc.medxc.dto.AppointmentDTO;
import com.dxc.medxc.dto.DoctorDTO;
import com.dxc.medxc.dto.PatientDTO;
import com.dxc.medxc.dto.PatientHistoryDTO;
import com.dxc.medxc.dto.RecordDTO;
import com.dxc.medxc.dto.SpecialtyDTO;
import com.dxc.medxc.dto.TestDTO;
import com.dxc.medxc.dto.TestTypeDTO;
import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.Patient;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.model.Specialty;
import com.dxc.medxc.persistence.model.Test;
import com.dxc.medxc.persistence.repository.PatientRepository;
import com.dxc.medxc.persistence.transaction.TransactionManager;
import com.dxc.medxc.services.PatientService;
import com.dxc.medxc.validators.models.rules.StringConstraintRule;
import com.dxc.medxc.validators.models.rules.StringFormatRule;
import com.dxc.medxc.validators.models.validators.BaseValidator;

/**
 * @author mhristov2
 */

public class PatientServiceImpl implements PatientService {

    private final static int ZERO = 0;
    private final static int TEN = 10;

    private static final String UTC = "UTC"; //$NON-NLS-1$
    private final TransactionManager manager;

    /**
     * Implementation of the patient services.
     *
     * @param manager
     *            TransactionManager passed from init module
     */
    public PatientServiceImpl(final TransactionManager manager) {
        this.manager = manager;
    }

    @SuppressWarnings("nls")
    @Override
    public List<RecordDTO> getAllMedicalRecords(final String id) {
        final BaseValidator validator = new BaseValidator();
        validator.validate("PAT_ID", id, new StringConstraintRule(ZERO, TEN));
        validator.validate("PAT_ID", id, new StringFormatRule("[0-9]{0,10}"));
        validator.checkValidated();
        return this.manager.execute((final Store s) -> {
            final PatientRepository rep = s.find(PatientRepository.class);
            final List<Record> recs = rep.getAllRecordsById(id);
            return ObjectConverter.convertList(r -> new RecordDTO(r.getAnamnesis(), r.getObservation(), r.getTherapy()),
                    recs);
        });
    }

    private static List<AppointmentDTO> convertJpaAppointmentsToDTO(final List<Appointment> list) {
        return ObjectConverter.convertList(PatientServiceImpl::createAppointmentDto, list);
    }

    @SuppressWarnings("nls")
    private static AppointmentDTO createAppointmentDto(final Appointment jpaObj) {
        final Record rec = jpaObj.getRecord();
        final RecordDTO newRecordDTO = rec == null ? new RecordDTO()
                : new RecordDTO(rec.getAnamnesis(), rec.getObservation(), rec.getTherapy());
        return new AppointmentDTO(jpaObj.getDate().toLocalDateTime().atZone(ZoneId.of("UTC")),
                jpaObj.getStatus().getName(), jpaObj.getSpecialty().getName(), newRecordDTO,
                new DoctorDTO(jpaObj.getDoctor().getDocNum(), jpaObj.getDoctor().getName(),
                        jpaObj.getDoctor().getEmail(), null, true, null, null),
                new PatientDTO(jpaObj.getPatient().getPIN(), jpaObj.getPatient().getName(),
                        jpaObj.getPatient().getEmail(), jpaObj.getPatient().getPhoneNumber(), new PatientHistoryDTO()),
                new ArrayList<TestDTO>());

    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByIdAndSpecialty(final String id, final String specialty) {
        return this.manager.execute((final Store store) -> {
            final PatientRepository repo = store.find(PatientRepository.class);
            final List<Appointment> list = repo.getAllAppointmentsByIdAndSpecialty(id, specialty);
            return convertJpaAppointmentsToDTO(list);
        });
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsById(final String id) {
        return this.manager.execute((final Store store) -> {
            final PatientRepository repo = store.find(PatientRepository.class);
            final List<Appointment> list = repo.getAllAppointmentsById(id);
            return convertJpaAppointmentsToDTO(list);
        });
    }

    @Override
    public List<SpecialtyDTO> getAllSpecialtiesByAppointments(final String id) {
        return this.manager.execute((final Store store) -> {
            final PatientRepository repo = store.find(PatientRepository.class);
            final List<Specialty> list = repo.getAllSpecialtiesByAppointments(id);
            return ObjectConverter.convertList(jpaObj -> new SpecialtyDTO(jpaObj.getName()), list);
        });

    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return this.manager.execute((final Store d) -> {
            final PatientRepository r = d.find(PatientRepository.class);
            final List<Patient> patientLs = r.getAllPatients();
            return ObjectConverter.convertList(jpaObj -> new PatientDTO(jpaObj.getPIN(), jpaObj.getName(),
                    jpaObj.getEmail(), jpaObj.getPhoneNumber(), jpaObj.getSex(), jpaObj.getAddress(),
                    convertJpaAppointmentsToDTO(jpaObj.getAppointments())), patientLs);
        });
    }

    @Override
    public PatientHistoryDTO viewPatientHistory(final String id) {

        return this.manager.execute((final Store s) -> {
            final PatientRepository rep = s.find(PatientRepository.class);
            final List<Record> rs = rep.getAllRecordsById(id);
            final List<Test> ts = rep.getAllTestsById(id);

            final List<RecordDTO> records = ObjectConverter
                    .convertList(r -> new RecordDTO(r.getAnamnesis(), r.getObservation(), r.getTherapy()), rs);

            final List<TestDTO> tests = ObjectConverter.convertList(t -> createTestDTO(t), ts);

            return new PatientHistoryDTO(records, tests);
        });
    }

    @Override
    public List<TestDTO> getAllTestsById(final String id) {
        return this.manager.execute((final Store s) -> {
            final PatientRepository rep = s.find(PatientRepository.class);
            final List<Test> tests = rep.getAllTestsById(id);
            return tests.stream().map(t -> createTestDTO(t)).collect(Collectors.toList());
        });
    }

    @Override
    public List<TestDTO> getTestsByIdAfterDueDate(final String id) {
        return this.manager.execute((final Store s) -> {
            final PatientRepository rep = s.find(PatientRepository.class);
            final List<Test> tests = rep.getTestsByIdAfterDueDate(id);
            return tests.stream().map(t -> createTestDTO(t)).collect(Collectors.toList());
        });
    }

    private static TestDTO createTestDTO(final Test t) {
        if (t.getTestDate() == null) {
            return new TestDTO(new TestTypeDTO(t.getTestType().getName(), t.getTestType().getRefLimits()),
                    t.getDueDate().toLocalDateTime().atZone(ZoneId.of(UTC)), t.getDescription(), t.getVal(), null);
        }

        return new TestDTO(new TestTypeDTO(t.getTestType().getName(), t.getTestType().getRefLimits()),
                t.getDueDate().toLocalDateTime().atZone(ZoneId.of(UTC)), t.getDescription(), t.getVal(),
                t.getTestDate().toLocalDateTime().atZone(ZoneId.of(UTC)));
    }

    @Override
    public Boolean registerPatient(final PatientDTO patient) {
        // validation here and return false if it does not meet the requirements
        return this.manager.execute((final Store s) -> {
            final PatientRepository rep = s.find(PatientRepository.class);
            return rep.registerPatient(patient.getId(), patient.getEmail(), patient.getName(), patient.getPhoneNumber(),
                    patient.getSex(), patient.getAddress(), new ArrayList<>());
        });
    }

}
