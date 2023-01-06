package com.distribuida.rest;

import com.distribuida.servicios.ServicioHolaMundo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@ApplicationScoped
@Path(value="/")
public class HolaMundoRest {

    @Inject
    private ServicioHolaMundo service;

    @GET
    @Path("/hola")
    @Produces(MediaType.APPLICATION_JSON)
    public String hola(){
        return "Hola3 "+ LocalDateTime.now();
    }



}
