package com.paw.trello.dtos.cards;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class CreateNewCardDto {

    private int listId;

    private int creatorId;

    private String name;

    private String description;

    public CreateNewCardDto(JsonObject input) {
        this.listId = input.getInt("listId");
        this.creatorId = input.getInt("creatorId");
        this.name = input.getString("name");
        this.description = input.getString("description");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("listId", this.listId)
                .add("creatorId", this.creatorId)
                .add("name", this.name)
                .add("description", this.description)
                .build();
    }

}
