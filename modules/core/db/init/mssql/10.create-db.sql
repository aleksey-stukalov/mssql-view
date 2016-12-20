-- begin MSSQLVIEW_EMPLOYEE
create table MSSQLVIEW_EMPLOYEE (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    DELETE_TS datetime,
    DELETED_BY varchar(50),
    --
    USER_ID uniqueidentifier not null,
    DEPARTMENT_ID uniqueidentifier not null,
    HOURLY_WAGE decimal(19, 2),
    --
    primary key nonclustered (ID)
)^
-- end MSSQLVIEW_EMPLOYEE
-- begin MSSQLVIEW_DEPARTMENT
create table MSSQLVIEW_DEPARTMENT (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime,
    CREATED_BY varchar(50),
    UPDATE_TS datetime,
    UPDATED_BY varchar(50),
    DELETE_TS datetime,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    PHONE_NUMBER varchar(255),
    --
    primary key nonclustered (ID)
)^
-- end MSSQLVIEW_DEPARTMENT
