package com.thinktag.persist.service;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class PersistenceRepository {

    @PersistenceContext
    EntityManager em;

    public <T> Collection<T> findAll(String type) {
        Query query = em.createQuery("SELECT e FROM " + type + " e");
        return (Collection<T>) query.getResultList();
    }

    public <T> T find(Class<T> clazz, Long id) {
        return em.find(clazz, id);
    }

    public <T> T save(Class<T> clazz, T t) {
        em.persist(t);
        return t;
    }

    public <T> void update(Class<T> clazz, T t) {
        em.merge(t);
    }


}
