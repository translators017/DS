
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.omg.Messaging.SyncScopeHelper;

import com.dataSwitch.ds.functionparser.Lexer;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;

public class FunctionTypeConv 
{
	static HashMap<String,String>  globalFnMap = new HashMap<String,String>();
	
	public static void main (String args[])
	{
		
		//String srcFnType = "IIF(ISNULL(comp.TRNSFD_VALU,a.*,1.10) OR NOT IS_NUMBER(:LKP.CleanseAmount(TRNSFD_VALU,56465jibnkm)),'Transferred Value is required and should be a number; ',IIF(IN(TRNS_STS_CD,'C','D') AND TO_DECIMAL(TRNSFD_VALU) < 0 ,:LKP.CleanseAmount2(TRNSFD_VALU),'Transferred Value must be equal or greater than zero for Change and Delete records; ',NULL),:LKP.CleanseAmount3(TRNSFD_VALU)) ";
		//String srcFnType = "IF ( IF IsNotNull((rw_LKP_ITEM.LP_MM1)) THEN (rw_LKP_ITEM.LP_MM1) ELSE (0)) = 0 and ( IF IsNotNull((rw_LKP_ITEM.LP_MM2)) THEN (rw_LKP_ITEM.LP_MM2) ELSE (0)) = 0 and ( IF IsNotNull((rw_LKP_ITEM.LP_MM3)) THEN (rw_LKP_ITEM.LP_MM3) ELSE (0)) = 0 and ( IF IsNotNull((rw_LKP_ITEM.LP_MM)) THEN (rw_LKP_ITEM.LP_MM) ELSE (0)) = 0 THEN Left(START_BATDT, 4) ELSE  if ( IF IsNotNull((rw_LKP_ITEM.LP_MM1)) THEN (rw_LKP_ITEM.LP_MM1) ELSE (0)) = 0 and (( IF IsNotNull((rw_LKP_ITEM.LP_MM2)) THEN (rw_LKP_ITEM.LP_MM2) ELSE (0)) <> 0 or ( IF IsNotNull((rw_LKP_ITEM.LP_MM3)) THEN (rw_LKP_ITEM.LP_MM3) ELSE (0)) <> 0) then 0 ELSE  if ( IF IsNotNull((rw_LKP_ITEM.LP_MM1)) THEN (rw_LKP_ITEM.LP_MM1) ELSE (0)) <> 0 THEN rw_LKP_ITEM.LP_YY1 ELSE rw_LKP_ITEM.LP_YY";
		//String srcFnType = "DS_CHECK_IF( DS_CHECK_NULL( TRNSFD_VALU) || OR NOT DS_CHECK_NUM(dsError_:UDF.CleanseAmount( TRNSFD_VALU)), 'Transferred Value is required and should be a number; ', DS_CHECK_IF( dsError_IN( TRNS_STS_CD, 'C', 'D') AND DS_CONV_STRTODEC( TRNSFD_VALU) < 0, 'Transferred Value must be equal or greater than zero for Change and Delete records; ', NULL))----- All records require a Transferred Value and it must be a number";
		//String srcFnType = "row_number() over(partition by a.c_prop_cd,a.c_pos_rev_center_cd,a.c_pos_trans_id,a.i_check_nbr,a.d_pos_trans_start_time,a.d_pos_trans_end_time order by a.d_pos_trans_start_time)";
		//String srcFnType = "if isnull(ds_hi_provider.PAR) then Space(1) Else  if trim(ds_hi_provider.PAR) = '' then Space(1) Else ds_hi_provider.PAR : 3";
		//String srcFnType = "dsIIf(dsCheckNull(ds_hi_provider.PAR), dsErrorCheckHolidaySpace(1), dsIIf(dsTrim(ds_hi_provider.PAR)= '' , dsErrorCheckHolidaySpace(1), ds_hi_provider.PAR dsConcat 3))";
		//String srcFnType = "dsIIf dsCheckNull(ds_hi_provider.PAR), dsErrorCheckHolidaySpace(1), dsIIf(dsTrim(ds_hi_provider.PAR)= '' ,dsErrorCheckHolidaySpace(1), ds_hi_provider.PAR dsConcat 3)))";
		//String srcFnType = "dsIIf((dsCheckNull(ds_hi_provider.PAR), dsErrorCheckHolidaySpace(1), dsIIf((dsTrim(ds_hi_provider.PAR)= '', dsErrorCheckHolidaySpace(1),ds_hi_provider.PAR dsConcat 3))))";
		//String srcFnType = "if (isnull(ds_hi_provider.PAR) then Space(1) Else  if( trim(ds_hi_provider.PAR) = '' then Space(1) Else ds_hi_provider.PAR : 3))";
		//String srcFnType = "if cond then val1 else if cond then vla3 else val4";
		//String srcFnType = "if cond then if cond then val1 else val2 else val3";
		//String srcFnType = "trim((Funnel_114.seq_generate : Space(3))[1, 3])";
		//String srcFnType = "dsTrim(dsSubString((Funnel_114.seq_generate dsConcat dsErrorCheckHolidaySpace(3)), 1,3))";
		//String srcFnType = "INSERT INTO ${EDW_OS_CNTLDB}.EDW_AUDIT_DTL_TABLE_NAME SELECT '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD')     as d_edw_order_date , '${EDW_ORDER_ID}'         as c_edw_order_id , '${C_SERVER_NAME}'        as c_cms_server_name , '${EDW_STREAM_NAME}'      as c_edw_stream_name , dtl.c_edw_substream_name   as c_edw_substream_name , dtl.c_prop_cd             as c_prop_cd , dtl.d_extract_to          as d_extract_from , svr.d_src_current_time    as d_extract_to , CURRENT_TIMESTAMP(0)      as d_started , NULL                      as d_stageed , NULL                      as d_completed , 'S'                       as c_status , NULL , NULL FROM ${EDW_OS_CNTLDB}.${EDW_AUDIT_DTL_TABLE_NAME} dtl , ( SELECT b.c_prop_cd , b.c_edw_stream_name , b.c_edw_substream_name , MAX(d_edw_order_date) as d_edw_order_date , MAX(c_edw_order_id) as c_edw_order_id , MAX(b.d_extract_to) as d_extract_to FROM ${EDW_OS_CNTLDB}.${EDW_AUDIT_DTL_TABLE_NAME} b ,  v_cms_active v WHERE c_edw_stream_name = '${EDW_STREAM_NAME}' AND b.c_prop_cd       = v.c_prop_cd AND b.c_status        = 'C' AND b.d_extract_to IS NOT NULL GROUP BY 1,2,3 ) mx , ( SELECT c_edw_stream_name , d_src_current_time FROM ${EDW_OS_CNTLDB}.${EDW_AUDIT_TABLE_NAME} WHERE c_edw_stream_name = '${EDW_STREAM_NAME}' AND c_cms_server_name = '${C_SERVER_NAME}' AND d_edw_order_date  = '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD') AND c_edw_order_id    = '${EDW_ORDER_ID}' ) svr WHERE dtl.c_prop_cd             = mx.c_prop_cd AND dtl.d_extract_to          = mx.d_extract_to AND dtl.d_edw_order_date      = mx.d_edw_order_date AND dtl.c_edw_stream_name     = mx.c_edw_stream_name AND dtl.c_edw_substream_name  = mx.c_edw_substream_name AND dtl.c_edw_order_id        = mx.c_edw_order_id AND dtl.c_edw_stream_name     = svr.c_edw_stream_name AND NOT EXISTS ( SELECT * FROM  ${EDW_OS_CNTLDB}.${EDW_AUDIT_DTL_TABLE_NAME} c WHERE  dtl.c_prop_cd             = c.c_prop_cd AND  dtl.d_extract_to          = c.d_extract_from AND  dtl.c_edw_stream_name     = c.c_edw_stream_name AND  dtl.c_edw_substream_name  = c.c_edw_substream_name AND  c.d_edw_order_date        = '${EDW_ORDER_DATE}' (DATE, FORMAT 'YYYYMMDD') AND  c.c_edw_order_id          = '${EDW_ORDER_ID}' ) ;";
		//String srcFnType = "IF (EXSales.DOCUMENT_TYPE= 'ZCAZ') then EXSales.SKU dsConcat 'C' else If (EXSales.DOCUMENT_TYPE= 'ZCAW') then EXSales.SKU dsConcat 'T' else EXSales.SKU";
		//String srcFnType = "dsIIf((EXSales.DOCUMENT_TYPE= 'ZCAZ'),EXSales.SKU dsConcat 'C', dsIIf((EXSales.DOCUMENT_TYPE= 'ZCAW'),EXSales.SKU dsConcat 'T',EXSales.SKU))";
		//String srcFnType = "(dsIIf(dsCheckNull(ds_hi_provider.PAR)? dsErrorCheckHolidaySpace(1)dsElse dsIIf(dsTrim(ds_hi_provider.PAR)= '' ? dsErrorCheckHolidaySpace(1)dsElse ds_hi_provider.PAR dsConcat 3)))";

		//String srcFnType = "${EDWDB}.opera_folio_dtl_l_key.d_timestamp.new != 20";

		//String srcFnType = "If isnull(Funnel_114.PRISNUM) then space(15) else (Funnel_114.PRISNUM : Space(15))[1, 15]";
		//String srcFnType = "dsIIf(dsCheckNull(Funnel_114.PRISNUM) dsSubString(? dsErrorCheckHolidayspace(15),(Funnel_114.PRISNUM dsConcat dsErrorCheckHolidaySpace(15)),1,15))";
		
		//String srcFnType = "dsIIf(dsRightSubString(DSLink9.PoNumber,3)= '31/',dsSubString(DSLink9.PoNumber,4,15),DSLink9.PoNumber)";

		//String srcFnType = "dsIIf(dsNot(dsCheckNull(DSLink2.SKU)), dsTrim(DSLink2.SKU),'..')";
		
		//String srcFnType = "if isnull(x) then SetNull(x) else val3";
		//String srcFnType = "dsIIf(dsCheckNull(SelectMaxId.COLUMN_ID)dsOR SelectMaxId.COLUMN_ID < 1,1,SelectMaxId.COLUMN_ID)";
		
		//String srcFnType = "dsSpDecode(F0101JU_VW_acc_for_phone.WPPHTP, 'BUS', '0', NULL, '1', 'GVA', '2', 'OBBU', '3', '4')dsConcat '-' dsConcat dsLeftPad(dsNumToStr(dsStrToInt(F0101JU_VW_acc_for_phone.WPRCK7)), 5, '0')";

		
		//String srcFnType = "dsTrim(dsSubString((Funnel_114.seq_generate dsConcat dsError_Space(3)),1,3))";
		
		//String srcFnType = "CASE WHEN uhot_stg_mms_policy.BillType1 = 'D' THEN 'PAPR' WHEN uhot_stg_mms_policy.BillType1 = 'C' dsAnd uhot_stg_mms_policy.CreditCardNumber &lt;&gt; ' ' THEN 'CRCD' WHEN uhot_stg_mms_policy.BillType1 = 'C' dsAnd uhot_stg_mms_policy.CreditCardNumber = ' ' THEN 'PAPR' WHEN uhot_stg_mms_policy.BillType1 = 'P' dsAnd uhot_stg_mms_policy.AccountNumber &lt;&gt; ' ' THEN 'EFT1' WHEN uhot_stg_mms_policy.BillType1 = 'P' dsAnd uhot_stg_mms_policy.AccountNumber = ' ' THEN 'PAPR' END";
		//String srcFnType = "dsSubString(x,3)";
		
		//String srcFnType = "dsIIf(dsCheckNull(TRNSFD_VALU)dsOR dsNot dsCheckNum(dsErrorCheckHoliday:LKP.CleanseAmount(TRNSFD_VALU, 56465jibnkm)), 'Transferred Value is required and should be a number; ', dsIIf(dsIn(TRNS_STS_CD, 'C', 'D')dsAnd dsStrToDec(TRNSFD_VALU)< 0, dsErrorCheckHoliday:LKP.CleanseAmount2(TRNSFD_VALU), 'Transferred Value must be equal or greater than zero for Change and Delete records; ', NULL), dsErrorCheckHoliday:LKP.CleanseAmount3(TRNSFD_VALU))";
		//String srcFnType = "iif(   (isnull(sc_FSTBC161_PM_MO_CT.TX_WP_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.TX_COMM_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.TX_EP_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.TX_UEP_RSRV_A) OR  isnull(sc_FSTBC161_PM_MO_CT.TX_TAX_COLL_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.CAC_WP_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.CAC_COMM_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.CAC_EP_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.CAC_UEP_RSRV_A) OR  isnull(sc_FSTBC161_PM_MO_CT.CAC_P_CURR_ADJ_A) OR  isnull(sc_FSTBC161_PM_MO_CT.CAC_TAX_COLL_CYTD_A) OR  isnull(sc_FSTBC161_PM_MO_CT.WP_CYTD_A) OR   isnull(sc_FSTBC161_PM_MO_CT.COMM_CYTD_A) OR   isnull(sc_FSTBC161_PM_MO_CT.EP_CYTD_A) OR   isnull(sc_FSTBC161_PM_MO_CT.UEP_RSRV_A) OR  isnull(sc_FSTBC161_PM_MO_CT.INF_PREM_A) OR   isnull(sc_FSTBC161_PM_MO_CT.P_CURR_ADJ_A) OR  isnull(sc_FSTBC161_PM_MO_CT.TAX_COLL_CYTD_A) OR  isnull(sc_GFS_PREM_STG.WP_ORIG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.COMM_ORIG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.EP_CNG_ORIG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.UEP_CNG_ORIG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.SCHG_TAX_ORIG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.WP_LEDG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.COMM_LEDG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.EP_CNG_LEDG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.UEP_CNG_LEDG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.UEP_CURY_ADJ_CNG_LEDG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.SCHG_TAX_LEDG_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.WP_US_CURY_AMT) OR   isnull(sc_GFS_PREM_STG.COMM_US_CURY_AMT) OR   isnull(sc_GFS_PREM_STG.EP_CNG_US_CURY_AMT) OR   isnull(sc_GFS_PREM_STG.UEP_CNG_US_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.INFRC_CONTR_PREM_US_CURY_AMT) OR   isnull(sc_GFS_PREM_STG.UEP_CURY_ADJ_CNG_US_CURY_AMT) OR  isnull(sc_GFS_PREM_STG.SCHG_TAX_US_CURY_AMT) ), 'Y',  iif(v_TX_WP_CYTD_A_Diff<>0 OR   v_TX_COMM_CYTD_A_Diff<>0 OR   v_TX_EP_CYTD_A_Diff<>0 OR   v_TX_UEP_RSRV_A_Diff<>0 OR   v_TX_TAX_COLL_CYTD_A_Diff<>0 OR  v_CAC_WP_CYTD_A_Diff<>0 OR  v_CAC_COMM_CYTD_A_Diff<>0 OR  v_CAC_EP_CYTD_A_Diff<>0 OR  v_CAC_UEP_RSRV_A_Diff<>0 OR  v_CAC_P_CURR_ADJ_A_Diff<>0 OR  v_CAC_TAX_COLL_CYTD_A_Diff<>0 OR  v_WP_CYTD_A_Diff<>0 OR  v_COMM_CYTD_A_Diff<>0 OR  v_EP_CYTD_A_Diff<>0 OR  v_UEP_RSRV_A_Diff<>0 OR  v_INF_PREM_A_Diff<>0 OR  v_P_CURR_ADJ_A_Diff<>0 OR  v_TAX_COLL_CYTD_A_Diff<>0  , 'Y',  'N'                    )        )     ";
		//String srcFnType = "dsIIf(( dsCheckNull( sc_FSTBC161_PM_MO_CT.TX_WP_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.TX_COMM_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.TX_EP_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.TX_UEP_RSRV_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.TX_TAX_COLL_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.CAC_WP_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.CAC_COMM_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.CAC_EP_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.CAC_UEP_RSRV_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.CAC_P_CURR_ADJ_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.CAC_TAX_COLL_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.WP_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.COMM_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.EP_CYTD_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.UEP_RSRV_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.INF_PREM_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.P_CURR_ADJ_A) dsOR dsCheckNull( sc_FSTBC161_PM_MO_CT.TAX_COLL_CYTD_A) dsOR dsCheckNull( sc_GFS_PREM_STG.WP_ORIG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.COMM_ORIG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.EP_CNG_ORIG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.UEP_CNG_ORIG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.SCHG_TAX_ORIG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.WP_LEDG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.COMM_LEDG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.EP_CNG_LEDG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.UEP_CNG_LEDG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.UEP_CURY_ADJ_CNG_LEDG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.SCHG_TAX_LEDG_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.WP_US_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.COMM_US_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.EP_CNG_US_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.UEP_CNG_US_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.INFRC_CONTR_PREM_US_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.UEP_CURY_ADJ_CNG_US_CURY_AMT) dsOR dsCheckNull( sc_GFS_PREM_STG.SCHG_TAX_US_CURY_AMT)), 'Y', dsIIf( v_TX_WP_CYTD_A_Diff < > 0 dsOR v_TX_COMM_CYTD_A_Diff < > 0 dsOR v_TX_EP_CYTD_A_Diff < > 0 dsOR v_TX_UEP_RSRV_A_Diff < > 0 dsOR v_TX_TAX_COLL_CYTD_A_Diff < > 0 dsOR v_CAC_WP_CYTD_A_Diff < > 0 dsOR v_CAC_COMM_CYTD_A_Diff < > 0 dsOR v_CAC_EP_CYTD_A_Diff < > 0 dsOR v_CAC_UEP_RSRV_A_Diff < > 0 dsOR v_CAC_P_CURR_ADJ_A_Diff < > 0 dsOR v_CAC_TAX_COLL_CYTD_A_Diff < > 0 dsOR v_WP_CYTD_A_Diff < > 0 dsOR v_COMM_CYTD_A_Diff < > 0 dsOR v_EP_CYTD_A_Diff < > 0 dsOR v_UEP_RSRV_A_Diff < > 0 dsOR v_INF_PREM_A_Diff < > 0 dsOR v_P_CURR_ADJ_A_Diff < > 0 dsOR v_TAX_COLL_CYTD_A_Diff < > 0, 'Y', 'N'))";
		//String srcFnType = " dsIIf( firstrow= 1, dsIIf( curr_SourceMD5 dsErrorCheckHoliday!= curr_TargetMD5, 1, 0), dsIIf( curr_SourceMD5 dsErrorCheckHoliday!= prev_SourceMD5, 1, 0)) ";
		//String srcFnType = "dsIIf( F0911JU_VW.SRC_SYS_CD= 'JDEW - FOOD' dsAnd v_ADAGE_FLAG= 'Y', dsLtrim( dsRtrim( dsSubString( F0911JU_VW.GLEXR, dsSearch( dsUpper( F0911JU_VW.GLEXR), 'PO#', 1, 1)+ 4, dsSearch( dsUpper( F0911JU_VW.GLEXR), 'RCP#', 1, 1) - 6))))" ;
	    //String srcFnType = "dsIIf(dsCheckNull(F0413JU.SRC_SYS_CD), null, dsMD5(F0413JU.SRC_SYS_CD dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNPYID)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNRC5)dsConcat dsChar(9)dsConcat F0413JU.RPKCO dsConcat dsChar(9)dsConcat F0413JU.RPDCT dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPDOC)dsConcat dsChar(9)dsConcat F0413JU.RPSFX dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPSFXE)dsConcat dsChar(9)dsConcat F0413JU.RNDCTM dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNICU)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPAN8)dsConcat dsChar(9)dsConcat F0413JU.RPBCRC dsConcat dsChar(9)dsConcat F0413JU.RPCRCD dsConcat dsChar(9)dsConcat F0413JU.RPCO dsConcat dsChar(9)dsConcat F0413JU.RPVINV dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPU)dsConcat dsChar(9)dsConcat F0413JU.RPUM dsConcat dsChar(9)dsConcat F0413JU.RPPO dsConcat dsChar(9)dsConcat F0413JU.RPPTC dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPFAP)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPAAP)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNPAAP)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNPFAP)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPAG)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RMDMTJ)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNPN)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNFY)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNCTRY)dsConcat dsChar(9)dsConcat F0413JU.RNRMK dsConcat dsChar(9)dsConcat F0413JU.RPVOD dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RMVDGJ)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RPUPMJ)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RMUPMJ)dsConcat dsChar(9)dsConcat dsNumToStr(F0413JU.RNUPMJ)dsConcat dsChar(9)dsConcat F0413JU.REQN_CD dsConcat dsChar(9)dsConcat F0413JU.LOC_CD dsConcat dsChar(9)dsConcat F0413JU.RPPDCT))";
		
		//String srcFnType = "StringToDate(s)";
		//String srcFnType = "dsStrToDate(s)";
		
		//String srcFnType = "SELECT employee_last_name FROM employee_dimension WHERE ASCII(SUBSTR(employee_last_name, 1, 1)) = 76 LIMIT 5;";
		//String srcFnType = "1/2";
		String srcFnType="cast (col1 AS TIMESTAMP FORMAT 'YYYYMMDDHHMISS') as col1,CAST(M.col AS TIMESTAMP(6)),cast( '9999-12-31 23:59:59.999999' as timestamp)";
		//String srcFnType="(EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM C.col) (col_alias))";
		//String srcFnType="CASE WHEN (REGEXP_INSTR(TRIM(COL),'[A-Za-z]')  = 1  OR REGEXP_INSTR(TRIM(COL),'[0-9]')  = 1 OR REGEXP_INSTR(TRIM(COL),'[0-9,+]')  = 1 OR REGEXP_INSTR(TRIM(COL),'[0-9,-]')  = 1 OR REGEXP_INSTR(TRIM(COL),'[A-Za-z 0-9,]')  = 1 OR ASCII(TRIM(COL)) BETWEEN 65281 AND 65374) THEN 0 ELSE 1 END AS TABLE";
		

		ArrayList<Token> alltokens = Lexer.interpret(srcFnType);

		for (int i = 0; i < alltokens.size(); i++) {
			//System.out.println(alltokens.get(i));
		}

		int currentToolId = ToolTypeConstants.TERADATA;
		int newToolId = ToolTypeConstants.AZURESQL;

		//System.out.println("output ---"+getCurrentFunctions(30, 10,srcFnType));
		
	    //VisualMapperCheck(srcFnType);

		//System.out.println(getCurrentFunctions(srcFnType, currentToolId, newToolId)); 

	    
	    //getCurrentNestedFuns(srcFnType);
	    
	    //getExprNode(srcFnType);
	    
	    Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
       
	   /* ObjectSizer sizer = new ObjectSizer();
	    long size = sizer.getObjectSize(theClass);
	    System.out.println("Approximate size of " + theClass + " objeds :" + size);*/
	}

	
	public static String VisualMapperCheck (String srcFnType)
	{
		String rule ="";
		if(globalFnMap.isEmpty())
			globalFnMap = getGlobalFnRules();
		
		 HashMap<String,String> hMap =  getCurrentNestedFuns(srcFnType);
		 HashMap<String,String> hMap2 = null;
		
		int currentToolID = -1;
		int newToolId = ToolTypeConstants.DS_TOOL;
		 	if(currentToolID == -1)
		    {
		 		for (Entry<String, String> entry : hMap.entrySet())
				{
		 			 String temp = entry.getValue();
		 			 if(temp.contains("()"))
		 			 {
		 				//String val  = TokenNodes.getConvertedFunc(temp, currentToolID, newToolId);
		 				String val = globalFnMap.get(temp);
						temp = entry.setValue(entry.getValue().replace(temp ,val));
		 			 }
		 			 else
		 			 {
			 			 ArrayList<Token> tokens = Lexer.interpret(temp);
			 			 if((tokens.get(0).type.name().equalsIgnoreCase("VAR") || tokens.get(0).type.name().equalsIgnoreCase("LOGICALOP") || tokens.get(0).type.name().equalsIgnoreCase("VARWITHDOT") && !tokens.get(0).data.contains("nestedVar")))
		 				 {
			 				 String val = globalFnMap.get(tokens.get(0).data);
		 					 if(val==null)
		 					 {
		 						if((!tokens.get(0).data.contains("ds")) && (!tokens.get(0).data.contains("dsError_")))
		 						    tokens.get(0).data = "dsError_"+ tokens.get(0).data;
		 					 }
		 					 else
		 						tokens.get(0).data = val; 
		 				
		 				 }
			 			 
			 			 for(int i = 1 ; i < tokens.size();i++)
			 			 {
			 				
			 				 if(tokens.get(i).type.name().equalsIgnoreCase("LOGICALOP"))
			 				 {
			 					String val = globalFnMap.get(tokens.get(i).data);
			 					 if(val==null)
			 					 {
			 						if(!tokens.get(i).data.contains("ds") && !tokens.get(i).data.contains("dsError_"))
			 						  	tokens.get(i).data = "dsError_"+ tokens.get(i).data;
			 					 }
			 					 else
			 						tokens.get(i).data = val; 
			 				 }
			 				 else if(tokens.get(i).type.name().equalsIgnoreCase("VAR"))
			 				 {
			 					 boolean checkLogical = TokenNodes.checktokenHasLogicalExp(tokens.get(i).data);
			 					 if(checkLogical == true)
			 					 {
			 						 String val = globalFnMap.get(tokens.get(i).data);
				 					 if(val==null)
				 					 {
				 					   if(!tokens.get(i).data.contains("ds") && !tokens.get(i).data.contains("dsError_"))
				 						  tokens.get(i).data = "dsError_"+ tokens.get(i).data;
				 					 }
				 					 else
				 						tokens.get(i).data = val; 
			 					 }
			 				 }
			 			 }
			 			 
			 			 String updValue = getConvertedString(tokens).trim();
			 			 entry.setValue(updValue);
			 		}
				}
		 		hMap2  = TokenNodes.getUpdatedSyntaxForTools(hMap,currentToolID ,newToolId);
		 		
		    }
		 	String lastKey = "";
		 	for (Entry<String, String> entry : hMap2.entrySet())
			{
	 			 lastKey = entry.getKey();
			}
		 	
		 	rule = hMap2.get(lastKey);
		 	rule = getStructFuncs(rule);
		 	
		//System.out.println(rule);
		 	
		return rule;
		
	}

	
	public static String getUpdatedFuncs(int currentToolID ,int newToolId,String srcFnType) 
	{
		boolean isCurrentToolIDIsDb = getCheckCurrentToolIdIsDb(currentToolID);
		
		   if(currentToolID == ToolTypeConstants.DATASTAGE || 
				   currentToolID == ToolTypeConstants.SSIS || currentToolID == ToolTypeConstants.TALEND || ( isCurrentToolIDIsDb && newToolId == ToolTypeConstants.DS_TOOL))
		   {
			   srcFnType =  TokenNodes.getUpdatedTokensForIfElseCond(srcFnType, currentToolID, isCurrentToolIDIsDb);
			   srcFnType = addBracketForIf(srcFnType);
		   }
		   
		   HashMap<String,String> curntFuns = getCurrentNestedFuns(srcFnType);
		   
		   for (Entry<String, String> entry : curntFuns.entrySet())
			{
	 			 String key = entry.getKey();
	 			 String temp = entry.getValue();
	 			 
	 			if(isCurrentToolIDIsDb && newToolId == ToolTypeConstants.DS_TOOL){
	 				 if(temp.startsWith("If("))
	 				 {
	 					 if(temp.contains("?") || temp.contains("dsElse")){
	 						temp = temp.replace("?", ",").replace("dsElse",",");
	 					}
	 				 }
	 			}
	 			 
	 			 String x1;
	 			 boolean flag = false;
	 			 if(temp.contains("()"))
	 			 {
	 				 String x = temp.substring(0, temp.indexOf("()"));
	 				 if(x.length() > 0)
	 				 {
	 					 x1 = x.substring(x.length()-1);
	 					 if(x1.matches("^[a-zA-Z]*$"))
	 						flag = true;
	 				 }
	 			 }
	 			 
	 			 if(temp.contains("()") && flag)//Chking for a()
	 			 {
	 				String val  = TokenNodes.getConvertedFunc(temp, currentToolID, newToolId);
					temp = entry.setValue(entry.getValue().replace(temp ,val));
	 			 }
	 			 else
	 			 {
		 			 ArrayList<Token> tokens = Lexer.interpret(temp);
		 			 
		 			 if(tokens.get(0).type.name().equalsIgnoreCase("VAR") || tokens.get(0).type.name().equalsIgnoreCase("LOGICALOP") || tokens.get(0).type.name().equalsIgnoreCase("VARWITHDOT") && !tokens.get(0).data.contains("nestedVar"))
					 {
	 					tokens.get(0).data = TokenNodes.getConvertedFunc(tokens.get(0).data, currentToolID, newToolId); 
	 					
					 }
		 			 for(int i = 1 ; i < tokens.size();i++)
		 			 {
		 				 if(tokens.get(i).type.name().equalsIgnoreCase("LOGICALOP"))
		 				 {
		 					tokens.get(i).data = TokenNodes.getConvertedFunc(tokens.get(i).data, currentToolID, newToolId);
		 					
		 				 }
		 				 else if(tokens.get(i).type.name().equalsIgnoreCase("VAR"))
		 				 {
		 					 boolean checkLogical = TokenNodes.checktokenHasLogicalExp(tokens.get(i).data);
		 					 if(checkLogical == true)
		 					 {
		 						tokens.get(i).data = TokenNodes.getConvertedFunc(tokens.get(i).data, currentToolID, newToolId);
		 					 }
		 				 }
		 			 }
		 			 
		 			 String convString = getConvertedString(tokens).trim();
		 			 temp =  entry.setValue(convString);
	 			 }
	 		}
		TokenNodes.getUpdatedSyntaxForTools(curntFuns,currentToolID,newToolId);
	
		if(curntFuns.entrySet() != null)
		{
			if(curntFuns.entrySet().toArray()[curntFuns.size()-1] != null)
			{
				String nestedFn = curntFuns.entrySet().toArray()[curntFuns.size()-1].toString();
				
				nestedFn = nestedFn.substring(nestedFn.indexOf("=")+1);
				return nestedFn;
			}
		}
		
		return null;
	}
	
	private static boolean getCheckCurrentToolIdIsDb(int currentToolID) {
		if(currentToolID == ToolTypeConstants.TERADATA || 
				currentToolID == ToolTypeConstants.ORACLE || currentToolID == ToolTypeConstants.VERTICA 
				|| currentToolID == ToolTypeConstants.NETEZZA)
			return true;
		
		return false;
	}


	public static  String getConvertedString(ArrayList<Token> tokens) 
	{
		String value = "";
		for (int i = 0; i < tokens.size(); i++) 
		{
			value += " " + tokens.get(i).data;
		}
		return value;
		
	}
	
	public static String addBracketForIf(String srcFnType) {
		
		String exp = srcFnType;
		int countOpenBrac = 0;
		
		if(srcFnType.contains("if ") || srcFnType.contains("If ") || srcFnType.contains("IF ")){
			
			while(true){
				
				if(srcFnType.contains("if ")){
					int index = srcFnType.indexOf("if ");
					srcFnType = srcFnType.substring(index+1);
					countOpenBrac++;
				}
				else if(srcFnType.contains("If ")){
					int index = srcFnType.indexOf("If ");
					srcFnType = srcFnType.substring(index+1);
					countOpenBrac++;
				}
				else if(srcFnType.contains("IF ")){
					int index = srcFnType.indexOf("IF ");
					srcFnType = srcFnType.substring(index+1);
					countOpenBrac++;
				}
				else{
					break;
				}
				
			}
			
			exp = exp.replace("if ", "if ( ").replace("If ","If ( ").replace("IF ", "IF ( ");
			
			if(exp.contains("dsElse ")){
				String[] s = exp.split(" ");
				List<String> list = Arrays.asList(s);
				
				int remOpnBrktCount = countOpenBrac;
				
				for(int i=0;i<list.size();i++){
					String temp = list.get(i);
					if(temp.equalsIgnoreCase("dsElse")){
						remOpnBrktCount = checkNextElem(list,i,remOpnBrktCount);
					}
				}
				
				exp = "";
				
				for(int i=0;i<list.size();i++){
					exp += list.get(i)+" ";
				}
				
				for(int k=0;k<remOpnBrktCount;k++){
					exp = exp+")";
				}
			}
			else{
				for(int i=0;i<countOpenBrac;i++){
					exp = exp+")";
				}
			}
			
		}
		
		return exp;
	}

	public static int checkNextElem(List<String> list,int i, int countOpenBrac){
		
		for(int j=i+1;j<list.size();j++){
			if(list.get(j).equalsIgnoreCase("if") || list.get(j).equalsIgnoreCase("then") || list.get(j).equalsIgnoreCase("?")){
			
				break;
			}
			else if(list.get(j).equalsIgnoreCase("dsElse")){
				
				list.set(j, " ) "+list.get(j));
				countOpenBrac = countOpenBrac - 1;
				break;
			}
		}
		
		return countOpenBrac;
	}

	public static HashMap<String,String> getCurrentNestedFuns(String currentFnType) 
	{
		 currentFnType = "("+currentFnType+")";
		 ArrayList<Token> alltokens = Lexer.interpret(currentFnType);
		 HashMap<String,String> str = TokenNodes.createNodeForNestedFuns(alltokens);
		 str = TokenNodes.getUpdatedNodes(str);
		return str;
	}
	
	public static ArrayList<ExprNode> getExprNode(String srcFnType) 
	{
		 HashMap<String,String> nodes=  getCurrentNestedFuns(srcFnType);
		 ArrayList<ExprNode> exprNodes = new  ArrayList<ExprNode>();
		 for (Entry<String, String> entry : nodes.entrySet())
		 {
			 ExprNode exprNode = new ExprNode();
			 if(!entry.getValue().startsWith("("))
			 {
				 exprNode.type = '3';
			 }
			 else
			 {
				 boolean checkOps = chekValHasBinayOps(entry.getValue()) ;
				 
				 if(checkOps == true)
					 exprNode.type = '2';
				 else
					 exprNode.type = '1';
				
			 }
			 
			 exprNode.value = entry.getValue();
		
			 if(exprNode.type == '2' || exprNode.type == '3' || entry.getValue().contains("nestedVar_"))
			 {
				 getUpdatedArgs(exprNode,entry.getValue(),nodes);
			 }
			 exprNodes.add(exprNode);
		 }		
		 
		return exprNodes;
		
	}

	
	private static void getUpdatedArgs(ExprNode exprNode, String entry, HashMap<String, String> nodes) 
	{
		exprNode.args = new LinkedHashMap<String,ExprNode>();
		ArrayList<Token> alltokens = Lexer.interpret(entry);
		for(int i = 0 ;i < alltokens.size() ;i++)
		{
			if(alltokens.get(i).data.contains("nestedVar_"))
			{
				ExprNode exprNd = new ExprNode();
				String val = nodes.get(alltokens.get(i).data);
				 if(!val.startsWith("("))
				 {
					 exprNd.type = '3';
				 }
				 else
				 {
					 boolean checkOps = chekValHasBinayOps(val) ;
					 
					 if(checkOps == true)
						 exprNd.type = '2';
					 else
						 exprNd.type = '1';
				 }
				 exprNd.value = val;
				 exprNode.args.put(alltokens.get(i).data, exprNd);
			}
		}
		
	}


	private static boolean chekValHasBinayOps(String entry) 
	{
		boolean checkOps = false;
		ArrayList<Token> alltokens = Lexer.interpret(entry);
		 for(int i = 0 ; i < alltokens.size();i++)
		 {
			 Token tokens = alltokens.get(i);
			 
			 if(tokens.type.name().equalsIgnoreCase("LOGICALOP") || tokens.type.name().equalsIgnoreCase("BINARYOP"))
			 {
				 checkOps = true;
			 }
		 }
		return checkOps;
	}


	public static HashMap<String,String> getGlobalFnRules() 
	{
		HashMap<String,String> globalFnMap = new HashMap<String,String>();
		
		HashMap<String,String> dsRulesMap = new HashMap<String,String>();
		FnMapRules dsRules = new FnMapRules();
		List<FnRule> fnRuleList= dsRules.getDSToolRules();
		
		for(int i=0;i<fnRuleList.size();i++){
			FnRule rule = fnRuleList.get(i);
			dsRulesMap.put(rule.getGenFnType().toString(), rule.getToolFnType());
		}
		
		globalFnMap = getGlobalMap(globalFnMap,dsRulesMap); 
		
		return globalFnMap;	
	}
	
	public static HashMap<String,String> getGlobalMap(HashMap<String,String> globalFnMap, HashMap<String, String> dsRulesMap) 
	{
		List<List<FnRule>> fnMapRules = new ArrayList<List<FnRule>>();
		fnMapRules.add(FnMapRules.getDSToolRules());
		fnMapRules.add(FnMapRules.getOracleRules());
		fnMapRules.add(FnMapRules.getDB2Rules());	
		fnMapRules.add(FnMapRules.getTeradataRules());  
		fnMapRules.add(FnMapRules.getNetezzaRules());  
		fnMapRules.add(FnMapRules.getSqlServerRules());  
		fnMapRules.add(FnMapRules.getSybaseRules()); 
		fnMapRules.add(FnMapRules.getRedshiftRules());
		fnMapRules.add(FnMapRules.getBigqueryRules());  
		fnMapRules.add(FnMapRules.getAzuresqlRules());  
		fnMapRules.add(FnMapRules.getSnowflakeRules());  
		fnMapRules.add(FnMapRules.getAuroraRules());  
		fnMapRules.add(FnMapRules.getDatastageRules());  
		fnMapRules.add(FnMapRules.getSsisRules()); 
		fnMapRules.add(FnMapRules.getInformaticaBDMRules());
		fnMapRules.add(FnMapRules.getInformaticaPCRules());
		fnMapRules.add(FnMapRules.getApacheBeamRules());
		fnMapRules.add(FnMapRules.getJavaRules());
		fnMapRules.add(FnMapRules.getOdbcRules());
		fnMapRules.add(FnMapRules.getPySparkRules());
		fnMapRules.add(FnMapRules.getTalendRules());
		fnMapRules.add(FnMapRules.getAzureDataFactoryRules());
        fnMapRules.add(FnMapRules.getAzureDataFactoryDataFlowRules());
        fnMapRules.add(FnMapRules.getMongoRules());
        fnMapRules.add(FnMapRules.getApacheBeamPythonRules());
        fnMapRules.add(FnMapRules.getSparkScalaRules());
		
        for(int i=0;i<fnMapRules.size();i++){
        	List<FnRule> fnRuleList = fnMapRules.get(i);
        	for(int j=0;j<fnRuleList.size();j++)
        	{
        		FnRule rule = fnRuleList.get(j);
        		String key = rule.getToolFnType();
        		if(!globalFnMap.containsKey(key))
        		{
        			String value = dsRulesMap.get(rule.getGenFnType());
        			if(key.contains("ds")){
        				key = key.substring(key.indexOf("ds")+3);
        			}
        			globalFnMap.put(key, value);
        		}
        	}
        }
        
        
        return globalFnMap;
	}



	public static String getCurrentFunctions(String currentFnType, int currentToolID ,int newToolId) 
	{
 		   String opRule = getUpdatedFuncs(currentToolID ,newToolId,currentFnType);
		   opRule = getStructFuncs(opRule);
		   
//		    System.out.println(opRule);
		    
		return opRule;
	}

	
	public static String getStructFuncs(String opRule) 
	{
		
		opRule = opRule.replace("( ", "(").replace(") ", ")").replace(" (", "(").replace(" )", ")")
				.replace("[ ", "[").replace("] ", "]").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
				.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").replace(" ;", ";").replace("& gt", "&gt").replace("& lt", "&lt").replace(" / ", "/").trim(); 
		if(opRule.startsWith("("))
		{
			opRule = opRule.substring(1, opRule.length()-1);
		}
		 
		return opRule;
	}


	public static ArrayList<Token> getTokens(String Exp)
	{
		ArrayList<Token> alltokens = Lexer.interpret(Exp);
		return alltokens;
	}

	public static void getTargetData(int srctoolType, int tgttooltype)
	{

		HashMap<String, String> srcTgt = new HashMap<>();

		FnConverter dtc= new FnConverter();	
		Map<String,FnRule> srcRuleMap = dtc.getSourceFnTypeRule(srctoolType);
		Map<String,FnRule> tgtRuleMap = dtc.getTargetFnTypeRule(tgttooltype);
		System.out.println();
		System.out.format("%25s%25s", "CURRENT_FUNCTIONTYPE","NEW_FUNCTIONTYPE");
		System.out.println();



		for (Map.Entry<String, FnRule> entry1 : srcRuleMap.entrySet()) {
			String tgtDataTyp="";
			FnRule source_dtr = entry1.getValue();
			String sdt = entry1.getKey();
			String gdt = source_dtr.getGenFnType().toString();
			String rdt = source_dtr.getApplicability();	

			if(rdt == "NULL")
			{			
				for (Map.Entry<String, FnRule> entry2 : tgtRuleMap.entrySet()) {
					FnRule target_dtr = entry2.getValue();
					if(target_dtr.getGenFnType().toString() == gdt){ 
						tgtDataTyp = target_dtr.getToolFnType();
						break;
					}	
				}
			}
			else{
				for (Map.Entry<String, FnRule> entry3 : tgtRuleMap.entrySet()) {
					FnRule target_dtr = entry3.getValue();
					//System.out.println(target_dtr);
					//System.out.println(target_dtr.getGenDataType());
					if(target_dtr.getGenFnType().toString() == rdt) {
						tgtDataTyp = target_dtr.getToolFnType();
						//System.out.println(tgtDataTyp);
						break;
					}	
				}	
			}	

			if(tgtDataTyp=="")
				tgtDataTyp="NA";
			System.out.println();
			System.out.format("%25s%25s", sdt,tgtDataTyp);

		}
	}

}