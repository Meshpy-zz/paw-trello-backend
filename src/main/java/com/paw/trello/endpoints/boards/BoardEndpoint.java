package com.paw.trello.endpoints.boards;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.boards.BoardDto;
import com.paw.trello.dtos.boards.CreateNewBoardDto;
import com.paw.trello.dtos.boards.EditBoardNameDto;
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

    @Path("/{boardId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBoard(@PathParam("boardId") Long boardId) {
        ResponseMessage responseMessage = boardService.deleteBoard(boardId);
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/archive/{boardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response archiveBoard(@PathParam("boardId") Long boardId) {
        ResponseMessage responseMessage = boardService.archiveCard(boardId);
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/unarchive/{boardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response unarchiveBoard(@PathParam("boardId") Long boardId) {
        ResponseMessage responseMessage = boardService.unarchiveCard(boardId);
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/invite/{boardId}/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response inviteUserToBoard(@PathParam("boardId") int boardId, @PathParam("username") String username) {
        ResponseMessage responseMessage = boardService.inviteUserToBoard(boardId, username);

        if (responseMessage.getMessage().equals("failed")) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Nie znaleziono takiego użytkownika!")
                    .build();
        }

        if (responseMessage.getMessage().equals("contains")) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Użytkownik już jest dodany do tablicy.")
                    .build();
        }

        if (responseMessage.getMessage().equals("same_user")) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("Nie możesz dodać samego siebie do swojej tablicy.")
                    .build();
        }

        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

}
