package com.paw.trello.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
@Setter
@ToString
public class RegisterUserDto {

    private String username;

    private String email;

    private String password;

    public RegisterUserDto(JsonObject input) {
        this.username = input.getString("username");
        this.email = input.getString("email");
        this.password = input.getString("password");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("username", this.username)
                .add("email", this.email)
                .add("password", this.password)
                .build();
    }

}
