package com.psu.est.dao.common;

import com.psu.est.model.interfaces.DomainObject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public T persist(T object) throws DataAccessException {
        sessionFactory.getCurrentSession().persist(object);
        return object;

    }

    @Override
    public T update(T object) throws DataAccessException {
        sessionFactory.getCurrentSession().update(object);
        return object;
    }

    @Override
    public T saveOrUpdate(T object) throws DataAccessException {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
        return object;
    }

    @Override
    public void delete(T object) throws DataAccessException {
        sessionFactory.getCurrentSession().delete(object);
    }

    @Override
    public T get(Serializable id) throws DataAccessException {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @Override
    public List<T> getAll() throws DataAccessException {
        return sessionFactory.getCurrentSession().createCriteria(type).list();
    }
}
