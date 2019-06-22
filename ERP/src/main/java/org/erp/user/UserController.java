package org.erp.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/new_user/edit")
	public String editUser() {
		System.out.println("UserController.editUser");
		return "/user_data";
	}
	
}
