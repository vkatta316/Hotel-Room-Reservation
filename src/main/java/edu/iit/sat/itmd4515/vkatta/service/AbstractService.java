/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * This is generic class for all the services
 * @author vinaychowdarykatta
 * @param <T> Entity type
 */
public abstract class AbstractService<T> {
    
    /**
     * Entity Manager instance
     */
    @PersistenceContext(name="ITMD4515PU")
    protected EntityManager em;
    
    /**
     * Entity class
     */
    protected final Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Persist entity to database
     * @param entity Table Name
     */
    public void create(T entity){
        
        em.persist(entity);
    }
    
    /**
     *
     * @param id
     * @return id of the entity
     */
    public T read(Long id){
       return em.find(entityClass, id);
        
    }
    
    /**
     * Update the table
     * @param entity
     */
    public void update(T entity){
        em.merge(entity);
    }
    
    /**
     * Delete table
     * @param entity table name
     */
    public void delete(T entity){
        em.remove(em.merge(entity));
    }
    
    /**
     *
     * @param queryName query on entity
     * @return all the rows for the passed query
     */
    protected List<T> findAll(String queryName){
        List<T> entities = new ArrayList<>();
        entities = em.createNamedQuery(queryName, entityClass).getResultList();
        return entities;
    }
    
    /**
     * Returns result when id is matched
     * @param id 
     * @return id of the passed entity
     */
    protected T findById(Long id){
        
        T entity;
        entity = em.createNamedQuery("Room.findById", entityClass)
                .setParameter("id", id)
                .getSingleResult();
        return entity;
    }
    
    /**
     * Returns result when name is matched
     * @param name
     * @param type
     * @return
     */
    protected T findByName(String name, String type){
        
        T entity;
        entity = em.createNamedQuery(type+".findByName", entityClass)
                .setParameter("name", name)
                .getSingleResult();
        return entity;
    }
   
}
