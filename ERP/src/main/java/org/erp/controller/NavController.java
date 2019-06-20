package org.erp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {
	
	private static enum Views{
		HOME,
		NEW_USER,
		CHANGE_USER
		};
	
	private static final Map<Views,String> VIEW_MAP=createViewMap();
	private static Map<Views, String> createViewMap() {
        Map<Views,String> map = new HashMap<Views,String>();
        map.put(Views.HOME, "home");
        map.put(Views.NEW_USER, "new_user");
        map.put(Views.CHANGE_USER, "change_user");
        return map;
    }
		
	public NavController() {
		
	}
	
	@RequestMapping("/home")
	public String home(Model m) {
		m.addAttribute("viewName", "Home");
		m.addAttribute("viewId",Views.HOME);
		return VIEW_MAP.get(Views.HOME);
	}
	
	@RequestMapping("/new_user")
	public String newUser(Model m) {
		System.out.println("Create new user");
		m.addAttribute("viewName", "New user");
		m.addAttribute("viewId",Views.NEW_USER);
		return VIEW_MAP.get(Views.NEW_USER);
	}
	
	@RequestMapping("/change_user")
	public String changeUser(Model m) {
		System.out.println("Change user");
		m.addAttribute("viewName", "Change user");
		m.addAttribute("viewId",Views.CHANGE_USER);
		return VIEW_MAP.get(Views.CHANGE_USER);
	}
	
	
}
