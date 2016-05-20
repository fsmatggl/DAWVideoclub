package daw.videoclub.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import daw.videoclub.model.User;
import daw.videoclub.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users")
	public ModelAndView manage() {
		return new ModelAndView("userManagement").addObject("users", (List<User>) userRepository.findAll());
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/search")
	public ModelAndView manageSearch(@RequestParam String username) {
		User u = userRepository.findByUsername(username);
		if(u==null)
			return new ModelAndView("userManagement").addObject("found", false);
		else
			return new ModelAndView("userManagement").addObject("found", true).addObject("user", u);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/new")
	public ModelAndView manageNew() {
		return new ModelAndView("userManagementNew");
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/modify")
	public ModelAndView manageModify(@RequestParam long id) {
		return new ModelAndView("userManagement");
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/delete")
	public ModelAndView manageDelete(@RequestParam long id) {
		String username = userRepository.findOne(id).getUsername();
		userRepository.delete(id);
		return new ModelAndView("userManagement").addObject("deleted_username", username);
	}
	
	@RequestMapping("/admin")
	public ModelAndView admin() {
		GrantedAuthority[] roles = { new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN") }; 
		User admin = new User("admin","admin","admin@videoclub.com",  Arrays.asList(roles));
		userRepository.save(admin);
		return new ModelAndView("login");
	}
}
