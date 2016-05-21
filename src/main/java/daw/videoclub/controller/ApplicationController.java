package daw.videoclub.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import daw.videoclub.model.Movie;
import daw.videoclub.model.User;
import daw.videoclub.repository.MovieRepository;
import daw.videoclub.repository.UserRepository;

@Controller
public class ApplicationController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/")
	public ModelAndView home(Principal principal){
		boolean admin = false;
		User u = userRepository.findByUsername(principal.getName());
		if(u!=null){
			List<GrantedAuthority> roles = u.getRoles();
			admin = roles.contains( new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		return new ModelAndView("home").addObject("admin", admin).addObject("movies", movies);
	}
}
