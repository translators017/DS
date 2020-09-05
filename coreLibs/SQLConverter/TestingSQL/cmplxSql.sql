accttyp_desc

LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('c_acct_type_cd,c_description' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION
         SELECT
            2 AS sqlorder,
            TRIM(c_acct_type_cd)
            ||','||TRIM(c_description) AS sqltxt 
         FROM ${EDW_OS_VEDWDB}.acct_type
      ) AS exporttxt
ORDER BY sqlorder;


cmpgn_score

LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,cast('i_dmid,c_campaign_type,c_campaign_type_desc,c_campaign_desc,d_campaign_score_dt,c_prop_market_cd,c_dom_cd,c_dom_pref_prop_cd,c_tier_cd,c_msa_cd,c_state_cd,c_zip_7_pdb' as char(600))AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION 
select
2 as sqlorder,
TRIM (CAST(((T11.i_dmid*13)+10)AS CHAR(12)))
||','||COALESCE(TRIM(CAST(c_campaign_type AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_campaign_type_desc AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(c_campaign_desc AS CHAR(50))),'')
||','||UPPER(COALESCE(TRIM(cast(cast(d_campaign_score_dt As date format 'ddmmmyyyy') as CHAR(20))),'')) 
||','||COALESCE(TRIM(CAST(c_prop_market_cd AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_dom_cd_prop AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_dom_pref_prop_cd AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_tier_cd AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_msa AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_marketing_state_cd AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_zip_7_pdb AS CHAR(20))),'')
from ${EDW_OS_VEDWDB}.MARKETING_CAMPAIGNS_hist T1, 
${EDW_OS_VEDWDB}.gst_cons_xref T11, 
${EDW_OS_WORKDB}.inside_sales_dmid as ISList
where T1.i_dmid=T11.i_xref_dmid
and T1.d_campaign_score_dt>=('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) - INTERVAL '2' YEAR
and T11.i_dmid=ISLIST.i_dmid
      ) AS exporttxt
ORDER BY sqlorder;

cpn_type_desc


LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('c_coupon_type,c_coupon_id,c_coupon_desc,c_coup_type_desc' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION
         SELECT
            2 AS sqlorder,
            coalesce(TRIM(T1.c_coupon_type),'')
            ||','|| coalesce(trim(c_coupon_id),'')
            ||','|| coalesce(trim(c_coupon_desc),'')
            ||','|| coalesce(trim(T2.c_coup_type_desc),'') AS sqltxt
         FROM ${EDW_OS_VEDWDB}.COUPON_DESC T1 
         LEFT JOIN ${EDW_OS_VEDWDB}.coupon_Type T2
            ON T1.c_coupon_Type=T2.c_coupon_Type 
       ) AS exporttxt
ORDER BY sqlorder;

credit_status

LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('c_credit_status,c_cred_stat_desc' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION 
         SELECT 
            2 AS sqlorder,
            trim(c_credit_status)
            ||','||c_cred_stat_desc  AS sqltxt
            FROM ${EDW_OS_VEDWDB}.CREDIT_STATUS_DESC 
       ) AS exporttxt
ORDER BY sqlorder;
cust_metric
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
SELECT 
            1 AS sqlorder,
            CAST('i_dmid,brand_create_date,c_home_prop_cd,c_dom_cd,c_dom_mkt_cd,age,d_dob,c_gender,c_tier_cd,c_game_type,f_denom_pref,c_main_ethnic_type_intl,asian_flag_domestic,c_main_ethnic_type_domestic,c_ethnic_group1_domestic,c_zip_7,c_state_cd,c_dma_desc,c_msa_cd,c_country_cd,c_prop_mail_cd,c_mail_flag,c_ofr_email,c_email_address_valid_cd,c_email_address_quality_cd,email_combo_flag,c_acct_type_cd,c_addr_pref' AS CHAR(1000))AS sqltxt 
            FROM dbc.dbcinfo
            GROUP BY 1 
            
UNION 
SELECT 
2 AS sqlorder,
TRIM (CAST(((T1.i_dmid*13)+10)AS CHAR(12)))
          ||','|| COALESCE(UPPER(TRIM(CAST(CAST(CASE 
                  WHEN Extract(year FROM T1.d_create_dt) < 1901 THEN NULL 
                  ELSE T1.d_create_dt 
                  END AS date format 'ddmmmyyyy') As VARCHAR(50)))),'')
          ||','||  COALESCE(TRIM(CAST( T1.c_home_prop_cd as VARCHAR(50))),'')
          ||','||  COALESCE(TRIM(CAST(T1.c_dom_cd as VARCHAR(50))),'')
          ||','||  COALESCE(TRIM(CAST(T13.c_prop_market_cd as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM(CAST(CASE 
                   WHEN Extract(year FROM T1.d_dob) = 1899 THEN 0 
                   ELSE T1.age 
                   END as VARCHAR(50))),'')
          ||','|| COALESCE(UPPER(TRIM(CAST(CAST(T1.d_dob As date format 'ddmmmyyyy') as VARCHAR(50)))),'')  
          ||','|| COALESCE(TRIM (CAST(T9.c_gender as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T7.c_tier_cd as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(T8.c_game_type as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM(cast(CAST(T8.f_denom_pref AS decimal  (15,2) FORMAT 'Z(9)9.99')as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T15.c_group1 as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T10.c_asian_flag as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(T10.c_main_ethnic_type as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(T10.c_ethnic_group1 as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T12.c_zip_7 as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T12.c_state_cd as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(T100.c_dma_desc as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(T100.c_msa_cd as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T12.c_country_cd as VARCHAR(50))),'')   
          ||','|| COALESCE(TRIM (CAST(T11.c_prop_mail_cd as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(T1.c_mail_flag as VARCHAR(50))),'')
          ||','|| COALESCE(TRIM (CAST(T14.c_ofr_email as VARCHAR(50))),'') 
          ||','|| COALESCE(TRIM (CAST(T14.c_email_address_valid_cd as VARCHAR(50))),'')   
          ||','|| COALESCE(TRIM (CAST(T14.c_email_address_quality_cd as VARCHAR(50))),'')  
          ||','|| COALESCE(TRIM (CAST(CASE WHEN ( T14.c_blank_email_flag = 'N'
                    AND T14.c_email_address_valid_cd = 'Y'
                    AND T14.c_email_address_quality_cd = 'G' )
             THEN 'G'
             ELSE 'B'
        END as VARCHAR(5))),'')
		||','|| COALESCE(TRIM (CAST(T1.c_acct_type_cd as VARCHAR(50))),'')  
        ||','|| COALESCE(TRIM (CAST(T1.c_addr_pref as VARCHAR(50))),'')
 
FROM   ${EDW_OS_VEDWDB}.prop_desc_multi AS T13, 
       ${EDW_OS_VEDWDB}.pdb_trip_hdr AS T6, 
       ${EDW_OS_WORKDB}.inside_sales_dmid  as ISList,
       ${EDW_OS_VEDWDB}.guest AS T1 
       LEFT OUTER JOIN ${EDW_OS_VEDWDB}.gst_tier AS T7 
                    ON T1.i_dmid = T7.i_dmid 
       LEFT OUTER JOIN ${EDW_OS_VEDWDB}.guest_ethnicity AS T15 
                    ON T1.i_dmid = T15.i_dmid 
       LEFT OUTER JOIN ${EDW_OS_VEDWDB}.guest_game5_dedup AS T8 
                    ON T8.i_dmid = T1.i_dmid 
       LEFT JOIN ${EDW_OS_VEDWDB}.gst_personal T9 
              ON T1.i_dmid = T9.i_dmid 
       LEFT OUTER JOIN ${EDW_OS_VEDWDB}.guest_ethnicity_append_dedup AS T10 
                    ON T10.i_dmid = T1.i_dmid 
       LEFT JOIN ${EDW_OS_VEDWDB}.gst_prop_mail_code T11 
              ON T1.i_dmid = T11.i_dmid 
       LEFT JOIN ${EDW_OS_VEDWDB}.gst_email T14 
              ON T1.i_dmid = T14.i_dmid 
       LEFT OUTER JOIN ${EDW_OS_VEDWDB}.gst_addr_hdr T12 
             ON T1.i_dmid = T12.i_dmid 
             AND T12.c_addr_type = T1.c_addr_pref  
       LEFT JOIN ${EDW_OS_VEDWDB}.zip_desc T100 
              ON T12.c_zip_7 = T100.c_zip_cd 

WHERE  T13.c_prop_cd = T1.c_dom_cd 
       AND T13.i_prop_market_type_id = 1 
       AND T1.i_dmid = T6.i_dmid 
       AND T6.d_end_dt > ( '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) - INTERVAL '3' YEAR  
        and T1.i_dmid=ISLIST.i_dmid) AS exporttxt  
       ORDER BY sqlorder;
Gst_metrics
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('i_dmid,prop_create_date,d_last_activity_dt,c_prop_cd,c_prop_market_cd,mileage_LRN,i_mileage,c_rep_id,c_credit_status,f_credit_limit,f_outstand_credit,f_avail_credit' AS VARCHAR(650))AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1 
UNION
SELECT 
2 AS sqlorder,
TRIM(CAST(((gaf.i_dmid*13)+10) AS CHAR(12))) 
||','||UPPER(COALESCE(TRIM(CAST(CAST(T4.d_create_date as date format 'ddmmmyyyy') AS VARCHAR(50))),'')) 
||','||UPPER(COALESCE(TRIM(CAST(cast(gaf.d_last_activity_dt as date format 'ddmmmyyyy') AS VARCHAR(50))),''))
||','||COALESCE(TRIM(CAST(gaf.c_prop_cd AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(T13.c_prop_market_cd AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(T3.c_geo_locregnat_zone AS VARCHAR(50))),'') 
||','||COALESCE(TRIM(CAST(T3.i_mileage AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(T4.c_rep_id AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(T4.c_credit_status AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(CAST(T4.f_credit_limit AS decimal  (15,2) FORMAT 'Z(9)9.99')AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(CAST(T4.f_outstand_credit AS decimal  (15,2) FORMAT 'Z(9)9.99')AS VARCHAR(50))),'')
||','||COALESCE(TRIM(CAST(CAST(T4.f_avail_credit AS decimal  (15,2) FORMAT 'Z(9)9.99')AS VARCHAR(50))),'')

from 
	${EDW_OS_VEDWDB}.prop_desc_multi as T13 INNER JOIN
        INNER JOIN 
	${EDW_OS_VEDWDB}.GST_ACTIVITY_FTD as gaf 
	ON T13.c_prop_cd=gaf.c_prop_cd
	INNER JOIN
        ${EDW_OS_WORKDB}.inside_sales_dmid as ISList
	and  gaf.i_dmid=ISLIST.i_dmid
	
	left join ${EDW_OS_VEDWDB}.gst_cas_dtl T4 
	on gaf.i_dmid=T4.i_dmid 
	and gaf.c_prop_cd=T4.c_prop_cd
	
	left join ${EDW_OS_VEDWDB}.gst_prop_mileage T3 
	on gaf.i_dmid=T3.i_dmid 
	and gaf.c_prop_cd=T3.c_prop_cd	
	
	left join (select T555.i_dmid, mc.c_prop_market_cd, mc.c_campaign_type_Desc,mc.c_campaign_desc,mc.d_campaign_score_dt 
	from ${EDW_OS_VEDWDB}.MARKETING_CAMPAIGNS mc, ${EDW_OS_VEDWDB}.gst_cons_xref T555 where mc.i_dmid=T555.i_xref_dmid) T5
	on gaf.i_dmid=T5.i_dmid
	and T13.c_prop_Market_cd=T5.c_prop_Market_cd

where  T13.i_prop_market_type_id=1
 ) AS exporttxt
ORDER BY sqlorder;
guest_activity
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('i_pdb_trip_id,i_dmid,c_prop_cd,d_start_dt,d_end_dt,i_nbr_offers_red,i_trip_nbr,i_brand_trip_nbr,i_rated_days,f_theo_all,f_theo_game,f_theo_slot,f_theo_oth,f_actual_all,f_actual_game,f_actual_slot,f_actual_oth' as char(300)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION
         SELECT
            2 AS sqlorder,
            trim(T1.i_pdb_trip_id)
            ||','|| COALESCE(trim(cast(i_dmid_masked as char(12))),'')
            ||','|| COALESCE(trim(T1.c_prop_cd),'')
            ||','|| upper(COALESCE(trim(cast(T1.d_start_dt as date format 'ddmmmyyyy')),''))
            ||','|| upper(COALESCE(trim(cast(T1.d_end_dt as date format 'ddmmmyyyy')),''))
            ||','|| COALESCE(trim(cast(T1.i_nbr_offers_red AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.i_trip_nbr AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.i_brand_trip_nbr AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.i_rated_days AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_theo_all AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_theo_game AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_theo_slot AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_theo_oth AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_actual_all AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_actual_game AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_actual_slot AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            ||','|| COALESCE(trim(cast(T1.f_actual_oth AS DECIMAL(8) FORMAT 'Z(8)9')),'')
            FROM ${EDW_OS_WORKDB}.inside_sales_dmid AS ISList,
                ${EDW_OS_EDWDB}.PDB_Trip AS T1 
            LEFT JOIN ${EDW_OS_EDWDB}.pdb_trip_cons_rev T15 
                ON t1.i_dmid        = t15.i_dmid 
               AND t1.i_pdb_trip_id = t15.i_pdb_trip_id  
            LEFT OUTER JOIN ${EDW_OS_EDWDB}.pdb_trip_info AS T2 
                ON T1.i_pdb_trip_id = T2.i_pdb_trip_id  
               AND T1.i_dmid        = T2.i_dmid 
               AND T1.c_prop_cd     = T2.c_prop_cd 
            LEFT JOIN ${EDW_OS_EDWDB}.pdb_trip_ap_pdb_trip_rel T4 
                ON T1.i_dmid                 = T4.i_dmid 
               AND T1.d_end_dt              >= add_months(current_date ,- 36)
               AND T4.i_prop_market_type_id  = 1
               AND T1.c_prop_cd              = T4.c_prop_cd 
               AND T1.i_pdb_trip_id          = T4.i_pdb_trip_id 
            WHERE T1.d_end_dt >= add_months(('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) ,- 36) 
               AND T1.i_dmid   = ISLIST.i_dmid

      ) AS exporttxt
ORDER BY sqlorder;
guest_interact
LOCK ROW FOR ACCESS
select a.sqltxt (TITLE'')
FROM 
( 
         SELECT 1 AS sqlorder,cast('i_dmid,D_CONTACT_DT,c_prop_cd,c_rep_id,I_CONTACT_ID,C_DIRECTION,c_contacted_channel,c_outcome,c_outcome_desc,C_CONTACT_SUBJECT,c_contact_status,c_creator_login,c_book_id' as char(250)) AS sqltxt
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
         SELECT
		 2 AS sqlorder,
             CAST((trim(cast(i_dmid_masked as char(12)))
            ||','|| upper(trim(cast(T1.D_CONTACT_DT as date format 'ddmmmyyyy')))
            ||','|| trim(T1.c_prop_cd)
            ||','|| trim(T1.c_rep_id)
            ||','|| trim(cast(T1.I_CONTACT_ID AS DECIMAL(11) FORMAT 'Z(11)9'))
            ||','|| trim(T1.C_DIRECTION)
            ||','|| trim(T1.c_contacted_channel)
            ||','|| trim(T1.C_OUTCOME)
            ||','|| trim(T1.c_outcome_desc)
            ||','|| trim(T1.C_CONTACT_SUBJECT)
            ||','|| trim(T1.c_contact_status)
            ||','|| trim(T1.c_creator_login)
            ||','|| trim(T1.c_book_id)) AS CHAR(250)) AS sqltxt
         from ${EDW_OS_PCSMAINDB}.pcs_contact_history T1, workdb.inside_sales_dmid  as ISList
         where substr(UPPER(T1.c_rep_id),1,1) in ('C','D','E','F','J','L','H','I','A','M')
            and c_direction not in ('')
            and T1.i_dmid       >  0
            and T1.i_dmid        = ISLIST.i_dmid
            and T1.D_CONTACT_DT >=('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD'))- INTERVAL '3' YEAR ) a
			order by a.sqlorder ;
hotel_resv
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            CAST('i_dmid,c_prop_cd,f_reserve_id,c_activity_status,c_source_cat1,d_reserve_dt,d_arrival_dt,d_departure_dt,c_offer_id,res_source,c_tracking' AS CHAR(650)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION 
SELECT 
2 AS sqlorder,
TRIM (CAST(((gha.i_dmid*13)+10)AS CHAR(12)))
||','||COALESCE(TRIM(CAST(gha.c_prop_cd AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(gha.f_reserve_id AS BIGINT)),'')
||','||COALESCE(TRIM(CAST(gha.c_activity_status AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(lsds.c_source_cat1 AS CHAR(20))),'')
||','||UPPER(COALESCE(TRIM(CAST(cast(gha.d_reserve_dt As date format 'ddmmmyyyy')as varCHAR(20))),''))
||','||UPPER(COALESCE(TRIM(CAST(cast(gha.d_arrival_dt As date format 'ddmmmyyyy')as varCHAR(20))),''))
||','||UPPER(COALESCE(TRIM(CAST(cast(gha.d_departure_dt As date format 'ddmmmyyyy')as varCHAR(20))),''))
||','||COALESCE(TRIM(CAST(SUBSTR(c_offer_id,1,5)AS CHAR(10))),'')
||','|| COALESCE(TRIM(CAST(CASE 
            WHEN c_reservation_agent_id = 'INTERNET' THEN 'b. H/C.COM Book' 
		    WHEN SUBSTR(c_reservation_agent_id,1,1)='\$' THEN 'c. SMART Booked (\$)'
			WHEN SUBSTR(c_reservation_agent_id,1,3) IN ('VIP','LVR','WST','WMK','WSP','WPN','WPT','LVC') THEN 'a. 1800-Call Center'
			WHEN SUBSTR(c_reservation_agent_id,1,3)='OTA' THEN 'd. HBSI/OTA book'
			WHEN SUBSTR(c_reservation_agent_id,1,3)='LV2' THEN 'e. LV CCS (LV2)'
			ELSE 'f. all Others'
			END AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(gha.c_tracking AS CHAR(50))),'')
FROM
${EDW_OS_VEDWDB}.gst_hotel_activity gha
INNER JOIN ${EDW_OS_WORKDB}.inside_sales_dmid gra
ON gha.i_dmid=gra.i_dmid
LEFT OUTER JOIN ${EDW_OS_EDWDB}.lms_source_desc_sda lsds
ON gha.c_prop_cd=lsds.c_prop_cd
AND gha.c_source_cd=lsds.c_source_cd
WHERE gha.d_reserve_dt >= ('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) - INTERVAL '2' YEAR
OR d_arrival_dt >= ('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) - INTERVAL '2' YEAR
      ) AS exporttxt
ORDER BY sqlorder;
mailcode_desc
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('c_prop_mail_cd,c_mail_desc,c_mail_flag' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION 
         SELECT 
            2 AS sqlorder,
            TRIM(c_prop_mail_cd)
            ||','||  case when index(c_mail_desc, ',') >0 then  '"'||TRIM(c_mail_desc)||'"'
                     else TRIM(c_mail_desc)
                     end 
            ||','||TRIM(c_mail_flag) AS sqltxt 
         FROM ${EDW_OS_VEDWDB}.mail_code_standard
      ) AS exporttxt
ORDER BY sqlorder;
mrkt_desc
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('c_prop_cd,c_prop_desc,c_prop_market_cd,i_prop_market_type_id,c_prop_market_desc' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION 
         SELECT 
            2 AS sqlorder,
            TRIM(c_prop_cd)
            -- handle comma in data
            ||','||  case when index(c_prop_desc, ',') >0 then  '"'||TRIM(c_prop_desc)||'"'
                     else TRIM(c_prop_desc)
                     end
            ||','||TRIM(c_prop_market_cd)
            ||','||TRIM(i_prop_market_type_id)
            ||','||TRIM(c_prop_market_desc) AS sqltxt
         FROM ${EDW_OS_VEDWDB}.prop_Desc_multi
      ) AS exporttxt
ORDER BY sqlorder;
nonvar_ofr_sent_val
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            CAST('c_offer_id,c_offer_desc,c_recp_grp_id,c_collateral_id,c_coupon_type,c_coupon_id,c_valid_prop_cd,f_coupon_value,d_start_dt_coupon,d_expire_dt_coupon,c_collateral_desc' AS CHAR(650)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT 
2 AS sqlorder,
COALESCE(TRIM(CAST(od.c_offer_id AS CHAR(30))),'')
||','||COALESCE(TRIM(CAST(od.c_offer_desc AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(rgc.c_recp_grp_id AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(rgc.c_collateral_id AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(occp.c_coupon_type AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(occp.c_coupon_id AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(occp.c_valid_prop_cd AS CHAR(50))),'')
||','||COALESCE(TRIM(cast(CAST(occp.f_coupon_value AS decimal (15,2) FORMAT 'Z(9)9.99')as VARCHAR(50))),'')
||','||UPPER(COALESCE(TRIM(cast(cast(occp.d_start_dt As date format 'ddmmmyyyy') as CHAR(20))),'')) 
||','||UPPER(COALESCE(TRIM(cast(cast(occp.d_expire_dt As date format 'ddmmmyyyy') as CHAR(20))),'')) 
||','||COALESCE(TRIM(CAST(OC.c_collateral_desc AS CHAR(50))),'')
FROM
${EDW_OS_VEDWDB}.offer_desc od, 
${EDW_OS_VEDWDB}.recp_grp_coll rgc, 
${EDW_OS_VEDWDB}.recp_group rg, 
${EDW_OS_VEDWDB}.ofr_coll_coup_prop occp , 
${EDW_OS_VEDWDB}.offer_collateral oc
where od.c_offer_id=rgc.c_offer_id 
and od.c_offer_id=rg.c_offer_id 
and od.c_offer_id=occp.c_offer_id 
and rgc.c_collateral_id=occp.c_collateral_id 
and occp.c_offer_id=oc.c_offer_id 
and occp.c_collateral_id=oc.c_collateral_id 
and rgc.c_recp_grp_id=rg.c_recp_grp_id 
and od.d_expire_dt >= ('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) -INTERVAL '3' year
and rg.c_recp_grp_type NOT IN ('X','C','Z')
      ) AS exporttxt
ORDER BY sqlorder;

ofr_chnl_typ_desc

LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            'c_channel_type,c_channel_desc' AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT
2 AS sqlorder ,
TRIM (c_channel_type)
||','|| TRIM (c_channel_desc)
FROM
${EDW_OS_VEDWDB}.ofr_channel_type
      ) AS exporttxt
ORDER BY sqlorder;
ofr_stat_desc
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT DISTINCT
            1 AS sqlorder,
            cast('c_offer_status,c_offer_stat_desc' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION 
         SELECT 
            2 AS sqlorder,
            trim(c_offer_status)
            ||','||trim( c_offer_stat_desc) AS sqltxt
         from ${EDW_OS_VEDWDB}.OFFER_STATUS_DESC
      ) AS exporttxt
ORDER BY sqlorder;
ofr_typ_desc
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            'c_offer_type,c_ofr_type_desc' AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT
2 AS sqlorder,
TRIM(c_offer_type)
||','||TRIM(c_ofr_type_desc)
FROM
${EDW_OS_VEDWDB}.offer_type
      ) AS exporttxt
ORDER BY sqlorder;
phone_flag
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('i_dmid,phone_bad_format_flag,dupe_num_flag,phone_combo_flag' as char(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION
        SELECT 
            2 AS sqlorder,
            TRIM(CAST(i_dmid_masked AS CHAR(12)))
            ||','|| COALESCE (MAX(CASE WHEN LENGTH( TRIM( GAD.c_area_cd ) ) = 3
                                 AND LENGTH( TRIM( GAD.c_tele_num ) ) = 7 
                            THEN 'G' 
                            ELSE 'B'
                        END),'')
            ||','|| COALESCE(MAX(CASE WHEN GADsub.c_area_cd IS NULL 
                            THEN 'G' 
                            ELSE 'B' 
                        END),'')
            ||','|| COALESCE(MAX(CASE WHEN LENGTH( TRIM( GAD.c_area_cd ) ) = 3
                             AND LENGTH( TRIM( GAD.c_tele_num ) ) = 7 
                        THEN 'G' 
                        ELSE 'B'
                    END) ||
                    MAX(CASE WHEN GADsub.c_area_cd IS NULL 
                        THEN 'G' 
                        ELSE 'B' 
                    END),'') AS sqltxt

            FROM  ${EDW_OS_WORKDB}.inside_sales_dmid AS ISList,
                  ${EDW_OS_VEDWDB}.gst_addr_dtl GAD
            LEFT JOIN ( SELECT DISTINCT   GAD2.c_area_cd,
                              GAD2.c_tele_num,
                              COUNT(DISTINCT GAD2.i_dmid) AS  gst_count
                        FROM  VEDW.gst_addr_dtl GAD2
                        GROUP BY GAD2.c_area_cd,
                              GAD2.c_tele_num
                        HAVING   COUNT(DISTINCT GAD2.i_dmid) >= 10
                     ) GADsub
               ON GAD.c_area_cd = GADsub.c_area_cd
               AND GAD.c_tele_num = GADsub.c_tele_num
            WHERE   GAD.i_dmid=ISLIST.i_dmid 
            AND  GAD.c_area_cd IS NOT  NULL
            AND  GAD.c_tele_num IS NOT NULL
            AND  GAD.c_area_cd NOT IN ('',' ','  ','   ')
            AND  GAD.c_tele_num NOT IN ('',' ','  ','   ') 
            GROUP BY GAD.i_dmid,i_dmid_masked
      ) AS exporttxt
ORDER BY sqlorder;
prop_desc
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            'c_prop_cd,c_prop_desc,c_state_group,c_division_desc,c_country_desc,c_brand_desc,f_latitude,f_longitude,c_zip_cd,c_city_name,c_address,c_phone_number,f_ggr_tax_rate,f_ggr_tax_rate_slot,f_ggr_tax_rate_table,f_ggr_tax_rate_other,c_rr_tax_flag' AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION
         SELECT 
            2 AS sqlorder,
            COALESCE(TRIM(CAST(c_prop_cd AS VARCHAR(20))),'')
            ||','|| COALESCE(TRIM(c_prop_desc),'')
            ||','|| COALESCE(TRIM(c_state_group),'')
            ||','|| COALESCE(TRIM(c_division_desc),'')
            ||','|| COALESCE(TRIM(c_country_desc),'')
            ||','|| COALESCE(TRIM(c_brand_desc),'')
            ||','|| COALESCE(TRIM(CAST(f_latitude AS DECIMAL(22,4))),'')
            ||','|| COALESCE(TRIM(CAST(f_longitude AS DECIMAL(22,4))),'') 
            ||','|| COALESCE(TRIM(c_zip_cd),'') 
            ||','|| COALESCE(TRIM(c_city_name),'') 
            ||','|| COALESCE(TRIM(c_address),'')
            ||','|| COALESCE(TRIM(c_phone_number),'')
            ||','|| COALESCE(TRIM(CAST(f_ggr_tax_rate AS DECIMAL(8,6) FORMAT '9.99999' )),'') 
            ||','|| COALESCE(TRIM(CAST(f_ggr_tax_rate_slot AS DECIMAL(8,6) FORMAT '9.99999')),'') 
            ||','|| COALESCE(TRIM(CAST(f_ggr_tax_rate_table AS DECIMAL(8,6) FORMAT '9.99999')),'') 
            ||','|| COALESCE(TRIM(CAST(f_ggr_tax_rate_other AS DECIMAL(8,6) FORMAT '9.99999')),'')
            ||','|| COALESCE(TRIM(c_rr_tax_flag),'') AS sqltxt
         FROM ${EDW_OS_VEDWDB}.prop_desc_sda
		 
      ) AS exporttxt
ORDER BY sqlorder;
rdmd_ofrs
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
			cast('i_dmid,c_valid_prop_cd,c_offer_id,c_recp_grp_id,c_coupon_type,c_coupon_id,c_collateral_id,c_offer_status,c_offer_status_dt,i_pdb_trip_id,f_rdm_amount' as  CHAR(650)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT 
2 AS sqlorder,
 TRIM(CAST(ISLIST.I_DMID AS CHAR(12))) 
				||','||COALESCE(TRIM(cast(ISList.c_valid_prop_cd as char(10))),'')
				||','||COALESCE(TRIM(cast(ISList.c_offer_id as char(15))),'')
				||','||COALESCE(TRIM(cast(ISList.c_recp_grp_id as char(15))),'')
				||','||COALESCE(TRIM(cast(ISList.c_coupon_type as char(15))),'')
				||','||COALESCE(TRIM(cast(ISList.c_coupon_id as char(15))),'')
				||','||COALESCE(TRIM(cast(ISList.c_collateral_id as char(27))),'')
				||','||COALESCE(TRIM(cast(ISList.c_offer_status as char(27))),'')
				||','||UPPER(COALESCE(TRIM(CAST(cast(ISList.d_offer_status_dt As date format 'ddmmmyyyy')as varCHAR(20))),''))
				||','||COALESCE(TRIM(cast(ISList.i_pdb_trip_id as char(40))),'')
				||','||trim(cast( cast( coalesce((case when variable_coupon_amt=0 then 0.0 else variable_coupon_amt end),f_rdm_amount,0) as decimal  (15,4) FORMAT 'Z(9)9.9999') as varchar(20)))
        
FROM    workdb.offers_volatile ISList
		  )AS exporttxt
		  ORDER BY sqlorder;

recp_group
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('c_offer_id,c_recp_grp_id,c_recp_grp_desc,c_gst_contact_cd,c_channel_type' as varchar(1024)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION
         SELECT
            2 AS sqlorder,
             COALESCE(TRIM(CAST(T1.c_offer_id AS VARCHAR(20))),'')
            ||','||COALESCE(TRIM(CAST(T1.c_recp_grp_id AS VARCHAR(50))),'')
			||','||COALESCE(TRIM(CAST(T1.c_recp_grp_desc AS VARCHAR(50))),'')
			||','||COALESCE(TRIM(CAST(T1.c_gst_contact_cd AS VARCHAR(50))),'')
			||','||COALESCE(TRIM(CAST(T1.c_channel_type AS VARCHAR(50))),'') AS sqltxt
         FROM ${EDW_OS_VEDWDB}.RECP_GROUP T1
		 INNER JOIN ${EDW_OS_VEDWDB}.offer_desc C 
		 ON T1.c_offer_id = C.c_offer_id AND
		 T1.d_purge_date = C.d_purge_date AND 
		 T1.c_purge_flag = C.c_purge_flag
         where  T1.c_recp_grp_type not in ('X','C','Z')
      ) AS exporttxt
ORDER BY sqlorder;
sent_offers

LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
           1 AS sqlorder,
            Cast('i_dmid,c_offer_status,c_offer_id,c_recp_grp_id,d_send_date,sent_prop_desc,c_offer_desc,c_offer_type,d_start_dt,d_expire_dt' AS CHAR(250)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT 
2 AS sqlorder,
    TRIM (CAST(((B.i_dmid*13)+10)AS CHAR(12)))
       ||','||COALESCE(TRIM(casT(b.c_offer_status as varchar(20))),'')
	   ||','||COALESCE(TRIM(cast(B.c_offer_id as VARCHAR(20))),'')
	   ||','||COALESCE(TRIM(cast(B.c_recp_grp_id as VARCHAR(20))),'')
       ||','||UPPER(COALESCE(TRIM(CAST(cast(b.d_send_date As date format 'ddmmmyyyy')as CHAR(20))),''))
	   ||','||COALESCE(TRIM(cast(substr(c.c_offer_desc ,1 ,3) as VARCHAR(20))),'')
	   ||','||COALESCE(TRIM(cast(c_offer_desc as VARCHAR(20))),'')
	   ||','||COALESCE(TRIM(cast(c_offer_type as VARCHAR(20))),'')
       ||','||UPPER(COALESCE(TRIM(cast(cast(c.d_start_dt As date format 'ddmmmyyyy')as CHAR(20))),''))
	   ||','||UPPER(COALESCE(TRIM(cast(cast(c.d_expire_dt As date format 'ddmmmyyyy') as CHAR(20))),''))
FROM   ${EDW_OS_VEDWDB}.guest_level7 AS ISList, 
       ${EDW_OS_VEDWDB}.gst_offer_sent b 
       inner join ${EDW_OS_VEDWDB}.offer_desc C 
               ON B.c_offer_id = C.c_offer_id 
                  AND B.d_purge_date = C.d_purge_date 
                  AND B.c_purge_flag = C.c_purge_flag 
       left join ${EDW_OS_VEDWDB}.recp_group A 
              ON B.c_offer_id = A.c_offer_id 
                 AND B.d_purge_date = A.d_purge_date 
                 AND B.c_purge_flag = A.c_purge_flag 
                 AND B.c_recp_grp_id = A.c_recp_grp_id 
WHERE  b.i_dmid = ISLIST.i_dmid 
       AND (b.d_send_date >= CURRENT_DATE - INTERVAL '3' YEAR OR d_expire_dt >= CURRENT_DATE - INTERVAL '3' YEAR)
       AND a.c_recp_grp_type NOT IN ( 'X', 'C', 'Z' )
	     ) AS exporttxt
		 ORDER BY sqlorder;

trip_survey
LOCK ROW FOR ACCESS
SELECT 
trim(sqltxt)||','||trim(sqltext1)||','||trim(sqltext2)||','||trim(sqltext3)||','||trim(sqltext4) (title '')
FROM (   
      SELECT 
            1 AS sqlorder,
            cast('i_dmid,c_prop_cd,D_ProcessedDateKey,TripEndDate,c_rep_id' as char(400)) AS sqltxt,cast('Overall_Experience'  as char(50))as sqltext1,'Overall_Cust_Service' as sqltext2,'Host_Initiate_Contact' as sqltext3,'Share_of_Wallet' as sqltext4
         FROM dbc.dbcinfo
         GROUP BY 1
      UNION 
      SELECT
	  2 AS sqlorder, 
            trim(CAST(ISList.i_dmid_masked as CHAR(12)))
            ||','||COALESCE(TRIM(CAST(T1.C_PropertyVisitCode AS CHAR(20))),'')
			||','||UPPER(COALESCE(TRIM(CAST(cast(T1.D_ProcessedDateKey As date format 'ddmmmyyyy')as varCHAR(20))),''))
            ||','||UPPER(COALESCE(TRIM(CAST(cast(T1.D_TripEndDate As date format 'ddmmmyyyy')as varCHAR(20))),''))
            ||','||COALESCE(TRIM(CAST(T1.C_CasinoRepCode AS CHAR(20))),'')
            ,COALESCE(TRIM(MAX(CASE WHEN T1.I_QuestionBusinessKey = 2 THEN T1.c_answer ELSE NULL end)),'')
            ,COALESCE(TRIM(MAX(CASE WHEN T1.I_QuestionBusinessKey = 3 THEN T1.c_answer ELSE NULL end)),'') 
            ,COALESCE(TRIM(MAX(CASE WHEN T1.I_QuestionBusinessKey = 2768 THEN T1.c_answer ELSE NULL end)),'') 
           ,COALESCE(TRIM(MAX(CASE WHEN T1.I_QuestionBusinessKey = 2534 THEN T1.c_answer ELSE NULL end)),'') 
           FROM ${EDW_OS_VEDWDB}.TSS_SURVEY T1
            ,${EDW_OS_WORKDB}.inside_sales_dmid ISList
         WHERE T1.I_QuestionBusinessKey IN (2,3,2768,2534)
         AND T1.i_dmid=ISLIST.i_dmid
         AND CAST(T1.D_TripEndDate AS DATE ) >= date '2015-01-01'
         group by 1,2
      ) AS exporttxt
ORDER BY sqlorder;

var_ofr_sent_val

LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,
            cast('i_dmid,c_offer_id,c_collateral_id,c_coupon_type,c_coupon_id,d_start_dt_coupon,d_expire_dt_coupon,c_redeem,f_var_coup_value' AS char(650)) AS sqltxt 
         FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT
2 as sqlorder,
TRIM (CAST(CAST(((i_dmid*13)+10)AS CHAR(12))
||','||COALESCE(TRIM(CAST(c_offer_id AS CHAR(20))),'')
||','||COALESCE(TRIM(CAST(c_collateral_id AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(c_coupon_type AS CHAR(50))),'')
||','||COALESCE(TRIM(CAST(c_coupon_id AS CHAR(50))),'')
||','||UPPER(COALESCE(TRIM(CAST(cast(d_start As date format 'ddmmmyyyy')as varCHAR(20))),''))
||','||UPPER(COALESCE(TRIM(CAST(cast(d_expire As date format 'ddmmmyyyy')as varCHAR(20))),''))
||','||COALESCE(TRIM(CAST(c_redeem AS CHAR(50))),'')
||','||COALESCE(TRIM(cast(CAST(f_var_coup_value AS decimal (15,2) FORMAT 'Z(9)9.99')as VARCHAR(50))),'') as char(350))) As sqltxt
FROM ${EDW_OS_VEDWDB}.GST_CASH_MASTER
WHERE d_expire >= CURRENT_DATE - INTERVAL '3' YEAR
      ) AS exporttxt
ORDER BY sqlorder;
wkly_callscore
LOCK ROW FOR ACCESS
SELECT 
sqltxt (title '')
FROM (
         SELECT 
            1 AS sqlorder,CAST('i_dmid,c_prop_cd,c_rep_id,Snapshotdate,callvalue,callscore,reasontext' AS CHAR(450)) AS sqltxt                                                                                                                                                  
		 FROM dbc.dbcinfo
         GROUP BY 1
UNION
SELECT 
2 AS sqlorder,
TRIM (CAST(((i_dmid*13)+10)AS CHAR(12)))
||','||COALESCE(TRIM(c_prop_cd),'')
||','||COALESCE(TRIM(c_rep_id),'')
||','||COALESCE(TRIM(cast(d_snapshot_date As CHAR(20))),'')
||','||COALESCE(TRIM(cast(i_callvalue as char(27))),'')
||','||COALESCE(TRIM(cast(i_callscore as char(27))),'')
||','||COALESCE(TRIM(cast(c_reasontext as char(200))),'')

FROM 
sasdb.CM_Weekly_callscores_history
WHERE d_snapshot_date >= ('${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')) - INTERVAL '2' year
AND c_rep_id LIKE ANY ('C%','D%','E%','F%','J%','L%','H%','I%','A%','M%')
      ) AS exporttxt
ORDER BY sqlorder;
























