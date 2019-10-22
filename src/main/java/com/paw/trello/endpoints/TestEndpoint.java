package com.paw.trello.endpoints;

import com.paw.trello.entities.TestEntity;
import com.paw.trello.services.TestService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;

@Path("/test")
public class TestEndpoint {

    private static final Logger LOGGER = Logger.getLogger(TestEndpoint.class.getName());

    @Inject
    private TestService testService;

    @Path("/get")
    @GET
    @Produces("application/json")
    public JsonArray get() {
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (TestEntity testEntity : testService.getAll()) {
            LOGGER.info(testEntity.getTestProperty());
            builder.add(Json.createObjectBuilder().add("test_property", testEntity.getTestProperty()));
        }

        return builder.build();
    }

}
