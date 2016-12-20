package com.company.mssqlview.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.security.entity.User;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.math.BigDecimal;
import javax.persistence.Column;

@NamePattern("%s|user")
@Table(name = "MSSQLVIEW_EMPLOYEE")
@Entity(name = "mssqlview$Employee")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -3715538565428428985L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", unique = true)
    protected User user;

    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEPARTMENT_ID")
    protected Department department;

    @Column(name = "HOURLY_WAGE")
    protected BigDecimal hourlyWage;

    public void setHourlyWage(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}