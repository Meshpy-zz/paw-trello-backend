package com.paw.trello.services.activity;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.activities.AddNewActivityDto;
import com.paw.trello.entities.Activity;
import com.paw.trello.services.auth.AuthorizationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class ActivityService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AuthorizationService authorizationService;

    public ResponseMessage addNewActivity(AddNewActivityDto addNewActivityDto) {
        Activity activity = new Activity();
        String username = authorizationService.getUsername(addNewActivityDto.getUserId());
        activity.setActivityUsername(username);
        activity.setActivityUserId((long) addNewActivityDto.getUserId());
        activity.setActivityMeaning(addNewActivityDto.getMeaning());
        activity.setActivityDate(new Date());
        activity.setActivityBoardId((long) addNewActivityDto.getBoardId());

        entityManager.persist(activity);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano nową aktywność.")
                              .build();
    }

    public List<Activity> getAllActivitiesForSpecificBoard(int boardId) {
        List<Activity> activities = entityManager.createQuery("SELECT a FROM Activity a WHERE a.activityBoardId = :boardId")
                .setParameter("boardId", boardId)
                .getResultList();

        return activities;
    }

}
