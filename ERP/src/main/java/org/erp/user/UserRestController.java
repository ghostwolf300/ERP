package org.erp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findById")
	public ResponseEntity<UserDTO> getUser(
			@RequestParam(value="userId") String userId){
		UserDTO user=null;
		User u=userService.findUser(userId);
		if(u!=null) {
			user=new UserDTO(u);
		}
		if(user==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
	}
	
}
