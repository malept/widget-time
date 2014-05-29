package com.malept.widgettime.resources;

import java.util.LinkedList;
import java.util.List;

import com.malept.widgettime.core.Widget;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/widget")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WidgetResource {
    private LinkedList<Widget> widgets;
    private int last_id;

    public WidgetResource() {
        this.widgets = new LinkedList<Widget>();
        this.last_id = 0;
    }

    @GET
    public List<Widget> retrieve() {
        return this.widgets;
    }

    @POST
    public Response create(Widget widget) {
        widget.setId(++this.last_id);
        widget.setDateCreated(System.currentTimeMillis() / 1000);
        this.widgets.add(widget);
        return Response.status(201).entity(widget).build();
    }
}
