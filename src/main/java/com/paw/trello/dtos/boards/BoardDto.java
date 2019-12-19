package com.paw.trello.dtos.boards;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDto {

    private Long boardId;

    private String name;

    private String isArchived;
}
