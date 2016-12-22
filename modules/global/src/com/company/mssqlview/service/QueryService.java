package com.company.mssqlview.service;


import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.util.List;

/**
 * Service to operate with native queries
 */
public interface QueryService {
    String NAME = "mssqlview_QueryService";

    /**
     * Method wraps query result to a list of non-persistent entities
     * @param query Native query expression
     * @param clazz Class of a non-persistent entity
     * @return List of non-persistent entities
     */
    <T extends AbstractNotPersistentEntity> List<T> executeQuery(String query, Class <T> clazz);

    int getCount(String countQuery);
}