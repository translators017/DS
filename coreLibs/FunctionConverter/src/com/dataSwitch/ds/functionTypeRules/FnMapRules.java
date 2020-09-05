
package com.dataSwitch.ds.functionTypeRules;
import java.util.ArrayList;
import java.util.List;


public class FnMapRules {

	public enum dsFuunctionType{

		AGG_AVERAGE,
		AGG_AVERAGEDISTINCT,
		AGG_COUNT,
		AGG_COUNTDISTINCT,
		AGG_FIRST,
		AGG_GROUPBY,
		AGG_LAST,
		AGG_MAX,
		AGG_MAXDISTINCT,
		AGG_MIN,
		AGG_MINDISTINCT,
		AGG_SUM,
		AGG_SUMDISTINCT,
		AGG_CUMEDIST,
		AGG_DENSERANK,
		AGG_DISTINCT,
		AGG_GREATEST,
		AGG_GREATEST_DEPRECATED,
		AGG_LAG,
		AGG_LEAD,
		AGG_LEAST,
		AGG_LEAST_DEPRECATED,
		AGG_MEDIAN,
		AGG_PERCENTILE,
		AGG_PERCENTILE_RANK,
		AGG_RANK,
		AGG_RANK_DEPRECATED,
		AGG_STDDEV,
		AGG_STDDEVPOPULATION,
		AGG_STDDEVSAMPLE,
		AGG_VARIANCE,
		AGG_VARIANCEDISTINCT,
		AGG_VARIANCE_DEPRECATED,
		AGG_VARIANCEPOPULATION,
		AGG_PERCENTILE_DISC,
		AGG_CORRELATION,
		AUD_DATE,
		AUD_DATE_DEPRECATED,
		AUD_CURR_DATE,
		AUD_CURR_TIME,
		AUD_TIME,
		AUD_TIMESTAMP,
		AUD_TIMESTAMP_DEPRECATED,
		AUD_CURRENTTIMESTAMP,
		AUD_UTCDATE,
		AUD_DATETIME,
		AUD_CURRENTTIME,
		AUD_CURRENTDATE,
		AUD_LOCALTIME,
		AUD_LOCALTIMESTAMP,
		AUD_DBTZ,
		ADD_SECONDSTOTIMESTAMP,
		AUD_SESSTIMEZONE,
		AUD_SYSDATETIMEOFFSET ,
		BOOL_OR,
		BOOL_AND,
		BOOL_XOR,
		CHAR_REPLACE,
		CHECK_DATE,
		CHECK_NUM,
		CHECK_IF,
		CHECK_IN,
		CHECK_ANY,
		CHECK_BETWEEN,
		CHECK_EXISTS,
		CHECK_NOT_NULL,
		CHECK_NULL,
		CHECK_NULL_DEPRECATED,
		CHECK_NULLIF,
		CHECK_ISBLANK,
		CHECK_REGMATCH,
		CHECK_ARRAY,
		CHECK_VARCHAR,
		CONV_ASCIITONUM,
		CONV_DATETOSTR,
		CONV_DATETOINT,
		CONV_NUMTOSTR,
		CONV_NUMTOSTR_DEPRECATED,
		CONV_NUMTODEC,
		CONV_NUMTOFLOAT,
		CONV_NUMTOINTEGER,
		CONV_NUMTODOUBLE,
		CONV_DECTOFLOAT,
		CONV_DECTOSTRING,
		CONV_FLOATTODEC,
		CONV_STRTODATE,
		CONV_STRTODEC,
		CONV_STRTOFLOAT,
		CONV_STRTOINT,
		CONV_STRTOBIGINT,
		CONV_STRTOBOOLEAN,
		CONV_STRTODOUBLE,
		CONV_STRTOLONG,
		CONV_STRTOTIME,
		CONV_STRTOTIMESTAMP,
		CONV_TODATE,
		CONV_TODATE_DEPRECATED,
		CONV_TOBIGINT,
		CONV_NULLTOZERO,
		CONV_NULLTOVALUE,
		CONV_RADTODEGREES,
		CONV_TIMESTAMPTOSTRING,
		CONV_TIMETOSTRING,
		CONV_CONVERT,
		CONV_CAST,
		CONV_USTRINGTOSTRING,
		CONV_INTERVALTOSTRING,
		CONV_UTCTOTIMESTAMP,
		CONV_TIMESTAMPTOTIMET,
		CONV_ZEROIFNULL,
		DT_ADDTODATE,
		DT_ADDDAYS,
		DT_ADDMONTHSTODATE,
		DT_CEIL,
		DT_DIFF,
		DT_TIMESTAMPDIFF,
		DT_FLOOR,
		DT_FORMATDATE,
		DT_FORMATDATEINUTC,
		DT_GET_DATE,
		DT_GET_FIRSTDAYOFMONTH,
		DT_GET_LASTDAYOFMONTH,
		DT_GET_HOURS_FROM_TIME,
		DT_GET_MICRO_SEC_FROM_TIME,
		DT_GET_MIDNIGHT_SECONDS,
		DT_GET_MINS_FROM_TIME,
		DT_GET_MONTH_FROM_DATE,
		DT_GET_DAY_FROM_DATE,
		DT_GET_DAY_FROM_DATE_DEPRECATED,
		DT_GET_MONTHS_BETWEEN,
		DT_GET_MONTHS_BETWEEN_DEPRECATED,
		DT_GET_YEARS_BETWEEN,
		DT_GET_NEXT_DAY_FROM_DATE,
		DT_GET_TIMEZONE_FROM_DATETIME,
		DT_GET_QUARTER,
		DT_GET_SECS_FROM_TIME,
		DT_GET_WEEKDAY_FROM_DATE,
		DT_GET_WEEKDAY_FROM_DATE_DEPRECATED,
		DT_GET_YEARDAY_FROM_DATE,
		DT_GET_YEAR_FROM_DATE,
		DT_GET_YEARWEEK_FROM_DATE,
		DT_LAST_DAY,
		DT_NEW_TIME,
		DT_SET_DATE_PART,
		DT_SUBDAYS,
		DT_ROUND,
		DT_TIMESTAMP_ROUND,
		DT_DATE_TRUNC,
		DT_TRUNC,
		DT_TRUNC_DEPRECATED,
		DT_EXTRACT,
		DT_WIDTH_BUCKET,
		DT_OVERLAPS,
		DT_TIMEOFDAY,
		DT_TIME_SLICE,
		ENCYP_MD5,
		ENC_SOUNDEX,
		FRM_TIME_ZONE,
		LOGICAL_OR,
		LOGICAL_OR_DEPRECATED,
		LOGICAL_AND,
		LOGICAL_AND_DEPRECATED,
		LOGICAL_NOT,
		LOGICAL_NOT_DEPRECATED,
		LOGICAL_XOR,
		NUM_ABSOLUTE,
		NUM_CEIL,
		NUM_CEIL_DEPRECATED,
		NUM_CUME,
		NUM_FLOOR,
		NUM_MOVINGAVG,
		NUM_MOVINGSUM,
		NUM_ROUND,
		NUM_TRUNC,
		NUM_CBRT,
		NUM_COVARIANCEPOPULATION,
		NUM_COVARIANCESAMPLE,
		NUM_DIV,
		NUM_EXPONENT,
		NUM_FACTORIAL,
		NUM_GREATER,
		NUM_GREATEROREQUAL,
		NUM_LESSER,
		NUM_LESSEROREQUAL,
		NUM_LN,
		NUM_LOG,
		NUM_LOG10_DEPRECATED,
		NUM_MOD,
		NUM_MULTIPLY,
		NUM_PERCENTILE,
		NUM_PMOD,
		NUM_RANDOM,
		RANDOMINT_DEPRECATED,
		RANDOMINT_CRYPTO_DEPRECATED,
		NUM_REMAINDER,
		NUM_SIGN,
		NUM_SQUARE,
		NUM_SQRT,
		NUM_PI,
		MATH_MOD,
		MATH_POWER,
		SCI_ACOS,
		SCI_ASIN,
		SCI_ATAN,
		SCI_ATAN2,
		SCI_COS,
		SCI_COSH,
		SCI_COT,
		SCI_DEGREES,
		SCI_KURTOSIS,
		SCI_RADIANS,
		SCI_SIN,
		SCI_SINH,
		SCI_TAN,
		SCI_TANH,
		SCI_DISTANCE,
		SCI_DISTANCE_V,
		SP_COALESCE,
		SP_ENCODE,
		SP_DECODE,
		SP_LOOKUP,
		SP_FORMAT,
		SP_FALSE,
		SP_TRUE,
		SP_NTILE,
		SP_ROWNUM,
		SP_ROWNUM_DEPRECATED,
		SP_OVER,
		GEN_CONST,
		GEN_EXP,
		GEN_SURROGATEKEY,
		STR_CHAR,
		STR_CHAR_DEPRECATED,
		STR_STRING,
		STR_CHRCODE,
		STR_FORMAT,
		STR_CONCAT,
		STR_CONCAT_DEPRECATED,
		STR_CONCAT_PIPE,
		STR_CONCAT_PIPE_DEPRECATED,
		STR_COLON,
		STR_INITCAP,
		STR_INITCAP_DEPRECATED,
		STR_LEFT,
		STR_LEFTPAD,
		STR_LENGTH,
		STR_LENGTH_DEPRECATED,
		STR_BITCOUNT_DEPRECATED,
		STR_OCTET_LENGTH_DEPRECATED,
		STR_CHARLENGTH,
		STR_CHARLENGTH_DEPRECATED,
		STR_INDEX,
		STR_LOWER,
		STR_LOWER_DEPRECATED,
		STR_LTRIM,
		STR_REPLACE,
		STR_REPLACE_DEPRECATED,
		STR_REPLACEB_DEPRECATED,
		STR_REPLACE_CHR,
		STR_RIGHTPAD,
		STR_RTRIM,
		STR_SEARCH,
		STR_SEARCH_DEPRECATED,
		STR_SUBSTR,
		STR_SUBSTR_DEPRECATED,
		STR_SUBSTRB_DEPRECATED,
		STR_RIGHT_SUBSTR,
		STR_LEFT_SUBSTR,
		STR_LEFT_SUBSTR_DEPRECATED,
		STR_TRIM,
		STR_TRIM_DEPRECATED,
		STR_BTRIM_DEPRECATED,
		STR_UPPER,
		STR_UPPER_DEPRECATED,
		STR_ASCII,
		STR_ASCII_DEPRECATED,
		STR_CHANGE,
		STR_COMPARE,
		STR_COMPOSE,
		STR_CONCATWITHSEP,
		STR_CONVERT,
		STR_CONVERSION,
		STR_CONVERTDENCODE,
		STR_CONVERTCHRSET,
		STR_DECOMPOSE,
		STR_DUMP,
		STR_EQUALS,
		STR_FIELD,
		STR_HEX,
		STR_LENGTH_BYTE,
		STR_LENGTH_UCS2,
		STR_LENGTH_UCS4,
		STR_LENGTH_UNICODE,
		STR_LIKE,
		STR_NCHR,
		STR_NOTEQUALS,
		STR_NOTEQUALS_DEPRECATED,
		STR_REGEXPLIKE,
		STR_REGEXMATCH_DEPRECATED,
		STR_REGEXMATCH,
		STR_REGEXCOUNT,
		STR_REGEXEXTRACT,
		STR_REGEXREPLACE,
		STR_REGEXSPLIT,
		STR_REGEXSPLIT_DEPRECATED,
		STR_REGEXSUBSTR,
		STR_REPLICATE,
		STR_REVERSE,
		STR_POSITION,
		STR_POSITION_DEPRECATED,
		STR_POSITIONB_DEPRECATED,
		STR_SEARCH_BYTE,
		STR_SEARCH_UCS2,
		STR_SEARCH_UCS4,
		STR_SEARCH_UNICODE,
		STR_SPACE,
		STR_SPLIT,
		STR_SPLIT_DEPRECATED,
		STR_STARTSWITH,
		STR_VSIZE,
		SET_NULL,
		VAR_SETMAXVARIABLE,
		VAR_SETVARIABLE,
		CASE_END,
		CASE_WHEN,
		CASE_THEN,
		CASE_ELSE,
		CASE_CASE,
		CHECK_SPACES,
		SCI__DISTANCE,
		SCI__DISTANCE_V,
		SYS_CONNECTPATH,
		NUM_HASH,
		CONV_ZEROTONULL,
		CONV_NULLTOVALUE2,
		CHECK_ISUTF8,
		STR_COLLATION,
		STR_INET_ATON,
		STR_INET_NTOA,
		STR_INSERT,
		SYSVAR_FOLDERNAME,
		SYSVAR_INTEGRATIONSERVICENAME,
		SYSVAR_MAPPINGNAME,
		SYSVAR_REPOSITORYSERVICENAME,
		SYSVAR_REPOSITORYUSERNAME,
		SYSVAR_SESSIONNAME,
		SYSVAR_SESSIONRUNMODE,
		SYSVAR_WORKFLOWNAME,
		SYSVAR_WORKFLOWRUNID,
		SYSVAR_WORKFLOWRUNINSTANCENAME,
		SYSVAR_SESSSTARTTIME,
		SYSVAR_SESSSTARTTIME_DEPRECATED,
		SYSVAR_WORKFLOWSTARTTIME,
		NULL

	}

	private static ArrayList<FnRule> dsFnTypeRules = null;
	private static ArrayList<FnRule> oraFnTypeRules = null;
	private static ArrayList<FnRule> db2FnTypeRules = null;
	private static ArrayList<FnRule> teraFnTypeRules = null;
	private static ArrayList<FnRule> netezzaTypeRules = null;  
	private static ArrayList<FnRule> sqlserverTypeRules = null;
	private static ArrayList<FnRule> sybaseFnTypeRules = null;
	private static ArrayList<FnRule> azureDataFactoryDataTypRules = null;
	private static ArrayList<FnRule> azureDataFactoryDataFlowRules = null;
	private static ArrayList<FnRule> hadoopHdfsDataTypRules = null;
	private static ArrayList<FnRule> redshiftFnTypeRules = null;
	private static ArrayList<FnRule> bigqueryFnTypeRules = null;
	private static ArrayList<FnRule> sqlDwFnTypeRules = null;
	private static ArrayList<FnRule> snowflakeFnTypeRules = null;
	private static ArrayList<FnRule> auroraFnTypeRules = null;
	private static ArrayList<FnRule> datastageFnTypeRules = null;
	private static ArrayList<FnRule> ssisFnTypeRules = null;
	private static ArrayList<FnRule> informaticaBDMFnTypeRules = null;
	private static ArrayList<FnRule> informaticaPCFnTypeRules = null;
	private static ArrayList<FnRule> apacheBeamFnTypeRules = null;
	private static ArrayList<FnRule> javaFnTypeRules = null;
	private static ArrayList<FnRule> odbcFnTypeRules = null;
	private static ArrayList<FnRule> pySparkRules = null;
	private static ArrayList<FnRule> talendFnTypeRules = null;
	private static ArrayList<FnRule> mongoFnTypeRules = null;
	private static ArrayList<FnRule> apacheBeamPythonFnTypRules = null;
	private static ArrayList<FnRule> greenPlum = null;
	private static ArrayList<FnRule> sparkScalaRules = null;
	private static ArrayList<FnRule> mysqlRules = null;
	private static ArrayList<FnRule> postgreSQLTypeRules = null;
	private static ArrayList<FnRule> impalaRules = null;
	private static ArrayList<FnRule> hiveRules = null;
	private static ArrayList<FnRule> verticaRules = null;
	private static ArrayList<FnRule> pythonRules = null;
	private static ArrayList<FnRule> pandasSqlRules = null;

	public static List<FnRule> getDSToolRules()
	{
		if(dsFnTypeRules!=null)
			return dsFnTypeRules;

		dsFnTypeRules = new ArrayList<FnRule>();


		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"dsAvg",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"dsCount",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"dsCountDistinct",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"dsFirstValue",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"dsGroupBy",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"dsLast",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"dsMax",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"dsMin",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"dsSum",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"dsCumeDist",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"dsDenseRank",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DISTINCT,"dsDistinct",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"dsGreatest",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"dsLag",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"dsLead",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"dsLeast",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"dsMedian",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"dsPercentile",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE_RANK,"dsPercentileRank",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"dsRank",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"dsStdDev",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"dsStdDevPopulation",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"dsStdDevSample",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"dsVariance",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"dsVariancePopulation",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE_DISC,"dsPercentileDisc",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CORRELATION,"dsCorrelation",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"dsDate()",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"dsTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"dsTimeStamp()",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"dsCurrentTimestamp",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"dsUTCDate()",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"dsDateTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"dsCurrentTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"dsCurrentDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"dsLocalTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"dsLocalTimeStamp",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"dsDBTimeZone",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.ADD_SECONDSTOTIMESTAMP,"dsAddSecToTs",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"dsSessionTimeZone",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"dsSysDateTimeOffSet",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.BOOL_OR,"dsBoolOR",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.BOOL_AND,"dsBoolAnd",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.BOOL_XOR,"dsBoolXor",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHAR_REPLACE,"dsCharReplace",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"dsCheckDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"dsCheckNum",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"dsIIf",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"dsIn",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ANY,"dsAny",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_BETWEEN,"BETWEEN",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"dsExists",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"dsCheckNotNull",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"dsCheckNull",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"dsCheckNullIf",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ISBLANK,"dsIsBlank",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"dsRegMatch",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ARRAY,"dsArray",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ASCIITONUM,"dsAsciiToNum",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"dsDateToStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"dsDateToInt",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"dsNumToStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"dsNumToDec",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"dsNumToFloat",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"dsNumToInt",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"dsNumToDouble",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"dsDecToFloat",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"dsDecToStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"dsFloatToDec",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"dsStrToDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"dsStrToDec",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"dsStrToFloat",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"dsStrToInt",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"dsStrToBigInt",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"dsStrToBoolean",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"dsStrToDouble",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"dsStrToLong",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"dsStrToTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"dsStrToTimeStamp",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"dsTsToDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"dsTobigint",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"dsNullToZero",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"dsNullToValue",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"dsRadToDegrees",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"dsTimestampToString",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"dsTimeToString",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"dsConvert",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"dsCast",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"dsUStrToStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"dsIntervalToStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"dsUTCToTimestamp",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOTIMET,"dsTimestampToTimet",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ZEROIFNULL,"dsZeroIfNull",dsFuunctionType.NULL,"B","N")); 
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"dsAddToDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"dsAddDays",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"dsAddMonthsToDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"dsDateCeil",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"dsDiff",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_TIMESTAMPDIFF,"dsTimestampDiff",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"dsDateFloor",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"dsFormatDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"dsFormatDateInUTC",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"dsGetDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"dsGetFirstDayOfMonth",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"dsGetLastDayOfMonth",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"dsGetHoursFromTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"dsGetMicroSecFromTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MIDNIGHT_SECONDS,"dsGetMidnightSec",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"dsGetMinsFromTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"dsGetMonthFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DAY_FROM_DATE,"dsGetDayFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"dsGetMonthsBetween",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARS_BETWEEN,"dsGetYearsBetween",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"dsGetNextDayFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_TIMEZONE_FROM_DATETIME,"dsGetTimezoneFromDateTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"dsGetQuarter",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"dsGetSecsFromTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"dsGetWeekdayFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"dsGetYeardayFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"dsGetYearFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARWEEK_FROM_DATE,"dsGetYearWeekFromDate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"dsLastDay",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_NEW_TIME,"dsNewTime",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_SET_DATE_PART,"dsSetDatePart",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_SUBDAYS,"dsSubdays",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"dsRound",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_TIMESTAMP_ROUND,"dsTimestampRound",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_DATE_TRUNC,"dsDateTrunc",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"dsTrunc",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"dsExtract",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"dsWidthBucket",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_OVERLAPS,"dsOverlaps",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_TIMEOFDAY,"dsTimeOfDay",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.DT_TIME_SLICE,"dsTimeSlice",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"dsMD5",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"dsSoundEx",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.FRM_TIME_ZONE,"dsFrmTimeZone",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"dsOR",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"dsAnd",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"dsNot",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"dsXor",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"dsAbsolute",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"dsNumCeil",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"dsNumCume",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"dsNumFloor",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"dsMovningAvg",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"dsNumMovingSum",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"dsNumRound",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"dsNumTrunc",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"dsNumCbrt",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"dsNumCoVariancePopulation",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"dsNumCoVarianceSample",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"dsNumDiv",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"dsNumExponent",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"dsNumFactorial",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"dsNumGreater",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,"dsNumGreaterOrEqual",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"dsNumLesser",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"dsNumLesserOrEqual",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"dsNumLn",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"dsNumLog",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"dsNumMultiply",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"dsNumPercentile",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"dsNumPMod",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"dsNumRandom",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"dsNumRemainder",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"dsNumSign",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"dsNumSquare",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"dsNumSqrt",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"dsNumPi",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"dsMod",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"dsPower",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"dsSciACos",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"dsSciASin",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"dsSciATan",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"dsSciATan2",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"dsSciCos",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"dsSciCosh",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"dsSciCot",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"dsSciDegrees",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"dsSciKurToSis",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"dsSciRadians",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"dsSciSin",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"dsSciSinh",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"dsSciTan",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"dsSciTanh",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"dsCoalesce",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"dsEncode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"dsDecode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"dsLookup",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"dsFormat",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"dsFalse",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"dsTrue",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"dsNTile",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"dsRowNum",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"dsOver",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"dsConstant",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"dsExpression",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.GEN_SURROGATEKEY,"dsSurrogateKey",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"dsChar",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_STRING,"dsStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"dsCharCode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_FORMAT,"dsFormat",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"dsConcat",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"dsPipe",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_COLON,"dsColon",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"dsInitCap",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"dsLeftPad",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"dsLength",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHARLENGTH,"dsCharLength",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_INDEX,"dsIndex",dsFuunctionType.NULL,"B","N")); 
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"dsLower",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"dsLTRIM",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"dsReplace",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"dsRightPad",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"dsRTRIM",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"dsSearch",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"dsSubString",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"dsRightSubString",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"dsLeftSubString",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"dsTrim",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"dsUpper",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"dsAscii",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHANGE,"dsChange",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"dsCompare",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"dsCompose",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"dsConcatWithSep",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"dsStrConvert",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"dsStrConversion",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"dsStrConvertDenCode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"dsStrConvertChrSet",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"dsDeCompose",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"dsDump",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"dsEquals",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"dsField",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"dsHex",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"dsLengthByte",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"dsLengthUCS2",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"dsLengthUCS4",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"dsLengthUniCode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_LIKE,"dsLike",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"dsLengthUniCode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"dsStrNotEquals",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXPLIKE,"dsRegexLike",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXMATCH,"dsRegexMatch",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"dsStrRegexCount",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"dsStrRegexExtract",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"dsStrRegexReplace",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"dsStrRegexSplit",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"dsStrRegexSubStr",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"dsStrReplicate",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"dsStrReverse",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"dsStrPosition",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"dsStrSearchByte",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"dsStrSearchUCS2",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"dsStrSearchUCS4",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"dsStrSearchUnicode",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"dsStrSpace",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"dsStrSplit",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"dsStrStartsWith",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"dsStrVsize",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"dsSetNull()",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"dsSetMaxVariable",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"dsSetVariable",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"dsEnd",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"dsWhen",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"dsIsSpace",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI__DISTANCE,"dsSciDistance",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DISTANCE_V,"dsSciDistanceV",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYS_CONNECTPATH,"SYS_CONNECT_BY_PATH",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.NUM_HASH,"dsNumHash",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ZEROTONULL,"dsZeroToNull",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE2,"dsNullToValue2",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ISUTF8,"dsIsUTF8",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_COLLATION,"dsCollation",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_INET_ATON,"dsInetAton",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_INET_NTOA,"INET_NTOA",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.STR_INSERT,"INSERT",dsFuunctionType.NULL,"B","N"));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_FOLDERNAME,"dsSysVarFolderName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_INTEGRATIONSERVICENAME,"dsSysVarIntegrationServiceName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_MAPPINGNAME,"dsSysVarMappingName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_REPOSITORYSERVICENAME,"dsSysVarRepositoryServiceName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_REPOSITORYUSERNAME,"dsSysVarRepositoryUserName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSIONNAME,"dsSysVarSessionName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSIONRUNMODE,"dsSysVarSessionRunMode",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWNAME,"dsSysVarWorkflowName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWRUNID,"dsSysVarWorkflowRunId",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWRUNINSTANCENAME,"dsSysWorkflowRunInstanceName",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSSTARTTIME,"dsSysVarSESSSTARTTIME",dsFuunctionType.NULL,"B","N",'V'));
		dsFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWSTARTTIME,"dsSysVarWORKFLOWSTARTTIME",dsFuunctionType.NULL,"B","N",'V'));

		return dsFnTypeRules;
	}
	public static List<FnRule> getMySqlRules()
	{	
		if(mysqlRules!=null)
			return mysqlRules;

		mysqlRules = new ArrayList<FnRule>();

		mysqlRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"avg",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","AVG(ALL numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COUNT(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COUNT(DISTINCT  numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"first_value",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST_VALUE(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F',"variableType","GROUP BY Column[,...]"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_LAST,"last_value",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST_VALUE (value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SUM(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CUME_DIST()"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK ()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DENSE_RANK()"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","GREATEST(value [,...])"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_LAG,"lag",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LAG(value [,offset [,default_value]])"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"lead",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LEAD(value [,offset [,default_value]])"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"least",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LEAST(value [, ...])"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_RANK,"rank()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANK()"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"stddev",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"stddev_pop",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV_POP(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"stddev_samp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV_SAMP(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"var_samp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","VARIANCE(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"var_pop",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","VAR_POP(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_DATE,"Date",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_TIME,"TIME",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"TIMESTAMP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"@@system_time_zone",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"current_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"current_timestamp()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"DATETIME",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"current_time",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIME"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"current_date",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"local_time",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIME"));
		mysqlRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"local_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIMESTAMP"));
		mysqlRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"DATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IF",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IF condition THEN"));
		mysqlRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"IFNULL",dsFuunctionType.NULL,"B","N",'F',"variableType","NULLIF(value1,value2)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"NULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","ISNULL(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"DATE_FORMAT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"UNIX_TIMESTAMP()",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"STR_TO_DATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CAST",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"to_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TO_TIMESTAMP(timestamp, format)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"to_date",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE(text, format)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"to_char",dsFuunctionType.NULL,"B","N",'F',"STRING","to_char(timestamp, value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"to_char",dsFuunctionType.NULL,"B","N",'F',"STRING","to_char(time, value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"DEGREES",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CAST",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"UTC_TIMESTAMP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"md5",dsFuunctionType.NULL,"B","N",'F',"STRING","md5(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"SOUNDEX",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","OR"));
		mysqlRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","AND"));
		mysqlRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT"));
		mysqlRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"XOR",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ABS(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","CEIL(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"POW",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_DIV,"div",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"FACT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"floor",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","FLOOR(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"exp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_GREATER,">",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","EXP(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,">=",dsFuunctionType.NULL,"B","N",'F',"NUMERIC",">="));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"<",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"<=",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<="));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LN(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_LOG,"log",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LOG(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"random()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANDOM()"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","POWER(numeric_value,2)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"sqrt",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SQRT(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_PI,"pi",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","PI()"));
		mysqlRules.add(new FnRule(dsFuunctionType.MATH_MOD,"mod",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","MOD(numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.MATH_POWER,"power",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","POWER(numeric_value,numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"MOD",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POWER",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"ADDDATE",dsFuunctionType.NULL,"B","N",'F',"DATE","ADDDATE(date, numeric_value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DATE_ADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE_ADD(date, INTERVAL value addunit)"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"DATE_ADD",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"DATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"HOUR",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"MICROSECOND",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"MINUTE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTHNAME",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"NEXT_DAY",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"QUARTER",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"SEC_TO_TIME",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"WEEKDAY",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"SUBDATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"YEAR",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_GET_YEARWEEK_FROM_DATE,"YEARWEEK",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"LAST_DAY",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_SUBDAYS,"SUBDATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNCATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"acos",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ACOS(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"asin",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ASIN(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"atan",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ATAN(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"atan2",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ATAN2(value,value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_COS,"cos",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COS(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"KURTOSIS",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_SIN,"sin",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SIN(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_TAN,"tan",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","TAN(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(value[,...])"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"encode",dsFuunctionType.NULL,"B","N",'F',"STRING","encode(data bytea, format text)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_DECODE,"decode",dsFuunctionType.NULL,"B","N",'F',"BYTEA","decode(string text, format text)"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_FALSE,"FALSE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_TRUE,"TRUE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHAR",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"STRCMP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT_WS",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"CONVERT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"CONVERT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"STRCMP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_FIELD,"FIELD",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_HEX,"HEX",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"STRCMP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"left",dsFuunctionType.NULL,"B","N",'F',"STRING","LPAD(value, numeric_value, value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"bit_length",dsFuunctionType.NULL,"B","N",'F',"INT","char_length(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"STRING","replace(value, from value, to value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"SUBSTRING_INDEX",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"REGEXP_SUBSTR",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPLICATE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SUBSTRING_INDEX",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"REGEXP",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"LENGTH",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N",'F',"STRING","RPAD(value, numeric_value, value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"INSTR",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"STRING","substring(string from pattern)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"RIGHT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"LEFT",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"STRING","trim([leading | trailing | both] [characters] from string)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ascii",dsFuunctionType.NULL,"B","N",'F',"INT","ASCII(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"reverse",dsFuunctionType.NULL,"B","N",'F',"STRING","reverse(value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.STR_POSITION,"strpos",dsFuunctionType.NULL,"B","N",'F',"INT","position(value in value)"));
		mysqlRules.add(new FnRule(dsFuunctionType.CASE_CASE,"CASE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"WHEN",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CASE_THEN,"THEN",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CASE_ELSE,"ELSE",dsFuunctionType.NULL,"B","N"));
		mysqlRules.add(new FnRule(dsFuunctionType.CASE_END,"END",dsFuunctionType.NULL,"B","N"));
		
		return mysqlRules;
	}

	

	public static List<FnRule> getOracleRules()
	{
		if(oraFnTypeRules!=null)
			return oraFnTypeRules;

		oraFnTypeRules = new ArrayList<FnRule>();

		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"FLOAT","AVG(All numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","COUNT(All numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","COUNT(Distinct numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST",dsFuunctionType.NULL,"B","N",'F',"variableType","COUNT(All value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"NUMBER","GROUP BY(All numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LAST(All value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(All numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(All numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(All numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CUME_DIST(numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK",dsFuunctionType.NULL,"B","N",'F',"NUMBER","DENSE_RANK(numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"NUMBER","GREATEST(ALL numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LAST_VALUE(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LAG(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"LEAD",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LEAD(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"LEAST",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LEAST(ALL numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","MEDIAN(numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"PERCENTILE_CONT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","PERCENTILE_CONT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK",dsFuunctionType.NULL,"B","N",'F',"NUMBER","RANK()"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV_POP(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STDDEV_SAMP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV_SAMP(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","VARIANCE(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","VAR_POP(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"SYSDATE",dsFuunctionType.NULL,"B","N",'F',"DATE","SYSDATE"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"SYSTIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP WITH TIMEZONE","SYSTIMESTAMP"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"LOCALTIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIMESTAMP"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"DBTIMEZONE",dsFuunctionType.NULL,"B","N",'F',"TIME ZONE OFFSET","DBTIMEZONE"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"SESSIONTIMEZONE",dsFuunctionType.NULL,"B","N",'F',"TIME ZONE OFFSET","SESSIONTIMEZONE"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"ISDATE",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","ISDATE(date)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IF",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IF condition THEN"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IN  (value [,...])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"EXISTS",dsFuunctionType.NULL,"B","N",'F',"void","EXISTS"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"NVL",dsFuunctionType.NULL,"B","N",'F',"void","NVL(exp1,exp2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N",'F',"void","NULLIF(exp1,exp2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"REGEXP_INSTR",dsFuunctionType.NULL,"B","N",'F',"NUMBER","REGEXP_INSTR(VALUES,...)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TO_CHAR( date_value [, format])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TO_CHAR( numeric_value [, format])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"TO_BINARY_FLOAT",dsFuunctionType.NULL,"B","N",'F',"variableType","TO_BINARY_FLOAT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"TO_BINARY_FLOAT",dsFuunctionType.NULL,"B","N",'F',"variableType","TO_BINARY_FLOAT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"TO_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"TO_BINARY_FLOAT",dsFuunctionType.NULL,"B","N",'F',"variableType","TO_BINARY_FLOAT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TO_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TO_TIMESTAMP(string[, format])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"NVL",dsFuunctionType.NULL,"B","N",'F',"void","NVL(exp1,exp2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NVL",dsFuunctionType.NULL,"B","N",'F',"void","NVL(exp1,exp2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","DEGREES(NUMERIC_VALUE)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TO_CHAR( date_value [, format])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DateAdd",dsFuunctionType.NULL,"B","N",'F',"DATE","DateAdd(date_value,numeric_value,numeric_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DateAdd",dsFuunctionType.NULL,"B","N",'F',"DATE","DateAdd(date_value,numeric_value,days)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N",'F',"DATE","ADD_MONTHS(date, number_months)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"DATE","DATEDIFF(date1,date2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","MONTHS_BETWEEN(date1,date2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"NEXT_DAY",dsFuunctionType.NULL,"B","N",'F',"DATE","NEXT_DAY(date, stringValue_day)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_TIMEZONE_FROM_DATETIME,"TZ_OFFSET",dsFuunctionType.NULL,"B","N",'F',"TIMEZONE OFFSET","TZ_OFFSET(timezone)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARWEEK_FROM_DATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"LAST_DAY",dsFuunctionType.NULL,"B","N",'F',"DATE","LAST_DAY(date)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_NEW_TIME,"NEW_TIME",dsFuunctionType.NULL,"B","N",'F',"DATE","NEW_TIME(date, zone1, zone2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_SET_DATE_PART,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_SUBDAYS,"DATE",dsFuunctionType.NULL,"B","N",'F',"variableType","DATE([value])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"DATE","ROUND(date [, format ])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"DATE","TRUNC(date [, format ])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXTRACT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.FRM_TIME_ZONE,"FROM_TZ",dsFuunctionType.NULL,"B","N",'F',"varchar","FROM_TZ(timestamp_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"GEN_CONST",dsFuunctionType.NULL,"B","N"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"GEN_EXP",dsFuunctionType.NULL,"B","N"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_HASH,"ORA_HASH",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ORA_HASH(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ABS(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"NUMBER","CEIL(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"NUMBER","FLOOR(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ROUND(number,[, decimal_places])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"NUMBER","TRUNC(number,[, decimal_places])"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"COVAR_POP",dsFuunctionType.NULL,"B","N",'F',"NUMBER","COVER_POP(value1, value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"COVAR_SAMP",dsFuunctionType.NULL,"B","N",'F',"NUMBER","SAMP(value1, value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"NUMBER","EXP(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"FACTORIAL",dsFuunctionType.NULL,"B","N",'F',"NUMBER","FACTORIAL(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"NUMBER","GREATEST(value,...)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,">=",dsFuunctionType.NULL,"B","N"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"LEAST",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LEAST(value,...)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"<=",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LN(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LOG(numeric_value1,numeric_value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"*",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"PERCENTILE_CONT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","PERCENTILE_CONT(value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"NUMBER","MOD(numeric_value1,numeric_value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"dbms_random.value",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"REMAINDER",dsFuunctionType.NULL,"B","N",'F',"NUMBER","REMAINDER(numeric_value1,numeric_value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","SIGN(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POWER",dsFuunctionType.NULL,"B","N",'F',"NUMBER","POWER(numeric_value1,numeric_value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","SQRT(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"NUMBER","MOD(numeric_value1,numeric_value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"NUMBER","POWER(numeric_value1,numeric_value2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ACOS(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ASIN(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ATAN(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN2",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ATAN2(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"NUMBER","COS(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"NUMBER","COSH(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","COT(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"NUMBER","DEGREES(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"KURTOSIS",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RADIANS(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","SIN(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"NUMBER","SINH(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","TAN(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"NUMBER","TANH(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(exp1,exp2,...,expN)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"variableType","DECODE(exp,search,result)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"FALSE",dsFuunctionType.NULL,"B","N",'F',"boolean","FALSE"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"TRUE",dsFuunctionType.NULL,"B","N",'F',"boolean","TRUE"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N",'F',"NUMBER","NTILE(number)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N",'F',"NUMBER","ROW_NUMBER()"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM_DEPRECATED,"ROWNUM",dsFuunctionType.SP_ROWNUM,"S","N",'F',"NUMBER","ROW_NUM"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"standard_hash",dsFuunctionType.NULL,"B","N",'F',"variableType","standard_hash"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"SOUNDEX",dsFuunctionType.NULL,"B","N",'F',"varchar","SOUNDEX(char)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"boolean","value1 OR value2"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"boolean","value1 AND value2"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"boolean","NOT value"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"XOR",dsFuunctionType.NULL,"B","N",'F',"boolean","value1 X value2"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CHR(number_codE)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CHR(number_codE)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"||",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","var1 || var2"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONCAT(string1,string2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","INITCAP(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LPAD( string1, padded_length [, pad_string] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LENGTH(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LOWER(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LTRIM( string1 [, trim_string] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REPLACE( string1, string_to_replace [, replacement_string] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE_DEPRECATED,"TRANSLATE",dsFuunctionType.STR_REPLACE,"B","N",'F',"VARCHAR","TRANSLATE( string1, string_to_replace, replacement_string )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","RPAD( string1, padded_length [, pad_string] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","RTRIM( string1 [, trim_string] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"INSTR",dsFuunctionType.NULL,"B","N",'F',"NUMBER","INSTR( string, substring [, start_position [, th_appearance ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","SUBSTR( string, start_position [, length ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"RIGHT",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"LEFT",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TRIM(string_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","UPPER(string_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"number","ASCII(char)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII_DEPRECATED,"ASCIISTR",dsFuunctionType.STR_ASCII,"S","N",'F',"VARCHAR","ASCII(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"STRCMP",dsFuunctionType.NULL,"B","N",'F',"NUMBER","STRCMP(str1,str2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"COMPOSE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","COMPOSE(string_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","STRCMP(str1,str2)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONVERT( string1, char_set_to [, char_set_from] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONVERT( string1, char_set_to [, char_set_from] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONVERT( string1, char_set_to [, char_set_from] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONVERT( string1, char_set_to [, char_set_from] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"DECOMPOSE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","DECOMPOSE(string_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"DUMP",dsFuunctionType.NULL,"B","N",'F',"VARCHAR2","DUMP(string_value)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"==",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"FIELD",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"HEX",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"LENGTHB",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LENGTHB(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"LENGTH2",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LENGTH2(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"LENGTH4",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LENGTH4(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"LENGTHC",dsFuunctionType.NULL,"B","N",'F',"NUMBER","LENGTH4(string)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"NCHR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","NCHR(number_code)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"!=",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXMATCH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"REGEXP_COUNT",dsFuunctionType.NULL,"B","N",'F',"NUMBER","REGEXP_COUNT( string, pattern [, start_position [, match_parameter ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REGEXP_REPLACE( string, pattern [, replacement_string [, start_position [, nth_appearance [, match_parameter ] ] ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"REGEXP_SUBSTR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REGEXP_SUBSTR( string, pattern [, start_position [, nth_appearance [, match_parameter [, sub_expression ] ] ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"INSTR4",dsFuunctionType.NULL,"B","N",'F',"NUMBER","INSTR4( string, substring [, start_position [, th_appearance ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"INSTR2",dsFuunctionType.NULL,"B","N",'F',"NUMBER","INSTR2( string, substring [, start_position [, th_appearance ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"INSTR4",dsFuunctionType.NULL,"B","N",'F',"NUMBER","INSTR4( string, substring [, start_position [, th_appearance ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"INSTRC",dsFuunctionType.NULL,"B","N",'F',"NUMBER","INSTR4( string, substring [, start_position [, th_appearance ] ] )"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"VSIZE",dsFuunctionType.NULL,"B","N",'F',"NUMBER","VSIZE(exp)"));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CASE_CASE,"CASE",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"WHEN",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CASE_THEN,"THEN",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CASE_ELSE,"ELSE",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"END",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		oraFnTypeRules.add(new FnRule(dsFuunctionType.SYS_CONNECTPATH,"SYS_CONNECT_BY_PATH",dsFuunctionType.NULL,"B","N",'F',"",""));



		return oraFnTypeRules;
	}

	public static List<FnRule> getDB2Rules()
	{
		if(db2FnTypeRules!=null)
			return db2FnTypeRules;

		db2FnTypeRules = new ArrayList<FnRule>();

		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT","COUNT(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"variableType","GROUP BY column1,column2"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(column_name)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(column_name)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(column_name)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","VARIANCE(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","MEDIAN(column_name)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","SQRT(VARIANCE(expression))"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","VARIANCE(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_TIMESTAMP"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_TIMESTAMP"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_TIMESTAMP"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"TIMEZONE",dsFuunctionType.NULL,"B","N",'F',"variableType","TIMEZONE(datetimeColumn,from-timezone,to-timezone)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N",'F',"variableType","NULLIF(expression,expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"VARCHAR_FORMAT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","VARCHAR_FORMAT(dateColumn, 'format of date') "));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"FLOAT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","FLOAT(columnName)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"FLOAT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","FLOAT(columnName)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TO_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE(column,dateFormat)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"ADD_DAYS",dsFuunctionType.NULL,"B","N",'F',"variableType","ADD_DAYS(dateColumn or expression,Number"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N",'F',"variableType","ADD_MONTHS(dateColumn or expression,Number"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATE_DIFF",dsFuunctionType.NULL,"B","N",'F',"INTEGER","AVG(ALL numeric_value)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","MONTHS_BETWEEN(date expression1, date expression2"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","ROUND(expression1,digits to round"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TRUNCATE(expression1,digits to truncate"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"variableType","EXTRACT expression FROM dateTimeExpression"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"SOUNDEX",dsFuunctionType.NULL,"B","N",'F',"ROW","SOUNDEX(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"LOR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","expression1 OR expression2"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"LNOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT expression"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"variableType","ABS(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"variableType","CEILING(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"variableType","FLOOR(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","ROUND(expression1,digits to round"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TRUNCATE(expression1,digits to truncate"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"CBRT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CBRT(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","EXP(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","LN(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","LOG10(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND",dsFuunctionType.NULL,"B","N",'F',"variableType","RAND(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"variableType","SIGN(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POW",dsFuunctionType.NULL,"B","N",'F',"variableType","POWER(expression, power expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","SQRT(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"variableType","MOD(expression 1, expression 2)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"variableType","POWER(expression, power expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","COS(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","COSH(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","COT(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","DEGREES(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RADIANS(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","SIN(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","SINH(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TAN(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TANH(expression 1)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONCAT(expression 1, expression 2)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LPAD(expression,length,character))"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"variableType","LENGTH(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LOWER(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LTRIM(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REPLACE(expression,search expression,replace expression"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","RPAD(expression,length,character))"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","RTRIM(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","SUBSTR(expression,start,end)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TRIM(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","UPPER(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ASCII(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CONCAT(expression 1, expression 2)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"HEX",dsFuunctionType.NULL,"B","N",'F',"variableType","HEX(expression)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"REGEXP_COUNT",dsFuunctionType.NULL,"B","N",'F',"INTEGER","REGEXP_COUNT(expression1,expression2)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"REGEXP_EXTRACT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REGEXP_EXTRACT(expression 1, expression 2, expression3)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REGEXP_REPLACE(expression 1, expression 2, expression3)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"REGEXP_SUBSTR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REGEXP_SUBSTR(expression 1, expression 2, expression3)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPEAT",dsFuunctionType.NULL,"B","N",'F',"variableType","REPEAT(expression 1, expression 2)"));
		db2FnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REVERSE(expression)"));

		return db2FnTypeRules;
	}


	public static List<FnRule> getTeradataRules()
	{
		if(teraFnTypeRules!=null)
			return teraFnTypeRules;

		teraFnTypeRules = new ArrayList<FnRule>();

		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"FLOAT","AVG(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INTEGER","COUNT(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INTEGER","COUNT(DISTINCT value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"void", "GROUP BY value"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK()",dsFuunctionType.NULL,"B","N",'F',"INTEGER","RANK()"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE_DEPRECATED,"VAR_SAMP",dsFuunctionType.AGG_VARIANCE,"S","N",'F',"FLOAT","VAR_SAMP(numeric_value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STDDEV_SAMP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","STDDEV_SAMP(numeric_value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","STDDEV_POP(numeric_value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","VAR_POP(numeric_value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE AT TIMEZONE"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TIMESTAMP(date)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP WITH TIMEZONE","CURRENT_TIMESTAMP"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHAR_REPLACE,"OTRANSLATE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","OTRANSLATE(string_value,from_string,to_string)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ANY,"ANY",dsFuunctionType.NULL,"B","N",'F',"void","ANY"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"If",dsFuunctionType.NULL,"B","N",'F',"void","IF Condition THEN"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"boolean","IN (value [,...])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"EXISTS",dsFuunctionType.NULL,"B","N",'F',"void","EXISTS"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"NVL",dsFuunctionType.NULL,"B","N",'F',"void","IS NULL"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N",'F',"void","NULLIF(<column-name, value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TO_CHAR(Date)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TO_CHAR(Number)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"NUMBER","TO_NUMBER(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TO_TIMESTAMP(value,format)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE(value,format)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TO_CHAR(TIMESTAMP)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ZEROIFNULL,"ZEROIFNULL",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ZEROIFNULL(value,0)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N",'F',"DATE","ADD_MONTHS(date,integer)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N",'F',"NUMBER","MONTHS_BETWEEN(date1,date2)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"STRING","EXTRACT ( datepart FROM date )"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"Boolean","OR"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"Boolean","AND"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"Boolean","NOT"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ABS(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceiling",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CEIL(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","FLOOR(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ROUND(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"INTEGER","TRUNC(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","EXP(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","LN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"FLOAT","LOG(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RANDOM",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Random(lower_bound,upper_bound)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"INTEGER","SIGN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","POWER(value,power)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ACOS(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ASIN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ATAN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN2",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ATAN2(value,value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","COS(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"FLOAT","COSH(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"FLOAT","DEGREES(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","RADIAN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","SIN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"FLOAT","SINH(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","TAN(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"FLOAT","TANH(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(Argument list,['default value']"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"Decode",dsFuunctionType.NULL,"B","N",'F',"variableType","DECODE(value"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"FALSE",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","False"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"TRUE",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","True"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ROW_NUMBER ()"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"void","OVER"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"||",dsFuunctionType.NULL,"B","N",'F',"STRING","value||value||[,...]"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"concat",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT(value,value[,value])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"initcap",dsFuunctionType.NULL,"B","N",'F',"STRING","INITCAP(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","LPAD(source_string,length,[fill_string])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","LENGTH(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_INDEX,"INDEX",dsFuunctionType.NULL,"B","N",'F',"INTEGER","INDEX(value1,value2)")); 
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM('value[,value])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","RPAD(source_string,length,[fill_string])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(value[,value])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTR(string,start_index[,length])"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","TRIM([BOTH/TRAILING/LEADING][Trim_expression][character_set] value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ASCII(value)"));
		teraFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"POSITION",dsFuunctionType.NULL,"B","N",'F',"INTEGER","POSITION(value1 IN value 2)"));
		

		return teraFnTypeRules;
	}


	public static List<FnRule> getNetezzaRules()
	{
		if(netezzaTypeRules!=null)
			return netezzaTypeRules;

		netezzaTypeRules = new ArrayList<FnRule>();


		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST()",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK()",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"lead",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"least",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"PERCENTILE_CONT",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STDDEV_SAMP",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"TIMEOFDAY()",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"CURRENTTIME",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"CURRENTDATE",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"isdate",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IF",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"dsExists",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"NOTNULL",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"ISNULL",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"nullif",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"to_date",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"string_to_int",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"to_timestamp",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"to_date",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NVL",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"degrees",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"to_char",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"add_months",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_TIMEZONE_FROM_DATETIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARWEEK_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"last_day",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_NEW_TIME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_SET_DATE_PART,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_SUBDAYS,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"date_trunc",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"extract",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"dsMD5",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"nysiis",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"or",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"and",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"not",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"xor",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"covar_pop",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"covar_samp",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"!",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"sqrt",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"fpow",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN2",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"cot",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"KURTOSIS_AGG",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"isfalse",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"istrue",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHR",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_FORMAT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"concat",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"lap",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM_DEPRECATED,"btrim",dsFuunctionType.STR_TRIM,"S","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXMATCH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CASE_CASE,"case",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"when",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CASE_THEN,"then",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CASE_ELSE,"else",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"END",dsFuunctionType.NULL,"B","N"));
		netezzaTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N"));


		return netezzaTypeRules;
	}

	public static List<FnRule> getSqlServerRules()
	{
		if(sqlserverTypeRules!=null)
			return sqlserverTypeRules;

		sqlserverTypeRules = new ArrayList<FnRule>();

		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDEV",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDEVP",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VARP",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"GETDATE()",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"SYSDATETIME",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"SYSDATETIMEOFFSET",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IIF",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"EXISTS",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"NULL",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"ISNULL",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"ISNULL",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATEADD",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTH",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_TIMEZONE_FROM_DATETIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"YEAR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARWEEK_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_NEW_TIME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_SET_DATE_PART,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_SUBDAYS,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"CONVERT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"SOUNDEX",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEILING",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"CONVERT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"SQUARE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN2",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"FALSE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"TRUE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHAR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHAR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"dsPipe",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE_DEPRECATED,"+",dsFuunctionType.STR_CONCAT_PIPE,"S","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_FORMAT,"FORMAT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LEN",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"RIGHT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"LEFT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT_WS",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"DATALENGTH",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"NCHAR",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXMATCH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPLICATE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"STRING_SPLIT",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CASE_CASE,"case",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"when",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CASE_THEN,"then",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CASE_ELSE,"else",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"End",dsFuunctionType.NULL,"B","N"));
		sqlserverTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N"));


		return sqlserverTypeRules;
	}

	public static List<FnRule> getSybaseRules()
	{
		if(sybaseFnTypeRules!=null)
			return sybaseFnTypeRules;

		sybaseFnTypeRules = new ArrayList<FnRule>();

		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUPING",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"LEAD",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"PERCENTILE_DISC",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"DATE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"DATETIME",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"TIMEZONE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"ISNULL",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CONVERT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"FLOAT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TO_DATE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"STR",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"STR",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATEADD",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DAYS",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DAYS",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"DATE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"HOUR",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"DATENAME",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"MINUTE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTH",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"DATEROUND",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNCNUM",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RANDOM",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"REMAINDER",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POWER",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"HTML_DECODE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"ASCII",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"STRING",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"CHAR_LENGTH",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LCASE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"DIFFERENCE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"HEX",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPLICATE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"",dsFuunctionType.NULL,"B","N"));
		sybaseFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N"));



		return sybaseFnTypeRules;
	}



	public static List<FnRule> getRedshiftRules()
	{
		if(redshiftFnTypeRules!=null)
			return redshiftFnTypeRules;

		redshiftFnTypeRules = new ArrayList<FnRule>();

		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR_SAMP",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"LEAST",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK()",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR_SAMP",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"EXISTS",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"to_number",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"to_number",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"to_number",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"to_number",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"to_number",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"to_number",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TO_DATE",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATEADD",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DATEADD",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"DATE_TRUNC",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"MD5",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEILING",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"CBRT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POW",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHR",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"||",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","var1 || var2"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LEN",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT",dsFuunctionType.NULL,"B","N"));

		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"CONVERT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"REGEXP_EXTRACT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPEAT",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT_PART",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"",dsFuunctionType.NULL,"B","N"));
		redshiftFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N"));

		return redshiftFnTypeRules;
	}


	public static List<FnRule> getBigqueryRules()
	{
		if(bigqueryFnTypeRules!=null)
			return bigqueryFnTypeRules;

		bigqueryFnTypeRules = new ArrayList<FnRule>();
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"variableType","AVG(numeric_value"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT64","COUNT(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT64","COUNT(DISTINCT numeric_value)" ));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"INT64","GROUP BY"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CUME_DIST(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","VARIANCE([DISTINCT] expression)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","DENSE_RANK()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N",'F',"variableType","LAG(value_1[,numeric_value [, value_2]])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"LEAD",dsFuunctionType.NULL,"B","N",'F',"variableType","LEAD(value_1[,numeric_value [, value_2]])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK",dsFuunctionType.NULL,"B","N",'F',"INT64","RANK()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","STDDEV([DISTINCT] numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"",""));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","VAR_POP([DISTINCT] numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE(year, month, day)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"TIME",dsFuunctionType.NULL,"B","N",'F',"TIME","TIME(hour, minute, second)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TIMESTAMP(datetime_value[, timezone]"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"DATETIME",dsFuunctionType.NULL,"B","N",'F',"DATETIME","DATETIME(year, month, day, hour, minute, second)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"CURRENT_TIME",dsFuunctionType.NULL,"B","N",'F',"TIME","CURRENT_TIME()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE([time_zone])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"TIME",dsFuunctionType.NULL,"B","N",'F',"TIME","TIME(timestamp, [timezone])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TIMESTAMP(string_expression[, timezone] "));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IF",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IF(test_condition,true_val,false_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IN(column_string, values_array)"));  
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"EXISTS",dsFuunctionType.NULL,"B","N",'F',"VOID","EXISTS"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"IFNULL",dsFuunctionType.NULL,"B","N",'F',"variableType","IFNULL(value, computed_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"IFNULL",dsFuunctionType.NULL,"B","N",'F',"variableType","IFNULL(value, computed_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"IFNULL",dsFuunctionType.NULL,"B","N",'F',"variableType","IFNULL(value, computed_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(date_value AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT64","CAST(date_value AS INT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(numeric_value AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT64","CAST(numeric_value AS INT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(numeric_value AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST(string_value AS DATE)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(numeric_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT64","CAST(string_value AS INT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT64","CAST(string_value AS INT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CAST",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","CAST(string_value AS BOOLEAN)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(string_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CAST(string_value AS FLOAT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIME","CAST(string_value AS TIME)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CAST(string_value AS TIMESTAMP)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST(value AS DATE)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT64","CAST(value AS INT64)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"IFNULL",dsFuunctionType.NULL,"B","N",'F',"INT64","IFNULL(value,0"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS num_type)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"CAST",dsFuunctionType.NULL,"B","N",'F',"DEGREES","CAST(rad_value AS DEGREES)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(timestamp_value AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(time AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS typename)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS typename)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ZEROIFNULL,"ifnull",dsFuunctionType.NULL,"B","N",'F',"INT64","ifnull(value,0)"));  
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(value AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(value AS STRING)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CAST(value AS TIMESTAMP)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATE_ADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE_ADD(date_value, INTERVAL datepart_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DATE_ADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE_ADD(date_value, INTERVAL day_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"DATE_ADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE_ADD(date_value, INTERVAL months_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CEIL(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATE_DIFF",dsFuunctionType.NULL,"B","N",'F',"INT64","DATE_DIFF(date_value, date_value, date_part)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","FLOOR(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F',"STRING","FORMAT_DATE(format_string, date_string)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N",'F',"variableType","EXTRACT(part FROM tmstmp_value[AT TIME ZONE tz_spec])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE([time_zone])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"DATE","EXTRACT(part FROM date_expression)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"DATE","EXTRACT(part FROM date_expression)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"INT64","EXTRACT(part FROM tmstmp_value[AT TIME ZONE tz_spec])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"INT64","EXTRACT(part FROM tmstmp_value[AT TIME ZONE tz_spec])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"INT64","EXTRACT(part FROM tmstmp_value[AT TIME ZONE tz_spec])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"INT64","EXTRACT(part FROM tmstmp_value[AT TIME ZONE tz_spec])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"INT64","EXTRACT(part FROM tmstmp_value[AT TIME ZONE tz_spec])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"DATE_ROUND",dsFuunctionType.NULL,"B","N",'F',"INT64","ROUND(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"DATE_TRUNC",dsFuunctionType.NULL,"B","N",'F',"DATE","DATE_TRUNC(date_value, date_part)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"DATE","EXTRACT(part FROM date_expression)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","OR(value1,value2"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","AND(value1,value2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT(value1)")); 
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","ABS(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","CEIL(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","FLOOR(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"INT64","ROUND(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"trunc",dsFuunctionType.NULL,"B","N",'F',"INT64","trunc(numeric_value,integer_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","COVAR_POP(numeric_expr1, numeric_expr2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"COVAR_SAMP",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","COVAR_SAMP(numeric_expr1, numeric_expr2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"DIV",dsFuunctionType.NULL,"B","N",'F',"INT64","DIV(numeric_val, numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","exp(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LOG",dsFuunctionType.NULL,"B","N",'F',"variableType","LOG(result_numeric_value, base_numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"variableType","LOG(result_numeric_value, base_numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"SAFE_MULTIPLY",dsFuunctionType.NULL,"B","N",'F',"variableType","SAFE_MULTIPLY(value1, value2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"INT64","MOD(value1, value2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","RAND()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","SIGN(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POW",dsFuunctionType.NULL,"B","N",'F',"variableType","POWER(numeric_val1, numeric_val2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","sqrt(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"PI",dsFuunctionType.NULL,"B","N",'F',"FLOAT64","pi()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"variableType","MOD(value1, value2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POW",dsFuunctionType.NULL,"B","N",'F',"variableType","POW(numeric_val1, numeric_val2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"variableType","ACOS(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"variableType","ASIN(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"variableType","ATAN(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"variableType","COS(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"variableType","COSH(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"variableType","COT(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"variableType","DEGREES(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"variableType","RADIANS(numeric_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"variableType","SIN(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"variableType","SINH(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"variableType","TAN(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"variableType","TANH(numeric_val)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"","coalesce([value1,value2,value3])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N",'F',"INT64","NTILE(num_)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N",'F',"INT64","ROWNUMBER()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"void","OVER()"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT(value1[, ...])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"||",dsFuunctionType.NULL,"B","N",'F',"STRING","value||value||[,...]"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"PROPER",dsFuunctionType.NULL,"B","N",'F',"","PROPER(column_string)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","pad(string_val,string_length,pad_string,pad_side)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INT64","CHAR_LENGTH(string_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHARLENGTH,"CHARACTER_LENGTH",dsFuunctionType.NULL,"B","N",'F',"INT64","CHARACTER_LENGTH(value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_INDEX,"INDEX",dsFuunctionType.NULL,"B","N",'F',"STRING","INDEX(string_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(string_value1[, value2])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REPLACE(original_value, from_value, to_value"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","pad(string_val,string_length,pad_string,pad_side)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(string_value1[, value2])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"FIND",dsFuunctionType.NULL,"B","N",'F',"STRING","FIND(input_string,string_pattern,[ignore_case], [start_index])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTR(value, position[, length])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"RIGHT",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTR(value, position[, length])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"LEFT",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTR(value, position[, length])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","TRIM(string_value1[, value2])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(string_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","CHAR(index_value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"EXACT",dsFuunctionType.NULL,"B","N",'F',"BOOL","EXACT(string_val1,string_val2"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT(value1[, ...])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS typename)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS typename)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS typename)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST(value AS typename)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"SPLIT",dsFuunctionType.NULL,"B","N",'F',"STRING","SPLIT(value[, delimiter])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"REGEXP_EXTRACT",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_EXTRACT(value, regexp)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_REPLACE(value, regexp, replacement)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPEAT",dsFuunctionType.NULL,"B","N",'F',"STRING","REPEAT(original_value, repetitions)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N",'F',"STRING","REVERSE(value)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"STRPOS",dsFuunctionType.NULL,"B","N",'F',"","STRPOS(string, substring)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT",dsFuunctionType.NULL,"B","N",'F',"STRING","SPLIT(value[, delimiter])"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"STARTS_WITH",dsFuunctionType.NULL,"B","N",'F',"STRING","STARTS_WITH(value1, value2)"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"WHEN",dsFuunctionType.NULL,"B","N",'F',"variableType","WHEN"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CASE_THEN,"THEN",dsFuunctionType.NULL,"B","N",'F',"variableType","THEN"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"END",dsFuunctionType.NULL,"B","N",'F',"variableType","END"));
		bigqueryFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_BETWEEN,"BETWEEN",dsFuunctionType.NULL,"B","N",'F',"variableType","BETWEEN"));

		return bigqueryFnTypeRules;
	}

	public static List<FnRule> getAzuresqlRules()
	{
		if(sqlDwFnTypeRules!=null)
			return sqlDwFnTypeRules;

		sqlDwFnTypeRules = new ArrayList<FnRule>();

		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"variableType","avg(numeric_value)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT","COUNT (  [ ALL | DISTINCT ] numeric_value ]  )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT","COUNT (  [ ALL | DISTINCT ] numeric_value ]  )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST_VALUE ( [value ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"variableType","GROUP BY"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST_VALUE ( [value] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX( [ ALL | DISTINCT ] numeric_value ) "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN( [ ALL | DISTINCT ] numeric_value )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM ( [ ALL | DISTINCT ] numeric_value )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK",dsFuunctionType.NULL,"B","N",'F',"BIGINT","DENSE_RANK ( )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N",'F',"variableType","LAG (scalar_value [,offset] [,default])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"LEAD",dsFuunctionType.NULL,"B","N",'F',"variableType","LEAD (scalar_value [,offset] [,default])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK()",dsFuunctionType.NULL,"B","N",'F',"BIGINT","RANK()"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDEV",dsFuunctionType.NULL,"B","N",'F',"FLOAT","STDEV ( [ ALL | DISTINCT ] numeric_value )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDEVP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","STDEVP ( [ ALL | DISTINCT ] numeric_value )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR",dsFuunctionType.NULL,"B","N",'F',"FLOAT","VAR ( [ ALL | DISTINCT ] numeric_value )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VARP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","VARP ( [ ALL | DISTINCT ] numeric_value )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"DATETIME2",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"DATETIME","CURRENT_TIMESTAMP"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"SYSDATETIME",dsFuunctionType.NULL,"B","N",'F',"DATETIME2","SYSDATETIME ( ) "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"GETDATE()",dsFuunctionType.NULL,"B","N",'F',"DATETIME","GETDATE ( )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"SYSDATETIMEOFFSET",dsFuunctionType.NULL,"B","N",'F',"DATETIMEOFFSET","SYSDATETIMEOFFSET ( ) "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHAR_REPLACE,"TRANSLATE",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IIF",dsFuunctionType.NULL,"B","N",'F',"variableType","IIF ( boolean_expression, true_value, false_value ) "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"EXISTS",dsFuunctionType.NULL,"B","N",'F',"void","EXISTS"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"dsCheckNotNull",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"NULL",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"PATINDEX",dsFuunctionType.NULL,"B","N",'F',"BIGINT","PATINDEX ( '%pattern%' , expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( expression AS STRING [ ( length ) ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT","CAST ( expression AS INT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( expression AS STRING [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST ( expression AS DECIMAL [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST ( expression AS FLOAT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT","CAST ( expression AS INT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CAST ( expression AS DOUBLE [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST ( expression AS FLOAT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( expression AS STRING [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST ( expression AS DECIMAL [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"variableType","CONVERT ( data_type [ ( length ) ] , value[ , style ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST ( expression AS DECIMAL [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST ( expression AS FLOAT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT","CAST ( expression AS INT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INT","CAST ( value AS INT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CAST",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","CAST ( value AS BOOLEAN [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CAST ( value AS DOUBLE [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"CAST",dsFuunctionType.NULL,"B","N",'F',"LONG","CAST ( value AS LONG [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIME","CAST ( value AS TIME [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CAST ( value AS TIMESTAMP [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST ( value AS DATE [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"BIGINT","CAST ( value AS BIGINT [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"ISNULL",dsFuunctionType.NULL,"B","N",'F',"boolean","ISNULL(value)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"ISNULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","ISNULL(value)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"CAST",dsFuunctionType.NULL,"B","N",'F',"DEGREES","CAST ( value AS DEGREES [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( value AS STRING [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( value AS STRING [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"variableType","CONVERT ( data_type [ ( length ) ] , value[ , style ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST ( value AS datatype [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( value AS STRING [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST ( value AS STRING [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CAST ( value AS TIMESTAMP [ ( length ) ])"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATEADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATEADD (datepart , number , date )  "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"DATEADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATEADD (datepart , number , date )  "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"INT","DATEDIFF ( datepart , startdate , enddate )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTH",dsFuunctionType.NULL,"B","N",'F',"INT","MONTH ( date )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"YEAR",dsFuunctionType.NULL,"B","N",'F',"INT","YEAR ( date )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"variableType","ROUND ( numeric_expression , length [ ,function ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"DATENAME",dsFuunctionType.NULL,"B","N",'F',"STRING","DATENAME ( datepart , date )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"SOUNDEX",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","SOUNDEX ( character_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","OR(value1,value2)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","AND(value1,value2)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT(value1,value2)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"variableType","ABS ( numeric_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEILING",dsFuunctionType.NULL,"B","N",'F',"variableType","CEILING ( numeric_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"variableType","ROUND ( numeric_expression , length [ ,function ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"FLOAT","EXP ( float_expression ) "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"FLOAT","LOG ( float_expression [, base ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND",dsFuunctionType.NULL,"B","N",'F',"FLOAT","RAND ( [ seed ] "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"variableType","SIGN ( numeric_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"SQUARE",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"FLOAT","SQRT ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"variableType","POWER ( float_expression , y )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ACOS ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ASIN ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ATAN ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN2",dsFuunctionType.NULL,"B","N",'F',"FLOAT","ATAN2 ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","COS ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"FLOAT","COT ( float_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"variableType","DEGREES ( numeric_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"variableType","RADIANS ( numeric_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","SIN( float_expression "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","TAN( float_expression "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COLESCE(value1,value2,value3.."));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N",'F',"FLOAT","NTILE (integer_expression)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N",'F',"INT","ROW_NUMBER ( )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"void","OVER"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHAR",dsFuunctionType.NULL,"B","N",'F',"CHAR","CHAR ( integer_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT ( string_value1, string_value2 [, string_valueN ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"+",dsFuunctionType.NULL,"B","N",'F',"STRING","+"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_DEPRECATED,"CONCAT",dsFuunctionType.STR_CONCAT,"S","N"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_FORMAT,"FORMAT",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","FORMAT ( value, format [, culture ] )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LEN",dsFuunctionType.NULL,"B","N",'F',"INT","LEN ( string_expression ) "));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"VACHAR","LOWER ( character_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LTRIM ( character_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","REPLACE ( string_expression , string_pattern , string_replacement )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","RTRIM ( character_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","SUBSTRING ( expression ,start , length )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHARLENGTH,"LEN",dsFuunctionType.NULL,"B","N",'F',"INT","LEN ( string_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"RIGHT",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","RIGHT ( character_expression , integer_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"LEFT",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","LEFT ( character_expression , integer_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","TRIM ( string )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","UPPER(STRING)"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INT","ASCII ( character_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT_WS",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT_WS ( separator, argument1, argument2 [, argumentN]... )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"DATALENGTH",dsFuunctionType.NULL,"B","N",'F',"",""));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"NCHAR",dsFuunctionType.NULL,"B","N",'F',"NVARCHAR","NCHAR ( integer_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPLICATE",dsFuunctionType.NULL,"B","N",'F',"STRING","REPLICATE ( string_expression , integer_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","REVERSE ( string_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","SPACE ( integer_expression )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"STRING_SPLIT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","STRING_SPLIT ( string , separator )"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CASE_CASE,"case",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"when",dsFuunctionType.NULL,"B","N",'F',"variableType","WHEN"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CASE_THEN,"then",dsFuunctionType.NULL,"B","N",'F',"variableType","THEN"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CASE_ELSE,"else",dsFuunctionType.NULL,"B","N",'F',"variableType","ELSE"));
		sqlDwFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"End",dsFuunctionType.NULL,"B","N",'F',"variableType","END"));
		




		return sqlDwFnTypeRules;
	}

	public static List<FnRule> getSnowflakeRules()
	{
		if(snowflakeFnTypeRules!=null)
			return snowflakeFnTypeRules;

		snowflakeFnTypeRules = new ArrayList<FnRule>();

		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"variableType","AVG( [ DISTINCT ] numeric_value ))"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGEDISTINCT,"AVG",dsFuunctionType.NULL,"B","N",'F',"varibaleType","AVG( [ DISTINCT ] numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"COVAR_POP",dsFuunctionType.NULL,"B","N",'F',"variableType","COVAR_POP(numeric_value,numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT","COUNT( [ DISTINCT ] <numeric_value1 [ , numeric_value2 ... ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CUME_DIST()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"INT","COUNT( [ DISTINCT ] <numeric_value1 [ , numeric_value2 ... ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK()",dsFuunctionType.NULL,"B","N",'F',"INT","DENSE_RANK ( ) "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DISTINCT,"DISTINCT",dsFuunctionType.NULL,"B","N",'F',"INT","DISTINCT numeric_value"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST_VALUE(numeric_value ) [ { IGNORE | RESPECT } NULLS ]"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY ",dsFuunctionType.NULL,"B","N",'F',"void","GROUP BY"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"variableType","GREATEST( value1 [ , <value2> ... ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST_VALUE( <expr> ) [ { IGNORE | RESPECT } NULLS ]"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N",'F',"variableType","LAG ( numeric_value [ , <offset> , numeric_value2 ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"LEAD",dsFuunctionType.NULL,"B","N",'F',"variableType","LEAD ( numeric_value [ , <offset> , numeric_value2 ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"LEAST",dsFuunctionType.NULL,"B","N",'F',"variableType","LEAST( <numeric_value> , ... )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX( numeric_value "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX( numeric_value "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","MEDIAN( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM( [ DISTINCT ]numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"PERCENTILE_CONT",dsFuunctionType.NULL,"B","N",'F',"FLOAT","PERCENTILE_CONT( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE_DISC,"PERCENTILE_DISC",dsFuunctionType.NULL,"B","N",'F',"variableType","PERCENTILE_DISC( numeric_value ) "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE_RANK,"PERCENT_RANK()",dsFuunctionType.NULL,"B","N",'F',"FLOAT","PERCENT_RANK()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK()",dsFuunctionType.NULL,"B","N",'F',"INT","RANK()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV( [DISTINCT] numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV_POP([DISTINCT] numeric_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STDDEV_SAMP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV_POP([DISTINCT] numeric_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"variableType","VARIANCE( [DISTINCT] numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VARIANCE_POP",dsFuunctionType.NULL,"B","N",'F',"variableType","VARIANCE_POP( [DISTINCT] numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CORRELATION,"CORR",dsFuunctionType.NULL,"B","N",'F',"variableType","CORR( numeric_value1,numeric_value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"CURRENT_TIME",dsFuunctionType.NULL,"B","N",'F',"TIME","CURRENT_TIME"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"SYSDATE()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP_NTZ","SYSDATE()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURR_DATE,"CURRENT_DATE()",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURR_TIME,"CURRENT_TIME",dsFuunctionType.NULL,"B","N",'F',"TIME","CURRENT_TIME"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"current timestamp","CURRENT_TIMESTAMP( [ <fract_sec_precision> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"LOCALTIME()",dsFuunctionType.NULL,"B","N",'F',"TIME","LOCALTIME()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"LOCALTIMESTAMP()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP_LTZ","LOCALTIMESTAMP()"));   
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.BOOL_OR,"BOOLOR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","BOOLOR( numeric_value1 , numeric_value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.BOOL_AND,"BOOLAND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","BOOLAND( numeric_value1 , numeric_value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.BOOL_XOR,"BOOLXOR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","BOOLXOR( numeric_value1 , numeric_value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"IS_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","IS_DATE( date_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"variableType","IN"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ANY,"ANY",dsFuunctionType.NULL,"B","N",'F',"variableType","ANY"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IFF",dsFuunctionType.NULL,"B","N",'F',"variableType","IFF( <condition> ,value1 , value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_BETWEEN,"BETWEEN",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","BETWEEN <lower_bound> AND <upper_bound>"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"REGEXP_INSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_INSTR( value ,pattern,position "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR, "TO_CHAR",dsFuunctionType.NULL,"B", "N",'F',"VARCHAR","TO_CHAR( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT, "cast",dsFuunctionType.NULL,"B", "N",'F',"","CAST( value AS INT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR, "TO_CHAR",dsFuunctionType.NULL,"B", "N",'F',"STRING","TO_CHAR( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC, "cast",dsFuunctionType.NULL,"B", "N",'F',"DECIMAL","CAST( value AS DECIMAL )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT, "CAST",dsFuunctionType.NULL,"B", "N",'F',"FLOAT","CAST( value AS FLOAT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE, "TO_DOUBLE",dsFuunctionType.NULL,"B", "N",'F',"DOUBLE","CAST( value AS INT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC, "cast",dsFuunctionType.NULL,"B", "N",'F',"DECIMAL","CAST( value AS DECIMAL )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT, "cast",dsFuunctionType.NULL,"B", "N",'F',"FLOAT","CAST( value AS FLOAT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING, "cast",dsFuunctionType.NULL,"B", "N",'F',"STRING","CAST( value AS STRING )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES, "DEGREES",dsFuunctionType.NULL,"B", "N",'F',"FLOAT","DEGREES(numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN, "TO_BOOLEAN",dsFuunctionType.NULL,"B", "N",'F',"BOOLEAN","TO_BOOLEAN( vlaue )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT, "CAST",dsFuunctionType.NULL,"B", "N",'F',"BIGINT","CAST( value AS BIGINT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING, "TO_VARCHAR",dsFuunctionType.NULL,"B", "N",'F',"STRING","TO_VARCHAR( time_value [, 'format' ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING, "TO_VARCHAR",dsFuunctionType.NULL,"B", "N",'F',"STRING","TO_VARCHAR( time_value [, 'format' ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER, "CAST",dsFuunctionType.NULL,"B", "N",'F',"INT","CAST( value AS INT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME, "cast",dsFuunctionType.NULL,"B", "N",'F',"TIME","CAST( value AS TIME )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP, "cast",dsFuunctionType.NULL,"B", "N",'F',"TIMESTAMP","CAST( value AS TIMESTAMP )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE, "cast",dsFuunctionType.NULL,"B", "N",'F',"DATE","CAST( value AS DATE )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT, "cast",dsFuunctionType.NULL,"B", "N",'F',"","CAST( value AS BIGINT )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO, "NULLIFZERO",dsFuunctionType.NULL,"B", "N",'F',"variableType","NULLIF( value1 , value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ZEROIFNULL,"ZEROIFNULL",dsFuunctionType.NULL,"B","N",'F',"INT","ZEROIFNULL( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE, "TO_DATE",dsFuunctionType.NULL,"B", "N",'F',"DATE","TO_DATE(string_value [, <format> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT, "cast",dsFuunctionType.NULL,"B", "N",'F',"variableType","CAST( value AS datatype )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC, "TO_DECIMAL",dsFuunctionType.NULL,"B", "N",'F',"variableType","TO_DECIMAL( <expr> [, '<format>' ] [, <precision> [, <scale> ] ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE, "TO_DOUBLE",dsFuunctionType.NULL,"B", "N",'F',"variableType","TO_DOUBLE( <expr> [, '<format>' ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST, "CAST",dsFuunctionType.NULL,"B", "N",'F',"variableType","CAST( value AS datatype )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING, "cast",dsFuunctionType.NULL,"B", "N",'F',"STRING","CAST(value AS STRING)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT, "CAST",dsFuunctionType.NULL,"B", "N",'F',"FLOAT","CAST(value AS FLOAT)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT, "CAST",dsFuunctionType.NULL,"B", "N",'F',"INT","CAST(value AS INT)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING, "cast",dsFuunctionType.NULL,"B", "N",'F',"STRING","CAST(value AS STRING)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG, "CAST",dsFuunctionType.NULL,"B", "N",'F',"LONG","CAST(value AS LONG)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CAST(value AS TIMESTAMP)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NVL",dsFuunctionType.NULL,"B","N",'F',"","NVL( value1 , value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DATEADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATEADD( day, <value>, date_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N",'F',"DATE","ADD_MONTHS(date_value,value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATEADD",dsFuunctionType.NULL,"B","N",'F',"DATE","DATEADD( date_part, <value>, date_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"DATE","DATEDIFF( <date_or_time_part>, <date_or_time_expr1>, <date_or_time_expr2> )"));              
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"INT","EXTRACT( <date_or_time_part> FROM <date_or_time_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"DATE_PART",dsFuunctionType.NULL,"B","N",'F',"INT","DATE_PART( <date_or_time_part> , <date_or_time_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_NEW_TIME,"CONVERT_TIMEZONE",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CONVERT_TIMEZONE( <target_tz> , <source_timestamp> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"HOUR",dsFuunctionType.NULL,"B","N",'F',"INT","HOUR( <time_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"DATE_PART",dsFuunctionType.NULL,"B","INT",'F',"INT","DATE_PART( time_part , date_or_time_expr )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"MINUTE",dsFuunctionType.NULL,"B","N",'F',"INT","MINUTE( <time_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTH",dsFuunctionType.NULL,"B","N",'F',"INT","MONTH( <date_or_timestamp_expr> "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"INT","DATEDIFF( month, date_or_time_expr1, date_or_time_expr2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARS_BETWEEN,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"INT","DATEDIFF( year, date_or_time_expr1, date_or_time_expr2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"NEXT_DAY",dsFuunctionType.NULL,"B","N",'F',"STRING","NEXT_DAY( <date_or_time_expr> , <dow_string> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"QUARTER",dsFuunctionType.NULL,"B","N",'F',"INT","QUARTER( <date_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"SECOND",dsFuunctionType.NULL,"B","N",'F',"INT","SECOND( <time_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"DAYOFWEEK",dsFuunctionType.NULL,"B","N",'F',"INT","DAYOFWEEK(date_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"DAYOFYEAR",dsFuunctionType.NULL,"B","N",'F',"INT","DAYOFYEAR(date_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"YEAR",dsFuunctionType.NULL,"B","N",'F',"INT","YEAR( <date_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_YEARWEEK_FROM_DATE,"WEEKOFYEAR",dsFuunctionType.NULL,"B","N",'F',"INT","WEEKOFYEAR( <date_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"LAST_DAY",dsFuunctionType.NULL,"B","N",'F',"DATE","LAST_DAY( <date_or_time_expr> [ , <date_part> ] )"));          
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"variableType","TRUNC( <input_expr> [ , <scale_expr> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_DATE_TRUNC,"DATE_TRUNC",dsFuunctionType.NULL,"B","N",'F',"","DATE_TRUNC(date_part,date_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"SOUNDEX",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","SOUNDEX( <varchar_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"MD5",dsFuunctionType.NULL,"B","N",'F',"STRING","MD5(string_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.FRM_TIME_ZONE,"CONVERT_TIMEZONE",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP_NTZ","CONVERT_TIMEZONE( <target_tz> , <source_timestamp> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DAY_FROM_DATE,"DAY",dsFuunctionType.NULL,"B","N",'F',"INT","DAY( <date_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DAY_FROM_DATE_DEPRECATED,"DAYOFMONTH",dsFuunctionType.DT_GET_DAY_FROM_DATE,"S","N",'F',"INT","DAYOFMONTH( <date_or_timestamp_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"variableType","ABS( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"NUMBER","CEIL( <input_expr> [, <scale_expr> ] "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"COVAR_POP",dsFuunctionType.NULL,"B","N",'F',"variableType","COVAR_POP(numeric_value1,numeric_value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"COVAR_SAMP",dsFuunctionType.NULL,"B","N",'F',"","COVAR_SAMP( numeric_value1 , numeric_value1 "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"variableType","EXP( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"FACTORIAL",dsFuunctionType.NULL,"B","N",'F',"INT","FACTORIAL( <integer_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"NUMBER","FLOOR( <input_expr> [, <scale_expr> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"FLOAT","LN(<expr>)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"FLOAT","LOG(<base>, <expr>)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"FLOAT","MOD( <expr1> , <expr2> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"PERCENTILE_CONT",dsFuunctionType.NULL,"B","N",'F',"FLOAT","PERCENTILE_CONT( numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"variableType","POWER (value1,value2)"));       
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"variableType","ROUND( <input_expr> [, <scale_expr> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RANDOM",dsFuunctionType.NULL,"B","N",'F',"variableType","RANDOM([seed])"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"variableType","SIGN( <expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"SQUARE",dsFuunctionType.NULL,"B","N",'F',"variableType","SQUARE(expr)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"variableType","SQRT(expr)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"NUMBER","TRUNC( <input_expr> [ , <scale_expr> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.NUM_HASH,"HASH",dsFuunctionType.NULL,"B","N",'F',"NUMBER","HASH( value1 [ , value2 ... ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"RADIANS","ACOS(value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"RADIANS","ASIN( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"RADIANS","ATAN( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"RADIANS","COS( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"FLOAT","COSH( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"FLOAT","COT(value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"FLOAT","DEGREES( <real_expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"KURTOSIS",dsFuunctionType.NULL,"B","N",'F',"variableType","KURTOSIS( <expr> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"FLOAT","RADIANS(value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"variableType","SIN( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"","SINH(value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"variableType","TAN( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"variableType","TANH( value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"","COALESCE( numeric_value1,numeric_value2,... )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"variableType","DECODE( value , <search1> , <result1> [ , <search2> , <result2> ... ] [ , <default> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N",'F',"INT","ROW_NUMBER()"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"void","OVER ( [ PARTITION BY numeric_value]"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SYS_CONNECTPATH,"SYS_CONNECT_BY_PATH",dsFuunctionType.NULL,"B","N",'F',"STRING","sys_connect_by_path(string_value, ' -> ')"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N",'F',"INT","NTILE( <constant_value> )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"void","OVER ( [ PARTITION BY numeric_value"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INT","ASCII( string_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHARLENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INT","LENGTH( string_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CHR(numeric_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT(string_value1,string_value2,.."));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_PIPE,"||",dsFuunctionType.NULL,"B","N",'F',"void","||"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_COLON,":",dsFuunctionType.NULL,"B","N",'F',"void",":"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"HEX_ENCODE",dsFuunctionType.NULL,"B","N",'F',"STRING","HEX_ENCODE(<input> [,case])"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"STRING","INITCAP( string_value [ , <delimiters> ] "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT,"LEFT",dsFuunctionType.NULL,"B","N",'F',"STRING","LEFT( string_value, length_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","LPAD(base, length_expr [,pad])"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INT","LENGTH( string_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(string_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM( string_value [, characters ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"REGEXP_COUNT",dsFuunctionType.NULL,"B","N",'F',"INT","REGEXP_COUNT( string_value , <pattern> [ , <position> , <parameters> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXPLIKE,"REGEXP_LIKE",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_LIKE( string_value , <pattern> [ , <parameters> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_REPLACE( string_value , <pattern> [ , <replacement> , <position> , <occurrence> , <parameters> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"REGEXP_SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_SUBSTR( string_value , <pattern> [ , <position> [ , <occurrence> [ , <regex_parameters> [ , <group_num ] ] ] ] )"));               
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REPLACE( string_value , <pattern> [ , <replacement> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N",'F',"STRING","REVERSE(string_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","RPAD(string_value, <length_expr> [, <pad>])"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(string_value [, <characters> ])"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPEAT",dsFuunctionType.NULL,"B","N",'F',"STRING","REPEAT(string_value,n)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"REGEXP_INSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_INSTR( string_value , <pattern> [ , <position> [ , <occurrence> [ , <option> [ , <regexp_parameters> [ , <group_num> ] ] ] ] ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N",'F',"STRING","SPACE(numeric_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT_PART",dsFuunctionType.NULL,"B","N",'F',"STRING","SPLIT_PART(<string>, <delimiter>, <partNumber>)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"STARTSWITH",dsFuunctionType.NULL,"B","N",'F',"STRING","STARTSWITH( string_value1 , string_value2 )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"REGEXP_INSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_INSTR( string_value , <pattern> [ , <position> [ , <occurrence> [ , <option> [ , <regexp_parameters> [ , <group_num> ] ] ] ] ] )"));                                   
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTR( string_value, start_string [ , length ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"RIGHT",dsFuunctionType.NULL,"B","N",'F',"STRING","RIGHT( string_value, length_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","TRIM( string_value [, <characters> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(string_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_INSERT,"INSERT",dsFuunctionType.NULL,"B","N",'F',"STRING","INSERT(multitable)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"LEFT",dsFuunctionType.NULL,"B","N",'F',"STRING","LEFT( string_value, length_value )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"POSITION ",dsFuunctionType.NULL,"B","N",'F',"INT","POSITION( string_value1, string_value2 [ , <start_pos> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_INDEX,"INDEX",dsFuunctionType.NULL,"B","N",'F',"INT","INDEX(string_value)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"POSITION",dsFuunctionType.NULL,"B","N",'F',"INT","POSITION( string_value1, string_value2 [ , <start_pos> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"TRANSLATE",dsFuunctionType.NULL,"B","N",'F',"STRING","TRANSLATE(string_value ,source, target) "));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"TRANSLATE",dsFuunctionType.NULL,"B","N",'F',"STRING","TRANSLATE(string_value ,source, target)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.STR_LIKE,"LIKE",dsFuunctionType.NULL,"B","N",'F',"variableType","LIKE( string_value , <pattern> [ , <escape> ] )"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","OR(value1,value2)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"XOR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","XOR(value1,value2)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","AND(value1,value2)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT(value1,value2)"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CASE_CASE,"CASE",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CASE_WHEN,"WHEN",dsFuunctionType.NULL,"B","N",'F',"variableType","WHEN"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CASE_THEN,"THEN",dsFuunctionType.NULL,"B","N",'F',"variableType","THEN"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CASE_ELSE,"ELSE",dsFuunctionType.NULL,"B","N",'F',"variableType","ELSE"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"END",dsFuunctionType.NULL,"B","N",'F',"variableType","END"));
		snowflakeFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"LOCALTIMESTAMP()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP_LTZ","LOCALTIMESTAMP()"));

		return snowflakeFnTypeRules;
	}

	public static List<FnRule> getDatastageRules()
	{
		if(datastageFnTypeRules!=null)
			return datastageFnTypeRules;
		datastageFnTypeRules = new ArrayList<FnRule>();

		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"Max",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"Min",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"Sum",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"CurrentDate()",dsFuunctionType.NULL,"B","N",'F',"date","CurrentDate()"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"CurrentTimestamp()",dsFuunctionType.NULL,"B","N",'F',"timestamp","CurrentTimestamp()"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"NUM",dsFuunctionType.NULL,"B","N",'F',"STRING","NUM(expression)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"If",dsFuunctionType.NULL,"B","N",'F',"variableType","If"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"IsNull",dsFuunctionType.NULL,"B","N",'F',"boolean","IsNull(Variable)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_ASCIITONUM,"SEQ",dsFuunctionType.NULL,"B","N",'F',"STRING","SEQ(Expression)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"DateToString",dsFuunctionType.NULL,"B","N",'F',"STRING","DateToString(DATE)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"",dsFuunctionType.NULL,"B","N"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"StringToDate",dsFuunctionType.NULL,"B","N",'F',"DATE","StringToDate(String)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"StringToDecimal",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","StringToDecimal(String)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"AsFloat",dsFuunctionType.NULL,"B","N",'F',"FLOAT","AsFloat(Number)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"AsInteger",dsFuunctionType.NULL,"B","N",'F',"Integer","AsInteger(Number)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"TimestampToDate",dsFuunctionType.NULL,"B","N",'F',	"DATE","TimestampToDate(timestamp)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"NullToZero",dsFuunctionType.NULL,"B","N",'F',"variableType","NullToZero(column)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NullToValue",dsFuunctionType.NULL,"B","N",'F',"variableType","NullToZero(column,value)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ISBLANK,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOTIMET,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.ADD_SECONDSTOTIMESTAMP,"TimestampFromSecondsSince",dsFuunctionType.NULL,"B","N",'F',"timestamp","TimestampFromSecondsSince(seconds (dfloat), [base_timestamp])"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DateFromDaysSince",dsFuunctionType.NULL,"B","Y",'F',"date","DateFromDaysSince(int[32],date)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DaysSinceFromDate",dsFuunctionType.NULL,"B","Y",'F',"date","DaysSinceFromDate(givendate, sourcedate)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.GEN_SURROGATEKEY,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"Abs",dsFuunctionType.NULL,"B","N",'F',"dfloat","Abs(numeric expression)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"Ceil",dsFuunctionType.NULL,"B","N",'F',"int32","Ceil(number(dfloat))"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"Floor",dsFuunctionType.NULL,"B","N",'F',"int32","Floor(number(dfloat))"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHAR",dsFuunctionType.NULL,"B","N",'F',"char","char(numeric)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_STRING,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,":",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"String","Convert (list, new.list, string)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"Len",dsFuunctionType.NULL,"B","N",'F',"numeric","Len(string)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"DownCase",dsFuunctionType.NULL,"B","N",'F',"String","DownCase(Column)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"TrimF",dsFuunctionType.NULL,"B","N",'F',"String","TrimF(String)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"EREPLACE",dsFuunctionType.NULL,"B","N",'F',"string","EREPLACE (expression, substring, replacement [,occurrence [,begin]] )"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"PadString",dsFuunctionType.NULL,"B","Y",'F',"String","PadString(inputString,padString,padLength(int))"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"TrimB",dsFuunctionType.NULL,"B","N",'F',"String","TrimB(String)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"",dsFuunctionType.NULL,"B","Y",'F'));//(differ)
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"Trim",dsFuunctionType.NULL,"B","N",'F',"String","Trim(String,character,option)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UpCase",dsFuunctionType.NULL,"B","N",'F',"String","UpCase(String)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM_DEPRECATED,"",dsFuunctionType.STR_TRIM,"S","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR_DEPRECATED,"Left",dsFuunctionType.STR_SUBSTR,"S","N",'F',"string","Left(string,position)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"Right",dsFuunctionType.NULL,"B","N",'F',"string","Right(string,position)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"Field",dsFuunctionType.NULL,"B","N",'F',"String","Field(String, delimiter,occurence,[numberoftimes])"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHANGE,"CHANGE",dsFuunctionType.NULL,"B","N",'F',"String","CHANGE (expression, substring, replacement [ ,occurrence [ ,begin] ])"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"Or",dsFuunctionType.NULL,"B","N",'F',"void","Or"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"And",dsFuunctionType.NULL,"B","N",'F',"void","And"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"Not",dsFuunctionType.NULL,"B","N",'F',"void","Not"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"SetNull()",dsFuunctionType.NULL,"B","N",'F',"void","SetNull()"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"StringToTimestamp",dsFuunctionType.NULL,"B","N",'F',"string","StringToTimestamp(string)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"IsNotNull",dsFuunctionType.NULL,"B","N",'F',"int8","IsNotNull(Any)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"Mod",dsFuunctionType.NULL,"B","N",'F',"numeric","Mod(dividend,divisor)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"matches_regex",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N",'F'));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"PWR",dsFuunctionType.NULL,"B","N",'F',"numeric","pwr(expression,power)"));
		datastageFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N",'F'));

		return datastageFnTypeRules;
	}

	public static List<FnRule> getSsisRules()
	{
		if(ssisFnTypeRules!=null)
			return ssisFnTypeRules;

		ssisFnTypeRules = new ArrayList<FnRule>();

		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DT_R8","AVG(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"DT_I4","COUNT(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT DISTINCT",dsFuunctionType.NULL,"B","N",'F',"DT_I4","COUNT DISTINCT(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAXIMUM",dsFuunctionType.NULL,"B","N",'F',"DT_I4","MAXIMUM(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MINIMUM",dsFuunctionType.NULL,"B","N",'F',"DT_I4","MINIMUM(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"@[System::CreationDate]",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"@[System::StartTime]",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"GETDATE()",dsFuunctionType.NULL,"B","N",'F',"DT_DBTIMESTAMP","GETDATE()"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"GETUTCDATE()",dsFuunctionType.NULL,"B","N",'F',"DT_DBTIMESTAMP","GETUTCDATE()"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"ISNULL",dsFuunctionType.NULL,"B","N",'F',"DT_BOOL","ISNULL(expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATEADD",dsFuunctionType.NULL,"B","N",'F',"DT_I4","DATEADD(datepart, number, date)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATE_DIFF",dsFuunctionType.NULL,"B","N",'F',"DT_I4","DATEDIFF(datepart, startdate, endate)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"DT_I4","ABS(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEILING",dsFuunctionType.NULL,"B","N",'F',"DT_I4","CEILING(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"DT_I4","FLOOR(numeric_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"",dsFuunctionType.NULL,"B","N",'F',"DT_I4","ROUND(numeric_expression,length)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"+",dsFuunctionType.NULL,"B","N",'F',"DT_STR ","expression1 + expression2"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","LOWER(character_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","LTRIM(character expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","REPLACE(character_expression,searchstring,replacementstring)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","RTRIM(character expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","SUBSTRING(character_expression, position, length)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","TRIM(character_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"DT_WSTR","UPPER(character_expression)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"||",dsFuunctionType.NULL,"B","N",'F',"DT_BOOL","boolean_expression1 || boolean_expression2"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"and",dsFuunctionType.NULL,"B","N",'F',"DT_BOOL","boolean_expression1 && boolean_expression2"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"!",dsFuunctionType.NULL,"B","N",'F',"DT_BOOL","!boolean_expression"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"%",dsFuunctionType.NULL,"B","N",'F',"DT_R8","dividend % divisor"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"DT_R8","POWER(numeric_expression,power)"));
		ssisFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N",'F',"",""));


		return ssisFnTypeRules;
	}


	public static List<FnRule> getInformaticaBDMRules()
	{
		if(informaticaBDMFnTypeRules!=null)
			return informaticaBDMFnTypeRules;

		informaticaBDMFnTypeRules = new ArrayList<FnRule>();

		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG( numeric_value [, filter_condition ] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(ALL numeric_value)"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"Integer","COUNT( value [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N",'F',"Integer","COUNT( value [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","FIRST( value [, filter_condition ] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F',"String",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","LAST( value [, filter_condition ] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"Integer","MAX( numeric_value [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"Integer","MIN( numeric_value [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(ALL numeric_value)"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","VARIANCE( numeric_value [, filter_condition ] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"SYSDATE",dsFuunctionType.NULL,"B","N",'F',"DATEE","SYSDATE"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"SESSSTARTTIME",dsFuunctionType.NULL,"B","N",'F',"DATETIME","SESSSTARTTIME"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"SYSTIMESTAMP()",dsFuunctionType.NULL,"B","N",'F',"DATETIME","SYSTIMESTAMP()"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N",'F',"DATETIME",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"IS_DATE",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IS_DATE( value [,format] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"IS_NUMBER",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IS_NUMBER( value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IN( valueToSearch, value1, [value2, ..., valueN,] CaseFlag )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IIF",dsFuunctionType.NULL,"B","N",'F',"Variable","IIF( condition, value1 [,value2] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"ISNULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","ISNULL( value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"String","TO_CHAR( date [,format] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"TO_INTEGER",dsFuunctionType.NULL,"B","N",'F',"Integer","TO_INTEGER( value [, flag] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"String","TO_CHAR( numeric_value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"TO_DECIMAL",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TO_DECIMAL( value [, scale] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"TO_DATE",dsFuunctionType.NULL,"B","N",'F',"Date","TO_DATE( string [, format] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"TO_DECIMAL",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TO_DECIMAL( value [, scale] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"TO_FLOAT",dsFuunctionType.NULL,"B","N",'F',"Double","TO_FLOAT( value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"TO_INTEGER",dsFuunctionType.NULL,"B","N",'F',"Integer","TO_INTEGER( value [, flag] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"ADD_TO_DATE",dsFuunctionType.NULL,"B","N",'F',"Date","ADD_TO_DATE( date, format, amount )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATE_DIFF",dsFuunctionType.NULL,"B","N",'F',"Double","DATE_DIFF( date1, date2, format )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"GET_DATE_PART",dsFuunctionType.NULL,"B","N",'F',"Date","GET_DATE_PART( date, format )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"","ROUND( date [,format] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"Date","TRUNC( date [,format] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"LAST_DAY",dsFuunctionType.NULL,"B","N",'F',"Date","LAST_DAY( date )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"MD5",dsFuunctionType.NULL,"B","N",'F',"String","MD5( value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"#Constant Value#",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"#Expression#",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"Double","ABS( numeric_value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"Double","CEIL( numeric_value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"CUME",dsFuunctionType.NULL,"B","N",'F',"Double","CUME( numeric_value [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"Double","FLOOR( numeric_value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"MOVINGAVG",dsFuunctionType.NULL,"B","N",'F',"Double","MOVINGAVG( numeric_value, rowset [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"MOVINGSUM",dsFuunctionType.NULL,"B","N",'F',"Double","MOVINGSUM( numeric_value, rowset [, filter_condition] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"Integer","ROUND( numeric_value [, precision] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"Integer","TRUNC( numeric_value [, precision] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"Variable","DECODE( value, first_search, first_result [, second_search, second_result]...[,default] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"LOOKUP()",dsFuunctionType.NULL,"B","N",'F',"","LOOKUP()"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N",'F',"String","CHR( numeric_value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHRCODE",dsFuunctionType.NULL,"B","N",'F',"Integer","CHRCODE ( string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"String","CONCAT( first_string, second_string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"||",dsFuunctionType.NULL,"B","N",'F',"String","CONCAT( first_string, second_string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"String","INITCAP( string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"String","LPAD( first_string, length [,second_string] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"Integer","LENGTH( string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LCASE",dsFuunctionType.NULL,"B","N",'F',"String","LOWER( string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"String","LTRIM( string [, trim_set] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACESTR",dsFuunctionType.NULL,"B","N",'F',"String","REPLACESTR ( CaseFlag, InputString, OldString1, [OldString2, ... OldStringN,] NewString )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"String","RPAD( first_string, length [,second_string] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"String","RTRIM( string [, trim_set] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"INSTR",dsFuunctionType.NULL,"B","N",'F',"Integer","INSTR( string, search_value [,start [,occurrence [,comparison_type ]]] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"String","SUBSTR( string, start [,length] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"String","SUBSTR( string, start [,length] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"LTRIM(RTRIM)",dsFuunctionType.NULL,"B","N",'F',"String","LTRIM(RTRIM( string [, trim_set] ))"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"String","UPPER( string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"Boolean","OR"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"Boolean","AND"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"Boolean","NOT"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER_DEPRECATED,"UCASE",dsFuunctionType.STR_UPPER,"S","N",'F',"String","UPPER( string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE_CHR,"REPLACECHR",dsFuunctionType.STR_REPLACE,"B","N",'F',"String","REPLACECHR( CaseFlag, InputString, OldCharSet, NewChar )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"TO_BIGINT",dsFuunctionType.NULL,"B","N",'F',"Bigint","TO_BIGINT( value [, flag] )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","MOD( numeric_value, divisor )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"REG_MATCH",dsFuunctionType.NULL,"B","N",'F',"Boolean","REG_MATCH( subject, pattern )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"SETMAXVARIABLE",dsFuunctionType.NULL,"B","N",'F',"String","SETMAXVARIABLE( $$Variable, value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"SETVARIABLE",dsFuunctionType.NULL,"B","N",'F',"String","SETVARIABLE( $$Variable, value )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_DEPRECATED,"CONCAT",dsFuunctionType.STR_CONCAT,"S","N",'F',"String","CONCAT( first_string, second_string )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"Double","POWER( base, exponent )"));
		informaticaBDMFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"IS_SPACES",dsFuunctionType.NULL,"B","N",'F',"Boolean","IS_SPACES( value )"));

		return informaticaBDMFnTypeRules;
	}



	public static List<FnRule> getInformaticaPCRules()
	{
		if(informaticaPCFnTypeRules!=null)
			return informaticaPCFnTypeRules;

		informaticaPCFnTypeRules = new ArrayList<FnRule>();

		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG( numeric_value [, filter_condition ] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(ALL numeric_value)"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"Integer","COUNT( value [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N",'F',"Integer","COUNT( value [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","FIRST( value [, filter_condition ] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F',"String",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","LAST( value [, filter_condition ] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"Integer","MAX( numeric_value [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"Integer","MIN( numeric_value [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(ALL numeric_value)"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","VARIANCE( numeric_value [, filter_condition ] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"SYSDATE",dsFuunctionType.NULL,"B","N",'F',"DATEE","SYSDATE"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"SESSSTARTTIME",dsFuunctionType.NULL,"B","N",'F',"DATETIME","SESSSTARTTIME"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"SYSTIMESTAMP()",dsFuunctionType.NULL,"B","N",'F',"DATETIME","SYSTIMESTAMP()"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N",'F',"DATETIME",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"IS_DATE",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IS_DATE( value [,format] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"IS_NUMBER",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IS_NUMBER( value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"IN",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IN( valueToSearch, value1, [value2, ..., valueN,] CaseFlag )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IIF",dsFuunctionType.NULL,"B","N",'F',"Variable","IIF( condition, value1 [,value2] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"ISNULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","ISNULL( value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"String","TO_CHAR( date [,format] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"TO_INTEGER",dsFuunctionType.NULL,"B","N",'F',"Integer","TO_INTEGER( value [, flag] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"String","TO_CHAR( numeric_value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"TO_DECIMAL",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TO_DECIMAL( value [, scale] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"TO_DATE",dsFuunctionType.NULL,"B","N",'F',"Date","TO_DATE( string [, format] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"TO_DECIMAL",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TO_DECIMAL( value [, scale] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"TO_FLOAT",dsFuunctionType.NULL,"B","N",'F',"Double","TO_FLOAT( value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"TO_INTEGER",dsFuunctionType.NULL,"B","N",'F',"Integer","TO_INTEGER( value [, flag] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"ADD_TO_DATE",dsFuunctionType.NULL,"B","N",'F',"Date","ADD_TO_DATE( date, format, amount )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATE_DIFF",dsFuunctionType.NULL,"B","N",'F',"Double","DATE_DIFF( date1, date2, format )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"GET_DATE_PART",dsFuunctionType.NULL,"B","N",'F',"Date","GET_DATE_PART( date, format )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"","ROUND( date [,format] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"Date","TRUNC( date [,format] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"LAST_DAY",dsFuunctionType.NULL,"B","N",'F',"Date","LAST_DAY( date )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"MD5",dsFuunctionType.NULL,"B","N",'F',"String","MD5( value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"#Constant Value#",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"#Expression#",dsFuunctionType.NULL,"B","N",'F',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"Double","ABS( numeric_value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"Double","CEIL( numeric_value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"CUME",dsFuunctionType.NULL,"B","N",'F',"Double","CUME( numeric_value [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"Double","FLOOR( numeric_value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"MOVINGAVG",dsFuunctionType.NULL,"B","N",'F',"Double","MOVINGAVG( numeric_value, rowset [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"MOVINGSUM",dsFuunctionType.NULL,"B","N",'F',"Double","MOVINGSUM( numeric_value, rowset [, filter_condition] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"Integer","ROUND( numeric_value [, precision] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"Integer","TRUNC( numeric_value [, precision] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"Variable","DECODE( value, first_search, first_result [, second_search, second_result]...[,default] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"LOOKUP()",dsFuunctionType.NULL,"B","N",'F',"","LOOKUP()"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N",'F',"String","CHR( numeric_value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHRCODE",dsFuunctionType.NULL,"B","N",'F',"Integer","CHRCODE ( string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"String","CONCAT( first_string, second_string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"||",dsFuunctionType.NULL,"B","N",'F',"String","CONCAT( first_string, second_string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"String","INITCAP( string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"String","LPAD( first_string, length [,second_string] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"Integer","LENGTH( string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LCASE",dsFuunctionType.NULL,"B","N",'F',"String","LOWER( string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"String","LTRIM( string [, trim_set] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACESTR",dsFuunctionType.NULL,"B","N",'F',"String","REPLACESTR ( CaseFlag, InputString, OldString1, [OldString2, ... OldStringN,] NewString )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"String","RPAD( first_string, length [,second_string] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"String","RTRIM( string [, trim_set] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"INSTR",dsFuunctionType.NULL,"B","N",'F',"Integer","INSTR( string, search_value [,start [,occurrence [,comparison_type ]]] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"String","SUBSTR( string, start [,length] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"String","SUBSTR( string, start [,length] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"LTRIM(RTRIM)",dsFuunctionType.NULL,"B","N",'F',"String","LTRIM(RTRIM( string [, trim_set] ))"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"String","UPPER( string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"Boolean","OR"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"Boolean","AND"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"Boolean","NOT"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER_DEPRECATED,"UCASE",dsFuunctionType.STR_UPPER,"S","N",'F',"String","UPPER( string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE_CHR,"REPLACECHR",dsFuunctionType.STR_REPLACE,"B","N",'F',"String","REPLACECHR( CaseFlag, InputString, OldCharSet, NewChar )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"TO_BIGINT",dsFuunctionType.NULL,"B","N",'F',"Bigint","TO_BIGINT( value [, flag] )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","MOD( numeric_value, divisor )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"REG_MATCH",dsFuunctionType.NULL,"B","N",'F',"Boolean","REG_MATCH( subject, pattern )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"SETMAXVARIABLE",dsFuunctionType.NULL,"B","N",'F',"String","SETMAXVARIABLE( $$Variable, value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"SETVARIABLE",dsFuunctionType.NULL,"B","N",'F',"String","SETVARIABLE( $$Variable, value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT_DEPRECATED,"CONCAT",dsFuunctionType.STR_CONCAT,"S","N",'F',"String","CONCAT( first_string, second_string )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"Double","POWER( base, exponent )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"IS_SPACES",dsFuunctionType.NULL,"B","N",'F',"Boolean","IS_SPACES( value )"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_FOLDERNAME,"$PMFolderName",dsFuunctionType.NULL,"B","N",'V',"","$PMFolderName"));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_INTEGRATIONSERVICENAME,"$PMIntegrationServiceName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_MAPPINGNAME,"$PMMappingName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_REPOSITORYSERVICENAME,"$PMRepositoryServiceName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_REPOSITORYUSERNAME,"$PMRepositoryUserName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSIONNAME,"$PMSessionName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSIONRUNMODE,"$PMSessionRunMode",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWNAME,"$PMWorkflowName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWRUNID,"$PMWorkflowRunId",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWRUNINSTANCENAME,"$PMWorkflowRunInstanceName",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSSTARTTIME,"SESSSTARTTIME",dsFuunctionType.NULL,"B","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_SESSSTARTTIME_DEPRECATED,"$$$SessStartTime",dsFuunctionType.SYSVAR_SESSSTARTTIME,"S","N",'V',"",""));
		informaticaPCFnTypeRules.add(new FnRule(dsFuunctionType.SYSVAR_WORKFLOWSTARTTIME,"WORKFLOWSTARTTIME",dsFuunctionType.NULL,"B","N",'V',"",""));

		return informaticaPCFnTypeRules;
	}

	public static List<FnRule> getApacheBeamRules()
	{
		if(apacheBeamFnTypeRules!=null)
			return apacheBeamFnTypeRules;

		apacheBeamFnTypeRules = new ArrayList<FnRule>();


		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"variableType","AVG( [ ALL | DISTINCT ] numeric)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"variableType","COUNT( [ ALL | DISTINCT ] value [, value[,... ]])"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N",'F',"variableType","COUNT( [ ALL | DISTINCT ] value [, value[,... ]])"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FIRST_VALUE",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST_VALUE(value)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"variableType","GROUP BY { groupItem [, groupItem[, ...]] }"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"LAST_VALUE",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST_VALUE(value)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(value)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(value)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(value)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"CURRENT_TIME",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIME"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IF",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE value WHEN value1  THEN result1 [ ELSE result2 ]"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"IS NULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","value IS NULL"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"IS NOT NULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","value IS NOT NULL"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_ISBLANK,"IsBlank",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","CASE WHEN(value IS NULL OR value= \"\") THEN (TRUE))"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CASTDTETOSTR",dsFuunctionType.NULL,"B","Y",'F',"STRING","CAST(val)AS STRING"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CASTDATETOINTEGER",dsFuunctionType.NULL,"B","N",'F',"INTEGER ","CAST(val)AS INTEGER "));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CASTNUMTOSTRING",dsFuunctionType.NULL,"B","N",'F',"STRING","CAST(val)AS STRING"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CASTNUMTODECIMAL",dsFuunctionType.NULL,"B","Y",'F',"DECIMAL","CAST(val)AS DECIMAL"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CASTSTRTODATE",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST(val)AS DATE"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CASTSTRTODECIMAL",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST(val)AS DECIMAL"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CASTSTRTOFLOAT",dsFuunctionType.NULL,"B","Y",'F',"FLOAT","CAST(val)AS FLOAT"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CASTSTRTOINTEGER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CAST(val)AS INTEGER"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"NullToZero",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE WHEN VALUE IS NULL THEN 0"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NullToValue",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE WHEN VALUE IS NULL THEN RETURN_VALUE"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CASTTsToDate",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST(val)AS DATE"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"TIMESTAMPADD",dsFuunctionType.NULL,"B","Y",'F',"TIMESTAMP","TIMESTAMPADD(timeUnit, integer, datetime)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"DATE","CEIL(datetime TO timeUnit)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"TIMESTAMPDIFF",dsFuunctionType.NULL,"B","Y",'F',"INTEGER","TIMESTAMPDIFF(timeUnit, datetime, datetime2)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"DATE","FLOOR(datetime TO timeUnit)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"LAST_DAY",dsFuunctionType.NULL,"B","N",'F',"DATE","LAST_DAY(date)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"variableType","ABS(numeric)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CEIL(numeric)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","FLOOR(numeric)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ROUND(numeric1 [, numeric2])"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N",'F',"CHAR ","CHR(integer)"));
		//apacheBeamFnTypeRules.add(new FnRule("NUM_TRUNC","NUM_TRUNC","NULL","B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"variableType","DECODE(value, value1, result1 [, valueN, resultN ]* [, default ])"));
		//apacheBeamFnTypeRules.add(new FnRule("SP_LOOKUP","SP_LOOKUP","NULL","B","N"));
		//apacheBeamFnTypeRules.add(new FnRule("STR_CHRCODE","STR_CHRCODE","NULL","B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_STRING,"REPEAT",dsFuunctionType.NULL,"B","N",'F',"STRING","REPEAT(string, integer)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHANGE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REPLACE(string, search, replacement)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"||",dsFuunctionType.NULL,"B","N",'F',"STRING","string || string"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"STRING","INITCAP(string)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"CHARACTER_LENGTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CHARACTER_LENGTH(string)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","LOWER(string)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(string)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_REPLACE(string, regexp, rep, [, pos [, occurrence [, matchType]]])"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(string)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"LOCATE",dsFuunctionType.NULL,"B","N",'F',"INTEGER","LOCATE(string1, string2 [, integer])"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTRING(string, offset, length)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","TRIM(string)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(string)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"TRANSLATE",dsFuunctionType.NULL,"B","N",'F',"STRING","TRANSLATE(expr, fromString, toString)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"AS NULL",dsFuunctionType.NULL,"B","N",'F',"variableType","value AS NULL"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","boolean1 OR boolean2"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","boolean1 AND boolean2"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT boolean"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CAST(BIGINT)",dsFuunctionType.NULL,"B","N",'F',"BIGINT","CAST(val) AS BIGINT" ));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MOD(numeric1, numeric2)"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		//apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"variableType","POWER(numeric1, numeric2)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"SPACE",dsFuunctionType.NULL,"B","N",'F',"STRING","SPACE(integer)"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION, "POSITION", dsFuunctionType.NULL, "B", "N",'F',"INTEGER","POSITION(string1 IN string2)"));
		//apacheBeamFnTypeRules.add(new FnRule("STR_LEFT_SUBSTR", "leftsubstring", "NULL", "B", "N"));
		apacheBeamFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE, "COALESCE", dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(value, value [, value ]*)"));

		return apacheBeamFnTypeRules;
	}



	public static List<FnRule> getJavaRules()
	{
		if(javaFnTypeRules!=null)
			return javaFnTypeRules;

		javaFnTypeRules = new ArrayList<FnRule>();


		javaFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"Math.abs",dsFuunctionType.NULL,"B","N",'F',"int","Math.abs(integer_value)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"Math.ceil",dsFuunctionType.NULL,"B","N",'F',"double","Math.ceil(numeric_value)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"Math.floor",dsFuunctionType.NULL,"B","N",'F',"double","Math.floor(numeric_value)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"Math.round",dsFuunctionType.NULL,"B","N",'F',"int","Math.round(numericr_value)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"length",dsFuunctionType.NULL,"B","N",'F',"int","string_value.length()"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"toLowerCase",dsFuunctionType.NULL,"B","N",'F',"string","string_value.toLowerCase()"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"string","string_value.replace(oldChar,newChar)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"indexOf",dsFuunctionType.NULL,"B","N",'F',"string","string_value.indexOf(string_value)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"string","string_value.substring(beginIndex,endIndex)"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"string","string_value.trim()"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"toUpperCase",dsFuunctionType.NULL,"B","N",'F',"string","string_value.toUpperCase()"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"||",dsFuunctionType.NULL,"B","N",'F',"variableType","value1 || value2"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"&&",dsFuunctionType.NULL,"B","N",'F',"variableType","value1 && value2"));
		javaFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"!",dsFuunctionType.NULL,"B","N",'F',"variableType","!value"));


		return javaFnTypeRules;
	}

	public static List<FnRule> getAzureDataFactoryRules()
	
	{
		if(azureDataFactoryDataTypRules!=null)
			return azureDataFactoryDataTypRules;

		azureDataFactoryDataTypRules = new ArrayList<FnRule>();


		/*azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,dsFuunctionType.AGG_AVERAGE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_COUNT,dsFuunctionType.AGG_COUNT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,dsFuunctionType.AGG_COUNTDISTINCT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_FIRST,dsFuunctionType.AGG_FIRST,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,dsFuunctionType.AGG_GROUPBY,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_LAST,dsFuunctionType.AGG_LAST,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_MAX,dsFuunctionType.AGG_MAX,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_MIN,dsFuunctionType.AGG_MIN,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_SUM,dsFuunctionType.AGG_SUM,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,dsFuunctionType.AGG_VARIANCE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AUD_DATE,dsFuunctionType.AUD_DATE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,dsFuunctionType.AUD_TIMESTAMP,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,dsFuunctionType.AUD_UTCDATE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CHECK_DATE,dsFuunctionType.CHECK_DATE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CHECK_NUM,dsFuunctionType.CHECK_NUM,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CHECK_IF,dsFuunctionType.CHECK_IF,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CHECK_NULL,dsFuunctionType.CHECK_NULL,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,dsFuunctionType.CONV_DATETOSTR,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,dsFuunctionType.CONV_DATETOINT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,dsFuunctionType.CONV_NUMTOSTR,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,dsFuunctionType.CONV_NUMTODEC,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,dsFuunctionType.CONV_STRTODATE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,dsFuunctionType.CONV_STRTODEC,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,dsFuunctionType.CONV_STRTOFLOAT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,dsFuunctionType.CONV_STRTOINT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,dsFuunctionType.DT_ADDTODATE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_DIFF,dsFuunctionType.DT_DIFF,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,dsFuunctionType.DT_FORMATDATE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,dsFuunctionType.DT_FORMATDATEINUTC,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,dsFuunctionType.DT_GET_LASTDAYOFMONTH,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_ROUND,dsFuunctionType.DT_ROUND,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.DT_TRUNC,dsFuunctionType.DT_TRUNC,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.GEN_CONST,dsFuunctionType.GEN_CONST,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.GEN_EXP,dsFuunctionType.GEN_EXP,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,dsFuunctionType.NUM_ABSOLUTE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_CEIL,dsFuunctionType.NUM_CEIL,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_CUME,dsFuunctionType.NUM_CUME,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,dsFuunctionType.NUM_FLOOR,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,dsFuunctionType.NUM_MOVINGAVG,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,dsFuunctionType.NUM_MOVINGSUM,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_ROUND,dsFuunctionType.NUM_ROUND,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,dsFuunctionType.NUM_TRUNC,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.SP_DECODE,dsFuunctionType.SP_DECODE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,dsFuunctionType.SP_LOOKUP,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_CHAR,dsFuunctionType.STR_CHAR,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,dsFuunctionType.STR_CHRCODE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_CONCAT,dsFuunctionType.STR_CONCAT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_CONCAT,dsFuunctionType.STR_CONCAT,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_INITCAP,dsFuunctionType.STR_INITCAP,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,dsFuunctionType.STR_LEFTPAD,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_LENGTH,dsFuunctionType.STR_LENGTH,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_LOWER,dsFuunctionType.STR_LOWER,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_LTRIM,dsFuunctionType.STR_LTRIM,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_REPLACE,dsFuunctionType.STR_REPLACE,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,dsFuunctionType.STR_RIGHTPAD,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_RTRIM,dsFuunctionType.STR_RTRIM,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_SEARCH,dsFuunctionType.STR_SEARCH,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,dsFuunctionType.STR_SUBSTR,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_TRIM,dsFuunctionType.STR_TRIM,dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.STR_UPPER,dsFuunctionType.STR_UPPER,dsFuunctionType.NULL,"B","N"));
		*/azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"dsOR",dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"dsAnd",dsFuunctionType.NULL,"B","N"));
		azureDataFactoryDataTypRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"dsNot",dsFuunctionType.NULL,"B","N"));



		return azureDataFactoryDataTypRules;
	}
	public static List<FnRule> getAzureDataFactoryDataFlowRules()
	{
		if(azureDataFactoryDataFlowRules!=null)
			return azureDataFactoryDataFlowRules;

		azureDataFactoryDataFlowRules = new ArrayList<FnRule>();

		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"average",dsFuunctionType.NULL,"B","N",'F',"variableType","average(numeric_value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"INT","count(numeric_value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"group by",dsFuunctionType.NULL,"B","N",'F',"variableType","group by"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"variableType","max(numeric_value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"variableType","min(numeric_value"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"variableType","sum(numeric_value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AUD_DATE,"currentDate()",dsFuunctionType.NULL,"B","N",'F',"DATE","currentDate()"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"currentTimestamp()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","currentTimestamp()"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CHECK_IF,"iif",dsFuunctionType.NULL,"B","N",'F',"variableType","iif(<expression>, <valueIfTrue>, <valueIfFalse>)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"isNull",dsFuunctionType.NULL,"B","N",'F',"variableType","isnull(value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"toString",dsFuunctionType.NULL,"B","N",'F',"STRING","tostring(value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"toString ",dsFuunctionType.NULL,"B","N",'F',"STRING","tostring(value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"toDate",dsFuunctionType.NULL,"B","N",'F',"",""));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"toDecimal",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","todecimal(Expr)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"toFloat",dsFuunctionType.NULL,"B","N",'F',"FLOAT","toint(Expr)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"toInteger",dsFuunctionType.NULL,"B","N",'F',"INT","toint(Expr)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.DT_ROUND,"round",dsFuunctionType.NULL,"B","N",'F',"INT","round(<number> : number, [<scale to round> : number], [<rounding option> : integral])"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N",'F',"variableType","abs(<value1> : number)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ceil(<value1> : number)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"floor",dsFuunctionType.NULL,"B","N",'F',"INTEGER","floor(<value1> : number)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"concat",dsFuunctionType.NULL,"B","N",'F',"STRING","concat('<text1>', '<text2>', ...)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"lpad",dsFuunctionType.NULL,"B","N",'F',"STRING","lpad(<string to pad> : string, <final padded length> : integral, <padding> : string)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"length",dsFuunctionType.NULL,"B","N",'F',"STRING","length(value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"STRING","lower(value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(<string to trim> : string, [<trim characters> : string]) "));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"STRING","replace('<text>', '<oldText>', '<newText>')"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N",'F',"STRING","rpad(<string to pad> : string, <final padded length> : integral, <padding> : string)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(<string to trim> : string, [<trim characters> : string]) "));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"STRING","substring('<text>', <startIndex>, <length>)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"STRING","trim('<text>')"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"STRING","upper(string_value)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"||",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","||"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"&&",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","&&"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"!",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","!"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.MATH_MOD,"mod",dsFuunctionType.NULL,"B","N",'F',"variableType","mod(<dividend>, <divisor>)"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"like",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","like(<string> : string, <pattern match> : string) => boolean"));
		azureDataFactoryDataFlowRules.add(new FnRule(dsFuunctionType.MATH_POWER,"power",dsFuunctionType.NULL,"B","N",'F',"variableType","power(base, exponent "));
		

		return azureDataFactoryDataFlowRules;
	}

	public static List<FnRule> getTalendRules()
	{
		if(talendFnTypeRules!=null)
			return talendFnTypeRules;

		talendFnTypeRules = new ArrayList<FnRule>();

		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"avg",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","avg(ALL numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGEDISTINCT,"",dsFuunctionType.AGG_AVERAGE,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"INTEGER","count(ALL numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"first",dsFuunctionType.NULL,"B","N",'F',"INTEGER","first(value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"last",dsFuunctionType.NULL,"B","N",'F',"INTEGER","last(ALL numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"INTEGER","max(ALL value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"INTEGER","min(ALL value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"INTEGER","sum(ALL value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"TalendDate.getCurrentDate()",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.getCurrentDate()"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"TalendDate.isDate",dsFuunctionType.NULL,"B","N",'F',"Boolean","TalendDate.isDate(String_Date, string_Pattern)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"Relational.ISNULL",dsFuunctionType.NULL,"B","N",'F',"Boolean","Relational.ISNULL(String)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"#var#.toString()",dsFuunctionType.NULL,"B","Y",'F',"String","#var#.toString(date_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"Integer.parseInt",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Integer.parseInt(date_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"#var#.toString()",dsFuunctionType.NULL,"B","Y",'F',"STRING","#var#.toString(numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"Numeric.convertImpliedDecimalFormat",dsFuunctionType.NULL,"B","N",'F',"FLOAT","Numeric.convertImpliedDecimalFormat(String_format, String_toConvert)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"TalendDate.parseDate",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.parseDate(String_pattern,stringDate)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"Integer.valueOf",dsFuunctionType.NULL,"B","N",'F',"FLOAT","Integer.valueOf(String_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"Integer.parseInt",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Integer.parseInt(String_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"TalendDate.parseDate",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.parseDate(String_pattern, stringDate)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"TalendDate.addDate",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.addDate(Date,numeric_valueToAdd,DateType_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"TalendDate.compareDate",dsFuunctionType.NULL,"B","N",'F',"INTEGER","TalendDate.compareDate(Date_date1,Date_date2)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"TalendDate.formatDate",dsFuunctionType.NULL,"B","N",'F',"STRING","TalendDate.formatDate(string_pattern, date_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"TalendDate.formatDateInUTC",dsFuunctionType.NULL,"B","N",'F',"STRING","TalendDate.formatDate(string_pattern, date_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"TalendDate.getFirstDayOfMonth",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.getFirstDayOfMonth(date)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"TalendDate.getLastDayOfMonth",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.getLastDayOfMonth(date)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"Mathematical.ABS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","Mathematical.ABS(double"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"Math.ceil",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Math.ceil(numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"Math.floor",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Math.floor(numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"Math.round",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Math.round(numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"+",dsFuunctionType.NULL,"B","N",'F',"STRING","string_value1+string_value2"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"StringHandling.LEN",dsFuunctionType.NULL,"B","N",'F',"INTEGER","StringHandling.LEN(string_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"StringHandling.DOWNCASE",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.DOWNCASE(string_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"StringHandling.FTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.FTRIM(string_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"StringHandling.EREPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.EREPLACE(value1,value2,value3)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"StringHandling.BTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.BTRIM(string_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"StringHandling.SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.SUBSTR(string, numeric_value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"StringHandling.TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.TRIM(string)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"StringHandling.UPCASE",dsFuunctionType.NULL,"B","N",'F',"STRING","StringHandling.UPCASE(string)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"||",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","or(value1,value2)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"&&",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","and(value1,value2)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"not",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","not(value)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"=null",dsFuunctionType.NULL,"B","N",'F',"void","null"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TalendDate.parseDate",dsFuunctionType.NULL,"B","N",'F',"DATE","TalendDate.parseDate(syringDate,Sring_pattern)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"Mathematical.MOD",dsFuunctionType.NULL,"B","N",'F',"STRING","Mathematical.MOD(double,double)"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"matches",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N",'F',"",""));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"Math.pow",dsFuunctionType.NULL,"B","N",'F',"INTEGER","Math.pow"));
		talendFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N",'F',"",""));


		return talendFnTypeRules;
	}

	public static List<FnRule> getOdbcRules()
	{
		if(odbcFnTypeRules!=null)
			return odbcFnTypeRules;

		odbcFnTypeRules = new ArrayList<FnRule>();

		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(ALL numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGEDISTINCT,"AVG",dsFuunctionType.AGG_AVERAGE,"B","N",'F',"DECIMAL","AVG(DISTINCT numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","COUNT(ALL value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","COUNT(DISTINCT value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(ALL value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAXDISTINCT,"MAX",dsFuunctionType.AGG_MAX,"B","N",'F',"variableType","MAX(DISTINCT value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(ALL value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MINDISTINCT,"MIN",dsFuunctionType.AGG_MIN,"B","N",'F',"variableType","MIN(DISTINCT value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","SUM(ALL numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUMDISTINCT,"SUM",dsFuunctionType.AGG_SUM,"B","N",'F',"DECIMAL","SUM(DISTINCT numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","VAR(ALL numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEDISTINCT,"VAR",dsFuunctionType.AGG_VARIANCE,"B","N",'F',"DECIMAL","VAR(DISTINCT numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"CURRENT_TIMESTAMP",dsFuunctionType.NULL,"B","N",'F',"datetime","CURRENT_TIMESTAMP"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CAST(date_value as VARCHAR (length)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CAST(date_value as INTEGER)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CAST(numeric_value as VARCHAR (length))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST(numeric_value as DECIMAL (precision,scale))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST(numeric_value as FLOAT[(n)])"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CAST(numeric_value as INTEGER)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CAST(numeric_value as DOUBLE (precision,scale))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST(decimal_value as FLOAT[(n)])"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CAST(decimal_value as VARCHAR (length))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST(float_value as DECIMAL (precision,scale))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST(STRING_value as DATE)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","CAST(string_value as DECIMAL (precision,scale))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","CAST(string_value as FLOAT[(n)])"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CAST(string_value as INTEGER)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"BIGINT","CAST(string_value as BIGINT)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CAST",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","CAST(string_value as BOOLEAN)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CAST(STRING_value as DOUBLE (precision,scale))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIME","CAST(STRING_value as TIME)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"CAST",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CAST(STRING_value as TIMESTAMP)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","CAST(value as DATE)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"BIGINT","CAST(value as BIGINT)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST ( NULL AS data_type [(length)] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CAST(datetime_value as VARCHAR (length))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CAST(time_value as VARCHAR (length))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N",'F',"variableType","CONVERT ( { 'data_type[(length)]' | data_type[(length)] }, value )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CAST",dsFuunctionType.NULL,"B","N",'F',"variableType","CAST ( { value | NULL } AS data_type [(length)] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CAST(datetime_value as VARCHAR (length))"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","ABS(numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEILING",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CEILING(numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","FLOOR(numeric_value)"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","ROUND( numeric_value, length [,operation_type] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNCATE",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TRUNCATE( numeric_value, length )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHAR",dsFuunctionType.NULL,"B","N",'F',"CHAR","CHAR ( integer_value )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","CONCAT ( value1 , value2 )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LCASE",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","LCASE ( value )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","LTRIM ( value [ , char_set ] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","REPLACE ( value1,value2,value3 )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","RPAD ( value, length [, pad_expression] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","RTRIM ( value [ , char_set ] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","SUBSTRING  ( value, start_position [, length ] )"));
		odbcFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UCASE",dsFuunctionType.NULL,"B","N",'F',"VARACHAR","UCASE ( value )"));
		
		return odbcFnTypeRules;
	}

	public static List<FnRule> getPySparkRules()
	{
		if(pySparkRules!=null)
			return pySparkRules;

		pySparkRules = new ArrayList<FnRule>();


		pySparkRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"avg",dsFuunctionType.NULL,"B","N",'F',"Double","avg(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"Integer","COUNT(value"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"countdistinct",dsFuunctionType.NULL,"B","N",'F',"Decimal","countDistinct(value1,value2)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"first",dsFuunctionType.NULL,"B","N",'F',"variabletype","first(col, ignorenulls)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"groupby",dsFuunctionType.NULL,"B","N",'F',"Variabletype","grouping(col)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_LAST,"last",dsFuunctionType.NULL,"B","N",'F',"variabletype","last(col, ignorenulls)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"Integer","max(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"Integer","min(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"Integer","sum(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"Variance",dsFuunctionType.NULL,"B","N",'F',"variabletype","variance(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.AUD_DATE,"lit(current_timestamp())",dsFuunctionType.NULL,"B","N",'F',"timestamp","current_timestamp()"));
		pySparkRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"lit(current_timestamp())",dsFuunctionType.NULL,"B","N",'F',"timestamp","current_timestamp()"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"isdate",dsFuunctionType.NULL,"B","N",'F',"Integer","to_date.cast(date).isNotNull()"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"isnumber",dsFuunctionType.NULL,"B","N",'F',"Boolean","is_numeric(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_IF,"when",dsFuunctionType.NULL,"B","N",'F',"variableType","when(condition,returnIfTrue)[.otherwise(returnIfFalse)]"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"isNull",dsFuunctionType.NULL,"B","N",'F',"Boolean","isnull(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_ISBLANK,"IsBlank",dsFuunctionType.NULL,"B","N",'F',"variabletype","isblank(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_ASCIITONUM,"ascii",dsFuunctionType.NULL,"B","N",'F',"Integer","ascii(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"date_format",dsFuunctionType.NULL,"B","N",'F',"string","date_format(timestamp,fmt)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"cast_numstr",dsFuunctionType.NULL,"B","N",'F',"string","value.cast(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"cast_numdecimal",dsFuunctionType.NULL,"B","N",'F',"decimal","Value.cast(decimal)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"to_timestamp",dsFuunctionType.NULL,"B","N",'F',"Datetime","to_timestamp(timestamp[,fmt])"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"cast_strdec",dsFuunctionType.NULL,"B","N",'F',"decimal","value.cast(Decimal)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"cast_strfloat",dsFuunctionType.NULL,"B","N",'F',"float","value.cast(Float)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"cast_strint",dsFuunctionType.NULL,"B","N",'F',"Integer","value.cast(Integer)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"to_date",dsFuunctionType.NULL,"B","N",'F',"date","to_date(date_str[,fmt])"));
		pySparkRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"date_add",dsFuunctionType.NULL,"B","Y",'F',"date","date_add(start_date,num_days)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOTIMET,"unix_timestamp",dsFuunctionType.NULL,"B","N",'F',"Integer","unix_timestamp([expr[,pattern]])"));
		pySparkRules.add(new FnRule(dsFuunctionType.DT_DIFF,"",dsFuunctionType.NULL,"B","N",'F',"Integer","datediff(endDate,startDate)"));
		pySparkRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N",'F',"Date","date_format(timestamp,fmt)"));
		pySparkRules.add(new FnRule(dsFuunctionType.DT_ROUND,"round",dsFuunctionType.NULL,"B","N",'F',"Float","round(expr,d"));
		pySparkRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"date_trunc",dsFuunctionType.NULL,"B","N",'F',"date","value.format()"));
		pySparkRules.add(new FnRule(dsFuunctionType.GEN_EXP,"exp",dsFuunctionType.NULL,"B","N",'F',"Variabletype","exp(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.GEN_SURROGATEKEY,"monotonically_increasing_id()",dsFuunctionType.NULL,"B","N",'F',"integer","monotonically_increasing_id()"));
		pySparkRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N",'F',"Integer","abs(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N",'F',"Integer","ceil(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.NUM_CUME,"cume",dsFuunctionType.NULL,"B","N",'F',"string","cume()"));
		pySparkRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"floor",dsFuunctionType.NULL,"B","N",'F',"Integer","floor(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"round",dsFuunctionType.NULL,"B","N",'F',"Integer","round(expr,d)"));
		pySparkRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"cast_Int",dsFuunctionType.NULL,"B","N",'F',"Integer","value.cast(Integer)"));
		//pySparkRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"NullToZero",dsFuunctionType.NULL,"B","N"));
		//pySparkRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NullToValue",dsFuunctionType.NULL,"B","N",'F',));
		pySparkRules.add(new FnRule(dsFuunctionType.SP_DECODE,"strdecode",dsFuunctionType.NULL,"B","N",'F',"string","col.decode(value)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_CHAR,"exp",dsFuunctionType.NULL,"B","N",'F',"string","char(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"concat",dsFuunctionType.NULL,"B","N",'F',"string","concat(str1,str2[,...,strN])"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"translate",dsFuunctionType.NULL,"B","N",'F',"string","translate(input,from,to)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_FIELD,"field",dsFuunctionType.NULL,"B","N",'F',"string","split(str,pattern)[index]"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"initcap",dsFuunctionType.NULL,"B","N",'F',"string","initcap(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"lpad",dsFuunctionType.NULL,"B","N",'F',"string","lpad(col, len, pad)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"len",dsFuunctionType.NULL,"B","N",'F',"Integer","length(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"string","lower(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"ltrim",dsFuunctionType.NULL,"B","N",'F',"string","ltrim(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"regexp_replace",dsFuunctionType.NULL,"B","N",'F',"string","regexp_replace(str,regexp,rep)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N",'F',"variabletype","rpad(col, len, pad)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"rtrim",dsFuunctionType.NULL,"B","N",'F',"string","rtrim(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"locate",dsFuunctionType.NULL,"B","N",'F',"string","locate(substr,str[,pos])"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"string","substring(str,pos[,len]"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"String","trim(str)"));
		//pySparkRules.add(new FnRule(dsFuunctionType.STR_CHANGE,"regexp_replace",dsFunctionTypFe.NULL,"B","N"));
		pySparkRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"String","upper(str)"));
		pySparkRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"|",dsFuunctionType.NULL,"B","N",'F',"variableType","(expr1) | (expr2)"));
     	pySparkRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"&",dsFuunctionType.NULL,"B","N",'F',"variableType","(expr1) & (expr2)"));
		pySparkRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"",dsFuunctionType.NULL,"B","N",'F',"variableType","!(expr1) "));
		pySparkRules.add(new FnRule(dsFuunctionType.SET_NULL,"lit(None)",dsFuunctionType.NULL,"B","N",'F',"variableType","lit(None)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"isnotnull",dsFuunctionType.NULL,"B","N",'F',"Boolean","isnotnull(expr)"));
		pySparkRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"cast_bigint",dsFuunctionType.NULL,"B","N",'F',"Integer"," col.cast(bigint)"));
		pySparkRules.add(new FnRule(dsFuunctionType.MATH_MOD,"mod",dsFuunctionType.NULL,"B","N",'F',"Float","expr1 mod expr2"));
		pySparkRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"rlike",dsFuunctionType.NULL,"B","N",'F',"Boolean","str rlike regexp)"));
		pySparkRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"greatest",dsFuunctionType.NULL,"B","N",'F',"Integer","greatest(expr,..)"));
		pySparkRules.add(new FnRule(dsFuunctionType.MATH_POWER,"pow",dsFuunctionType.NULL,"B","N",'F',"Integer","pow(expr1,expr2)"));
		//pySparkRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"is_space",dsFuunctionType.NULL,"B","N",'F',"","value.str.strip()"));

		return pySparkRules;
	}

	public static List<FnRule> getMongoRules()
	{
		if(mongoFnTypeRules!=null)
			return mongoFnTypeRules;

		mongoFnTypeRules = new ArrayList<FnRule>();

		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"$avg:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"$max:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"$min:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"$sum:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"$dateToString:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"$toDecimal:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"$toInt:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"$abs:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"$ceil:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"$flooe:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"$trunc:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"$concat:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"$concat:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"$toLower:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"$LTRIM:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"$RTRIM:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"$substr:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"$trim:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"$toUpper:",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"dsOR",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"dsAnd",dsFuunctionType.NULL,"B","N"));
		mongoFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"dsNot",dsFuunctionType.NULL,"B","N"));

		return mongoFnTypeRules;
	}


	public static List<FnRule> getAuroraRules()
	{
		if(auroraFnTypeRules!=null)
			return auroraFnTypeRules;

		auroraFnTypeRules = new ArrayList<FnRule>();

		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR_SAMP",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"LAG",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"LEAD",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"LEAST",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"RANK",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VAR_SAMP",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATE,"SYSDATE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIME,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_DBTZ,"TIMEZONE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SESSTIMEZONE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.AUD_SYSDATETIMEOFFSET,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_IN,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_EXISTS,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"to_number",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"to_number",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"to_number",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"to_number",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"to_number",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"to_number",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TO_DATE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_RADTODEGREES,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_CAST,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_USTRINGTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CONV_UTCTOTIMESTAMP,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"DATE_ADD",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_CEIL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATE_DIFF",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_FLOOR,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"CURRENT_DATE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.DT_WIDTH_BUCKET,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.ENC_SOUNDEX,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"BIT_OR",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"BIT_AND",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"BIT_NOT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_XOR,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"CBRT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_DIV,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RANDOM",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_REMAINDER,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"POW",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POW",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_SINH,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_FORMAT,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_FALSE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_TRUE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"CHR",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LEN",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTRING",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LEFT_SUBSTR,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPARE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_COMPOSE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERSION,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTDENCODE,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERTCHRSET,"CONVERT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_DECOMPOSE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_DUMP,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_EQUALS,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_FIELD,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_HEX,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_NCHR,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_NOTEQUALS,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"REGEXP_EXTRACT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REPLICATE,"REPEAT",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_BYTE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS2,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UCS4,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SEARCH_UNICODE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPACE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT_PART",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.STR_VSIZE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.SET_NULL,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CASE_END,"",dsFuunctionType.NULL,"B","N"));
		auroraFnTypeRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N"));


		return auroraFnTypeRules;
	}

	public static List<FnRule> getApacheBeamPythonRules()
	{
		if(apacheBeamPythonFnTypRules!=null)
			return apacheBeamPythonFnTypRules;

		apacheBeamPythonFnTypRules = new ArrayList<FnRule>();

		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_LAST,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_MAX,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_MIN,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_SUM,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AUD_DATE,"datetime.datetime.now()",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AUD_TIME,"datetime.datetime.now()",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"datetime.datetime.now()",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"IS NULL",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"IS NOT NULL",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_DIFF,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.SP_DECODE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_CHAR,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"+",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"#var#.title()",dsFuunctionType.NULL,"B","Y"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_LOWER,"#var#.lower()",dsFuunctionType.NULL,"B","Y"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"#var#.lstrip()",dsFuunctionType.NULL,"B","Y"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"#var#.rstrip()",dsFuunctionType.NULL,"B","Y"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"index",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_TRIM,"#var#.strip()",dsFuunctionType.NULL,"B","Y"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.STR_UPPER,"#var#.upper()",dsFuunctionType.NULL,"B","Y"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"dsOR",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"dsAnd",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"dsNot",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.MATH_MOD,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.MATH_POWER,"",dsFuunctionType.NULL,"B","N"));
		apacheBeamPythonFnTypRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"",dsFuunctionType.NULL,"B","N"));


		return apacheBeamPythonFnTypRules;
	}


	public static List<FnRule> getSparkScalaRules()
	{	
		if(sparkScalaRules!=null)
			return sparkScalaRules;

		sparkScalaRules = new ArrayList<FnRule>();


		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"avg",dsFuunctionType.NULL,"B","N",'F',"double","avg(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"long","count(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"countDistinct",dsFuunctionType.NULL,"B","N",'F',"long","countDistinct(expr1[, expr2[,... ]])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"first",dsFuunctionType.NULL,"B","N",'F',"variableType","first(value[,ignoreNulls])"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"groupBy",dsFuunctionType.NULL,"B","N",'F',"variableType","groupBy(value1[,value1[,...])")));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_LAST,"last",dsFuunctionType.NULL,"B","N",'F',"variableType","last(value[, ignoreNulls])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"variableType","max(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"variableType","min(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"variableType","sum(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"variance",dsFuunctionType.NULL,"B","N",'F',"double","variance(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AUD_DATE,"current_timestamp()",dsFuunctionType.NULL,"B","N",'F',"timestamp","current_timestamp()"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"current_timestamp()",dsFuunctionType.NULL,"B","N",'F',"timestamp","current_timestamp()"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.AUD_UTCDATE,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_DATE,"isdate",dsFuunctionType.NULL,"B","N",'F',"boolean","to_date(value).cast(\"date\").isNotNull()"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"isnumber",dsFuunctionType.NULL,"B","N",'F',"boolean","value.cast(\"double\").isNotNull()"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_IF,"when",dsFuunctionType.NULL,"B","N",'F',"variableType","when(condition,returnIfTrue)[.otherwise(returnIfFalse)]"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_IN,"isin",dsFuunctionType.NULL,"B","N",'F',"boolean","value.isin(val1[,val2...])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"isnull",dsFuunctionType.NULL,"B","N",'F',"boolean","value.isNull"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_ISBLANK,"IsBlank",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_ASCIITONUM,"ascii",dsFuunctionType.NULL,"B","N",'F',"integer","ascii(value)"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"cast_String",dsFuunctionType.NULL,"B","N",'F',"string","value.cast(\"string\")"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"to_timestamp",dsFuunctionType.NULL,"B","N",'F',"timestamp","to_timestamp(value[,format])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"float","value.cast(\"float\")"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"cast_Int",dsFuunctionType.NULL,"B","N",'F',"integer","value.cast(\"int\")"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"to_date",dsFuunctionType.NULL,"B","N",'F',"date","to_date(value[,fmt)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"NullToZero",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_NULLTOVALUE,"NullToValue",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"date_add",dsFuunctionType.NULL,"B","Y",'F',"date","date_add(start,days)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.DT_DIFF,"datediff",dsFuunctionType.NULL,"B","N",'F',"integer","datediff(end,start)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.DT_FORMATDATE,"date_format",dsFuunctionType.NULL,"B","N",'F',"string","date_format(dateExpr,format)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.DT_FORMATDATEINUTC,"from_utc_timestamp",dsFuunctionType.NULL,"B","N",'F',"timestamp","from_utc_timestamp(ts,tzone)"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.DT_GET_FIRSTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.DT_GET_LASTDAYOFMONTH,"",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.DT_ROUND,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"date_trunc",dsFuunctionType.NULL,"B","N",'F',"timestamp","date_trunc(format,timestamp)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"md5",dsFuunctionType.NULL,"B","N",'F',"",	"md5(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.GEN_CONST,"",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.GEN_EXP,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.GEN_SURROGATEKEY,"monotonically_increasing_id()",dsFuunctionType.NULL,"B","N",'F',"integer","monotonically_increasing_id()"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N",'F',"variableType","abs(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N",'F',"variableType","ceil(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_CUME,"",dsFuunctionType.NULL,"B","N",'F',"variableType","abs(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"floor",dsFuunctionType.NULL,"B","N",'F',"variableType","floor(value)"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"round",dsFuunctionType.NULL,"B","N",'F',"double","round(value,scale)"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"trunc",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.SP_DECODE,"decode",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"",dsFuunctionType.NULL,"B","N"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.STR_CHAR,"char",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_STRING,"repeat",dsFuunctionType.NULL,"B","N",'F',"string","repeat(str,n)"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.STR_CHRCODE,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"concat",dsFuunctionType.NULL,"B","N",'F',"string","concat(str1[,str2[,...]])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"initcap",dsFuunctionType.NULL,"B","N",'F',"string","initcap(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"leftPad",dsFuunctionType.NULL,"B","N",'F',"string","lpad(str,len,  pad)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"length",dsFuunctionType.NULL,"B","N",'F',"string","length(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"string","lower(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"ltrim",dsFuunctionType.NULL,"B","N",'F',"string","ltrim(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"regexp_replace",dsFuunctionType.NULL,"B","N",'F',"string","regexp_replace(value, pattern, replacement)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N",'F',"string","rpad(str,len,  pad)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"rtrim",dsFuunctionType.NULL,"B","N",'F',"string","rtrim(value[,trimString])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"locate",dsFuunctionType.NULL,"B","N",'F',"string","locate(substr, str[, pos])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"string","substring_index(str,delim,count)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"string","trim(value[,trimString])"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"string","upper(value)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_FIELD,"field",dsFuunctionType.NULL,"B","N",'F',"string","split(str,pattern)[index]"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"translate",dsFuunctionType.NULL,"B","N",'F',"string","translate(src,matchingString,replaceString)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.STR_CHANGE,"regexp_replace",dsFuunctionType.NULL,"B","N",'F',"string","regexp_replace(value, pattern, replacement)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"or",dsFuunctionType.NULL,"B","N",'F',"boolean","exp1&exp2"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"&&",dsFuunctionType.NULL,"B","N",'F',"boolean","exp1&exp2"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"!",dsFuunctionType.NULL,"B","N",'F',"boolean","!exp"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CONV_TOBIGINT,"cast_bigint",dsFuunctionType.NULL,"B","N",'F',"bigint","value.cast(\"bigint\")"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.MATH_MOD,"mod",dsFuunctionType.NULL,"B","N",'F',"integer","value1.mod(value2)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"rlike",dsFuunctionType.NULL,"B","N",'F',"boolean","value.rlike(pattern)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.VAR_SETMAXVARIABLE,"greatest",dsFuunctionType.NULL,"B","N",'F',"variableType","greatest(exprs1[,expr2…])"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.VAR_SETVARIABLE,"",dsFuunctionType.NULL,"B","N"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.MATH_POWER,"pow",dsFuunctionType.NULL,"B","N",'F',"","pow(value1,value2)"));
		sparkScalaRules.add(new FnRule(dsFuunctionType.CHECK_SPACES,"is_space",dsFuunctionType.NULL,"B","N",'F',"boolean","value.rlike(\"^\\s*$\")"));
		//sparkScalaRules.add(new FnRule(dsFuunctionType.STR_CHANGE,"change",dsFuunctionType.NULL,"B","N"));

		return sparkScalaRules;

	}

	public static List<FnRule> getPostgreSQL()
	{	
		if(postgreSQLTypeRules != null)
			return postgreSQLTypeRules;

		postgreSQLTypeRules = new ArrayList<FnRule>();

		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"avg",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","AVG(ALL numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COUNT(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COUNT(DISTINCT  numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"first_value",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST_VALUE(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F',"variableType","GROUP BY Column[,...]"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_LAST,"last_value",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST_VALUE (value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SUM(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CUME_DIST()"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK ()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DENSE_RANK()"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","GREATEST(value [,...])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_LAG,"lag",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LAG(value [,offset [,default_value]])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAD,"lead",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LEAD(value [,offset [,default_value]])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"least",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LEAST(value [, ...])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","percentile_disc(0.5)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"percentile_count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","percentile_disc(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_RANK,"rank()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANK()"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"stddev",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"stddev_pop",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV_POP(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"stddev_samp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV_SAMP(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"var_samp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","VARIANCE(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"var_pop",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","VAR_POP(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"current_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"current_timestamp()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"current_time",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIME"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"current_date",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"local_time",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIME"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"local_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIMESTAMP"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IF condition THEN"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"",dsFuunctionType.NULL,"B","N",'F',"variableType","NULLIF(value1,value2)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"to_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TO_TIMESTAMP(timestamp, format)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"to_date",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE(text, format)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"to_char",dsFuunctionType.NULL,"B","N",'F',"STRING","to_char(timestamp, text)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"to_char",dsFuunctionType.NULL,"B","N",'F',"STRING","to_char(time, text)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"md5",dsFuunctionType.NULL,"B","N",'F',"STRING","md5(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","OR"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","AND"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ABS(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","CEIL(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"floor",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","FLOOR(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"exp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATER,">",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","EXP(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,">=",dsFuunctionType.NULL,"B","N",'F',"NUMERIC",">="));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSER,"<",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"<=",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<="));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_LOG,"log",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LOG(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"random()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANDOM()"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_SQUARE,"",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","POWER(numeric_value,2)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"sqrt",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SQRT(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.NUM_PI,"pi",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","PI()"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.MATH_MOD,"mod",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","MOD(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.MATH_POWER,"power",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","POWER(numeric_value,numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"acos",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ACOS(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"asin",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ASIN(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"atan",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ATAN(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"atan2",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ATAN2(value,value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_COS,"cos",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COS(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_SIN,"sin",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SIN(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SCI_TAN,"tan",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","TAN(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(value[,...])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"encode",dsFuunctionType.NULL,"B","N",'F',"STRING","encode(data bytea, format text)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.SP_DECODE,"decode",dsFuunctionType.NULL,"B","N",'F',"BYTEA","decode(string text, format text)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_CHAR,"chr",dsFuunctionType.NULL,"B","N",'F',"CHAR","CHR(numeric_value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"left",dsFuunctionType.NULL,"B","N",'F',"STRING","LPAD(value, numeric_value [, value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"bit_length",dsFuunctionType.NULL,"B","N",'F',"INT","char_length(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(value [, value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"STRING","replace(value, from value, to value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N",'F',"STRING","RPAD(value, numeric_value [, value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(value [, value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"STRING","substring(string from pattern)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"STRING","trim([leading | trailing | both] [characters] from string)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ascii",dsFuunctionType.NULL,"B","N",'F',"INT","ASCII(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_CONVERT,"convert",dsFuunctionType.NULL,"B","N",'F',"STRING","convert_from(string bytea, src_encoding name)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXMATCH,"regexp_matches",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_matches(value, value [, value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"regex_replace",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_replace(value, value, value [, value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"regexp_split_to_table",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_matches(value, value [,value])"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"reverse",dsFuunctionType.NULL,"B","N",'F',"STRING","reverse(value)"));
		postgreSQLTypeRules.add(new FnRule(dsFuunctionType.STR_POSITION,"strpos",dsFuunctionType.NULL,"B","N",'F',"INT","position(value in value)"));


		return postgreSQLTypeRules;
	}
	
	public static List<FnRule> getGreenPlum()
	{	
		if(greenPlum != null)
			return greenPlum;

		greenPlum = new ArrayList<FnRule>();

		greenPlum.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"avg",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","AVG(ALL numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_COUNT,"count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COUNT(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COUNT(DISTINCT  numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_FIRST,"first_value",dsFuunctionType.NULL,"B","N",'F',"variableType","FIRST_VALUE(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"",dsFuunctionType.NULL,"B","N",'F',"variableType","GROUP BY Column[,...]"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_LAST,"last_value",dsFuunctionType.NULL,"B","N",'F',"variableType","LAST_VALUE (value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_MAX,"max",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_MIN,"min",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SUM(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_CUMEDIST,"CUME_DIST()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CUME_DIST()"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DENSE_RANK ()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DENSE_RANK()"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","GREATEST(value [,...])"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_LAG,"lag",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LAG(value [,offset [,default_value]])"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_LEAD,"lead",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LEAD(value [,offset [,default_value]])"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_LEAST,"least",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LEAST(value [, ...])"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"MEDIAN",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","percentile_disc(0.5)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"percentile_count",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","percentile_disc(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_RANK,"rank()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANK()"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_STDDEV,"stddev",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"stddev_pop",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV_POP(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"stddev_samp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","STDDEV_SAMP(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"var_samp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","VARIANCE(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"var_pop",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","VAR_POP(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"current_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		greenPlum.add(new FnRule(dsFuunctionType.AUD_DATETIME,"current_timestamp()",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIMESTAMP"));
		greenPlum.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"current_time",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","CURRENT_TIME"));
		greenPlum.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"current_date",dsFuunctionType.NULL,"B","N",'F',"DATE","CURRENT_DATE"));
		greenPlum.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"local_time",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIME"));
		greenPlum.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"local_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","LOCALTIMESTAMP"));
		greenPlum.add(new FnRule(dsFuunctionType.CHECK_IF,"",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IF condition THEN"));
		greenPlum.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"",dsFuunctionType.NULL,"B","N",'F',"variableType","NULLIF(value1,value2)"));
		greenPlum.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"to_timestamp",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TO_TIMESTAMP(timestamp, format)"));
		greenPlum.add(new FnRule(dsFuunctionType.CONV_TODATE,"to_date",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE(text, format)"));
		greenPlum.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"to_char",dsFuunctionType.NULL,"B","N",'F',"STRING","to_char(timestamp, text)"));
		greenPlum.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"to_char",dsFuunctionType.NULL,"B","N",'F',"STRING","to_char(time, text)"));
		greenPlum.add(new FnRule(dsFuunctionType.ENCYP_MD5,"md5",dsFuunctionType.NULL,"B","N",'F',"STRING","md5(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.LOGICAL_OR,"OR",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","OR"));
		greenPlum.add(new FnRule(dsFuunctionType.LOGICAL_AND,"AND",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","AND"));
		greenPlum.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"NOT",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","NOT"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"abs",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ABS(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_CEIL,"ceil",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","CEIL(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_FLOOR,"floor",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","FLOOR(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"exp",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_GREATER,">",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","EXP(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_GREATEROREQUAL,">=",dsFuunctionType.NULL,"B","N",'F',"NUMERIC",">="));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_LESSER,"<",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_LESSEROREQUAL,"<=",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","<="));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_LOG,"log",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","LOG(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_RANDOM,"random()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANDOM()"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_SQUARE,"",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","POWER(numeric_value,2)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_SQRT,"sqrt",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SQRT(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.NUM_PI,"pi",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","PI()"));
		greenPlum.add(new FnRule(dsFuunctionType.MATH_MOD,"mod",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","MOD(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.MATH_POWER,"power",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","POWER(numeric_value,numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_ACOS,"acos",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ACOS(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_ASIN,"asin",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ASIN(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_ATAN,"atan",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ATAN(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_ATAN2,"atan2",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ATAN2(value,value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_COS,"cos",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","COS(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_SIN,"sin",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","SIN(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SCI_TAN,"tan",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","TAN(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(value[,...])"));
		greenPlum.add(new FnRule(dsFuunctionType.SP_ENCODE,"encode",dsFuunctionType.NULL,"B","N",'F',"STRING","encode(data bytea, format text)"));
		greenPlum.add(new FnRule(dsFuunctionType.SP_DECODE,"decode",dsFuunctionType.NULL,"B","N",'F',"BYTEA","decode(string text, format text)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_CHAR,"chr",dsFuunctionType.NULL,"B","N",'F',"CHAR","CHR(numeric_value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"left",dsFuunctionType.NULL,"B","N",'F',"STRING","LPAD(value, numeric_value [, value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_LENGTH,"bit_length",dsFuunctionType.NULL,"B","N",'F',"INT","char_length(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(value [, value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"STRING","replace(value, from value, to value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"rpad",dsFuunctionType.NULL,"B","N",'F',"STRING","RPAD(value, numeric_value [, value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(value [, value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_SUBSTR,"substring",dsFuunctionType.NULL,"B","N",'F',"STRING","substring(string from pattern)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_TRIM,"trim",dsFuunctionType.NULL,"B","N",'F',"STRING","trim([leading | trailing | both] [characters] from string)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_ASCII,"ascii",dsFuunctionType.NULL,"B","N",'F',"INT","ASCII(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_CONVERT,"convert",dsFuunctionType.NULL,"B","N",'F',"STRING","convert_from(string bytea, src_encoding name)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_REGEXMATCH,"regexp_matches",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_matches(value, value [, value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"regex_replace",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_replace(value, value, value [, value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_REGEXSPLIT,"regexp_split_to_table",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_matches(value, value [,value])"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_REVERSE,"reverse",dsFuunctionType.NULL,"B","N",'F',"STRING","reverse(value)"));
		greenPlum.add(new FnRule(dsFuunctionType.STR_POSITION,"strpos",dsFuunctionType.NULL,"B","N",'F',"INT","position(value in value)"));


		return greenPlum;
	}


	

	public static List<FnRule> getImpalaRules()
	{	
		if(impalaRules != null)
			return impalaRules;

		impalaRules = new ArrayList<FnRule>();
		impalaRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"variableType","AVG(column_name)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","COUNT(All column_name)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","COUNT( column_name)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(column_name)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(column_name)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(column_name)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"APPX_MEDIAN",dsFuunctionType.NULL,"B","N",'F',"variableType","APPX_MEDIAN([DISTINCT | ALL] expression)"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV ( expression )"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV_POP ( expression )"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STSDEV_SAMP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","STDDEV_SAMP( expression )"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"variance",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","VARIANCE ( expression )"));
		impalaRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"variance_pop",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","VAR_POP ( expression )"));
		impalaRules.add(new FnRule(dsFuunctionType.CHECK_IF,"IF",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","IF(BOOLEAN condition, type ifTrue, type ifFalseOrNull)"));
		impalaRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"nullif",dsFuunctionType.NULL,"B","N",'F',"variableType","NULLIF(type expr1, type expr2)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_ADDTODATE,"ADDDATE",dsFuunctionType.NULL,"B","N",'F',"variableType","ADDDATE(TIMESTAMP / DATE date, INT / BIGINT days)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_ADDDAYS,"DAYS_ADD",dsFuunctionType.NULL,"B","N",'F',"variableType","DAYS_ADD(TIMESTAMP / DATE date, INT / BIGINT days)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N",'F',"variableType","ADD_MONTHS(TIMESTAMP / DATE date, INT months)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DATEDIFF(TIMESTAMP / DATE enddate, TIMESTAMP / DATE startdate)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"HOUR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","HOUR(TIMESTAMP ts)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"MINUTE",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MINUTE(TIMESTAMP date)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MONTH(TIMESTAMP / DATE date)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","MONTHS_BETWEEN(TIMESTAMP / DATE enddate, TIMESTAMP / DATE startdate)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"NEXT_DAY",dsFuunctionType.NULL,"B","N",'F',"variableType","NEXT_DAY(TIMESTAMP / DATE date, STRING weekday)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"QUARTER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","QUARTER(TIMESTAMP / DATE date)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"SECOND",dsFuunctionType.NULL,"B","N",'F',"INTEGER","SECOND(TIMESTAMP date)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"YEAR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","YEAR(TIMESTAMP / DATE date)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"DATE_TRUNC",dsFuunctionType.NULL,"B","N",'F',"variableType","DATE_TRUNC(STRING unit, TIMESTAMP / DATE ts)"));
		impalaRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","EXTRACT(TIMESTAMP / DATE ts, STRING unit)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ABS(numeric_type a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"",dsFuunctionType.NULL,"B","N",'F',"variableType","CEIL(DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"",dsFuunctionType.NULL,"B","N",'F',"variableType","FLOOR(DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"variableType","ROUND(DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","EXP(DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"",dsFuunctionType.NULL,"B","N",'F',"BIGINT","FACTORIAL(integer_type a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","LN(DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","LOG(DOUBLE base, DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"PMOD()",dsFuunctionType.NULL,"B","N",'F',"variableType","PMOD(BIGINT a, BIGINT b)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RANDOM()"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"INTEGER","SIGN ( DOUBLE a )"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SQRT(DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.NUM_PI,"PI()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","PI()"));
		impalaRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"variableType","MOD( DOUBLE a, DOUBLE b )"));
		impalaRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POW",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","POWER ( DOUBLE a, DOUBLE b )"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ACOS ( DOUBLE a )"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ASIN ( DOUBLE a )"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ATAN ( DOUBLE a )"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ATAN2 ( DOUBLE a, DOUBLE b )"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COS (  DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COSH (  DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COT (  DOUBLE a))"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","DEGREES (DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RADIANS (DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SIN (DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SINH (DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","TAN (DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","TANH (DOUBLE a)"));
		impalaRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE(type v1, type v2, ...)"));
		impalaRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"variableType","DECODE(type expression, type search1, type result1 [, type search2, type result2 ...] [, type default] )"));
		impalaRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","NTILE (expr [, offset ...] OVER ([partition_by_clause] order_by_clause)"));
		impalaRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ROW_NUMBER() OVER([partition_by_clause] order_by_clause)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","CHR ( string a)"));
	    impalaRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT(STRING a, STRING b...)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"STRING","INITCAP(STRING str)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","LPAD(STRING str, INT len, STRING pad)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","LENGTH(STRING a)"));
	    impalaRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"STRING","LOWER(STRING a)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","LTRIM(STRING a [, STRING chars_to_trim])"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REPLACE(STRING initial, STRING target, STRING replacement)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","RPAD(STRING str, INT len, STRING pad)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","RTRIM(STRING a [, STRING chars_to_trim])"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","SUBSTR(STRING a, INT start [, INT len])"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","TRIM(STRING a)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"STRING","UPPER(STRING a)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ASCII(STRING str)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_HEX,"",dsFuunctionType.NULL,"B","N",'F',"INTEGER","HEX(STRING a)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"REGEXP_EXTRACT",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_EXTRACT(STRING subject, STRING pattern, INT index)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_REPLACE(STRING initial, STRING pattern, STRING replacement)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N",'F',"STRING","REVERSE(STRING a)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N",'F',"INTEGER","SPACE(INT n)"));
		impalaRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT_PART",dsFuunctionType.NULL,"B","N",'F',"STRING","SPLIT_PART(STRING source, STRING delimiter, BIGINT index)"));
		impalaRules.add(new FnRule(dsFuunctionType.CASE_END,"",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE a WHEN b THEN c [WHEN d THEN e]... [ELSE f] END"));
		
		return impalaRules;
	}

	public static List<FnRule> getHiveRules()
	{	
		if(hiveRules != null)
			return hiveRules;

		hiveRules = new ArrayList<FnRule>();
		hiveRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","AVG(column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","COUNT(All column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","COUNT(DISTINCT column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_GROUPBY,"GROUP BY",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","group by col1,col2,...,colN"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","MAX( column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","MIN(column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SUM( column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","greatest(T v1, T v2, ...)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_LEAST,"LEAST",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","least(T v1, T v2, ...)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_PERCENTILE,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","percentile(BIGINT col, p)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","stddev_pop(col)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STSDEV_SAMP",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","stddev_samp(col)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"variance",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","variance(col)"));
		hiveRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"variance_pop",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","var_pop(col)"));
		hiveRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"ISNOTNULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","isnotnull ( a )"));
		hiveRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"ISNULL",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","isnull( a )"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INTEGER","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","COUNT(All column_name)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"CAST",dsFuunctionType.NULL,"B","N",'F',"INTEGER","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"CAST",dsFuunctionType.NULL,"B","N",'F',"STRING","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DATE","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"CAST",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"CAST",dsFuunctionType.NULL,"B","N",'F',"FLOAT","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"INTEGER","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTOBIGINT,"CAST",dsFuunctionType.NULL,"B","N",'F',"BIGINT","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTOBOOLEAN,"CAST",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"CAST",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTOLONG,"",dsFuunctionType.NULL,"B","N",'F',"LONG","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"",dsFuunctionType.NULL,"B","N",'F',"TIME","cast(expr as <type>)"));
		hiveRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"",dsFuunctionType.NULL,"B","N",'F',"STRING","last_day(string date)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","abs(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"",dsFuunctionType.NULL,"B","N",'F',"BIGINT","CEIL(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"",dsFuunctionType.NULL,"B","N",'F',"BIGINT","FLOOR(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"BIGINT","round(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","cbrt(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","covar_pop(col1, col2)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","covar_samp(col1, col2)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_FACTORIAL,"",dsFuunctionType.NULL,"B","N",'F',"BIGINT","factorial(INT a)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ln(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","log10(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_PERCENTILE,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","percentile(BIGINT col, p)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_PMOD,"PMOD()",dsFuunctionType.NULL,"B","N",'F',"INTEGER","pmod(INT a, INT b)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RAND()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","rand()"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","sign(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","sqrt(num)"));
		hiveRules.add(new FnRule(dsFuunctionType.NUM_PI,"PI()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ACOS(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POW",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","pow(DOUBLE a, DOUBLE p)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ACOS(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ASIN(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ATAN(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COS(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","DEGREE(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RADIANS(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SIN(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","tan(DOUBLE a)"));
		hiveRules.add(new FnRule(dsFuunctionType.SP_ENCODE,"",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","encode(string src, string charset)"));
		hiveRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"STRING","decode(binary bin, string charset)"));
		hiveRules.add(new FnRule(dsFuunctionType.SP_NTILE,"NTILE",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ntile(INTEGER x)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","chr(bigint|double A)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","concat(str1, str2, ...)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"STRING","initcap(string A)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","lpad(string str,int len,string pad)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","length(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"STRING","lower(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","ltrim(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","rpad(string str,int len,string pad)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"STRING","rpad(string str,int len,string pad)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","rtrim(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"STRING","substr(str, int start, int length)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","trim(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"STRING","upper(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ASCII(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_CONCATWITHSEP,"CONCAT_WS",dsFuunctionType.NULL,"B","N",'F',"STRING","concat_ws(string SEP, string A, string B...)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_FIELD,"FIND_IN_SET",dsFuunctionType.NULL,"B","N",'F',"INTEGER","field(val T,val1 T,val2 T,val3 T,...)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_HEX,"",dsFuunctionType.NULL,"B","N",'F',"STRING","hex(BIGINT a)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_REGEXEXTRACT,"REGEXP_EXTRACT",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_replace(string INITIAL_STRING, string PATTERN, string REPLACEMENT)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","regexp_replace(string INITIAL_STRING, string PATTERN, string REPLACEMENT)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_REVERSE,"REVERSE",dsFuunctionType.NULL,"B","N",'F',"STRING","reverse(str)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N",'F',"STRING","space(int)"));
		hiveRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT_PART",dsFuunctionType.NULL,"B","N",'F',"array","split(string str, string pat)"));
		hiveRules.add(new FnRule(dsFuunctionType.CASE_END,"",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE a WHEN b THEN c [WHEN d THEN e]* [ELSE f] END"));



		return hiveRules;
	}

	public static List<FnRule> getVerticaRules()
	{
		if(verticaRules!=null)
			return verticaRules;

		verticaRules = new ArrayList<FnRule>();


		verticaRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"AVG",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","AVG(column_name)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"COUNT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","COUNT(All column_name)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"APPROXIMATE_COUNT_DISTINCT",dsFuunctionType.NULL,"B","N",'F',"BIGINT","COUNT(DISTINCT column_name)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","MAX(column_name)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","MIN(column_name)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","SUM(column_name)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_GREATEST,"GREATEST",dsFuunctionType.NULL,"B","N",'F',"variableType","GREATEST(expression1,expression2,....expression-n)"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"STDDEV",dsFuunctionType.NULL,"B","N",'F',"variableType","STDDEV ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_STDDEVPOPULATION,"STDDEV_POP",dsFuunctionType.NULL,"B","N",'F',"variableType","STDDEV_POP ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_STDDEVSAMPLE,"STDDEV_SAMP",dsFuunctionType.NULL,"B","N",'F',"variableType","STDDEV_SAMP ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"VARIANCE",dsFuunctionType.NULL,"B","N",'F',"variableType","VARIANCE ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.AGG_VARIANCEPOPULATION,"VAR_POP",dsFuunctionType.NULL,"B","N",'F',"variableType","VAR_POP ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.CHECK_NULLIF,"NULLIF",dsFuunctionType.NULL,"B","N",'F',"variableType","NULLIF( expression1, expression2 )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_ASCIITONUM,"ASCII",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","TO_CHAR ( expression [, pattern ]  )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_DATETOINT,"",dsFuunctionType.NULL,"B","N",'F',"INTEGER","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","TO_CHAR ( expression [, pattern ]  )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_NUMTODEC,"",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"",dsFuunctionType.NULL,"B","N",'F',"FLOAT","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"HEX_TO_INTEGER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"",dsFuunctionType.NULL,"B","N",'F',"FLOAT","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_DECTOSTRING,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","TO_CHAR ( expression [, pattern ]  )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"TO_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE ( expression , pattern )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_STRTODEC,"TO_NUMBER",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"",dsFuunctionType.NULL,"B","N",'F',"FLOAT","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"TO_NUMBER",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","TO_NUMBER ( expression, [ pattern ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIMESTAMP,"TO_TIMESTAMP ",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TO_TIMESTAMP ( expression, pattern )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"TO_DATE",dsFuunctionType.NULL,"B","N",'F',"DATE","TO_DATE ( expression , pattern )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_NULLTOZERO,"ZEROIFNULL",dsFuunctionType.NULL,"B","N",'F',"NULL","ZEROIFNULL(expression)"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_TIMESTAMPTOSTRING,"TO_CHAR ",dsFuunctionType.NULL,"B","N",'F',"STRING","TO_CHAR ( expression [, pattern ]  )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_TIMETOSTRING,"TO_CHAR ",dsFuunctionType.NULL,"B","N",'F',"STRING","TO_CHAR ( expression [, pattern ]  )"));
		verticaRules.add(new FnRule(dsFuunctionType.CONV_INTERVALTOSTRING,"TO_CHAR",dsFuunctionType.NULL,"B","N",'F',"STRING","TO_CHAR ( expression [, pattern ]  )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_ADDMONTHSTODATE,"ADD_MONTHS",dsFuunctionType.NULL,"B","N",'F',"DATE","ADD_MONTHS ( start-date, num-months );"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_DIFF,"DATEDIFF",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DATEDIFF ( datepart, start, end );"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_DATE,"DATE_PART",dsFuunctionType.NULL,"B","N",'F',"DATE","GETDATE()"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_HOURS_FROM_TIME,"HOUR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","HOUR( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"MICROSECOND",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MICROSECOND ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_MIDNIGHT_SECONDS,"MIDNIGHT_SECONDS",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MIDNIGHT_SECONDS ( date )"));
		//verticaRules.add(new FnRule(dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME_DEPRECATED","MICROSECOND",dsFuunctionType.DT_GET_MICRO_SEC_FROM_TIME,"S","N"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_MINS_FROM_TIME,"MINUTE",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MINUTE ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_MONTH_FROM_DATE,"MONTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MONTH ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_DAY_FROM_DATE,"DAY",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DAY ( value )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_MONTHS_BETWEEN,"MONTHS_BETWEEN",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MONTHS_BETWEEN ( date1 , date2 );"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_NEXT_DAY_FROM_DATE,"NEXT_DAY",dsFuunctionType.NULL,"B","N",'F',"DATE","NEXT_DAY( 'date', 'day-string')"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"QUARTER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","QUARTER ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_SECS_FROM_TIME,"SECOND",dsFuunctionType.NULL,"B","N",'F',"INTEGER","SECOND ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_WEEKDAY_FROM_DATE,"DAYOFWEEK",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DAYOFWEEK ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_YEARDAY_FROM_DATE,"DAYOFYEAR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DAYOFYEAR ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"YEAR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","YEAR( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_LAST_DAY,"LAST_DAY",dsFuunctionType.NULL,"B","N",'F',"DATE","LAST_DAY ( date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_NEW_TIME,"NEW_TIME",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","NEW_TIME( 'timestamp' , 'timezone1' , 'timezone2')"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_SET_DATE_PART,"DATE_PART",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DATE_PART ( 'field', date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"DAY PRECISION","ROUND( rounding-target[, 'precision'] )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_TRUNC,"TRUNC",dsFuunctionType.NULL,"B","N",'F',"DAY PRECISION","TRUNC( trunc-target[, 'precision'] )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_EXTRACT,"EXTRACT",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","EXTRACT ( field FROM date )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_OVERLAPS,"OVERLAPS",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","( start, end ) OVERLAPS ( start, end )" ));
		verticaRules.add(new FnRule(dsFuunctionType.ENCYP_MD5,"MD5",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","MD5 ( string )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"ABS",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ABS ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"CEIL",dsFuunctionType.NULL,"B","N",'F',"INTEGER","CEIL ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"FLOOR",dsFuunctionType.NULL,"B","N",'F',"INTEGER","FLOOR ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_MOVINGAVG,"",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","Avg(exp)over([win]..[win]..)"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_MOVINGSUM,"",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","sum(exp)over([win]..[win])"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ROUND ( expression [ , places ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"TRUNC ",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","TRUNC ( expression [ , places ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_CBRT,"CBRT ",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","CBRT ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCEPOPULATION,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COVAR_POP ( expression1, expression2 )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_COVARIANCESAMPLE,"",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SELECT COVAR_SAMP ( expression1, expression2 )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_EXPONENT,"EXP",dsFuunctionType.NULL,"B","N",'F',"INTEGER","EXP ( exponent )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_LN,"LN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","LN ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_LOG,"LOG",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","LOG ( [ base, ] expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"RANDOM()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RANDOM()"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_SIGN,"SIGN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SIGN ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_SQRT,"SQRT ",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SQRT ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.NUM_PI,"PI()",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","PI()"));
		verticaRules.add(new FnRule(dsFuunctionType.MATH_MOD,"MOD",dsFuunctionType.NULL,"B","N",'F',"INTEGER","MOD( expression1, expression2 )"));
		verticaRules.add(new FnRule(dsFuunctionType.MATH_POWER,"POWER",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","POWER( expression1, expression2 )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_ACOS,"ACOS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ACOS ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_ASIN,"ASIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ASIN ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_ATAN,"ATAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ATAN ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_ATAN2,"ATAN2",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","ATAN2 ( quotient, divisor )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_COS,"COS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COS ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_COSH,"COSH",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COSH ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_COT,"COT",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","COT ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_DEGREES,"DEGREES",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","DEGREES (radians)"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_RADIANS,"RADIANS",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","RADIANS (degrees [, minutes, seconds])"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_SIN,"SIN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SIN ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_SINH,"SINH",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","SINH ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_TAN,"TAN",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","TAN ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_TANH,"TANH",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","TANH ( expression )"));
        verticaRules.add(new FnRule(dsFuunctionType.SP_COALESCE,"COALESCE",dsFuunctionType.NULL,"B","N",'F',"variableType","COALESCE ( expression[,…] );"));
		verticaRules.add(new FnRule(dsFuunctionType.SP_DECODE,"DECODE",dsFuunctionType.NULL,"B","N",'F',"variableType","DECODE ( expression, search, result [ , search, result ]...[, default ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.SP_ROWNUM,"ROW_NUMBER()",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","NTILE ( constant-value ) OVER ([win]..[win])"));
		verticaRules.add(new FnRule(dsFuunctionType.SP_OVER,"OVER",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","ROW_NUMBER ( ) OVER ( [win]..[win]..)"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_CHAR,"CHR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","CHR ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"CONCAT",dsFuunctionType.NULL,"B","N",'F',"STRING","CONCAT ('string1','string2')"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"INITCAP",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","INITCAP ( expression ) "));
		verticaRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LPAD",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LPAD ( expression , length [ , fill ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"LENGTH",dsFuunctionType.NULL,"B","N",'F',"INTEGER","LENGTH ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_LOWER,"LOWER",dsFuunctionType.NULL,"B","N",'F',"INTEGER","LOWER ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"LTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","LTRIM ( expression [ , characters ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REPLACE ('string', 'target', 'replacement' ) )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RPAD",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","RPAD ( expression , length [ , fill ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"RTRIM",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","RTRIM ( expression [ , characters ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"SUBSTR",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","SUBSTR ( string , position [ , extent ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_TRIM,"TRIM",dsFuunctionType.NULL,"B","N",'F',"STRING","TRIM ( [ [ LEADING | TRAILING | BOTH ] characters FROM ] expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_UPPER,"UPPER",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","UPPER ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_ASCII,"ASCII",dsFuunctionType.NULL,"B","N",'F',"INTEGER","ASCII ( expression ) "));
		verticaRules.add(new FnRule(dsFuunctionType.STR_REGEXPLIKE,"REGEXP_LIKE",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","REGEXP_LIKE( string, pattern[,  modifiers ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_REGEXCOUNT,"REGEXP_COUNT",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","REGEXP_COUNT( string, pattern [, position [, regexp_modifier ] ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_REGEXREPLACE,"REGEXP_REPLACE",dsFuunctionType.NULL,"B","N",'F',"STRING","REGEXP_REPLACE( string, target [, replacement [, position [, occurrence ... [, regexp_modifiers ] ] ] ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_REGEXSUBSTR,"REGEXP_SUBSTR",dsFuunctionType.NULL,"B","N",'F',"variableType","REGEXP_SUBSTR( string, pattern [, position [,  occurrence  [, regexp_modifier...  [, captured_subexp ] ] ] ])"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_POSITION,"POSITION ",dsFuunctionType.NULL,"B","N",'F',"INTEGER","POSITION ( substring IN string [ USING { CHARACTERS | OCTETS } ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_SPACE,"SPACE",dsFuunctionType.NULL,"B","N",'F',"INTEGER","SPACE(n)"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_SPLIT,"SPLIT_PART",dsFuunctionType.NULL,"B","N",'F',"STRING","SPLIT_PART ( string , delimiter , field )"));
		verticaRules.add(new FnRule(dsFuunctionType.CASE_END,"",dsFuunctionType.NULL,"B","N",'F',"variableType","CASE when condition then result … end"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_DISTANCE,"DISTANCE ",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DISTANCE ( lat0, lon0, lat1, lon1, radius_of_curvature )"));
		verticaRules.add(new FnRule(dsFuunctionType.SCI_DISTANCE_V,"DISTANCEV ",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DISTANCEV (lat0, lon0, lat1, lon1)"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_COLLATION,"COLLATION",dsFuunctionType.NULL,"B","N",'F',"variableType","COLLATION ( 'expression' [ , 'locale_or_collation_name' ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_INET_ATON,"INET_ATON",dsFuunctionType.NULL,"B","N",'F',"INTEGER","INET_ATON ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_INET_NTOA,"INET_NTOA",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","INET_NTOA ( expression )"));
		verticaRules.add(new FnRule(dsFuunctionType.STR_INSERT,"INSERT",dsFuunctionType.NULL,"B","N",'F',"VARCHAR","INSERT( 'string1', n, m, 'string2' )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_TIMEOFDAY,"TIMEOFDAY()",dsFuunctionType.NULL,"B","N",'F',"TIME","TIMEOFDAY()"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_TIME_SLICE,"TIME_SLICE",dsFuunctionType.NULL,"B","N",'F',"TIMESTAMP","TIME_SLICE( expression, slice-length [, 'time-unit' [, 'start-or-end' ] ] )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_TIMESTAMP_ROUND,"TIMESTAMP_ROUND",dsFuunctionType.NULL,"B","N",'F',"DAY PRECISION","TIMESTAMP_ROUND ( rounding-target[, 'precision'] )"));
		verticaRules.add(new FnRule(dsFuunctionType.DT_DATE_TRUNC,"DATE_TRUNC",dsFuunctionType.NULL,"B","N",'F',"DAY PRECISION","DATE_TRUNC( precision, trunc-target )"));
		

		

		return verticaRules;
	}
	public static List<FnRule> getPythonRules()
	{
		if(pythonRules!=null)
			return pythonRules;

		pythonRules = new ArrayList<FnRule>();


		pythonRules.add(new FnRule(dsFuunctionType.AGG_AVERAGE,"mean",dsFuunctionType.NULL,"B","N",'F',"float"," value.mean()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_COUNT,"Count",dsFuunctionType.NULL,"B","N",'F',"int32","value.count()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_COUNTDISTINCT,"CountDistinct",dsFuunctionType.NULL,"B","N",'F',"int","value.nunique()()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_FIRST,"FirstValue",dsFuunctionType.NULL,"B","N",'F',"variableType","value.iloc[0]"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_MAX,"Max",dsFuunctionType.NULL,"B","N",'F',"Integer","value.max()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_MIN,"Min",dsFuunctionType.NULL,"B","N",'F',"Integer","value.min()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_SUM,"sum",dsFuunctionType.NULL,"B","N",'F',"Integer","value.sum()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_DENSERANK,"DenseRank",dsFuunctionType.NULL,"B","N",'F',"variableType","value.rank(method='dense')"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_MEDIAN,"Median",dsFuunctionType.NULL,"B","N",'F',"float","value.median()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_RANK,"Rank",dsFuunctionType.NULL,"B","N",'F',"float","value.rank()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_STDDEV,"StdDev",dsFuunctionType.NULL,"B","N",'F',"float","value.std()"));
		pythonRules.add(new FnRule(dsFuunctionType.AGG_VARIANCE,"Variance",dsFuunctionType.NULL,"B","N",'F',"float","value.var()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_TIMESTAMP,"pd.Timestamp.now()",dsFuunctionType.NULL,"B","N",'F',"datetime","pd.Timestamp.now()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIMESTAMP,"pd.Timestamp.now()",dsFuunctionType.NULL,"B","N",'F',"datetime","pd.Timestamp.now()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_DATE,"dt.datetime.now()",dsFuunctionType.NULL,"B","N",'F',"datetime","dt.datetime.now()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_DATETIME,"dt.datetime.now()",dsFuunctionType.NULL,"B","N",'F',"datetime","dt.datetime.now()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_CURRENTTIME,"pd.datetime.now().time()",dsFuunctionType.NULL,"B","N",'F',"datetime","pd.datetime.now().time()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_CURRENTDATE,"dt.datetime.now().date()",dsFuunctionType.NULL,"B","N",'F',"datetime","dt.datetime.now().date()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIME,"pd.datetime.now().time()",dsFuunctionType.NULL,"B","N",'F',"datetime","pd.datetime.now().time()"));
		pythonRules.add(new FnRule(dsFuunctionType.AUD_LOCALTIMESTAMP,"pd.datetime.now().time()",dsFuunctionType.NULL,"B","N",'F',"datetime","pd.datetime.now().time()"));
		pythonRules.add(new FnRule(dsFuunctionType.ADD_SECONDSTOTIMESTAMP,"AddSecToTs",dsFuunctionType.NULL,"B","N",'F',"datetime","value +pd.Timedelta(seconds=<secs>)"));
		pythonRules.add(new FnRule(dsFuunctionType.CHECK_NUM,"CheckNum",dsFuunctionType.NULL,"B","N",'F',"bool","str.isnumeric()"));
        pythonRules.add(new FnRule(dsFuunctionType.CHECK_IN,"In",dsFuunctionType.NULL,"B","N",'F',"variabletype","value.isin({value1[,value2..]})"));
		pythonRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"notnull",dsFuunctionType.NULL,"B","N",'F',"variabletype","notnull()"));
		pythonRules.add(new FnRule(dsFuunctionType.CHECK_NULL,"isnull",dsFuunctionType.NULL,"B","N",'F',"variabletype","value.isnull()"));
		pythonRules.add(new FnRule(dsFuunctionType.CHECK_REGMATCH,"RegMatch",dsFuunctionType.NULL,"B","N",'F',"bool","value.str.match(pattern)"));
		pythonRules.add(new FnRule(dsFuunctionType.CONV_DATETOSTR,"strftime",dsFuunctionType.NULL,"B","N",'F',"","value.strftime(format)"));
	    pythonRules.add(new FnRule(dsFuunctionType.CONV_NUMTOSTR,"cast_string",dsFuunctionType.NULL,"B","N",'F',"string","value.astype('str')"));
		pythonRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"NumToFloat",dsFuunctionType.NULL,"B","N",'F',"float","value.astype('float')"));
		pythonRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"DecToFloat",dsFuunctionType.NULL,"B","N",'F',"float","value.astype('float')"));
	    pythonRules.add(new FnRule(dsFuunctionType.CONV_STRTOFLOAT,"StrToFloat",dsFuunctionType.NULL,"B","N",'F',"float",".astype('float')"));
		pythonRules.add(new FnRule(dsFuunctionType.CONV_STRTOINT,"cast_StrToInt",dsFuunctionType.NULL,"B","N",'F',"int",".astype('string')"));
		pythonRules.add(new FnRule(dsFuunctionType.CONV_STRTODOUBLE,"StrToDouble",dsFuunctionType.NULL,"B","N",'F',"float",".astype('double')"));
	    pythonRules.add(new FnRule(dsFuunctionType.DT_GET_QUARTER,"GetQuarter",dsFuunctionType.NULL,"B","N",'F',"int","value.dt.quarter"));
		pythonRules.add(new FnRule(dsFuunctionType.DT_GET_YEAR_FROM_DATE,"GetYearFromDate",dsFuunctionType.NULL,"B","N",'F',"float","value.dt.year"));
		pythonRules.add(new FnRule(dsFuunctionType.DT_ROUND,"Round",dsFuunctionType.NULL,"B","N",'F',"datetime","value.dt.round('format')"));
		pythonRules.add(new FnRule(dsFuunctionType.LOGICAL_OR,"|",dsFuunctionType.NULL,"B","N",'F',"variableType","(exp1)|(exp2)"));
		pythonRules.add(new FnRule(dsFuunctionType.LOGICAL_AND,"&",dsFuunctionType.NULL,"B","N",'F',"variableType","(exp1)&(exp2)"));
		pythonRules.add(new FnRule(dsFuunctionType.LOGICAL_NOT,"not",dsFuunctionType.NULL,"B","N",'F',"variableType","~(exp))"));
		pythonRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"Absolute",dsFuunctionType.NULL,"B","N",'F',"int64","value.abs()"));
		pythonRules.add(new FnRule(dsFuunctionType.NUM_CEIL,"NumCeil",dsFuunctionType.NULL,"B","N",'F',"float64","value.apply(np.ceil)"));
		pythonRules.add(new FnRule(dsFuunctionType.NUM_FLOOR,"Floor",dsFuunctionType.NULL,"B","N",'F',"float64","value.apply(np.floor)"));
		pythonRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"round",dsFuunctionType.NULL,"B","N",'F',"float","value.round(decimals)"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_STRING,"repeat",dsFuunctionType.NULL,"B","N",'F',"object","value.str.repeat(num)"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"+",dsFuunctionType.NULL,"B","N",'F',"string","value1+value2[+..]"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_INITCAP,"InitCap",dsFuunctionType.NULL,"B","N",'F',"string","value.str.capitalize()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_LEFTPAD,"LeftPad",dsFuunctionType.NULL,"B","N",'F',"string","(value).str.lpad(width=<len>, side='left', fillchar=<pad>)"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"len",dsFuunctionType.NULL,"B","N",'F',"float64","value.str.len()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"string","value.str.upper()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_LTRIM,"lstrip",dsFuunctionType.NULL,"B","N",'F',"string","s.str.lstrip()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"string","value.str.replace(old,new)"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_RIGHTPAD,"RightPad",dsFuunctionType.NULL,"B","N",'F',"object","(value).str.rpad(width=<len>, side='right', fillchar=<pad>)"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_RTRIM,"rstrip",dsFuunctionType.NULL,"B","N",'F',"string","s.str.rstrip()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_SEARCH,"find",dsFuunctionType.NULL,"B","N",'F',"int64","value.str.find(sub, start=0, end=None)"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_SUBSTR,"slice",dsFuunctionType.NULL,"B","N",'F',"string","value.str.slice(start,end)---substring"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_TRIM,"strip",dsFuunctionType.NULL,"B","N",'F',"string","s.str.strip()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"string","value.str.upper()"));
		pythonRules.add(new FnRule(dsFuunctionType.STR_STARTSWITH,"StrStartsWith",dsFuunctionType.NULL,"B","N",'F',"bool","value.str.startswith(pattern)"));
		
		return pythonRules;
	}
	public static List<FnRule> pandasSqlRules()
	{
		if(pandasSqlRules!=null)
			return pandasSqlRules;

		pandasSqlRules = new ArrayList<FnRule>();

		pandasSqlRules.add(new FnRule(dsFuunctionType.AGG_MAX,"MAX",dsFuunctionType.NULL,"B","N",'F',"variableType","agg(['max',axis='columns'])"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.AGG_MIN,"MIN",dsFuunctionType.NULL,"B","N",'F',"variableType","agg(['min',axis='columns'])"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.AGG_SUM,"SUM",dsFuunctionType.NULL,"B","N",'F',"variableType","agg(['sum',axis='columns'])"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CHECK_NOT_NULL,"notnull",dsFuunctionType.NULL,"B","N",'F',"BOOLEAN","notna(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_NUMTOFLOAT,"dsNumToFloat",dsFuunctionType.NULL,"B","N",'F',"FLOAT","to_numeric(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_NUMTOINTEGER,"dsNumToInt",dsFuunctionType.NULL,"B","N",'F',"INTEGER","astype(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_NUMTODOUBLE,"dsNumToDouble",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","astype(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_DECTOFLOAT,"dsDecToFloat",dsFuunctionType.NULL,"B","N",'F',"FLOAT","astype(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_FLOATTODEC,"dsFloatToDec",dsFuunctionType.NULL,"B","N",'F',"DECIMAL","astype(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_STRTODATE,"dsStrToDate",dsFuunctionType.NULL,"B","N",'F',"DATE","to_datetime(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_STRTOTIME,"dsStrToTime",dsFuunctionType.NULL,"B","N",'F',"TIME","to_datetime(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.CONV_TODATE,"dsTsToDate",dsFuunctionType.NULL,"B","N",'F',"DATE","to_datetime(argument)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_ABSOLUTE,"dsAbsolute",dsFuunctionType.NULL,"B","N",'F',"INTEGER","DataFrame.abs"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_ROUND,"ROUND",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DataFrame.round"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_TRUNC,"dsNumTrunc",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DataFrame.truncate"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_DIV,"dsNumDiv",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DataFrame.div"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_LN,"dsNumLn",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","log(num)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_LOG,"dsNumLog",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","log10(num)"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_MULTIPLY,"dsNumMultiply",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","DataFrame.mul()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_RANDOM,"dsNumRandom",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","RANDOM()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.NUM_PI,"dsNumPi",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","PI()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.MATH_MOD,"dsMod",dsFuunctionType.NULL,"B","N",'F',"NUMERIC","MOD()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.MATH_POWER,"dsPower",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","POW()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.SCI_KURTOSIS,"dsSciKurToSis",dsFuunctionType.NULL,"B","N",'F',"DOUBLE","DataFrame.kurtosis"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.SP_LOOKUP,"dsLookup",dsFuunctionType.NULL,"B","N",'F',"ndarray","DataFrame.lookup"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.STR_CONCAT,"+",dsFuunctionType.NULL,"B","N",'F',"variableType","str.cat()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.STR_LENGTH,"len",dsFuunctionType.NULL,"B","N",'F',"INTEGER","str.len()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.STR_LOWER,"lower",dsFuunctionType.NULL,"B","N",'F',"STRING","str.lower()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.STR_REPLACE,"replace",dsFuunctionType.NULL,"B","N",'F',"variableType","str.replace()"));
		pandasSqlRules.add(new FnRule(dsFuunctionType.STR_UPPER,"upper",dsFuunctionType.NULL,"B","N",'F',"STRING","str.upper()"));
		

		return pandasSqlRules;
	}

}

