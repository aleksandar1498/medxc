package com.dxc.medxc.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.converters.ObjectConverter;
import com.dxc.medxc.dto.AppointmentDTO;
import com.dxc.medxc.dto.DoctorDTO;
import com.dxc.medxc.dto.PatientDTO;
import com.dxc.medxc.dto.PatientHistoryDTO;
import com.dxc.medxc.dto.RecordDTO;
import com.dxc.medxc.dto.SpecialtyDTO;
import com.dxc.medxc.dto.TestDTO;
import com.dxc.medxc.persistence.model.Appointment;
import com.dxc.medxc.persistence.model.DocSpec;
import com.dxc.medxc.persistence.model.Doctor;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.repository.DoctorRepository;
import com.dxc.medxc.persistence.transaction.TransactionManager;
import com.dxc.medxc.services.DoctorService;

/**
 * Implementation of the doctor services
 *
 * @author atsekov
 */
@SuppressWarnings("nls")
public final class DoctorServiceImpl implements DoctorService {

    private final TransactionManager manager;

    /**
     * Implementation of the doctor services
     *
     * @param manager
     *            TransactionManager passed from init module
     */
    public DoctorServiceImpl(final TransactionManager manager) {
        this.manager = manager;
    }

    private static List<AppointmentDTO> convertJpaAppointmentsToDTO(final List<Appointment> list) {
        return ObjectConverter.convertList(jpaObj -> createAppointmentDto(jpaObj), list);
    }

    private static AppointmentDTO createAppointmentDto(final Appointment jpaObj) {
        final Record rec = jpaObj.getRecord();
        final RecordDTO newRecordDTO = (rec == null) ? new RecordDTO()
                : new RecordDTO(rec.getAnamnesis(), rec.getObservation(), rec.getTherapy());
        return new AppointmentDTO(jpaObj.getDate().toLocalDateTime().atZone(ZoneId.of("UTC")),
                jpaObj.getStatus().getName(), jpaObj.getSpecialty().getName(), newRecordDTO,
                new DoctorDTO(jpaObj.getDoctor().getDocNum(), jpaObj.getDoctor().getName(),
                        jpaObj.getDoctor().getEmail(), null, true, null, null),
                new PatientDTO(jpaObj.getPatient().getPIN(), jpaObj.getPatient().getName(),
                        jpaObj.getPatient().getEmail(), jpaObj.getPatient().getPhoneNumber(), new PatientHistoryDTO()),
                new ArrayList<TestDTO>());

    }

    private static List<SpecialtyDTO> convertJpaDocSpecToSpecialtyDTO(final List<DocSpec> list) {
        return ObjectConverter.convertList(jpaObj -> new SpecialtyDTO(jpaObj.getSpecialty().getName()), list);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return this.manager.execute((final Store store) -> {
            final DoctorRepository r = store.find(DoctorRepository.class);
            final List<Doctor> doctors = r.getAllDoctors();
            return ObjectConverter.convertList(jpaObj -> new DoctorDTO(jpaObj.getDocNum(), jpaObj.getName(),
                    jpaObj.getEmail(), jpaObj.getPhoneNumber(), false, null, null), doctors);
        });
    }

    /**
     * Method to view a doctor's appointments between two dates.
     *
     * @author mhristov2
     * @param id
     *            ID of the doctor whose appointments need to be returned.
     * @param from
     *            The date from which appointments should start to be displayed.
     * @param to
     *            The date until appointments should be displayed Any appointments
     *            that that have date equal to <strong>from</strong> or
     *            <strong>to</strong> are included.
     * @return A list containing all appointments between the two dates of a
     *         particular doctor.
     */
    @Override
    public List<AppointmentDTO> viewAppointments(final String id, final String from, final String to) {
        final ZonedDateTime fromDate = ZonedDateTime.of(LocalDate.parse(from), LocalTime.of(0, 0, 0, 0),
                ZoneId.of("UTC"));
        final ZonedDateTime toDate = ZonedDateTime.of(LocalDate.parse(to), LocalTime.of(23, 59, 59, 999_999_999),
                ZoneId.of("UTC"));

        return this.manager.execute((final Store store) -> {
            final DoctorRepository rep = store.find(DoctorRepository.class);
            final List<Appointment> list = rep.findAppointmentsByDoctorIdAndFromToDates(id, fromDate, toDate);

            return ObjectConverter.convertList(jpaObj -> new AppointmentDTO(
                    ZonedDateTime.ofInstant(jpaObj.getDate().toInstant(), ZoneId.of("UTC")),
                    jpaObj.getStatus().getName(), jpaObj.getSpecialty().getName(), new RecordDTO(),
                    new DoctorDTO(jpaObj.getDoctor().getDocNum(), jpaObj.getDoctor().getName(),
                            jpaObj.getDoctor().getEmail(), null, true, null, null),
                    new PatientDTO(jpaObj.getPatient().getPIN(), jpaObj.getPatient().getName(),
                            jpaObj.getPatient().getEmail(), jpaObj.getPatient().getPhoneNumber(),
                            new PatientHistoryDTO()),
                    new ArrayList<TestDTO>()), list);
        });
    }
}
