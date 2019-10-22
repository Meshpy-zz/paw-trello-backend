package com.paw.trello.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_audit_cd")
    private Date auditCd;

    @Column(name = "user_audit_md")
    private Date auditMd;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
