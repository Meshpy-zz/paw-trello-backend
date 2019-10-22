package com.paw.trello.services;

import com.paw.trello.entities.TestEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TestService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TestEntity> getAll() {
        TypedQuery<TestEntity> query = entityManager.createQuery("select t from TestEntity t", TestEntity.class);
        return query.getResultList();
    }

}
