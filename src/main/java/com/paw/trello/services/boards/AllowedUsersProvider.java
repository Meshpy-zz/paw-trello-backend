package com.paw.trello.services.boards;

import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public class AllowedUsersProvider {

    public String[] provide(String users) {
        if (users.contains(",")) {
            users = users.replace(" ", "");
            return users.split(",");
        }

        return new String[]{users};
    }

}
