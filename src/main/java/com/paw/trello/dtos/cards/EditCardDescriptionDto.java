package com.paw.trello.dtos.cards;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class EditCardDescriptionDto {

    private int id;

    private String title;

    private String description;

    public EditCardDescriptionDto(JsonObject input) {
        this.id = input.getInt("id");
        this.title = input.getString("title");
        this.description = input.getString("description");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("title", this.title)
                .add("description", this.description)
                .build();
    }
}
