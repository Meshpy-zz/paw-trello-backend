package com.paw.trello.dtos.comments;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class EditCommentDto {

    private int commentId;

    private int cardId;

    private String content;

    public EditCommentDto(JsonObject input) {
        this.commentId = input.getInt("commentId");
        this.cardId = input.getInt("cardId");
        this.content = input.getString("content");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("cardId", cardId)
                .add("content", content)
                .add("commentId", commentId)
                .build();
    }

}
