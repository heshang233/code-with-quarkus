package cn.huangsy.api.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

public class SecondResource {

    @PathParam("id")
    Long parentId;

    @GET()
    @Path("{sub-id}")
    public String getFormSubresource(@PathParam("sub-id") Long id) {
        return "Hello from sub-resource of parent " + parentId + " sub " + id;
    }

}
