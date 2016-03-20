package com.psu.est.dao.common;

import com.psu.est.model.interfaces.DomainObject;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danielkarkee on 2/1/16.
 */
public interface GenericDao<T extends DomainObject> {

    /**
     *
     * @param object
     * @throws DataAccessException
     */
    public void persist(T object) throws DataAccessException;

    /**
     *
     * @param object
     * @throws DataAccessException
     */
    public void update(T object) throws DataAccessException;

    /**
     *
     * @param object
     * @throws DataAccessException
     */
    public T saveOrUpdate(T object) throws DataAccessException;

    /**
     *
     * @param object
     * @throws DataAccessException
     */
    public void delete(T object) throws DataAccessException;

    /**
     *
     * @param id
     * @return
     * @throws DataAccessException
     */
    public T get(Serializable id) throws DataAccessException;

    /**
     *
     * @return
     * @throws DataAccessException
     */
    public List<T> getAll() throws DataAccessException;
}
