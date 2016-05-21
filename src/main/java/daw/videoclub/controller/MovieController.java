package daw.videoclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/movies/search")
	public ModelAndView manageSearch(@RequestParam(required=false) String name) {
		if(name==null)
			return new ModelAndView("movieManagementSearch");
		
		Movie m = movieRepository.findByName(name);
		if(m==null)
			return new ModelAndView("movieManagementSearch").addObject("found", false);
		else
			return new ModelAndView("movieManagementSearch").addObject("found", true).addObject("movie", m);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/movies/list")
	public ModelAndView manageList() {
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		if(!movies.isEmpty())
			return new ModelAndView("movieManagementList").addObject("none", false).addObject("movies", movies);
		else
			return new ModelAndView("movieManagementList").addObject("none", true);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/movies/new")
	public ModelAndView manageNew(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String url,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String year,
			@RequestParam(required = false) String director,
			@RequestParam(required = false) String cast,
			@RequestParam(required = false) String front,
			@RequestParam(required = false) String rating) {
		
		if(name==null||url==null)
			return new ModelAndView("movieManagementNew");
		
		if(name.equals("")||name.equals(""))
			return new ModelAndView("movieManagementNew").addObject("no_nameOrUrl");
		
		Movie m = movieRepository.findByName(name);
		if(m!=null)
			return new ModelAndView("movieManagementNew").addObject("in_use", name);
		
		m = new Movie(name, url);
		
		if(!description.equals(""))
			m.setDescription(description);
		if(!year.equals(""))
			m.setYear(year);
		if(!director.equals(""))
			m.setDirector(director);
		if(!cast.equals(""))
			m.setCast(cast);
		if(!front.equals(""))
			m.setFront(front);
		if(!rating.equals(""))
			m.setRating(rating);
			
		movieRepository.save(m);
		return new ModelAndView("movieManagementNew").addObject("created", name);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/movies/modify")
	public ModelAndView manageModify(@RequestParam String id) {
		
		String name = movieRepository.findOne(Long.decode(id)).getName();
		return new ModelAndView("movieManagementModify").addObject("id", id).addObject("name", name);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/movies/modify/{id}")
	public ModelAndView manageModifyId(
			@PathVariable String id,
			@RequestParam(required = false) String url,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String year,
			@RequestParam(required = false) String director,
			@RequestParam(required = false) String cast,
			@RequestParam(required = false) String front,
			@RequestParam(required = false) String rating) {
		
		Movie m = movieRepository.findOne(Long.decode(id));
		if(m==null)
			return new ModelAndView("movieManagementModify").addObject("not_found");
		
		if(url.equals("") && description.equals("") && year.equals("") && director.equals("") && 
				cast.equals("") && front.equals("") && rating.equals(""))
			return new ModelAndView("movieManagementModify").addObject("name", m.getName());
			
		if(!url.equals(""))
			m.setUrl(url);
		if(!description.equals(""))
			m.setDescription(description);
		if(!year.equals(""))
			m.setYear(year);
		if(!director.equals(""))
			m.setDirector(director);
		if(!cast.equals(""))
			m.setCast(cast);
		if(!front.equals(""))
			m.setFront(front);
		if(!rating.equals(""))
			m.setRating(rating);
		
		movieRepository.save(m);
		return new ModelAndView("movieManagement").addObject("modified_movie", m.getName());
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/movies/delete")
	public ModelAndView manageDelete(@RequestParam long id) {
		Movie m = movieRepository.findOne(id);
		String name = m.getName();
		movieRepository.delete(id);
		return new ModelAndView("movieManagement").addObject("deleted_movie", name);
	}

}
