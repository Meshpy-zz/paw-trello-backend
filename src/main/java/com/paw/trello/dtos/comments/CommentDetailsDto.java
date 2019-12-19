package com.paw.trello.dtos.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CommentDetailsDto {

    private Long commentId;

    private Long cardId;

    private String content;

    private Date auditCd;

}
