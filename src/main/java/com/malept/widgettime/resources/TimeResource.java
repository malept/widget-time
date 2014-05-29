package com.malept.widgettime.resources;

import com.malept.widgettime.core.UnixTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {
    @GET
    public UnixTime timestamp() {
        return new UnixTime(System.currentTimeMillis() / 1000);
    }
}
