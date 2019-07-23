package org.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String loginError(@RequestParam(value="error",required=false) boolean error,Model m) {
		if(error) {
			System.out.println("to error page...");
			m.addAttribute("error", true);
			return "/login";
		}
		else {
			return "/login";
		}
	}
	
}
