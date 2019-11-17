package com.paw.trello.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "lists")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Long listId;

    @Column(name = "list_board_id")
    @NotNull
    private Long boardId;

    @Column(name = "list_name")
    private String name;

    @Column(name = "list_audit_cd")
    private Date auditCd;

    @Column(name = "list_audit_md")
    private Date auditMd;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
