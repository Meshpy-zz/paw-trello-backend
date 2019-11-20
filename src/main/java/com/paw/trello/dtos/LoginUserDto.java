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

    private String email;

    private String password;

    public LoginUserDto(JsonObject input) {
        this.email = input.getString("email");
        this.password = input.getString("password");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("email", this.email)
                .add("password", this.password)
                .build();
    }

}
