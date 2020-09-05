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