/**
 *
 */
package com.dxc.medxc.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.dto.AppointmentDTO;
import com.dxc.medxc.dto.PatientDTO;
import com.dxc.medxc.dto.PatientHistoryDTO;
import com.dxc.medxc.dto.RecordDTO;
import com.dxc.medxc.dto.SpecialtyDTO;
import com.dxc.medxc.dto.TestDTO;
import com.dxc.medxc.services.PatientService;
import com.dxc.medxc.validators.exceptions.ValidationException;

/**
 * @author astefanov2
 */
@Path("patients")
public final class PatientRestService {
    @Context
    private ServletContext context;

    /**
     *
     * @return All patients
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PatientDTO> getAllPatients() {
        return getService().getAllPatients();
    }

    /**
     *
     * @param id Id of the patient
     * @return @return all the registered medical records for the users
     */
    @GET
    @Path("/records/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecordDTO> getAllMedicalRecords(@PathParam("id") final String id) {
        List<RecordDTO> records = new ArrayList<>();
        try {
            records = getService().getAllMedicalRecords(id);
        } catch (final ValidationException e) {
            throw e;
        }
        return records;
    }

    /**
     * @param id            id of the patient whose appointments need to be returned
     * @param specialtyName Name of the specialty the appointment has to be about
     * @return returns the appointments of the patient
     */
    @GET
    @Path("/appointments/{id}/{specialty}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppointmentDTO> getAllAppointmentsBySpecialty(@PathParam("id") final String id,
            @PathParam("specialty") final String specialtyName) {
        return getService().getAllAppointmentsByIdAndSpecialty(id, specialtyName);
    }

    /**
     * @param id Id of the patient
     * @return List of appointments the patient has
     */
    @GET
    @Path("/appointments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppointmentDTO> getAllAppointments(@PathParam("id") final String id) {
        return getService().getAllAppointmentsById(id);
    }

    /**
     * @param id Id of the patient
     * @return Lsit of specialties the patient has appointments for
     */
    @GET
    @Path("/specialties/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SpecialtyDTO> getAllSpecialtiesByAppointments(@PathParam("id") final String id) {
        return getService().getAllSpecialtiesByAppointments(id);
    }

    /**
     *
     * @param id Id of the patient
     * @return @return all the registered medical records for the users
     */
    @GET
    @Path("/history/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PatientHistoryDTO getPatientHistory(@PathParam("id") final String id) {
        return getService().viewPatientHistory(id);
    }

    /**
     * @return the DoctorService from the ServletContext
     */
    private PatientService getService() {
        final Store s = (Store) context.getAttribute(Store.class.getName());
        return s.find(PatientService.class);
    }

    /**
     * @param id of patient
     * @return list of tests to be performed
     */
    @GET
    @Path("/tests/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestDTO> getAllTests(@PathParam("id") final String id) {
        return getService().getTestsByIdAfterDueDate(id);
    }

    /**
     * @param newPatient A new patient DTO
     * @return true if the patient is successfully registered and false otherwise.
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean registerPatient(final PatientDTO newPatient) {
        final PatientService service = getService();
        return service.registerPatient(newPatient).booleanValue();
    }
}
