package com.psu.est.dao.common;

import com.psu.est.model.interfaces.DomainObject;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danielkarkee on 2/1/16.
 */
@Transactional
public class GenericDaoImpl<T extends DomainObject> implements GenericDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void persist(T object) {
        sessionFactory.getCurrentSession().persist(object);
    }

    @Override
    public void update(T object) {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public T saveOrUpdate(T object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
        return object;
    }

    @Override
    public void delete(T object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    @Override
    public T get(Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @Override
    public List<T> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(type).list();
    }
}