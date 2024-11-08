package cn.huangsy.api;


import cn.huangsy.model.ResponseEntityView;
import cn.huangsy.model.authentication.APITokenRequest;
import cn.huangsy.model.authentication.TokenEntity;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthenticationAPI {

    @POST
    ResponseEntityView<TokenEntity> getToken();
}
