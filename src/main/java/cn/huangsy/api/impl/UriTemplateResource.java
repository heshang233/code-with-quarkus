package cn.huangsy.api.impl;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/uri")
public class UriTemplateResource {

    @PathParam("parentId")
    Long parentId;

    @GET
    @Path("{parentId}")
    public String getFormSubresource() {
        return "Hello from sub-resource of parent " + parentId;
    }
}
