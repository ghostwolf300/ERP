package org.erp.role;

import org.erp.controller.NavController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@RequestMapping("/view_roles")
	public ModelAndView viewRoles() {
		ModelAndView mv =new ModelAndView();
		mv.addObject("viewName", "Roles");
		mv.addObject("viewId", NavController.Views.ROLES);
		mv.setViewName("roles");
		return mv;
	}
	
}
