package com.paw.trello.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    @NotNull
    private Long taskId;

    @Column(name = "task_panel_id")
    @NotNull
    private Long panelId;

    @Column(name = "task_creator_id")
    @NotNull
    private Long creatorId;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_description")
    private String description;

    @Column(name = "task_members")
    private String members;

    @Column(name = "task_category")
    private String category;

    @Column(name = "task_audit_cd")
    private Date auditCd;

    @Column(name = "task_audit_md")
    private Date auditMd;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getAuditCd() {
        return auditCd;
    }

    public void setAuditCd(Date auditCd) {
        this.auditCd = auditCd;
    }

    public Date getAuditMd() {
        return auditMd;
    }

    public void setAuditMd(Date auditMd) {
        this.auditMd = auditMd;
    }

}
