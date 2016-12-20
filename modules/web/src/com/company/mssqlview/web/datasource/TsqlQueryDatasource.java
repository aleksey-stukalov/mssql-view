/*
 * TODO Copyright
 */

package com.company.mssqlview.web.datasource;

import com.company.mssqlview.helper.FieldsMapper;
import com.company.mssqlview.service.QueryService;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.data.impl.CustomCollectionDatasource;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Custom datasource, that builds t-sql query, based on the {@link javax.persistence.Table} annotation
 * of an entity and transfers the result to the list of non-persistent entities.
 *
 * The datasource implementation supports paging and sort on the DB side.
 *
 * @param <T> Type of a non-persistent entity
 */
public class TsqlQueryDatasource<T extends AbstractNotPersistentEntity> extends CustomCollectionDatasource<T, UUID> {

    protected QueryService queryService = AppBeans.get(QueryService.class);

    public TsqlQueryDatasource() {
        super();
        this.sortOnDb = true;
    }

    @Override
    protected Collection<T> getEntities(Map<String, Object> params) {
        String query = buildQueryBody() + " "
                + buildOrderByClause() + " "
                + buildOffsetClause();

        //noinspection unchecked
        return queryService.executeQuery(query, getMetaClass().getJavaClass());
    }

    @Override
    public int getCount() {
        return queryService.getCount(getTableName());
    }

    protected String getTableName() {
        return FieldsMapper.getTableNameByAnnotation(getMetaClass().getJavaClass());
    }

    protected String buildQueryBody() {
        return "SELECT * FROM " + getTableName();
    }

    protected String buildOrderByClause() {
        if (sortInfos == null || sortInfos.length == 0)
            return "ORDER BY (SELECT NULL)";

        String orderClause = "ORDER BY ";
        Map<String, String> mapping = FieldsMapper.getField2ColumnMappingByAnnotation(getMetaClass().getJavaClass());

        boolean needComma = false;
        for (SortInfo si : sortInfos) {
            String propName = si.getPropertyPath().toString();
            String colName = mapping.get(propName);
            String direction = si.getOrder().toString();
            orderClause += (needComma ? ", " : "") + colName + " " + direction;
            needComma = true;
        }
        return orderClause;
    }

    protected String buildOffsetClause() {
        if (maxResults == 0)
            return "";
        return String.format("OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", firstResult, maxResults);
    }
}
