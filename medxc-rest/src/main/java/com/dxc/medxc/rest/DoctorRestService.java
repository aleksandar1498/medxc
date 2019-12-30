package com.dxc.medxc.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.dto.AppointmentDTO;
import com.dxc.medxc.dto.DoctorDTO;
import com.dxc.medxc.services.DoctorService;

/**
 * @author astefanov2
 */
@Path("doctors")
public final class DoctorRestService {
    @Context
    private ServletContext context;

    /**
     * @return a JSON representation of the Doctors retrieved from the service
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DoctorDTO> getAllDoctors() {
        return getService().getAllDoctors();
    }

    /**
     * @return the DoctorService from the ServletContext
     */
    private DoctorService getService() {
        final Store s = (Store) context.getAttribute(Store.class.getName());
        return s.find(DoctorService.class);
    }

    /**
     * @author mhristov2 Method to view a doctor's appointments between two dates.
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

    // path -> medxc/rest/doctors/appointments/0055555555/2019-11-01/2019-12-31
    @GET
    @Path("/appointments/{id}/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppointmentDTO> viewAppointments(@PathParam("id") final String id, @PathParam("from") final String from,
            @PathParam("to") final String to) {
        return getService().viewAppointments(id, from, to);

    }
}
