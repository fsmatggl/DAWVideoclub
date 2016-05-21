package daw.videoclub.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		return new ModelAndView("userManagement");
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/search")
	public ModelAndView manageSearch(@RequestParam(required=false) String username) {
		if(username==null)
			return new ModelAndView("userManagementSearch");
		
		User u = userRepository.findByUsername(username);
		if(u==null)
			return new ModelAndView("userManagementSearch").addObject("found", false);
		else
			return new ModelAndView("userManagementSearch").addObject("found", true).addObject("user", u);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/list")
	public ModelAndView manageList() {
		List<User> users = (List<User>) userRepository.findAll();
		if(!users.isEmpty())
			return new ModelAndView("userManagementList").addObject("none", false).addObject("users", users);
		else
			return new ModelAndView("userManagementList").addObject("none", true);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/new")
	public ModelAndView manageNew(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String role) {
		
		if(username==null||password==null||email==null||role==null)
			return new ModelAndView("userManagementNew");
		
		User u = userRepository.findByUsername(username);
		if(u!=null)
			return new ModelAndView("userManagementNew").addObject("in_use", username);
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if(role.equals("admin")){
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else if(roles.equals("user")){
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else
			return new ModelAndView("userManagementNew").addObject("invalid_role", username);
		
		userRepository.save(new User(username, password, email, roles));
		return new ModelAndView("userManagementNew").addObject("created", username);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/modify")
	public ModelAndView manageModify(@RequestParam String id) {
		
		String username = userRepository.findOne(Long.decode(id)).getUsername();
		return new ModelAndView("userManagementModify").addObject("id", id).addObject("username", username);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/modify/{id}")
	public ModelAndView manageModifyId(
			@PathVariable String id,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String role) {
		
		User u = userRepository.findOne(Long.decode(id));
		if(u==null)
			return new ModelAndView("userManagementModify").addObject("not_found");
		
		if(password.equals("") && email.equals("") && role.equals(""))
			return new ModelAndView("userManagementModify").addObject("username", u.getUsername());
			
		if(!password.equals(""))
			u.setPassword(password);
		if(!email.equals(""))
			u.setEmail(email);
		if(!role.equals("")){
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			if(role.equals("admin")){
				roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			}
			else if(role.equals("user")){
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			}
			else
				return new ModelAndView("userManagementModify").addObject("invalid_role", u.getUsername()).addObject("username", u.getUsername());
			u.setRoles(roles);
		}

		userRepository.save(u);
		return new ModelAndView("userManagement").addObject("modified_user", u.getUsername());
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/management/users/delete")
	public ModelAndView manageDelete(@RequestParam long id) {
		String username = userRepository.findOne(id).getUsername();
		userRepository.delete(id);
		return new ModelAndView("userManagement").addObject("deleted_user", username);
	}
	
	@RequestMapping("/admin")
	public ModelAndView admin() {
		if(userRepository.findByUsername("admin")!=null)
			return new ModelAndView("login");
		
		GrantedAuthority[] roles = { new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN") }; 
		User admin = new User("admin","admin","admin@videoclub.com",  Arrays.asList(roles));
		userRepository.save(admin);
		return new ModelAndView("login");
	}
}
