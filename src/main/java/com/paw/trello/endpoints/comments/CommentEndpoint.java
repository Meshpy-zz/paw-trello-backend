package com.paw.trello.endpoints.comments;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.comments.CommentDetailsDto;
import com.paw.trello.dtos.comments.CreateNewCommentDto;
import com.paw.trello.entities.Comment;
import com.paw.trello.services.comments.CommentService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @Path("/all/{cardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommentsForSpecificCard(@PathParam("cardId") int cardId) {
        List<CommentDetailsDto> commentDetailsDtos = commentService.getAllCommentsForSpecificCard(cardId);
        return Response
                .ok()
                .entity(commentDetailsDtos)
                .build();
    }

}
