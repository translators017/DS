INSERT INTO
   H_T3_ERROR_LOG (SEQ_ID, COUNTRY_CODE, ERROR_CODE, CREATED_DATE, UPDATED_DATE, VARIABLE1, VARIABLE2, VARIABLE4, FILENAME, TYPE_KW, GROUP_KW, SUBGROUP, STEP_ID) 
   SELECT
      ROW_NUMBER() OVER(
   ORDER BY
      S.COUNTRY_CODE, S.CODE, M.MARKET_DESC_MULTI_SOURCE) + MAX_SEQ_ID.ID SEQ_ID,
      S.COUNTRY_CODE,
      'ERR025' ERROR_CODE,
      CURRENT_TIMESTAMP(0) CREATED_DATE,
      CURRENT_TIMESTAMP(0) UPDATED_DATE,
      cast(cast(P.PRODUCT_CODE_MULTI_SOURCE as varchar(20)) || '||' || P.PRODUCT_DESC_MULTI_SOURCE as varchar(100)) VARIABLE1,
      cast(S.CODE as varchar(50)) VARIABLE2,
      'Supplied Product in Product Rebuild' VARIABLE4,
      NVL(R.FILE_NAME, '') FILENAME,
      'Files' AS TYPE_KW,
      'SALES' as GROUP_KW,
      'MARKET_REBUILD' SUBGROUP,
      MAX_STEP_ID.ID as STEP_ID 
   FROM
      (
         select distinct
            SRC_MULTI_SOURCE_ID,
            MARKET_MULTI_SOURCE_ID,
            PRODUCT_MULTI_SOURCE_ID,
            COUNTRY_ID 
         from
            (
               SELECT
                  src.SRC_MULTI_SOURCE_ID,
                  src.TIME_ID,
                  src.CUSTOMER_ID,
                  src.GEOGRAPHY_ID,
                  src.MARKET_MULTI_SOURCE_ID,
                  src.PRODUCT_MULTI_SOURCE_ID,
                  src.CHANNEL_MULTI_SOURCE_ID,
                  src.COUNTRY_ID,
                  count(1) CNT 
               FROM
                  (
                     select
                        SRC_MULTI_SOURCE_ID,
                        TIME_ID,
                        CUSTOMER_ID,
                        GEOGRAPHY_ID,
                        MARKET_MULTI_SOURCE_ID,
                        PRODUCT_MULTI_SOURCE_ID,
                        CHANNEL_MULTI_SOURCE_ID,
                        COUNTRY_ID 
                     from
                        F_T3_PRODUCT_SALES_MULTI_SOURCE_VT 
                     where
                        MARKET_MULTI_SOURCE_ID <> '-1' 
                     group by
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8 
                     union all
                     select
                        SRC_MULTI_SOURCE_ID,
                        TIME_ID,
                        CUSTOMER_ID,
                        GEOGRAPHY_ID,
                        MARKET_MULTI_SOURCE_ID,
                        PRODUCT_MULTI_SOURCE_ID,
                        CHANNEL_MULTI_SOURCE_ID,
                        COUNTRY_ID 
                     from
                        ${COREVIEW}.F_T3_PRODUCT_SALES_MULTI_SOURCE 
                     group by
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8 
                  )
                  src 
               group by
                  1,
                  2,
                  3,
                  4,
                  5,
                  6,
                  7,
                  8 
               having
                  count(1) > 1 
            )
            t 
      )
      org 
      JOIN
         ${COREVIEW}.D_T3_SOURCE_MULTI_SOURCE S 
         ON S.SRC_MULTI_SOURCE_ID = org.SRC_MULTI_SOURCE_ID 
         AND S.COUNTRY_ID = org.COUNTRY_ID 
      JOIN
         ${COREVIEW}.D_T3_MARKET_MULTI_SOURCE M 
         ON org.MARKET_MULTI_SOURCE_ID = M.MARKET_MULTI_SOURCE_ID 
         AND org.COUNTRY_ID = M.COUNTRY_ID 
      JOIN
         ${COREVIEW}.D_T3_PRODUCT_MULTI_SOURCE P 
         ON org.MARKET_MULTI_SOURCE_ID = P.MARKET_MULTI_SOURCE_ID 
         AND org.PRODUCT_MULTI_SOURCE_ID = P.PRODUCT_MULTI_SOURCE_ID 
         AND org.COUNTRY_ID = P.COUNTRY_ID 
      left join
         (
            select distinct
               SOURCE,
               COUNTRY,
               FILE_NAME 
            from
               ${STAGEDB}.SF_T3_REBUILD_MULTI_SOURCE
         )
         R 
         on s.CODE = R.SOURCE 
         and s.COUNTRY_CODE = trim(R.COUNTRY) 
      CROSS JOIN
         (
            SELECT
               COALESCE(MAX(STEP_ID), 0) + 1 ID 
            FROM
               ${COREVIEW}.H_T3_ERROR_LOG 
            WHERE
               GROUP_KW = 'SALES' 
               AND SUBGROUP = 'MARKET_REBUILD'
         )
         MAX_STEP_ID 
      CROSS JOIN
         (
            SELECT
               COALESCE(MAX(SEQ_ID), 0) ID 
            FROM
               ${COREVIEW}.H_T3_ERROR_LOG 
            WHERE
               GROUP_KW = 'SALES' 
               AND SUBGROUP = 'MARKET_REBUILD'
         )
         MAX_SEQ_ID;