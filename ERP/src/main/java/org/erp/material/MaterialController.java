package org.erp.material;

import org.erp.controller.NavController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/material")
public class MaterialController {

	@RequestMapping("/select")
	public ModelAndView selectMaterial() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewName", "Material Select");
		mv.addObject("viewId", NavController.Views.MATERIAL_SELECT);
		mv.setViewName("material_select");
		return mv;
	}
	
	@RequestMapping("/display")
	public ModelAndView displayMaterial(@RequestParam(value="materialId") String materialId) {
		ModelAndView mv=createModelAndView(materialId,3);
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editMaterial(@RequestParam(value="materialId") String materialId) {
		ModelAndView mv=createModelAndView(materialId,2);
		return mv;
	}
	
	@RequestMapping("/create")
	public ModelAndView createMaterial(@RequestParam(value="materialId") String materialId) {
		ModelAndView mv=createModelAndView(materialId,1);
		return mv;
	}
	
	private ModelAndView createModelAndView(String materialId,int action) {
		MaterialDTO material=null;
		material=new MaterialDTO();
		material.setId("T000000100");
		material.setName("Test material 01");
		boolean edit=true;
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewName", "Material data");
		mv.addObject("viewId", NavController.Views.MATERIAL_DATA);
		mv.setViewName("material_data");
		mv.addObject("material", material);
		mv.addObject("canEdit", edit);
		return mv;
	}
	
}
