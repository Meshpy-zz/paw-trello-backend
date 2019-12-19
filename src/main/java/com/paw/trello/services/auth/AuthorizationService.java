package com.paw.trello.services.auth;

import com.paw.trello.dtos.LoginUserDto;
import com.paw.trello.dtos.RegisterUserDto;
import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.UserSession;
import com.paw.trello.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class AuthorizationService {

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseMessage registerUser(RegisterUserDto registerUserDto) {
        if (!isUsernameAvailable(registerUserDto.getUsername())) {
            return ResponseMessage.builder()
                                  .message("Nazwa użytkownika jest już zajęta!")
                                  .build();
        }

        if (!isEmailAvailable(registerUserDto.getEmail())) {
            return ResponseMessage.builder()
                                  .message("Adres e-mail jest już zajęty!")
                                  .build();
        }

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setEmail(registerUserDto.getEmail());
        user.setAuditCd(new Date());

        entityManager.persist(user);

        return ResponseMessage.builder()
                              .message("Pomyślnie zarejestrowano nowego użytkownika: " + registerUserDto)
                              .build();
    }

    private boolean isEmailAvailable(String email) {
        try {
            entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
                    .setParameter("email", email)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return true;
        }

        return false;
    }

    private boolean isUsernameAvailable(String username) {
        try {
            entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username LIKE :username")
                    .setParameter("username", username)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return true;
        }

        return false;
    }

    public UserSession loginUser(LoginUserDto loginUserDto) {
        try {
            User user = (User) entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email LIKE :email AND u.password LIKE :password")
                    .setParameter("email", loginUserDto.getEmail())
                    .setParameter("password", loginUserDto.getPassword())
                    .getSingleResult();

            return UserSession.builder()
                              .userId(user.getUserId())
                              .password(user.getPassword())
                              .username(user.getUsername())
                              .build();
        }
        catch (NoResultException e) {
            return null;
        }


    }

    public String getUsername(int userId) {
        User user = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.userId = :userId")
                .setParameter("userId", userId)
                .getSingleResult();

        return user.getUsername();
    }

    public Long getUserIdByUsername(String username) {
        try {
            User user = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username")
                                            .setParameter("username", username)
                                            .getSingleResult();

            return user.getUserId();
        }
        catch (NoResultException e) {
            return null;
        }
    }

}
