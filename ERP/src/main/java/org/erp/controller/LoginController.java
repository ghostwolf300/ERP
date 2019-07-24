package org.erp.controller;

import org.erp.component.ERPAuthFailureHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String loginError(
			@RequestParam(value="error",required=false) boolean error,
			@RequestParam(value="type",required=false) Integer type,
			Model m) {
		
		String errorMessage=null;
		
		if(error) {
			m.addAttribute("error", true);
			switch(type) {
				case ERPAuthFailureHandler.BAD_CREDENTIALS :
					errorMessage="Invalid username or password";
					break;
				case ERPAuthFailureHandler.ACCOUNT_DISABLED :
					errorMessage="Account disabled";
					break;
				case ERPAuthFailureHandler.ACCOUNT_EXPIRED :
					errorMessage="Account expired";
					break;
				case ERPAuthFailureHandler.ACCOUNT_LOCKED :
					errorMessage="Account locked";
					break;
				case ERPAuthFailureHandler.OTHER_ERROR :
					errorMessage="Unknown error";
					break;
			}
			m.addAttribute("errorMessage", errorMessage);
			return "/login";
		}
		else {
			return "/login";
		}
	}
	
}
