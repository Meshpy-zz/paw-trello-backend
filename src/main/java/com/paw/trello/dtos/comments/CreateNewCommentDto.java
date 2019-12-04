package com.paw.trello.dtos.comments;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class CreateNewCommentDto {

    private int cardId;

    private String content;

    public CreateNewCommentDto(JsonObject input) {
        this.cardId = input.getInt("cardId");
        this.content = input.getString("content");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder().add("cardId", this.cardId).add("content", this.content).build();
    }

}
