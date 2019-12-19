package com.paw.trello.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSession {

    private Long userId;

    private String username;

    private String password;

}
