package com.paw.trello.dtos.activities;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class AddNewActivityDto {

    private int userId;

    private int boardId;

    private String meaning;

    public AddNewActivityDto(JsonObject input) {
        this.userId = input.getInt("userId");
        this.boardId = input.getInt("boardId");
        this.meaning = input.getString("meaning");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("userId", this.userId)
                   .add("boardId", this.boardId)
                .add("meaning", this.meaning)
                .build();
    }

}
