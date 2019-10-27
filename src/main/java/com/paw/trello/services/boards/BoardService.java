package com.paw.trello.services.boards;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.boards.CreateNewBoardDto;
import com.paw.trello.entities.Board;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class BoardService {

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseMessage createNewBoard(CreateNewBoardDto createNewBoardDto) {
        Board boardToAdd = new Board();
        boardToAdd.setName(createNewBoardDto.getName());
        boardToAdd.setAllowedUsers(createNewBoardDto.getAllowedUsers());
        boardToAdd.setAuditCd(new Date());
        boardToAdd.setCreatorId((long) createNewBoardDto.getCreatorId());

        entityManager.persist(boardToAdd);

        return ResponseMessage.builder()
                              .message("Pomyślnie utworzono tablicę: " + boardToAdd)
                              .build();
    }

}
