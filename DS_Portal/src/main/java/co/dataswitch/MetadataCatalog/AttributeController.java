package co.dataswitch.MetadataCatalog;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Attribute;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Entity;
import co.dataswitch.nosqldbDTO.ReferencedAttributes;
import co.dataswitch.utils.Utils;


@Controller
public class AttributeController {

private static final Logger dslogger = Logger.getLogger(AttributeController.class);
	
	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}/saveAttributes")
	public ModelAndView saveAttributesforEntity(HttpServletRequest request, @PathVariable("catalogName") String catalogName , @PathVariable("entityName") String entityName,@ModelAttribute("attribute") Attribute attribute, RedirectAttributes redirectAttributes) {
		dslogger.info("Saving new Attribute for entity "+entityName+" in catalog "+catalogName);
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/"+catalogName+"/"+entityName);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		boolean isInsert = false;
		Catalogs catalog = Utils.getCatalogObject(request,catalogName);
		if(catalog != null)
		{
			for(int i = 0; i < catalog.getEntityLst().size(); i++){
				if(catalog.getEntityLst().get(i).getPhysicalName().equalsIgnoreCase(entityName)){
					catalog.getEntityLst().get(i).getAttrList().add(attribute);
					break;
				}
			}
			try{
				isInsert = DBEngine_new.updateData("Catalogs", catalogName, catalog,dataStoreDir+ File.separator +username);
				if(isInsert)
				{
					redirectAttributes.addFlashAttribute("type", "Success");
					redirectAttributes.addFlashAttribute("message", "Attribute has been added successfully.");
				}
				else
				{
					redirectAttributes.addFlashAttribute("type", "Failed");
					redirectAttributes.addFlashAttribute("message", "Failed to add attribute.");
				}
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to add attribute - "+e.getMessage());
				dslogger.error("Error adding attribute - "+e.getMessage());
			}
		}
		return mv;
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}/delete-attribute")
	public ModelAndView deleteAttributes(@PathVariable("entityName") String entityName, @PathVariable("catalogName") String catalogName ,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String attributeName = request.getParameter("attributeName");
		attributeName = Jsoup.clean(attributeName, Whitelist.none());
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}");
		dslogger.info("Deleting attribute "+attributeName+" from entity "+entityName+" in catalog "+catalogName);
		Catalogs catalogData = Utils.getCatalogObject(request, catalogName);
		boolean deleteStatus = false;
		String username = (String) request.getSession().getAttribute("username");
		if(catalogData != null)
		{
			try{
				for(int i = 0 ; i < catalogData.getEntityLst().size(); i++)
				{
					for (Iterator<Attribute> iter = catalogData.getEntityLst().get(i).getAttrList().iterator(); iter.hasNext(); ) 
					{
						Attribute attr = iter.next();
						if (attr.getPhysicalName().equalsIgnoreCase(attributeName)) {
							iter.remove();
							break;
						}
					}
				}
				deleteStatus = DBEngine_new.updateData("Catalogs", catalogName, catalogData,dataStoreDir + File.separator + username);
				if(deleteStatus)
				{
					redirectAttributes.addFlashAttribute("type", "Success");
					redirectAttributes.addFlashAttribute("message", "Attribute has been deleted successfully.");
				}
				else
				{
					redirectAttributes.addFlashAttribute("type", "Failed");
					redirectAttributes.addFlashAttribute("message", "Failed to delete attribute. Please try again after some time.");
				}
			}catch(Exception e){
				dslogger.error("Error deleting attribute from entity - "+e.getMessage());
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to delete attribute. "+e.getMessage());
			}
		}
		return mv;
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}/editAttributes")
	public ModelAndView editAttributeDetails(@PathVariable("catalogName") String catalogName, @PathVariable("entityName") String entityName,HttpServletRequest request, @ModelAttribute("Attribute") Attribute attr, RedirectAttributes redirectAttributes) {
		dslogger.info("Saving edited attributes");
		catalogName = Jsoup.clean(catalogName, Whitelist.none());
		entityName = Jsoup.clean(entityName, Whitelist.none());
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}");
		String username = (String) request.getSession().getAttribute("username");
		Catalogs catalogData = Utils.getCatalogObject(request, catalogName);
		boolean updateStatus = false;
		if(catalogData != null)
		{
			try{
				
				for(int i = 0; i < catalogData.getEntityLst().size(); i++)
				{
					if(catalogData.getEntityLst().get(i).getPhysicalName().equals(entityName))
					{
						for(int j = 0; j < catalogData.getEntityLst().get(i).getAttrList().size(); j++)
						{
							if(catalogData.getEntityLst().get(i).getAttrList().get(j).getPhysicalName().equals(attr.getPhysicalName()))
							{
								catalogData.getEntityLst().get(i).getAttrList().get(j).setLogicalName(attr.getLogicalName());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setDescription(attr.getDescription());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setDatatype(attr.getDatatype());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setLength(attr.getLength());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setPrecision(attr.getPrecision());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setScale(attr.getScale());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setNullable(attr.getNullable());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setDataFormat(attr.getDataFormat());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setKeyType(attr.getKeyType());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setPrimaryTableName(attr.getPrimaryTableName());
								catalogData.getEntityLst().get(i).getAttrList().get(j).setprimaryColumnName(attr.getprimaryColumnName());
								
								updateStatus = DBEngine_new.updateData("Catalogs", catalogData.getCatalogName(), catalogData,dataStoreDir + File.separator + username);
								if(updateStatus){
									redirectAttributes.addFlashAttribute("type", "Success");
									redirectAttributes.addFlashAttribute("message", "Attribute has been updated successfully.");
								}else{
									redirectAttributes.addFlashAttribute("type", "Failed");
									redirectAttributes.addFlashAttribute("message", "Failed to update attribute. Please try again after sometime.");
								}
								break;
							}
						}
					}
				}
				
			}catch(Exception e){
				dslogger.error("Error - "+e.getMessage());
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to update attribute - "+e.getMessage());
			}
		}
		return mv;
	}
}
