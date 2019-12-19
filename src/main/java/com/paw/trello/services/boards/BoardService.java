package com.paw.trello.services.boards;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.boards.BoardDto;
import com.paw.trello.dtos.boards.CreateNewBoardDto;
import com.paw.trello.dtos.boards.EditBoardNameDto;
import com.paw.trello.entities.Board;
import com.paw.trello.entities.Card;
import com.paw.trello.entities.Comment;
import com.paw.trello.services.auth.AuthorizationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class BoardService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AuthorizationService authorizationService;

    public ResponseMessage createNewBoard(CreateNewBoardDto createNewBoardDto) {
        Board board = new Board();
        board.setName(createNewBoardDto.getName());
        board.setCreatorId((long) createNewBoardDto.getCreatorId());
        board.setAuditCd(new Date());
        board.setIsArchived("N");

        entityManager.persist(board);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano nową tablicę! " + board)
                              .build();
    }

    public List<BoardDto> getAllBoards(Long userId) {
        // dodac sprawdzanie uprawnien uzytkownika (allowedUsers) do tablicy
        List<Board> ownerBoards = entityManager.createQuery("SELECT b FROM Board b WHERE b.creatorId = :userId")
                                          .setParameter("userId", userId)
                                          .getResultList();

        List<Board> boardsFromAnotherUsers = entityManager.createQuery("SELECT b FROM Board b WHERE b.allowedUsers LIKE :userId")
                .setParameter("userId", "%" + userId + "%")
                .getResultList();

        List<Board> boards = new ArrayList<>(ownerBoards);
        boards.addAll(boardsFromAnotherUsers);

        return convertBoardsListToBoardsDtoList(boards);
    }

    private List<BoardDto> convertBoardsListToBoardsDtoList(List<Board> boards) {
        List<BoardDto> boardsDto = new ArrayList<>();

        for (Board board : boards) {
            BoardDto boardDto = BoardDto.builder()
                                        .boardId(board.getBoardId())
                                        .name(board.getName())
                                        .isArchived(board.getIsArchived())
                                        .build();
            boardsDto.add(boardDto);
        }

        return boardsDto;
    }

    public ResponseMessage editBoardName(EditBoardNameDto editBoardNameDto) {
        Board board = (Board) entityManager
                .createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId")
                .setParameter("boardId", editBoardNameDto.getBoardId())
                .getSingleResult();

        board.setName(editBoardNameDto.getName());
        entityManager.merge(board);

        return ResponseMessage.builder()
                              .message("Pomyślnie zmieniono nazwę tablicy!")
                              .build();
    }

    public BoardDto getBoardDetails(Long boardId) {
        Board board = (Board) entityManager.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId")
                                           .setParameter("boardId", boardId)
                                           .getSingleResult();

        return BoardDto.builder()
                       .boardId(board.getBoardId())
                       .name(board.getName())
                       .build();
    }

    public ResponseMessage deleteBoard(Long boardId) {
        Board board = (Board) entityManager.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId")
                                           .setParameter("boardId", boardId)
                                           .getSingleResult();
//        List<com.paw.trello.entities.List> lists = entityManager.createQuery("SELECT l FROM List l WHERE l.boardId = :boardId")
//                .setParameter("boardId", boardId)
//                .getResultList();
//
//        if (lists != null) {
//            for (com.paw.trello.entities.List list : lists) {
//                List<Card> allCardsFromSpecificList = entityManager.createQuery("SELECT c FROM Card c WHERE c.listId = :listId")
//                                                                   .setParameter("listId", list.getListId())
//                                                                   .getResultList();
//
//                if (allCardsFromSpecificList != null) {
//                    for (Card card : allCardsFromSpecificList) {
//                        List<Comment> comments = entityManager.createQuery("SELECT c FROM Comment c WHERE c.cardId = :cardId")
//                                                              .setParameter("cardId", card.getCardId())
//                                                              .getResultList();
//                        if (comments != null) {
//                            for (Comment comment : comments) {
//                                entityManager.remove(comment);
//                            }
//                        }
//
//                        entityManager.remove(card);
//                    }
//                }
//
//                entityManager.remove(list);
//            }
//        }

        entityManager.remove(board);

        return ResponseMessage.builder()
                .message("Pomyślnie usunięto tablicę o nr id " + boardId)
                .build();
    }

    public ResponseMessage unarchiveCard(Long boardId) {
        Board board = (Board) entityManager.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId")
                                           .setParameter("boardId", boardId)
                                           .getSingleResult();

        board.setIsArchived("N");

        entityManager.merge(board);

        return ResponseMessage.builder()
                              .message("Pomyślnie odarchiwizowano tablicę!")
                              .build();
    }

    public ResponseMessage archiveCard(Long boardId) {
        Board board = (Board) entityManager.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId")
                                           .setParameter("boardId", boardId)
                                           .getSingleResult();

        board.setIsArchived("Y");

        entityManager.merge(board);

        return ResponseMessage.builder()
                              .message("Pomyślnie zarchiwizowano tablicę!")
                              .build();
    }

    public ResponseMessage inviteUserToBoard(int boardId, String username) {
        Board board = (Board) entityManager.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId")
                                           .setParameter("boardId", boardId)
                                           .getSingleResult();
        String allowedUsers = board.getAllowedUsers();
        Long userId = this.authorizationService.getUserIdByUsername(username);

        if (userId == null) {
            return ResponseMessage.builder()
                                  .message("failed")
                                  .build();
        }

        if (userId.equals(board.getCreatorId())) {
            return ResponseMessage.builder()
                                  .message("same_user")
                                  .build();
        }

        if (allowedUsers != null) {
            if (String.valueOf(userId).contains(allowedUsers)) {
                return ResponseMessage.builder()
                                      .message("contains")
                                      .build();
            }
        }

        if (allowedUsers == null) {
            board.setAllowedUsers(String.valueOf(userId));
        } else {
            board.setAllowedUsers(allowedUsers.concat("," + userId));
        }


        entityManager.merge(board);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano użytkownika do tablicy.")
                              .build();
    }
}
