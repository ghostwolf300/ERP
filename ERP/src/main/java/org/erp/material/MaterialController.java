package org.erp.material;

import org.erp.controller.NavController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/material")
public class MaterialController {
	
	public static final int AUTH_OBJ_ID=2;
	
	@Autowired
	private MaterialService materialService;
	
	@PreAuthorize("hasAccess("+AUTH_OBJ_ID+",'display')")
	@RequestMapping("/select")
	public ModelAndView selectMaterial() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewName", "Material Select");
		mv.addObject("viewId", NavController.Views.MATERIAL_SELECT);
		mv.setViewName("material_select");
		return mv;
	}
	
	@PreAuthorize("hasAccess("+AUTH_OBJ_ID+",'display')")
	@RequestMapping("/display")
	public ModelAndView displayMaterial(@RequestParam(value="materialId") String materialId) {
		ModelAndView mv=createModelAndView(materialId,3);
		return mv;
	}
	
	@PreAuthorize("hasAccess("+AUTH_OBJ_ID+",'update')")
	@RequestMapping("/edit")
	public ModelAndView editMaterial(@RequestParam(value="materialId") String materialId) {
		ModelAndView mv=createModelAndView(materialId,2);
		return mv;
	}
	
	@PreAuthorize("hasAccess("+AUTH_OBJ_ID+",'create')")
	@RequestMapping("/create")
	public ModelAndView createMaterial(@RequestParam(value="materialId") String materialId) {
		ModelAndView mv=createModelAndView(materialId,1);
		return mv;
	}
	
	private ModelAndView createModelAndView(String materialId,int action) {
		MaterialDTO material=null;
		boolean edit=false;
		if(action==3) {
			material=materialService.findMaterial(materialId);
			edit=false;
		}
		else if(action==2) {
			material=materialService.findMaterial(materialId);
			edit=true;
		}
		else if(action==1) {
			material=new MaterialDTO(materialId);
			edit=true;
		}
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewName", "Material data");
		mv.addObject("viewId", NavController.Views.MATERIAL_DATA);
		mv.setViewName("material_data");
		mv.addObject("material", material);
		mv.addObject("canEdit", edit);
		mv.addObject("action", action);
		return mv;
	}
	
}
