/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
public abstract class AbstractService<T> {
    
     
    @PersistenceContext(name="ITMD4515PU")
    protected EntityManager em;
    
    protected final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    public void create(T entity){
        
        em.persist(entity);
    }
    
    public T read(Long id){
       return em.find(entityClass, id);
        
    }
    
    public void update(T entity){
        em.merge(entity);
    }
    
    public void delete(T entity){
        em.remove(em.merge(entity));
    }
    
    protected List<T> findAll(String queryName){
        List<T> entities = new ArrayList<>();
        entities = em.createNamedQuery(queryName, entityClass).getResultList();
        return entities;
    }
    
    protected T findById(Long id){
        
        T entity;
        entity = em.createNamedQuery("Room.findById", entityClass)
                .setParameter("id", id)
                .getSingleResult();
        return entity;
    }
    
   
}
