-- begin MSSQLVIEW_EMPLOYEE
alter table MSSQLVIEW_EMPLOYEE add constraint FK_MSSQLVIEW_EMPLOYEE_USER foreign key (USER_ID) references SEC_USER(ID)^
alter table MSSQLVIEW_EMPLOYEE add constraint FK_MSSQLVIEW_EMPLOYEE_DEPARTMENT foreign key (DEPARTMENT_ID) references MSSQLVIEW_DEPARTMENT(ID)^
create unique index IDX_MSSQLVIEW_EMPLOYEE_UNIQ_USER_ID on MSSQLVIEW_EMPLOYEE (USER_ID, DELETE_TS) ^
create index IDX_MSSQLVIEW_EMPLOYEE_DEPARTMENT on MSSQLVIEW_EMPLOYEE (DEPARTMENT_ID)^
create index IDX_MSSQLVIEW_EMPLOYEE_USER on MSSQLVIEW_EMPLOYEE (USER_ID)^
-- end MSSQLVIEW_EMPLOYEE
