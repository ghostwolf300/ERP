package org.erp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/edit/{userId}/{isNewUser}")
	public ModelAndView editUser(
			@PathVariable String userId,
			@PathVariable boolean isNewUser) {
		
		UserDTO user=null;
		
		if(isNewUser) {
			user=new UserDTO();
			user.setUsername(userId);
			user.setLocked(false);
			user.setInitialPw(true);
		}
		else {
			//find user data
		}
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/user_data");
		System.out.println("UserController.editUser");
		return modelAndView;
	}
	
}
