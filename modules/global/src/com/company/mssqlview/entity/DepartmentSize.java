package com.company.mssqlview.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@MetaClass(name = "mssqlview$DepartmentSize")
@Table(name = "department_size")
public class DepartmentSize extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 982148350407896414L;

    @MetaProperty(mandatory = true)
    @Column(name = "name")
    protected String name;

    @MetaProperty(mandatory = true)
    @Column(name = "size")
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