package com.crud.errors;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class errorHandling implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.
                getCause().getCause().getCause().getCause().getLocalizedMessage()).build();
    }
}
