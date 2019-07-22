package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;

import org.erp.controller.NavController;
import org.erp.controller.NavController.Views;
import org.erp.message.MessageDTO;
import org.erp.message.MessageDTO.Type;
import org.erp.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
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
			user=userService.findUser(userId);
			System.out.println("Found user "+user.getFirstName()+" "+user.getLastName());
		}
		
		ModelAndView modelAndView =new ModelAndView();
		
		System.out.println("Edit user"); 
		modelAndView.addObject("viewName","Edit user"); 
		modelAndView.addObject("viewId",NavController.Views.EDIT_USER);
		
		modelAndView.addObject("user", user);
		if(isNewUser) {
			modelAndView.addObject("mode","new");
		}
		else {
			modelAndView.addObject("mode","change");
		}
		modelAndView.setViewName("/user_data");
		System.out.println("UserController.editUser");
		return modelAndView;
	}
	
	@RequestMapping("/new_user") 
	public ModelAndView newUser(@RequestParam(value="status",required=false) String status) {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("viewName","New user");
		modelAndView.addObject("viewId", Views.NEW_USER);
		modelAndView.setViewName("new_user");
		System.out.println("UserController.newUser");
		if(status!=null && status.equals("cancel")) {
			messageService.addMessage(new MessageDTO(-1,new Timestamp(System.currentTimeMillis()),MessageDTO.Type.INFO,"User creation cancelled"));
		}
		return modelAndView;
	}
	
	@RequestMapping("/change_user") 
	public ModelAndView changeUser(@RequestParam(value="status",required=false) String status) {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("viewName","Change user");
		modelAndView.addObject("viewId", Views.CHANGE_USER);
		modelAndView.setViewName("change_user");
		System.out.println("UserController.changeUser");
		if(status!=null && status.equals("cancel")) {
			messageService.addMessage(new MessageDTO(-1,new Timestamp(System.currentTimeMillis()),MessageDTO.Type.INFO,"User change cancelled"));
		}
		return modelAndView;
	}
	
}
