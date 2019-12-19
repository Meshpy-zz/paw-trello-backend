package com.paw.trello.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "activity_user_id")
    private Long activityUserId;

    @Column(name = "activity_board_id")
    private Long activityBoardId;

    @Column(name = "activity_username")
    private String activityUsername;

    @Column(name = "activity_meaning")
    private String activityMeaning;

    @Column(name = "activity_date")
    private Date activityDate;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityUserId() {
        return activityUserId;
    }

    public void setActivityUserId(Long activityUserId) {
        this.activityUserId = activityUserId;
    }

    public String getActivityUsername() {
        return activityUsername;
    }

    public void setActivityUsername(String activityUsername) {
        this.activityUsername = activityUsername;
    }

    public Long getActivityBoardId() {
        return activityBoardId;
    }

    public void setActivityBoardId(Long activityBoardId) {
        this.activityBoardId = activityBoardId;
    }

    public String getActivityMeaning() {
        return activityMeaning;
    }

    public void setActivityMeaning(String activityMeaning) {
        this.activityMeaning = activityMeaning;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    @Override public String toString() {
        return "Activity{"
               + "activityId="
               + activityId
               + ", activityUserId="
               + activityUserId
               + ", activityBoardId="
               + activityBoardId
               + ", activityUsername='"
               + activityUsername
               + '\''
               + ", activityMeaning='"
               + activityMeaning
               + '\''
               + ", activityDate="
               + activityDate
               + '}';
    }

}
