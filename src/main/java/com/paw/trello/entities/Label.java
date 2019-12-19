package com.paw.trello.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "labels")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long labelId;

    @Column(name = "label_card_id")
    private Long cardId;

    @Column(name = "label_creator_id")
    private Long creatorId;

    @Column(name = "label_creator_username")
    private String creatorUsername;

    @Column(name = "label_color")
    private String labelColor;

    @Column(name = "label_content")
    private String labelContent;

    @Column(name = "label_audit_cd")
    private Date auditCd;

    @Column(name = "label_audit_md")
    private Date auditMd;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
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

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    @Override public String toString() {
        return "Label{"
               + "labelId="
               + labelId
               + ", cardId="
               + cardId
               + ", creatorId="
               + creatorId
               + ", creatorUsername='"
               + creatorUsername
               + '\''
               + ", labelColor='"
               + labelColor
               + '\''
               + ", labelContent='"
               + labelContent
               + '\''
               + ", auditCd="
               + auditCd
               + ", auditMd="
               + auditMd
               + '}';
    }

}
