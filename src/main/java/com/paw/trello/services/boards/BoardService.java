package com.paw.trello.services.boards;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.boards.BoardDto;
import com.paw.trello.dtos.boards.CreateNewBoardDto;
import com.paw.trello.dtos.boards.EditBoardNameDto;
import com.paw.trello.entities.Board;

import javax.ejb.Stateless;
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

    public ResponseMessage createNewBoard(CreateNewBoardDto createNewBoardDto) {
        Board board = new Board();
        board.setName(createNewBoardDto.getName());
        board.setCreatorId((long) createNewBoardDto.getCreatorId());
        board.setAuditCd(new Date());

        entityManager.persist(board);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano nową tablicę! " + board)
                              .build();
    }

    public List<BoardDto> getAllBoards(Long userId) {
        // dodac sprawdzanie uprawnien uzytkownika (allowedUsers) do tablicy
        List<Board> boards = entityManager.createQuery("SELECT b FROM Board b WHERE b.creatorId = :userId")
                                          .setParameter("userId", userId)
                                          .getResultList();
        return convertBoardsListToBoardsDtoList(boards);
    }

    private List<BoardDto> convertBoardsListToBoardsDtoList(List<Board> boards) {
        List<BoardDto> boardsDto = new ArrayList<>();

        for (Board board : boards) {
            BoardDto boardDto = BoardDto.builder()
                                        .boardId(board.getBoardId())
                                        .name(board.getName())
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

}
