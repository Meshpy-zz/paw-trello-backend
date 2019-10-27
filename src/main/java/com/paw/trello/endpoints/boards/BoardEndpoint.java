package com.paw.trello.endpoints.boards;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.boards.CreateNewBoardDto;
import com.paw.trello.services.boards.BoardService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/board")
public class BoardEndpoint {

    @Inject
    private BoardService boardService;

    @Path("/new")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBoard(JsonObject input) {
        ResponseMessage responseMessage = boardService.createNewBoard(new CreateNewBoardDto(input));
        return Response
                .status(Response.Status.OK)
                .entity(responseMessage.getMessage())
                .build();
    }

}
