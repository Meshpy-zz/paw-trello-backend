package com.paw.trello.endpoints.lists;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.lists.CreateNewListDto;
import com.paw.trello.dtos.lists.ListDto;
import com.paw.trello.services.lists.ListService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/list")
public class ListEndpoint {

    @Inject
    private ListService listService;

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewList(JsonObject input) {
        ResponseMessage responseMessage = listService.createNewList(new CreateNewListDto(input));
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/all/{boardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllListsForSpecificBoard(@PathParam("boardId") Long boardId) {
        List<ListDto> lists = listService.getAllListsForSpecificBoard(boardId);
        return Response
                .ok()
                .entity(lists)
                .build();
    }

}
