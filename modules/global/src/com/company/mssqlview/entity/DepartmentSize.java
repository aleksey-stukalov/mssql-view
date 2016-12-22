package com.company.mssqlview.entity;

import com.company.mssqlview.annotation.EntityQuery;
import com.company.mssqlview.annotation.QueryField;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

@MetaClass(name = "mssqlview$DepartmentSize")
@EntityQuery(query = "select * from department_size",
        countQuery = "select count(*) from department_size")
public class DepartmentSize extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 982148350407896414L;

    @MetaProperty(mandatory = true)
    @QueryField(name = "name")
    protected String name;

    @MetaProperty(mandatory = true)
    @QueryField(name = "size")
    protected Integer size;

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}