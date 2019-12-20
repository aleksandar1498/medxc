package com.dxc.medxc.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.dto.DoctorDTO;
import com.dxc.medxc.services.AdminService;

/**
 * Service rest class for admin
 *
 * @author atsekov
 */
@Path("admins")
public final class AdminRestService {

    @Context
    private ServletContext context;

    /**
     * @return return names of all available specialties.
     */
    @GET
    @Path("/getSpecialties")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllSpecialtyNames() {
        final AdminService service = getService();
        return service.getAllSpecialtyNames();
    }

    /**
     * Temporary method to test post service.
     *
     * @param doctor
     *            New doctor's data.
     * @return the list of all doctors in the database.
     */
    @POST
    @Path("doctors/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean createDoctor(final DoctorDTO doctor) {
        final AdminService service = getService();
        return service.createDoctor(doctor);
    }

    private AdminService getService() {
        final Store s = (Store) context.getAttribute(Store.class.getName());
        return s.find(AdminService.class);
    }
}
