package daw.videoclub.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import daw.videoclub.model.User;
import daw.videoclub.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users")
	public ModelAndView manage() {
		return new ModelAndView("userManagement");
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/admin")
	public ModelAndView admin() {
		GrantedAuthority[] roles = { new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN") }; 
		User admin = new User("admin","admin","admin@videoclub.com",  Arrays.asList(roles));
		userRepository.save(admin);
		return new ModelAndView("login");
	}
}
