package co.dataswitch.MetadataCatalog;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;

import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Attribute;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Entity;
import co.dataswitch.utils.Utils;

@Controller
public class FlatFileImport {

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "home/migrate/schema-migrator/catalogs/importFlatFileToCatalog", method = RequestMethod.POST)
	public String importFlatFileToCatalog(MultipartHttpServletRequest request) throws IOException{
		JSONObject fileObj = new JSONObject();
		String root = request.getServletContext().getRealPath("/");
		String sessionId = request.getSession(false).getId();
		File fullPath = new File(root + "temp" + File.separator + sessionId);
		if(!fullPath.exists())
		{
			fullPath.mkdirs();
		}
		FileUtils.cleanDirectory(fullPath);
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());		
		String fileName = "";
		if (!mpf.isEmpty()) {
			fileName = mpf.getOriginalFilename();
			try {
				byte[] bytes = mpf.getBytes();
				fileName = mpf.getOriginalFilename();

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath.getPath(),fileName)));
				stream.write(bytes);
				stream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JSONArray contentArray = new JSONArray();
		JSONObject contentObj = new JSONObject();
		BufferedReader reader = null;
		try {
			int no = 0;
			List<String> lines = Files.readAllLines(Paths.get(fullPath.getPath() + File.separator + fileName), StandardCharsets.UTF_8);
			for(String line  : lines)
			{
				no++;
				contentObj = new JSONObject();
				contentObj.put("lineNo", no);
				contentObj.put("content", line);
				contentArray.add(contentObj);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader != null)
				reader.close();
		}

		fileObj.put("filename", fileName);
		fileObj.put("content", contentArray);
		if(fileName.contains(".")){
			request.getSession().setAttribute("filename", fileName.substring(0, fileName.indexOf(".")));
		}else{
			request.getSession().setAttribute("filename", fileName);
		}

		return fileObj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "home/migrate/schema-migrator/catalogs/getFlatfileDatatypes", method = RequestMethod.POST)
	public String getdatatypesForImport(HttpServletRequest request){
		String catalogName = Jsoup.clean(request.getParameter("catalogName"), Whitelist.none());
		String arr = Jsoup.clean(request.getParameter("tableArr"), Whitelist.none());
		Catalogs catalog = Utils.getCatalogObject(request, catalogName);
		List<String> dtList =  new ArrayList<>();
		JSONObject datatypeObj = new JSONObject();
		String dbToolName= catalog.getDbType();
		if(dbToolName != null && !dbToolName.equals(""))
		{
			DataTypeConvertor dtypConv = new DataTypeConvertor();
			dtList = dtypConv.getAllDataType(Utils.toolNo(dbToolName));
			datatypeObj.put("dtList", dtList);
		}
		JSONParser parserJson = new JSONParser();
		System.out.println("arr--"+arr);
		try{
			Object objJson = parserJson.parse(arr);
			org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) objJson;
			if(jsonArray.size() == 0)
				return null;
			JSONArray predDatatypes = new JSONArray(); 
			for(int p = 0; p < jsonArray.size(); p++)
			{
				org.json.simple.JSONObject arrObj = (org.json.simple.JSONObject) jsonArray.get(p);
				List<String> arrList = (List<String>) arrObj.get("dataArray");
				String dtypeWithLength = datatypecheck(arrList);
				JSONObject predDtObj = new JSONObject();
				predDtObj.put("datatype", dtypeWithLength.split("@")[0]);
				predDtObj.put("length", dtypeWithLength.split("@")[1]);
				predDatatypes.add(predDtObj);
			}
			datatypeObj.put("predDatatypes", predDatatypes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return datatypeObj.toString();
	}
	
	
	public static String datatypecheck(List<String> coloumn)
	{
		String val="";
		int str=0;
		int num=0;
		int inte=0;
		int date_time=0;
		int date_d=0;
		int maxLength=0;

		for(String s:coloumn){
			if (s.length() > maxLength) {
				maxLength = s.length();
			}
			if(s.matches("^[-+]?\\d+.\\d+$"))
			{
				num=num+1;
			}
			else if(s.matches("^[-+]?\\d+$"))
			{
				inte=inte+1;
			}
			else if(s.matches("^((0?[13578]|10|12)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1}))|(0?[2469]|11)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1})))$")||s.matches("((?:19|20)\\d\\d)/(0?[1-9]|1[012])/([12][0-9]|3[01]|0?[1-9])"))
			{
				date_d=date_d+1;
			}
			else if(s.matches("^(0?[1-9]|1[0-2])/0?[0-3][0-9]/\\d\\d [0-11]:[0-6][0-9] (am|pm|AM|PM)")){
				date_time=date_time+1;
			}
			else
			{
				str=str+1;
			}

		} 
		String maxlength=String.valueOf(maxLength);
		//	System.out.println(num+" "+inte+" "+date_d+" "+str);
		if(num>inte && num>str && num>date_d && num>date_time)
		{
			val="Number";
		}
		else if(inte>num && inte>str && inte>date_d && inte>date_time)
		{
			val="Integer";
		}
		else if(date_d>num && date_d>str && date_d>inte && date_d>date_time)
		{
			val="Date";
		}
		else if(date_time>num && date_time>str && date_time>inte && date_time>date_d)
		{
			val="Datetime";
		}
		else
		{
			val="String";
		}
		return val+"@"+maxlength;
	}
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "home/migrate/schema-migrator/catalogs/importRefinedData", method = RequestMethod.POST)
	public String importTableAndColumns(HttpServletRequest request){
		boolean isUpdate = false;
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String colArray = Jsoup.clean(request.getParameter("colArray"), Whitelist.none());
		String hasHeader = Jsoup.clean(request.getParameter("hasHeader"), Whitelist.none());
		String delimiter = Jsoup.clean(request.getParameter("delimiter"), Whitelist.none());
		String extension = Jsoup.clean(request.getParameter("extension"), Whitelist.none());
		String catalogName = Jsoup.clean(request.getParameter("catalogName"), Whitelist.none());
		String fileName = (String) request.getSession().getAttribute("filename");
		String username = (String) request.getSession().getAttribute("username");
		int headerRows = hasHeader.equals("true") ? 1 : 0;
		JSONObject validationObj = new JSONObject();
		Catalogs catalog = Utils.getCatalogObject(request, catalogName);
		JSONParser parserJson = new JSONParser();
		try{
			
			for (Iterator<Entity> iter = catalog.getEntityLst().listIterator(); iter.hasNext(); ) {
				Entity en = iter.next();
				if (en.getPhysicalName().equals(fileName)) {
					iter.remove();
				}
			}
			
			Entity entity = new Entity();
			entity.setPhysicalName(fileName);
			entity.setLogicalName(fileName);
			entity.setColumnDelimiter(delimiter);
			entity.setHeaderRows(String.valueOf(headerRows));
			entity.setFileExtenstion("."+extension);
			entity.setEntityType("File");
			Object objJson = parserJson.parse(colArray);
			org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) objJson;
			if(jsonArray.isEmpty())
			{
				validationObj.put("type", "Error");
				validationObj.put("message", "Unable to parse data. Please check the data.");
			}
			String datatype = "";
			for(int p = 0; p < jsonArray.size(); p++)
			{
				org.json.simple.JSONObject attrObj = (org.json.simple.JSONObject) jsonArray.get(p);
				datatype = (String) attrObj.get("datatype");
				Attribute attr = new Attribute();
				attr.setPhysicalName((String) attrObj.get("colName"));
				attr.setLogicalName((String) attrObj.get("colName"));
				attr.setDatatype(datatype);
				attr.setLength_precision(String.valueOf(attrObj.get("length")));
				if(datatype.equals("number") || datatype.equals("integer")){
					attr.setScale("0");
				}
				entity.getAttrList().add(attr);
			}
			catalog.getEntityLst().add(entity);
			isUpdate = DBEngine_new.updateData("Catalogs", catalog.getCatalogName(), catalog , dataStoreDir+"/"+username);
			if(isUpdate)
			{
				validationObj.put("type", "Success");
				validationObj.put("message", "Entity has been successfully imported to catalog.");
				validationObj.put("filename", fileName);
				
			}else
			{
				validationObj.put("type", "Error");
				validationObj.put("message", "Unable to insert entity into db.");
			}
		}catch(Exception e){
			validationObj.put("type", "Error");
			validationObj.put("message", "Error importing data - "+e.getMessage());
		}
		
		return validationObj.toString();
	}
}
