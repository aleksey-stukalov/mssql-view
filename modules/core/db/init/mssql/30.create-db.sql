CREATE VIEW [dbo].[avg_hourly_rate]
AS
SELECT        d.ID, d.NAME, AVG(e.HOURLY_WAGE) AS avg_hourly_wage
FROM            dbo.MSSQLVIEW_DEPARTMENT AS d INNER JOIN
                         dbo.MSSQLVIEW_EMPLOYEE AS e ON d.ID = e.DEPARTMENT_ID
GROUP BY d.ID, d.NAME^

CREATE VIEW [dbo].[department_size]
AS
SELECT        d.NAME, COUNT(e.ID) AS size
FROM            dbo.MSSQLVIEW_DEPARTMENT AS d INNER JOIN
                         dbo.MSSQLVIEW_EMPLOYEE AS e ON d.ID = e.DEPARTMENT_ID
GROUP BY d.NAME^
