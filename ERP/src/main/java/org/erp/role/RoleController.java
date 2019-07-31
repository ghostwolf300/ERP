package org.erp.role;

import java.util.List;

import org.erp.controller.NavController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/view_roles")
	public ModelAndView viewRoles() {
		ModelAndView mv=new ModelAndView();
		List<RoleDTO> roles=roleService.findAllRolesDTO();
		mv.addObject("viewName", "Roles");
		mv.addObject("viewId", NavController.Views.ROLES);
		mv.setViewName("roles");
		mv.addObject("roles", roles);
		return mv;
	}
	
}
