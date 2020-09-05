package co.dataswitch.MetadataCatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.dataswitch.nosqldbDTO.Attribute;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Entity;
import co.dataswitch.utils.Utils;

@Controller
public class MetadataImport {
	
	private static final Logger dslogger = Logger.getLogger(MetadataImport.class);
	
	@ResponseBody
	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/exportMetadata")
	public void exportMetadataFile(HttpServletRequest request, @PathVariable("catalogName") String catalogName, HttpServletResponse response) throws IOException {
		dslogger.info("Entering method to export metadata");
		String selectedEntities = request.getParameter("entityList");
		System.out.println(selectedEntities);
		String[] selectedEntityList = selectedEntities.split(",");
		int r=1, r2=1;
		Catalogs catalog = Utils.getCatalogObject(request,catalogName);
		List<Entity> entityList = catalog.getEntityLst();
		String root = request.getServletContext().getRealPath("/");
		String sessionId = request.getSession(false).getId();
		String fullPath = root + "temp" + File.separator + sessionId + File.separator + "catalogs" + File.separator + "MetadataImport";
		File fpath = new File(fullPath);
		if (!fpath.exists()) {
			fpath.mkdirs();
		}
		FileUtils.cleanDirectory(new File(fullPath));
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			CellStyle style = workbook.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Font font = workbook.createFont();
			font.setColor(IndexedColors.WHITE.getIndex());
			style.setFont(font);
			//Get first/desired sheet from the workbook
			XSSFSheet sheet1 = workbook.createSheet("Entity Sheet");
			XSSFSheet sheet = workbook.createSheet("Attribute Sheet");
			XSSFRow row1 = sheet.createRow(0);

			XSSFCell r1c1 = row1.createCell(0);
			r1c1.setCellValue("Entity Physical Name");
			r1c1.setCellStyle(style);
			XSSFCell r1c2 = row1.createCell(1);
			r1c2.setCellValue("Attribute Physical Name");
			r1c2.setCellStyle(style);
			XSSFCell r1c3 = row1.createCell(2);
			r1c3.setCellValue("Attribute Logical Name");
			r1c3.setCellStyle(style);
			XSSFCell r1c4 = row1.createCell(3);
			r1c4.setCellValue("Attribute Description");
			r1c4.setCellStyle(style);
			XSSFCell r1c5 = row1.createCell(4);
			r1c5.setCellValue("Datatype");
			r1c5.setCellStyle(style);
			XSSFCell r1c6 = row1.createCell(5);
			r1c6.setCellValue("Length/Precision");
			r1c6.setCellStyle(style);
			XSSFCell r1c8 = row1.createCell(6);
			r1c8.setCellValue("Scale");
			r1c8.setCellStyle(style);
			XSSFCell r1c9 = row1.createCell(7);
			r1c9.setCellValue("Nullable");
			r1c9.setCellStyle(style);
			XSSFCell r1c10 = row1.createCell(8);
			r1c10.setCellValue("Data Format");
			r1c10.setCellStyle(style);
			XSSFCell r1c11 = row1.createCell(9);
			r1c11.setCellValue("Default Value");
			r1c11.setCellStyle(style);
			XSSFCell r1c12 = row1.createCell(10);
			r1c12.setCellValue("Check Constraint");
			r1c12.setCellStyle(style);
			XSSFCell r1c13 = row1.createCell(11);
			r1c13.setCellValue("Key Type");
			r1c13.setCellStyle(style);
			XSSFCell r1c16 = row1.createCell(12);
			r1c16.setCellValue("Primary Table Name");
			r1c16.setCellStyle(style);
			XSSFCell r1c17 = row1.createCell(13);
			r1c17.setCellValue("Primary Column Name");
			r1c17.setCellStyle(style);

			XSSFRow row_entity = sheet1.createRow(0);
			XSSFCell r2c1 = row_entity.createCell(0);
			r2c1.setCellValue("Entity Physical Name");
			r2c1.setCellStyle(style);
			XSSFCell r2c2 = row_entity.createCell(1);
			r2c2.setCellValue("Entity Logical Name");
			r2c2.setCellStyle(style);
			XSSFCell r2c3 = row_entity.createCell(2);
			r2c3.setCellValue("Entity Description");
			r2c3.setCellStyle(style);
			XSSFCell r2c4 = row_entity.createCell(3);
			r2c4.setCellValue("Type");
			r2c4.setCellStyle(style);

			for(int j = 0; j < selectedEntityList.length; j++)
			{
				for(int k = 0;k < entityList.size(); k++)
				{
					if(selectedEntityList[j].equalsIgnoreCase(entityList.get(k).getPhysicalName()))
					{

						XSSFRow row2 = sheet1.createRow(r2++);
						XSSFCell cell01 = row2.createCell(0);
						cell01.setCellValue(entityList.get(k).getPhysicalName());
						XSSFCell cell02 = row2.createCell(1);
						cell02.setCellValue(entityList.get(k).getLogicalName());
						XSSFCell cell03 = row2.createCell(2);
						cell03.setCellValue(entityList.get(k).getDescription());
						XSSFCell cell04 = row2.createCell(3);
						cell04.setCellValue(entityList.get(k).getEntityType());


						List<Attribute> attributeList = entityList.get(k).getAttrList();
						for (int i = 0; i < attributeList.size(); i++)
						{
							row1 = sheet.createRow(r++);
							XSSFCell cell = row1.createCell(0);
							cell.setCellValue( entityList.get(k).getPhysicalName());
							XSSFCell r1c21 = row1.createCell(1);
							r1c21.setCellValue(attributeList.get(i).getPhysicalName());
							XSSFCell r1c31 = row1.createCell(2);
							r1c31.setCellValue(attributeList.get(i).getLogicalName());
							XSSFCell r1c41 = row1.createCell(3);
							r1c41.setCellValue(attributeList.get(i).getDescription());
							XSSFCell r1c51 = row1.createCell(4);
							r1c51.setCellValue(attributeList.get(i).getDatatype());
							XSSFCell r1c61 = row1.createCell(5);
							r1c61.setCellValue(attributeList.get(i).getLength_precision());
							XSSFCell r1c71 = row1.createCell(6);
							r1c71.setCellValue(attributeList.get(i).getScale());
							XSSFCell r1c91 = row1.createCell(7);
							r1c91.setCellValue(attributeList.get(i).getNullable());
							XSSFCell r1c101 = row1.createCell(8);
							r1c101.setCellValue(attributeList.get(i).getDataFormat());
							XSSFCell r1c111 = row1.createCell(9);
							r1c111.setCellValue(attributeList.get(i).getDefaultValue());
							XSSFCell r1c121 = row1.createCell(10);
							r1c121.setCellValue(attributeList.get(i).getCheckConstraint());
							XSSFCell r1c131 = row1.createCell(11);
							r1c131.setCellValue(attributeList.get(i).getKeyType());
							XSSFCell r1c141 = row1.createCell(12);
							r1c141.setCellValue(attributeList.get(i).getPrimaryTableName());
							XSSFCell r1c171 = row1.createCell(13);
							r1c171.setCellValue(attributeList.get(i).getprimaryColumnName());
						}
					} 
				}
				FileOutputStream fos = new FileOutputStream(new File(fullPath + File.separator + catalogName+"_Metadata"+".xlsx"));
				workbook.write(fos);
				fos.close();
			}
			workbook.close();
			
			File file = new File(fullPath + File.separator + catalogName+"_Metadata"+".xlsx");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment;filename="+catalogName+"_metadata.xlsx");
			
				FileInputStream fileInputStream = new FileInputStream(file);
				OutputStream responseOutputStream = response.getOutputStream();
				int bytes;
				while ((bytes = fileInputStream.read()) != -1) {
					responseOutputStream.write(bytes);
				}
				fileInputStream.close();
				responseOutputStream.close();
			
		}catch (Exception e) {
			dslogger.error("Error - "+e.getMessage());
		}
	}
	
	@ResponseBody
	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/import-metadata-file")
	public void importMetadataFile(HttpServletRequest request, @PathVariable("catalogName") String catalogName, HttpServletResponse response) throws IOException {
		dslogger.info("Entering method to import metadata");
		
	}
}
