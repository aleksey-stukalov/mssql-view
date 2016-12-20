package com.company.mssqlview.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "MSSQLVIEW_DEPARTMENT")
@Entity(name = "mssqlview$Department")
public class Department extends StandardEntity {
    private static final long serialVersionUID = -8629203662173154500L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "PHONE_NUMBER")
    protected String phoneNumber;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}