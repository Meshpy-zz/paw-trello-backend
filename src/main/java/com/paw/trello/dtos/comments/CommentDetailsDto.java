package com.paw.trello.dtos.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDetailsDto {

    private Long commentId;

    private int cardId;

    private String content;

}
