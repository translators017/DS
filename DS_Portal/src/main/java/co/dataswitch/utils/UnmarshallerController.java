package co.dataswitch.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import co.dataswitch.config.bean.Configurations;


@Controller
public class UnmarshallerController {
	
	private static final Logger dslogger = Logger.getLogger(UnmarshallerController.class);

	public static Configurations unmarshallDataSwitchConfigXML(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/") + "resources"+ File.separator + "conf" + File.separator + "dataswitch_config.xml";
		File configFile = new File(path);
		Configurations ds = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(co.dataswitch.config.bean.Configurations.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ds = (Configurations) unmarshaller.unmarshal(configFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
}
