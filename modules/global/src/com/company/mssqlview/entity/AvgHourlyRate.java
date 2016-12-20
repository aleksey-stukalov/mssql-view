package com.company.mssqlview.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@NamePattern("%s|name")
@Table(name = "AVG_HOURLY_RATE")
@Entity(name = "mssqlview$AvgHourlyRate")
public class AvgHourlyRate extends BaseUuidEntity {
    private static final long serialVersionUID = -2220333494630515825L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "AVG_HOURLY_WAGE", precision = 38, scale = 6)
    protected BigDecimal avgHourlyWage;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvgHourlyWage(BigDecimal avgHourlyWage) {
        this.avgHourlyWage = avgHourlyWage;
    }

    public BigDecimal getAvgHourlyWage() {
        return avgHourlyWage;
    }
}