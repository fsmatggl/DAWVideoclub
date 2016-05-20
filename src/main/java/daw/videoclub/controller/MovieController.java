package daw.videoclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import daw.videoclub.model.Movie;
import daw.videoclub.repository.MovieRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String title){
		Movie m = movieRepository.findByName(title);
		if(m==null)
			return new ModelAndView("search").addObject("found", false);
		else
			return new ModelAndView("search").addObject("found", true).addObject("movie", m);
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
