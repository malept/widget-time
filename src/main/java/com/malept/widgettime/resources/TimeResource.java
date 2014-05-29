package com.malept.widgettime.resources;

import com.malept.widgettime.core.UnixTime;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/v1/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {

    @GET
    public UnixTime timestamp() {
        return new UnixTime(System.currentTimeMillis() / 1000);
    }
}
