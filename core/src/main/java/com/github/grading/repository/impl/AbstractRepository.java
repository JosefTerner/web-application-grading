package com.github.grading.repository.impl;

import com.github.grading.repository.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.github.grading.utils.HibernateSessionFactoryUtil.getSession;

public abstract class AbstractRepository <T, K extends Serializable> implements Repository<T, K> {

    private Class<T> domainClass;

    @Override
    public T save(T entity) {
        getSession().save(entity);
        getSession().close();
        return entity;
    }

    @Override
    public T getOne(K id) {
        T obj = getSession().getReference(getDomainClass(), id);
        getSession().close();
        return obj;
    }

    @Override
    public T findOne(K id) {
        T obj = getSession().find(getDomainClass(), id);
        getSession().close();
        return obj;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getDomainClass());
        criteriaQuery.from(getDomainClass());
        TypedQuery<T> query = getSession().createQuery(criteriaQuery);
        getSession().close();
        return query.getResultList();
    }

    @Override
    public void update(T entity) {
        Session session = getSession();
        Transaction txn = session.beginTransaction();
        session.merge(entity);
        txn.commit();
    }

    @Override
    public void delete(T entity) {
        getSession().remove(entity);
    }

    @Override
    public void delete(K id) {
        getSession().remove(getOne(id));
    }

    @Override
    public void deleteAll() {
        getSession().createQuery("delete " + getDomainClassName()).executeUpdate();
    }

    @Override
    public long count() {
        long result = (long) getSession().createQuery("Select count(*) from " + getDomainClassName()).getSingleResult();
        getSession().close();
        return result;
    }

    @Override
    public boolean exists(K id) {
        return findOne(id) != null;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    protected String getDomainClassName() {
        return getDomainClass().getName();
    }
}
