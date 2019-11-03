package com.paw.trello.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_creator_id")
    @NotNull
    private Long creatorId;

    @Column(name = "board_name")
    private String name;

    @Column(name = "board_allowed_users")
    private String allowedUsers;

    @Column(name = "board_audit_cd")
    private Date auditCd;

    @Column(name = "board_audit_md")
    private Date auditMd;

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
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

    public String getAllowedUsers() {
        return allowedUsers;
    }

    public void setAllowedUsers(String allowedUsers) {
        this.allowedUsers = allowedUsers;
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

    @Override '
    public String toString() {
        return "Board{"
               + "boardId="
               + boardId
               + ", creatorId="
               + creatorId
               + ", name='"
               + name
               + '\''
               + ", allowedUsers='"
               + allowedUsers
               + '\''
               + ", auditCd="
               + auditCd
               + ", auditMd="
               + auditMd
               + '}';
    }

}
