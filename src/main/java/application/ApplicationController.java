package application;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping
	public ModelAndView home(){
		return new ModelAndView("home");
	}
}
