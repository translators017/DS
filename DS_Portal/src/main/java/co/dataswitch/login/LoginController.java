package co.dataswitch.login;

import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.dataswitch.MetadataCatalog.CatalogController;
import co.dataswitch.utils.Utils;

@Controller
public class LoginController {
	
	private static final Logger dslogger = Logger.getLogger(LoginController.class);

	@GetMapping(value = "/")
	public ModelAndView baseurl(HttpServletRequest request,HttpServletResponse response) {
		dslogger.info("Redirecting to login page");
		ModelAndView mv = new ModelAndView("common/login");
		return mv;
	}

	@GetMapping(value = "/home")
	public ModelAndView userpage(HttpServletRequest request, Principal principal) {
		dslogger.info("Redirecting to home page");
		String noSqlPath = Jsoup.clean(Utils.getNoSQLDBPath(request), Whitelist.none());
		String username = Jsoup.clean(principal.getName(), Whitelist.none());
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("nosqlPath", noSqlPath);
		ModelAndView mv = new ModelAndView("common/landing");
		return mv;
	}
	
	@RequestMapping("/error-page")
    public ModelAndView handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		dslogger.info("Error - "+Integer.valueOf(status.toString()));
		ModelAndView mv = new ModelAndView("common/error");
        return mv;
    }
	
	@GetMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("common/login");

		return model;
	}
	
	@GetMapping(value="/logout")  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
		dslogger.info("Logging out of the application");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/login?logout";  
     }  
}
