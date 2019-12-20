/**
 *
 */
package com.dxc.medxc.rest.utils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.dxc.medxc.validators.exceptions.ValidationException;

/**
 * @author astefanov2
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(final ValidationException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getRootErrors()).type(MediaType.APPLICATION_JSON)
                .build();
    }

}
