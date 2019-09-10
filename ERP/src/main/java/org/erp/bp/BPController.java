package org.erp.bp;

import org.erp.controller.NavController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bp")
public class BPController {
	
	public static final int AUTH_OBJ_ID=3;
	
	@PreAuthorize("hasAccess("+AUTH_OBJ_ID+",'display')")
	@RequestMapping("/select")
	public ModelAndView selectBP(@RequestParam(value="bpId",required=false) String bpId) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewName", "BP Select");
		mv.addObject("viewId", NavController.Views.BP_SELECT);
		mv.addObject("bpId", bpId);
		mv.setViewName("bp_select");
		System.out.println("BP controller received call");
		return mv;
	}
	
}
