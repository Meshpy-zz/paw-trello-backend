package com.paw.trello.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
@ToString
public class LoginUserDto {

    private String username;

    private String password;

    public LoginUserDto(JsonObject input) {
        this.username = input.getString("username");
        this.password = input.getString("password");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("username", this.username)
                .add("password", this.password)
                .build();
    }

}
