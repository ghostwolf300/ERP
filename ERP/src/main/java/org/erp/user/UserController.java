package org.erp.user;

import java.sql.Date;

import org.erp.controller.NavController;
import org.erp.controller.NavController.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/edit/{userId}/{isNewUser}")
	public ModelAndView editUser(
			@PathVariable String userId,
			@PathVariable boolean isNewUser) {
		
		UserDTO user=null;
		
		if(isNewUser) {
			user=new UserDTO();
			user.setUsername(userId);
			user.setLocked(false);
			user.setInitialPw(true);
			user.setValidFrom(new Date(System.currentTimeMillis()));
		}
		else {
			//find user data
		}
		
		ModelAndView modelAndView =new ModelAndView();
		
		System.out.println("Edit user"); 
		modelAndView.addObject("viewName","Edit user"); 
		modelAndView.addObject("viewId",NavController.Views.EDIT_USER);
		
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/user_data");
		System.out.println("UserController.editUser");
		return modelAndView;
	}
	
	@RequestMapping("/new_user") 
	public ModelAndView newUser() {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("viewName","New user");
		modelAndView.addObject("viewId", Views.NEW_USER);
		modelAndView.setViewName("new_user");
		System.out.println("UserController.newUser");
		return modelAndView;
	}
	
}
