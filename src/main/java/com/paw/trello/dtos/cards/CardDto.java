package com.paw.trello.dtos.cards;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardDto {

    private Long id;

    private Long listId;

    private String title;

    private String description;

}
