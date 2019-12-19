package com.paw.trello.dtos.labels;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class EditLabelDto {

    private String color;

    private String content;

    private int creatorId;

    private int labelId;

    public EditLabelDto(JsonObject input) {
        this.color = input.getString("color");
        this.content = input.getString("content");
        this.creatorId = input.getInt("creatorId");
        this.labelId = input.getInt("labelId");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("color", this.color)
                .add("content", this.content)
                .add("creatorId", this.creatorId)
                   .add("labelId", this.labelId)
                .build();
    }

}
