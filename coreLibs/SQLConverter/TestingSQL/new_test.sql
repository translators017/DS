SELECT  "Associate Employee Id" ,
        "Case Country",
        "Case Description",
        "Case Id",
        "Sent To Gov. Agency On",
        "Case Valid From Date",
        "Case Valid To Date",
        "Firm Define Case Status",
        "Doc Valid From Date",
        "Doc Valid To Date"
FROM(
SELECT  "Associate Employee Id" ,
        "Case Country",
        "Case Description",
        "Case Id",
        "Sent To Gov. Agency On",
        "Case Valid From Date",
        "Case Valid To Date",
        "Firm Define Case Status",
        "Doc Valid From Date",
        "Doc Valid To Date"
FROM  sgit01.git_b9view_myvisa a
WHERE length("Associate Employee Id") =6 OR length("Associate Employee Id") =7
AND  
( UPPER("Case Description")  NOT  LIKE '%BUSINESS%VISA%' AND  UPPER("Case Description")   NOT LIKE '%ORANGE%CARPET%'
AND    UPPER("Case Description")  NOT  LIKE '%BV%') 

UNION 

SELECT  "Associate Employee Id" ,
        "Case Country",
        "Case Description",
        "Case Id",
        "Sent To Gov. Agency On",
        "Case Valid From Date",
        "Case Valid To Date",
        "Firm Define Case Status",
        "Doc Valid From Date",
        "Doc Valid To Date"
FROM  sgit01.git_b9view_myvisa 
WHERE length("Associate Employee Id") =6 OR length("Associate Employee Id") =7 
AND "Firm Define Case Status"  = 'No Show'
AND ("Case Country" != 'Singapore' )
) a

UNION 

SELECT  associateemployeeid as "Associate Employee Id" ,
        casecountry as "Case Country",
        casedescription as  "Case Description",
        caseid as  "Case Id",
        senttogovagencyon as "Sent To Gov. Agency On",
        casevalidfromdate as "Case Valid From Date",
        casevalidtodate as  "Case Valid To Date",
       firmdefinecasestatus as "Firm Define Case Status",
        docvalidfromdate as "Doc Valid From Date",
        docvalidtodate as "Doc Valid To Date" from 
sgit01.git_b9_utilization_manual_table 

