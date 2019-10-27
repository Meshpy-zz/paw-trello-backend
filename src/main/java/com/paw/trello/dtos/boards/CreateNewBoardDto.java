package com.paw.trello.dtos.boards;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
@ToString
public class CreateNewBoardDto {

    private int creatorId;

    private String name;

    private String allowedUsers;

    public CreateNewBoardDto(JsonObject input) {
        this.creatorId = input.getInt("creatorId");
        this.name = input.getString("name");
        this.allowedUsers = input.getString("allowedUsers");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("creatorId", this.creatorId)
                .add("name", this.name)
                .add("allowedUsers", this.allowedUsers)
                .build();
    }

}
