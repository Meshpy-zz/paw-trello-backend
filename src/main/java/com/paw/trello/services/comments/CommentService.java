package com.paw.trello.services.comments;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.comments.CommentDetailsDto;
import com.paw.trello.dtos.comments.CreateNewCommentDto;
import com.paw.trello.dtos.comments.EditCommentDto;
import com.paw.trello.entities.Comment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class CommentService {

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseMessage addNewComment(CreateNewCommentDto createNewCommentDto) {
        Comment comment = new Comment();
        comment.setCardId((long) createNewCommentDto.getCardId());
        comment.setContent(createNewCommentDto.getContent());
        comment.setAuditCd(new Date());
        comment.setCreatorId((long) createNewCommentDto.getCreatorId());

        entityManager.persist(comment);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano nowy komentarz! " + comment)
                              .build();
    }

    public List<CommentDetailsDto> getAllCommentsForSpecificCard(Long cardId) {
        List<Comment> comments = entityManager.createQuery("SELECT c FROM Comment c WHERE c.cardId = :cardId")
                .setParameter("cardId", cardId)
                .getResultList();
        List<CommentDetailsDto> commentDetailsDtos = new ArrayList<>();

        for (Comment comment : comments) {
            commentDetailsDtos.add(CommentDetailsDto.builder()
                                                    .cardId(cardId)
                                                    .commentId(comment.getCommentId())
                                                    .content(comment.getContent())
                                                    .build());
        }

        return commentDetailsDtos;
    }

    public ResponseMessage editComment(EditCommentDto editCommentDto) {
        Comment comment = (Comment) entityManager.createQuery("SELECT c FROM Comment c WHERE c.commentId = :commentId")
                                                 .setParameter("commentId", editCommentDto.getCommentId())
                                                 .getSingleResult();

        comment.setContent(editCommentDto.getContent());
        comment.setCardId((long) editCommentDto.getCardId());
        comment.setCommentId((long) editCommentDto.getCommentId());

        entityManager.merge(comment);

        return ResponseMessage.builder()
                              .message("Poprawnie zedytowano komentarz!")
                              .build();
    }

    public ResponseMessage deleteComment(int commentId) {
        Comment comment = (Comment) entityManager.createQuery("SELECT c FROM Comment c WHERE c.commentId = :commentId")
                .setParameter("commentId", commentId)
                .getSingleResult();

        entityManager.remove(comment);

        return ResponseMessage.builder()
                .message("Pomyślnie usunięto komentarz!")
                .build();
    }

    public CommentDetailsDto getCommentDetails(int commentId) {
        Comment comment = (Comment) entityManager.createQuery("SELECT c FROM Comment c WHERE c.commentId = :commentId")
                                                 .setParameter("commentId", commentId)
                                                 .getSingleResult();

        return CommentDetailsDto.builder()
                                .cardId(comment.getCardId())
                                .content(comment.getContent())
                                .commentId(comment.getCommentId())
                                .auditCd(comment.getAuditCd())
                                .build();
    }

}
