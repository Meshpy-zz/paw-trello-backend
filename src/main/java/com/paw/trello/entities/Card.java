package com.paw.trello.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_list_id")
    @NotNull
    private Long listId;

    @Column(name = "card_creator_id")
    @NotNull
    private Long creatorId;

    @Column(name = "card_name")
    private String name;

    @Column(name = "card_description")
    private String description;

    @Column(name = "card_members")
    private String members;

    @Column(name = "card_category")
    private String category;

    @Column(name = "card_audit_cd")
    private Date auditCd;

    @Column(name = "card_audit_md")
    private Date auditMd;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
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

    @Override public String toString() {
        return "Card{"
               + "cardId="
               + cardId
               + ", listId="
               + listId
               + ", creatorId="
               + creatorId
               + ", name='"
               + name
               + '\''
               + ", description='"
               + description
               + '\''
               + ", members='"
               + members
               + '\''
               + ", category='"
               + category
               + '\''
               + ", auditCd="
               + auditCd
               + ", auditMd="
               + auditMd
               + '}';
    }

}
