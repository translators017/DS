package co.dataswitch.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class ConsoleController {

	@ResponseBody
	@GetMapping("/home/console")
	public void consoleEmitter(HttpServletRequest request, HttpServletResponse response) {
		List<String> messages = new ArrayList<String>();
		String data = "";
		Console console = (Console) request.getSession().getAttribute("console");
		System.out.println(request.getSession().getAttribute("console"));
		if(console!=null){
			messages = console.get();
			response.setContentType("text/event-stream");
			response.setCharacterEncoding("UTF-8");
			
			for(int i=0;i < messages.size();i++){
				String[] msgs = messages.get(i).split(":::");
				if(msgs.length > 1){
					msgs[1] = msgs[1].replace("\n", "<br>");
					msgs[1] = msgs[1].replace("\r", "<br>");
					if(msgs[0].equalsIgnoreCase("warning")){
						data = data + "<p class='m-0 fnt-15 text-left font-weight-normal text-danger'>" + msgs[1] + "</p>";
					}else if(msgs[0].equalsIgnoreCase("error")){
						data = data + "<p class='m-0 fnt-15 text-left font-weight-normal text-warning'>" + msgs[1] + "</p>";
					}else if(msgs[0].equalsIgnoreCase("success")){
						data = data + "<p class='m-0 fnt-15 text-left font-weight-normal text-success'>" + msgs[1] + "</p>";
					}else if(msgs[0].equalsIgnoreCase("Message")){
						data = data + "<p class='m-0 fnt-15 text-dark text-left font-weight-normal'>" + msgs[1]+ "</p>";
					}
				}
			}
			try {
				PrintWriter printwriter = null;
				printwriter = response.getWriter();
				printwriter.println(data);
				response.flushBuffer();
				printwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				

			}
		}
	}
}
