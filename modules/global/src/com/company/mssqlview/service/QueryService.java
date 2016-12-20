package com.company.mssqlview.service;


import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.util.List;

public interface QueryService {
    String NAME = "mssqlview_QueryService";

    <T extends AbstractNotPersistentEntity> List<T> executeQuery(String query, Class <T> clazz);

    int getCount(String table, String whereClause);

    int getCount(String table);
}