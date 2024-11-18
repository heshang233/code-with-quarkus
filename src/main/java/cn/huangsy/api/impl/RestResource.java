package cn.huangsy.api.impl;

import io.quarkus.arc.Unremovable;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.container.ResourceContext;

import java.lang.annotation.Annotation;

@Path("/rest")
public class RestResource {

    @Inject
    ResourceContext resourceContext;

    @Path("{parentId}/sub")
    public SubResource subResource(@PathParam("parentId") Long id) {
        return resourceContext.getResource(SubResource.class);
    }

    @Path("{id}/second")
    public SecondResource secondResource(@PathParam("id") Long id) {
        return new SecondResource();
    }
}
