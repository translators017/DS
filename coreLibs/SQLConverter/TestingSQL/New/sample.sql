INSERT INTO
   $ {EDW_OS_CNTLDB}. $ {EDW_AUDIT_DTL_TABLE_NAME} 
   SELECT
      '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD') as d_edw_order_date,
      '${EDW_ORDER_ID}' as c_edw_order_id,
      '${C_SERVER_NAME}' as c_cms_server_name,
      '${EDW_STREAM_NAME}' as c_edw_stream_name,
      dtl.c_edw_substream_name as c_edw_substream_name,
      dtl.c_prop_cd as c_prop_cd,
      dtl.d_extract_to as d_extract_from,
      svr.d_src_current_time as d_extract_to,
      CURRENT_TIMESTAMP(0) as d_started,
      NULL as d_stageed,
      NULL as d_completed,
      'S' as c_status,
      NULL,
      NULL 
   FROM
      $ {EDW_OS_CNTLDB}. $ {EDW_AUDIT_DTL_TABLE_NAME} dtl,
      (
         SELECT
            b.c_prop_cd,
            b.c_edw_stream_name,
            b.c_edw_substream_name,
            MAX(d_edw_order_date) as d_edw_order_date,
            MAX(c_edw_order_id) as c_edw_order_id,
            MAX(b.d_extract_to) as d_extract_to 
         FROM
            $ {EDW_OS_CNTLDB}. $ {EDW_AUDIT_DTL_TABLE_NAME} b,
            v_cms_active v 
         WHERE
            c_edw_stream_name = '${EDW_STREAM_NAME}' 
            AND b.c_prop_cd = v.c_prop_cd 
            AND b.c_status = 'C' 
            AND b.d_extract_to IS NOT NULL 
         GROUP BY
            1,
            2,
            3 
      )
      mx,
      (
         SELECT
            c_edw_stream_name,
            d_src_current_time 
         FROM
            $ {EDW_OS_CNTLDB}. $ {EDW_AUDIT_TABLE_NAME} 
         WHERE
            c_edw_stream_name = '${EDW_STREAM_NAME}' 
            AND c_cms_server_name = '${C_SERVER_NAME}' 
            AND d_edw_order_date = '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD') 
            AND c_edw_order_id = '${EDW_ORDER_ID}' 
      )
      svr 
   WHERE
      dtl.c_prop_cd = mx.c_prop_cd 
      AND dtl.d_extract_to = mx.d_extract_to 
      AND dtl.d_edw_order_date = mx.d_edw_order_date 
      AND dtl.c_edw_stream_name = mx.c_edw_stream_name 
      AND dtl.c_edw_substream_name = mx.c_edw_substream_name 
      AND dtl.c_edw_order_id = mx.c_edw_order_id 
      AND dtl.c_edw_stream_name = svr.c_edw_stream_name 
      AND NOT EXISTS 
      (
         SELECT
            * 
         FROM
            $ {EDW_OS_CNTLDB}. $ {EDW_AUDIT_DTL_TABLE_NAME} c 
         WHERE
            dtl.c_prop_cd = c.c_prop_cd 
            AND dtl.d_extract_to = c.d_extract_from 
            AND dtl.c_edw_stream_name = c.c_edw_stream_name 
            AND dtl.c_edw_substream_name = c.c_edw_substream_name 
            AND c.d_edw_order_date = '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD') 
            AND c.c_edw_order_id = '${EDW_ORDER_ID}' 
      )
;
COPY TO CLIPBOARD	 SELE