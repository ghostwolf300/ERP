package org.erp.role;

import java.util.List;

import org.erp.controller.NavController;
import org.erp.roleobject.RoleObjectDTO;
import org.erp.roleobject.RoleObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleObjectService roleObjectService;
	
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
	
	@RequestMapping("/select")
	public ModelAndView selectRole() {
		ModelAndView mv=new ModelAndView();
		List<RoleDTO> roles=roleService.findAllRolesDTO();
		mv.addObject("viewName", "Role Select");
		mv.addObject("viewId", NavController.Views.ROLE_SELECT);
		mv.setViewName("role_select");
		mv.addObject("roles", roles);
		return mv;
	}
	
	@RequestMapping("/display")
	public ModelAndView displayRole(@RequestParam(value="roleId") int roleId) {
		ModelAndView mv=createModelAndView(roleId,false);
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editRole(@RequestParam(value="roleId") int roleId) {
		ModelAndView mv=createModelAndView(roleId,true);
		return mv;
	}
	
	private ModelAndView createModelAndView(int roleId,boolean edit) {
		RoleDTO role=roleService.findRoleDTO(roleId);
		List<RoleObjectDTO> roleObjects=roleObjectService.findRoleObjects(roleId);
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewName", "Role data");
		mv.addObject("viewId", NavController.Views.ROLE_DATA);
		mv.setViewName("role_data");
		mv.addObject("role", role);
		mv.addObject("roleObjects",roleObjects);
		mv.addObject("canEdit", edit);
		return mv;
	}
	
}
