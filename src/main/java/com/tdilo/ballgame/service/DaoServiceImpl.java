package com.tdilo.ballgame.service;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

public abstract class DaoServiceImpl<E, K> implements DaoService<E, K> {

    public static final Logger log = Logger.getLogger(DaoServiceImpl.class);
    
    private Class entityClass;
    private String idProperty;
    private String entityClassName;

    public abstract EntityManager getEntityManager();

    public Class getEntityClass() {
        return entityClass;
    }

    public String getIdProperty() {
        return idProperty;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    @SuppressWarnings("unchecked")
    private String getIdPropertyFromClass(Class ec) {
        log.info("getting id property of entityClass: " + ec.getSimpleName() );
        String idProperty = null;
        Metamodel metamodel = getEntityManager().getMetamodel();
        EntityType entity = metamodel.entity(entityClass);
        Set<SingularAttribute> singularAttributes = entity.getSingularAttributes();
        for (SingularAttribute singularAttribute : singularAttributes) {
            if (singularAttribute.isId()) {
                idProperty = singularAttribute.getName();
                break;
            }
        }
        if (idProperty == null)
            throw new RuntimeException("id field not found");

        log.info(ec.getSimpleName() + " id property is " + idProperty);
        return idProperty;
    }

    @PostConstruct
    protected void init() {
        entityClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        entityClassName = entityClass.getSimpleName();
        idProperty = getIdPropertyFromClass(entityClass);
        log.info("constructed dao for entityClass:" + entityClassName +", with id property: " + idProperty);
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(K id) {
        final String hql = "SELECT x FROM " + getEntityClass().getName() + " x WHERE x." + this.idProperty + " = :id";

        Query q = getEntityManager().createQuery(hql);
        q.setParameter("id", id);

        return (E) q.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        final String hql = "SELECT x FROM " + getEntityClass().getName() + " x";

        Query q = getEntityManager().createQuery(hql);

        return (List<E>) q.getResultList();
    }

    @Override
    public void save(E entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void delete(E entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public void merge(E entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void refresh(E entity) {
        getEntityManager().refresh(entity);
    }
}
