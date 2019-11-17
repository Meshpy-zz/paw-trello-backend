package com.paw.trello.dtos.lists;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListDto {

    private Long id;

    private Long boardId;

    private String name;

}
