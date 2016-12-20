/*
 * TODO Copyright
 */

package com.company.mssqlview.helper;

import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import org.eclipse.persistence.internal.helper.DatabaseField;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dfgd on 18.12.2016.
 */


/**
 * This class provides a number of useful methods to map query result to entity fields
 */
public class FieldsMapper {

    /**
     * Returns corresponding table name
     * @param clazz class of an entity
     * @return Name of the table which entity refers to by its {@link Table} annotation
     */
    public static String getTableNameByAnnotation(Class clazz) {
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        if (annotation == null)
            throw new NullPointerException("Table annotation is not defined for entity " + clazz.getName());
        return annotation.name();
    }

    /**
     * Returns mapping of map with (columnName, fieldName) entries
     * </p>Column names are defined by the {@link Column} annotations
     * @param clazz class of an entity
     * @return Returns map with (columnName, fieldName) entries
     */
    public static Map<String, String> getColumn2FieldMappingByAnnotation(Class clazz) {
        Map<String, String> result = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null)
                result.put(column.name(), field.getName());
        }
        return result;
    }

    /**
     * Returns mapping of map with (fieldName, columnName) entries
     * </p>Column names are defined by the {@link Column} annotations
     * @param clazz class of an entity
     * @return Returns map with (fieldName, columnName) entries
     */
    public static Map<String, String> getField2ColumnMappingByAnnotation(Class clazz) {
        Map<String, String> c2f = getColumn2FieldMappingByAnnotation(clazz);
        return c2f.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    /**
     * Transforms query result to a list of entities
     * @param clazz class of an entity to map the resulting list to
     * @param queryResult the resulting list of a query execution
     * @param c2fMapping DB columns to entity fields mapping; see the {@link #getColumn2FieldMappingByAnnotation} method
     * @param <T> Type of the non-persistent entity
     * @return List of non-persistent entities
     */
    public static <T extends AbstractNotPersistentEntity> List<T> doMapResult(Class <T> clazz,
                                                                                  List<Map<DatabaseField, Object>> queryResult,
                                                                                  Map<String, String> c2fMapping) {
        List<T> result = new ArrayList<>();

        Map<String, String> keyInsensitiveMapping = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        keyInsensitiveMapping.putAll(c2fMapping);

        for (Map<DatabaseField, Object> row : queryResult) {
            Map<String, Object> field2value = new HashMap<>();
            row.keySet().forEach(column -> {
                String field = keyInsensitiveMapping.get(column.getName());
                if (field != null)
                    field2value.put(field, row.get(column));
            });

            if (field2value.size() > 0) {
                T entity = AppBeans.get(Metadata.class).create(clazz);
                field2value.keySet().forEach(field -> entity.setValue(field, field2value.get(field)));
                result.add(entity);
            }
        }
        return result;
    }

    /**
     * Transforms query result to a list of entities
     * @param clazz lass of an entity to map the resulting list to
     * @param queryResult the resulting list of a query execution
     * @param <T> Type of the non-persistent entity
     * @return List of non-persistent entities
     */
    public static <T extends AbstractNotPersistentEntity> List<T> doMapResult(Class <T> clazz,
                                                                                  List<Map<DatabaseField, Object>> queryResult) {
        return doMapResult(clazz, queryResult, getColumn2FieldMappingByAnnotation(clazz));
    }
}
