package org.erp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/edit/save", method=RequestMethod.POST)
	public ResponseEntity<UserDTO> saveUser(@RequestParam("create") boolean create,@RequestBody UserDTO user){
		User u=null;
		if(create) {
			System.out.println("Create new user");
			System.out.println(user.getUsername());
			u=userService.createUser(user);
		}
		else {
			System.out.println("Update existing user");
			u=userService.saveUser(user);
		}
		UserDTO usr=new UserDTO(u);
		return new ResponseEntity<UserDTO>(usr,HttpStatus.OK);
	}
	
}
