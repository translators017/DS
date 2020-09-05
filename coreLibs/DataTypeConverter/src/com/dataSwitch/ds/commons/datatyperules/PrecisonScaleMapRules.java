
package com.dataSwitch.ds.commons.datatyperules;

public class PrecisonScaleMapRules {
	public static void getPrecisionScale(int tgttooltype, DataTypeObject tgtDtPreSc, DataTypeRule rule) 
	{
		String tgtDtype = tgtDtPreSc.getDataType();
		String scale = tgtDtPreSc.getScale();
		String precision = tgtDtPreSc.getPrecision();
		if (tgttooltype == ToolTypeConstants.ORACLE) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "blob":
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "clob":
					scale = "0";
					break;
				case "date":
					precision = "19";
					scale = "0";
					break;
				case "long":
					scale = "0";
					break;
				case "longraw":
					scale = "0";
					break;
				case "nchar":
					scale = "0";
					break;
				case "nclob":
					scale = "0";
					break;
				case "number":
					precision = "15";
					scale = "0";
					break;
				case "number(p,s)":
					break;
				case "nvarchar2":
					scale = "0";
					break;
				case "raw":
					scale = "0";
					break;
				case "timestamp":
					precision = "26";
					scale = "6";
					break;
				case "varchar":
					scale = "0";
					break;
				case "varchar2":
					scale = "0";
					break;
				case "xmltype":
					scale = "0";
					break;
				default:
			}
			
		}
		else if (tgttooltype == ToolTypeConstants.ODBC) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "binary":
					scale = "0";
					break;
				case "bit":
					precision = "1";
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "date":
					precision = "10";
					scale = "0";
					break;
				case "decimal":
					break;
				case "double":
					precision = "15";
					scale = "0";
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "integer":
					precision = "10";
					scale = "0";
					break;
				case "longvarbinary":
					scale = "0";
					break;
				case "nchar":
					scale = "0";
					break;
				case "ntext":
					scale = "0";
					break;
				case "numeric":
					break;
				case "nvarchar":
					scale = "0";
					break;
				case "real":
					precision = "7";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "text":
					scale = "0";
					break;
				case "time":
					precision = "8";
					scale = "0";
					break;
				case "timestamp":
					precision = "29";
					scale = "9";
					break;
				case "tinyint":
					precision = "3";
					scale = "0";
					break;
				case "varbinary":
					scale = "0";
					break;
				case "varchar":
					scale = "0";
					break;
				default:
			}
			
		}
		else if (tgttooltype == ToolTypeConstants.TERADATA) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "byte":
					scale = "0";
					break;
				case "byteint":
					precision = "3";
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "date":
					scale = "0";
					break;
				case "decimal":
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "integer":
					precision = "10";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "time":
					precision = "8";
					scale = "0";
					break;
				case "timestamp":
					precision = "26";
					scale = "6";
					break;
				case "varbyte":
					scale = "0";
					break;
				case "varchar":
					scale = "0";
					break;
				default:
			}
			
		}
		else if (tgttooltype == ToolTypeConstants.FLATFILE) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "datetime":
					precision = "29";
					scale = "9";
					break;
				case "double":
					break;
				case "int":
					scale = "0";
					break;
				case "nstring":
					scale = "0";
					break;
				case "number":
					break;
				case "string":
					scale = "0";
					break;
				default:
			}
			
		}
		else if (tgttooltype == ToolTypeConstants.NETEZZA) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "byteint":
					precision = "3";
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "date":
					precision = "10";
					scale = "0";
					break;
				case "decimal":
					break;
				case "float":
					break;	
				case "double":
					precision = "15";
					scale = "0";
					break;
				case "integer":
					precision = "10";
					scale = "0";
					break;
				case "nchar":
					scale = "0";
					break;
				case "numeric":
					break;
				case "nvarchar":
					scale = "0";
					break;
				case "real":
					precision = "6";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "text":
					scale = "0";
					break;
				case "time":
					break;
				case "timestamp":
					break;
				case "varchar":
					scale = "0";
					break;
				default:
			}	
		}
		else if (tgttooltype == ToolTypeConstants.REDSHIFT) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "blob":
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "clob":
					scale = "0";
					break;
				case "long":
					scale = "0";
					break;
				case "longraw":
					scale = "0";
					break;
				case "nchar":
					scale = "0";
					break;
				case "nclob":
					scale = "0";
					break;
				case "int2":
					precision = "5";
					scale = "0";
					break;
				case "int4":
					precision = "10";
					scale = "0";
					break;
				case "int8":
					precision = "19";
					scale = "0";
					break;
				case "float8":
					precision = "15";
					scale = "0";
					break;
				case "float4":
					precision = "6";
					scale = "0";
					break;
				case "numeric":
					break;
				case "nvarchar2":
					scale = "0";
					break;
				case "raw":
					scale = "0";
					break;
				case "timestamp":
					precision = "29";
					scale = "6";
					break;
				case "varchar":
					scale = "0";
					break;
				case "varchar2":
					scale = "0";
					break;
				case "xmltype":
					scale = "0";
					break;
				default:
			}
			
		}
		else if (tgttooltype == ToolTypeConstants.DB2) 
		{
			switch (tgtDtype.toLowerCase())
			{		
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "blob":
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "char for bit data":
					scale = "0";
					break;
				case "clob":
					scale = "0";
					break;
				case "date":
					precision = "10";
					scale = "0";
					break;	
				case "dbclob":
					scale = "0";
					break;
				case "decimal":
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "graphic":
					scale = "0";
					break;
				case "integer":
					precision = "10";
					scale = "0";
					break;
				case "long varchar":
					scale = "0";
					break;
				case "long vargraphic":
					scale = "0";
					break;
				case "numeric":
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "time":
					precision = "8";
					scale = "0";
					break;
				case "timestamp":
					precision = "26";
					scale = "6";
					break;
				case "varchar":
					scale = "0";
					break;
				case "varchar for bit data":
					scale = "0";
					break;
				case "vargraphic":
					scale = "0";
					break;
				default:
			}
			
		}
		else if (tgttooltype == ToolTypeConstants.SYBASE) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "binary":
					scale = "0";
					break;
				case "bit":
					precision = "1";
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "datetime":
					precision = "23";
					scale = "3";
					break;
				case "decimal":
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "image":
					scale = "0";
					break;
				case "int":
					precision = "10";
					scale = "0";
					break;
				case "money":
					precision = "19";
					scale = "4";
					break;
				case "nchar":
					scale = "0";
					break;
				case "numeric":
					break;
				case "numeric identity":
					scale = "0";
					break;
				case "nvarchar":
					scale = "0";
					break;
				case "real":
					precision = "7";
					scale = "0";
					break;
				case "smalldatetime":
					precision = "19";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "smallmoney":
					precision = "10";
					scale = "4";
					break;
				case "text":
					scale = "0";
					break;
				case "timestamp":
					precision = "8";
					scale = "0";
					break;
				case "tinyint":
					precision = "3";
					scale = "0";
					break;
				case "varbinary":
					scale = "0";
					break;
				case "varchar":
					scale = "0";
					break;
				default:
			}
		}
		else if (tgttooltype == ToolTypeConstants.SQLSERVER) 
			{
				switch (tgtDtype.toLowerCase())
				{
					case "bigint":
						precision = "19";
						scale = "0";
						break;
					case "binary":
						scale = "0";
						break;
					case "bit":
						precision = "1";
						scale = "0";
						break;
					case "char":
						scale = "0";
						break;
					case "datetime":
						precision = "23";
						scale = "3";
						break;
					case "datetime2":
						precision = "27";
						scale = "7";
						break;
					case "decimal":
						break;
					case "float":
						precision = "15";
						scale = "0";
						break;
					case "image":
						scale = "0";
						break;
					case "int":
						precision = "10";
						scale = "0";
						break;
					case "money":
						precision = "19";
						scale = "4";
						break;
					case "nchar":
						scale = "0";
						break;
					case "ntext":
						scale = "0";
						break;
					case "numeric":
						break;
					case "numeric identity":
						scale = "0";
						break;
					case "nvarchar":
						scale = "0";
						break;
					case "real":
						precision = "7";
						scale = "0";
						break;
					case "smalldatetime":
						precision = "19";
						scale = "0";
						break;
					case "smallint":
						precision = "5";
						scale = "0";
						break;
					case "smallmoney":
						precision = "10";
						scale = "4";
						break;
					case "sysname":
						precision = "30";
						scale = "0";
						break;
					case "text":
						scale = "0";
						break;
					case "timestamp":
						precision = "8";
						scale = "0";
						break;
					case "tinyint":
						precision = "3";
						scale = "0";
						break;
					case "varbinary":
						scale = "0";
						break;
					case "varchar":
						scale = "0";
						break;
					default:
				}
			}
		else if (tgttooltype == ToolTypeConstants.AZURESQL) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "binary":
					precision = "19";
					scale = "0";
					break;
				case "bit":
					precision = "1";
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "date":
					precision = "23";
					scale = "3";
					break;
				case "datetime":
					precision = "27";
					scale = "7";
					break;
				case "datetime2":
					precision = "27";
					scale = "7";
					break;
				case "datetimeoffset":
					precision = "27";
					scale = "7";
					break;
				case "decimal":
					precision = "10";
					scale = "0";
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "int":
					precision = "10";
					scale = "0";
					break;
				case "money":
					precision = "19";
					scale = "4";
					break;
				case "nchar":
					scale = "0";
					break;
				case "nvarchar":
					scale = "0";
					break;
				case "real":
					precision = "7";
					scale = "0";
					break;
				case "smalldatetime":
					precision = "19";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "smallmoney":
					precision = "10";
					scale = "4";
					break;
				case "sysname":
					precision = "30";
					scale = "0";
					break;
				case "time":
					precision = "8";
					scale = "0";
					break;
				case "tinyint":
					precision = "3";
					scale = "0";
					break;
				case "uniqueidentifier":
					precision = "3";
					scale = "0";
					break;
				case "varbinary":
					scale = "0";
					break;
				case "varchar":
					scale = "0";
					break;
					
				default:
			}
		}
		else if (tgttooltype == ToolTypeConstants.SNOWFLAKE) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigdecimal":
					precision = "19";
					scale = "0";
					break;
				case "byteint":
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "date":
					precision = "23";
					scale = "3";
					break;
				case "decimal":
					break;
				case "numeric":
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "integer":
					precision = "10";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "time":
					precision = "8";
					scale = "0";
					break;
				case "timestamp":
					precision = "8";
					scale = "0";
					break;
				case "varchar":
					scale = "0";
					break;
				case "doubleprecision":
					precision = "7";
					scale = "0";
					break;
				case "real":
					precision = "19";
					scale = "0";
					break;
				case "int":
					precision = "10";
					scale = "4";
					break;
				case "character":
					precision = "30";
					scale = "0";
					break;
				case "binary":
					scale = "0";
					break;
				case "string":
					precision = "3";
					scale = "0";
					break;
				case "double":
					scale = "0";
					break;
				case "timestamp with timezone":
					scale = "0";
					break;
				default:
			}
		}
		else if (tgttooltype == ToolTypeConstants.MONGODB) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "bigint":
					precision = "19";
					scale = "0";
					break;
				case "binary":
					scale = "0";
					break;
				case "bit":
					precision = "1";
					scale = "0";
					break;
				case "char":
					scale = "0";
					break;
				case "datetime":
					precision = "23";
					scale = "3";
					break;
				case "datetime2":
					precision = "27";
					scale = "7";
					break;
				case "decimal":
					break;
				case "float":
					precision = "15";
					scale = "0";
					break;
				case "image":
					scale = "0";
					break;
				case "int":
					precision = "10";
					scale = "0";
					break;
				case "money":
					precision = "19";
					scale = "4";
					break;
				case "nchar":
					scale = "0";
					break;
				case "ntext":
					scale = "0";
					break;
				case "numeric":
					break;
				case "numeric identity":
					scale = "0";
					break;
				case "nvarchar":
					scale = "0";
					break;
				case "real":
					precision = "7";
					scale = "0";
					break;
				case "smalldatetime":
					precision = "19";
					scale = "0";
					break;
				case "smallint":
					precision = "5";
					scale = "0";
					break;
				case "smallmoney":
					precision = "10";
					scale = "4";
					break;
				case "sysname":
					precision = "30";
					scale = "0";
					break;
				case "text":
					scale = "0";
					break;
				case "timestamp":
					precision = "8";
					scale = "0";
					break;
				case "tinyint":
					precision = "3";
					scale = "0";
					break;
				case "varbinary":
					scale = "0";
					break;
				case "varchar":
					scale = "0";
					break;
				default:
			}
		}
		else if (tgttooltype == ToolTypeConstants.AMAZONS3) 
		{
			switch (tgtDtype.toLowerCase())
			{
				case "String":
					precision = "256";
					scale = "0";
					break;

				default:
			}
		}
		
			
		tgtDtPreSc.setPrecision(precision);
		tgtDtPreSc.setScale(scale);
	}
}