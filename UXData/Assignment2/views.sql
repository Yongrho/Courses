Use CovisData

/* For cases */
SELECT dbo.Cases$.cumulative_cases / dbo.can_pro_population$.Population * 10000 AS rel_prop_cases_per10000, dbo.can_pro_population$.Province
FROM     dbo.Cases$ INNER JOIN
                  dbo.can_pro_population$ ON dbo.Cases$.province = dbo.can_pro_population$.Province
WHERE  (dbo.Cases$.date_report =
                      (SELECT MAX(date_report) AS Expr1
                       FROM      dbo.Cases$ AS mCases
                       WHERE   (dbo.Cases$.province = province)))

/* For deaths */
SELECT m_1.deaths / p.Population * 10000 AS rel_prop_deaths_per10000, m_1.province
FROM     (SELECT m.province, m.cumulative_deaths AS deaths
                  FROM      dbo.Mortality$ AS m INNER JOIN
                                        (SELECT province, MAX(date_death_report) AS dt
                                         FROM      dbo.Mortality$
                                         GROUP BY province) AS x ON m.province = x.province AND m.date_death_report = x.dt) AS m_1 INNER JOIN
                  dbo.can_pro_population$ AS p ON m_1.province = p.Province

/* For vaccines */
SELECT dbo.Vaccines$.cumulative_cvaccine / dbo.can_pro_population$.Population * 10000 AS cvaccines_per10000, dbo.can_pro_population$.Province
FROM     dbo.Vaccines$ INNER JOIN
                  dbo.can_pro_population$ ON dbo.Vaccines$.province = dbo.can_pro_population$.Province
WHERE  (dbo.Vaccines$.date_vaccine_completed =
                      (SELECT MAX(date_vaccine_completed) AS Expr1
                       FROM      dbo.Vaccines$ AS maxVaccines
                       WHERE   (dbo.Vaccines$.province = province)))

/* For summary */
SELECT TOP (100) PERCENT MONTH(date_active) AS m, YEAR(date_active) AS y, FORMAT(CAST(date_active AS date), 'MMM-yyyy') AS labelDt, cumulative_cases AS Cases, cumulative_deaths AS Deaths, 
                  cumulative_recovered AS Recovered
FROM     dbo.Summary$
WHERE  (date_active IN
                      (SELECT MAX(date_active) AS Expr1
                       FROM      dbo.Summary$ AS Summary$_1
                       GROUP BY YEAR(date_active), MONTH(date_active)))
ORDER BY y, m