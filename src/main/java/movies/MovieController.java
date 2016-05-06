package movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/search")
	public ModelAndView search(){
		return new ModelAndView("search");
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/watch")
	public ModelAndView movie(@RequestParam long id){
		return new ModelAndView("watch");
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/management/movies")
	public ModelAndView manage() {
		return new ModelAndView("movieManagement");
	}
}
