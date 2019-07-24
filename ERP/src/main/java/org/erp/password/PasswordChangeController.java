package org.erp.password;

import org.erp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PasswordChangeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/changePassword")
	public String changePassword(Model m) {
		System.out.println("Loading change password page...");
		m.addAttribute("password", new PasswordDTO());
		return "/change_password";
	}
	
	@RequestMapping("/changePassword/save")
	public String changePassword(@ModelAttribute("password") PasswordDTO password) {
		System.out.println("Changing password");
		userService.changeOwnPassword(password.getPassword());
		userService.logout();
		return "redirect:/login";
	}
	
	

}
