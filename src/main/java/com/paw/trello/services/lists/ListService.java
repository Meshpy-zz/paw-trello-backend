package com.paw.trello.services.lists;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.lists.CreateNewListDto;
import com.paw.trello.dtos.lists.ListDto;
import com.paw.trello.entities.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;

@Stateless
public class ListService {

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseMessage createNewList(CreateNewListDto createNewListDto) {
        List list = new List();
        list.setBoardId((long) createNewListDto.getBoardId());
        list.setName(createNewListDto.getName());
        list.setAuditCd(new Date());

        entityManager.persist(list);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano nową listę! " + list)
                              .build();
    }

    public java.util.List<ListDto> getAllListsForSpecificBoard(Long boardId) {
        java.util.List<List> lists = entityManager.createQuery("SELECT l FROM List l WHERE l.boardId = :boardId")
                .setParameter("boardId", boardId)
                .getResultList();

        return convertListEntityToListDto(lists);
    }

    private java.util.List<ListDto> convertListEntityToListDto(java.util.List<List> lists) {
        java.util.List<ListDto> listDtos = new ArrayList<>();

        for (List list : lists) {
            listDtos.add(ListDto.builder()
                                .id(list.getListId())
                                .boardId(list.getBoardId())
                                .name(list.getName())
                                .build());
        }

        return listDtos;
    }
}
