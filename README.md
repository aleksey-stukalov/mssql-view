# mssql-view
This sample demonstrates two ways of working with views in ms-sql. The same approach can be used for another database.

## Overview
The sample has two persistent entities 

* [Department](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/Department.java) 
* [Employee](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/Employee.java)

Each employee belongs to some department.

There are two views declared in the database and 2 corresponding entities: 

1. The [AvgHourlyRate](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/AvgHourlyRate.java) entity mapped to the _avg_hourly_rate_ DB view - shows the average hourly rate between all employees in the department

 * Mapping works through the standard CUBA mechanism
 * The only restriction of the approach is that the view must have id field
2. The [DepartmentSize](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/entity/DepartmentSize.java) entity mapped to the _department_size_ DB view (shows how many employees work in the department) by the [EntityQuery](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/annotation/EntityQuery.java) and [QueryField](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/global/src/com/company/mssqlview/annotation/QueryField.java) annotations

 * Mapping is implemented in the custom [TsqlQueryDatasource](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/web/src/com/company/mssqlview/web/datasource/TsqlQueryDatasource.java) datasource via [QueryServiceBean](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/core/src/com/company/mssqlview/service/QueryServiceBean.java)
 * The datasource supprots paging and sorting on the DB side
 
 ## Using TsqlQueryDatasource
 
 Define a datasource in your screen (as it is shown [here](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/web/src/com/company/mssqlview/web/avghourlyrate/avg-hourly-rate-browse.xml#L16-L20)) and then you can specify it for any data-aware UI component in CUBA (for example it is shown [here](https://github.com/aleksey-stukalov/mssql-view/blob/master/modules/web/src/com/company/mssqlview/web/avghourlyrate/avg-hourly-rate-browse.xml#L40-L48)).
 
