package com.paw.trello.dtos.boards;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class CreateNewBoardDto {

    private int creatorId;

    private String name;

    public CreateNewBoardDto(JsonObject input) {
        this.creatorId = input.getInt("creatorId");
        this.name = input.getString("name");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                   .add("name", this.name)
                   .add("creatorId", this.creatorId)
                   .build();
    }

}
