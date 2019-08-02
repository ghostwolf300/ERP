package org.erp.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleRestController {
	
	@Autowired
	private RoleService roleService;
	
	public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role) {
		System.out.println(role);
		RoleDTO r=null;
		return new ResponseEntity<RoleDTO>(r,HttpStatus.OK);
	}
	
}
