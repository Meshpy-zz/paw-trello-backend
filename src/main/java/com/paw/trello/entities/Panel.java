package com.paw.trello.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "panels")
public class Panel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long panelId;

    @Column(name = "panel_board_id")
    @NotNull
    private Long boardId;

    @Column(name = "panel_name")
    private String name;

    @Column(name = "panel_audit_cd")
    private Date auditCd;

    @Column(name = "panel_audit_md")
    private Date auditMd;

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
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
