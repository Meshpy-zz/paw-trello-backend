package com.paw.trello.endpoints.activities;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.activities.AddNewActivityDto;
import com.paw.trello.entities.Activity;
import com.paw.trello.services.activity.ActivityService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activity")
public class ActivityEndpoint {

    @Inject
    private ActivityService activityService;

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewActivity(JsonObject input) {
        ResponseMessage responseMessage = activityService.addNewActivity(new AddNewActivityDto(input));
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/{boardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActivitiesForSpecificBoard(@PathParam("boardId") int boardId) {
        List<Activity> activities = activityService.getAllActivitiesForSpecificBoard(boardId);
        return Response
                .ok()
                .entity(activities)
                .build();
    }

}
