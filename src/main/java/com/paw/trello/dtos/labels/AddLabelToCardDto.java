package com.paw.trello.dtos.labels;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class AddLabelToCardDto {

    private int cardId;

    private int creatorId;

    private String color;

    private String content;

    public AddLabelToCardDto(JsonObject input) {
        this.cardId = input.getInt("cardId");
        this.creatorId = input.getInt("creatorId");
        this.color = input.getString("color");
        this.content = input.getString("content");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("cardId", this.cardId)
                .add("creatorId", this.creatorId)
                .add("color", this.color)
                .add("content", this.content)
                .build();
    }

}
