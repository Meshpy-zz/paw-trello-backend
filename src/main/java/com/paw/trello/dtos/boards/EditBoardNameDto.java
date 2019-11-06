package com.paw.trello.dtos.boards;

import lombok.Getter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
public class EditBoardNameDto {

    private int boardId;

    private String name;

    public EditBoardNameDto(JsonObject input) {
        this.boardId = input.getInt("boardId");
        this.name = input.getString("name");
    }


    public JsonObject toJson() {
        return Json.createObjectBuilder()
                   .add("boardId", this.boardId)
                   .add("name", this.name)
                   .build();
    }

}
