package com.crud;


import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class errorHandling implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        JsonObject err = Json.createObjectBuilder().add("error", "Email, ou cpf já cadastrado.").build();
        return Response.ok(err).status(Response.Status.BAD_REQUEST).build();

//        return Response.status(Response.Status.BAD_REQUEST).entity(exception.
//                getCause().getCause().getCause().getCause().getLocalizedMessage()).build();
    }
}
