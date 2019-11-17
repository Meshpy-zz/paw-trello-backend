package com.paw.trello.dtos.lists;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
public class CreateNewListDto {

    private int boardId;

    private String name;

    public CreateNewListDto(JsonObject input) {
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
