package com.paw.trello.endpoints.boards;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.boards.BoardDto;
import com.paw.trello.dtos.boards.CreateNewBoardDto;
import com.paw.trello.dtos.boards.EditBoardNameDto;
import com.paw.trello.security.JWTTokenNeeded;
import com.paw.trello.services.boards.BoardService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/board")
public class BoardEndpoint {

    @Inject
    private BoardService boardService;

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBoard(JsonObject input) {
        ResponseMessage responseMessage = boardService.createNewBoard(new CreateNewBoardDto(input));
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/all/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response getAllBoards(@PathParam("userId") Long userId) {
        List<BoardDto> boards = boardService.getAllBoards(userId);
        return Response
                .ok()
                .entity(boards)
                .build();
    }

    @Path("/edit")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editBoardName(JsonObject input) {
        ResponseMessage responseMessage = boardService.editBoardName(new EditBoardNameDto(input));
        return Response
                .ok(responseMessage.getMessage())
                .build();
    }

    @Path("/single/{boardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBoardDetails(@PathParam("boardId") Long boardId) {
        BoardDto boardDto = boardService.getBoardDetails(boardId);
        return Response
                .ok()
                .entity(boardDto)
                .build();
    }

}
