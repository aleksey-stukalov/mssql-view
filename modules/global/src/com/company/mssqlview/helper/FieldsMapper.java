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

public class FieldsMapper {

    public static String getTableNameByAnnotation(Class clazz) {
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        if (annotation == null)
            throw new NullPointerException("Table annotation is not defined for entity " + clazz.getName());
        return annotation.name();
    }

    public static Map<String, String> getColumn2FieldMappingByAnnotation(Class clazz) {
        Map<String, String> result = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null)
                result.put(column.name(), field.getName());
        }
        return result;
    }

    public static Map<String, String> getField2ColumnMappingByAnnotation(Class clazz) {
        Map<String, String> c2f = getColumn2FieldMappingByAnnotation(clazz);
        return c2f.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }


    public static <T extends AbstractNotPersistentEntity> List<T> doMapResult(Class <T> clazz,
                                                                                  List<Map<DatabaseField, Object>> queryResult,
                                                                                  Map<String, String> mapping) {
        List<T> result = new ArrayList<>();

        Map<String, String> keyInsensitiveMapping = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        keyInsensitiveMapping.putAll(mapping);

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
    public static <T extends AbstractNotPersistentEntity> List<T> doMapResult(Class <T> clazz,
                                                                                  List<Map<DatabaseField, Object>> queryResult) {
        return doMapResult(clazz, queryResult, getColumn2FieldMappingByAnnotation(clazz));
    }
}
