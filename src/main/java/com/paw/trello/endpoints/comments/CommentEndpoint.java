package com.paw.trello.endpoints.comments;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.comments.CreateNewCommentDto;
import com.paw.trello.services.comments.CommentService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentEndpoint {

    @Inject
    private CommentService commentService;

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewComment(JsonObject input) {
        ResponseMessage responseMessage = commentService.addNewComment(new CreateNewCommentDto(input));
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

}
