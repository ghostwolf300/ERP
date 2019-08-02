package org.erp.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleRestController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<RoleDTO> saveRole(@RequestParam("create") boolean create,@RequestBody RoleDTO role) {
		System.out.println(role);
		RoleDTO r=roleService.saveRole(role);
		if(r!=null) {
			return new ResponseEntity<RoleDTO>(r,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
}
