package com.paw.trello.dtos.cards;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardDetailsDto {

    private Long cardId;

    private String title;

    private String description;

    private String isArchived;

}
