# mssql-view
This sample demonstrates two ways of working with views in ms-sql. The same approach can be used for another database.

## Overview
The sample has two persistent entities 
* [Department](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/Department.java) 
* [Employee](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/Employee.java)

Each employee belongs to some department.

There are two views declared in the database and 2 corresponding non-persistent entities: 
1. The [AvgHourlyRate](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/AvgHourlyRate.java) entity mapped to the _avg_hourly_rate_ DB view - shows the average hourly rate between all employees in the department
..* Mapping works through the standard CUBA mechanism
..* The only restriction of the approach is that the view must have id field
2. The [DepartmentSize](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/DepartmentSize.java) entity mapped to the _department_size_ DB view - shows how many employees work in the department
..* Mapping works through the custom [TsqlQueryDatasource](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/web/src/com/company/mssqlview/web/datasource/TsqlQueryDatasource.java) datasource
..* The datasource supprots paging and sorting on the DB side
