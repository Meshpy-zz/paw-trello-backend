package com.paw.trello.endpoints.auth;

import com.paw.trello.dtos.LoginUserDto;
import com.paw.trello.dtos.RegisterUserDto;
import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.UserSession;
import com.paw.trello.services.auth.AuthorizationService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthorizationEndpoint {

    @Inject
    private AuthorizationService authorizationService;

    @Path("/sign-up")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(JsonObject input) {
        ResponseMessage responseMessage = authorizationService.registerUser(new RegisterUserDto(input));
        return Response
                .status(Response.Status.OK)
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/sign-in")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(JsonObject input) {
        UserSession userSession = authorizationService.loginUser(new LoginUserDto(input));

        if (userSession == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response
                .ok()
                .entity(userSession)
                .build();
    }

    @Path("/get-username/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsername(@PathParam("userId") int userId) {
        String username = authorizationService.getUsername(userId);
        return Response
                .ok()
                .entity(username)
                .build();
    }

}
