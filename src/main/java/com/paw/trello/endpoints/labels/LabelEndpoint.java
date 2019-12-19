package com.paw.trello.endpoints.labels;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.labels.AddLabelToCardDto;
import com.paw.trello.dtos.labels.EditLabelDto;
import com.paw.trello.entities.Label;
import com.paw.trello.services.labels.LabelService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/label")
public class LabelEndpoint {

    @Inject
    private LabelService labelService;

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLabelToCard(JsonObject input) {
        ResponseMessage responseMessage = labelService.addLabelToCard(new AddLabelToCardDto(input));
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/{cardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabelsForSpecificCard(@PathParam("cardId") int cardId) {
        List<Label> labels = labelService.getLabelsForSpecificCard(cardId);
        return Response
                .ok()
                .entity(labels)
                .build();
    }

    @Path("/{labelId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLabelFromCard(@PathParam("labelId") int labelId) {
        ResponseMessage responseMessage = labelService.deleteLabelFromCard(labelId);
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

    @Path("/edit")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editLabel(JsonObject input) {
        ResponseMessage responseMessage = labelService.editLabel(new EditLabelDto(input));
        return Response
                .ok()
                .entity(responseMessage.getMessage())
                .build();
    }

}
