package com.paw.trello.endpoints.auth;

import com.paw.trello.dtos.LoginUserDto;
import com.paw.trello.dtos.RegisterUserDto;
import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.security.SecurityKeyGenerator;
import com.paw.trello.services.auth.AuthorizationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Path("/auth")
public class AuthorizationEndpoint {

    @Inject
    private AuthorizationService authorizationService;

    @Inject
    private SecurityKeyGenerator securityKeyGenerator;

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
        ResponseMessage responseMessage = authorizationService.loginUser(new LoginUserDto(input));
        String token = issueToken(input.getString("email"));
        return Response
                .status(Response.Status.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .entity(responseMessage.getMessage())
                .build();
    }

    private String issueToken(String email) {
        Key key = securityKeyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(30L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
