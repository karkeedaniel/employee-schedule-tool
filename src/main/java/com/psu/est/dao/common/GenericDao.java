package com.psu.est.dao.common;

import com.psu.est.model.interfaces.DomainObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danielkarkee on 2/1/16.
 */
public interface GenericDao<T extends DomainObject> {

    /**
     *
     * @param object
     */
    public void persist(T object);

    /**
     *
     * @param object
     */
    public void update(T object);

    /**
     *
     * @param object\
     */
    public T saveOrUpdate(T object);

    /**
     *
     * @param object\
     */
    public void delete(T object);

    /**
     *
     * @param id
     * @return
     */
    public T get(Serializable id);

    /**
     *
     * @return\
     */
    public List<T> getAll();
}
