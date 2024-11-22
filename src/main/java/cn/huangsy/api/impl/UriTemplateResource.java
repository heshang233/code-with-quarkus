package cn.huangsy.api.impl;

import cn.huangsy.dao.DDLDao;
import cn.huangsy.exception.BadRequestException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/uri")
public class UriTemplateResource {

    @PathParam("parentId")
    Long parentId;

    @Inject
    DDLDao ddlDao;

    @GET
    @Path("{parentId}")
    public String getFormSubresource() {
        ddlDao.createTable();
        ddlDao.addAttribute();
        return "SUCCESS";
    }
}
