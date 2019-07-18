package org.erp.user;

import java.sql.Timestamp;

import org.erp.message.MessageDTO;
import org.erp.message.MessageService;
import org.erp.message.MessageServiceImpl;
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
	
	@Autowired
	private MessageService messageService;
	
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
		if(u!=null) {
			UserDTO usr=new UserDTO(u);
			createSuccessMessage(usr);
			return new ResponseEntity<UserDTO>(usr,HttpStatus.OK);
		}
		else {
			createFailureMessage(user);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private void createSuccessMessage(UserDTO user) {
		MessageDTO message=new MessageDTO();
		message.setTs(new Timestamp(System.currentTimeMillis()));
		message.setType(MessageDTO.Type.SUCCESS);
		message.setMessageText("User "+user.getUsername()+" created.");
		messageService.addMessage(message);
	}
	
	private void createFailureMessage(UserDTO user) {
		MessageDTO message=new MessageDTO();
		message.setTs(new Timestamp(System.currentTimeMillis()));
		message.setType(MessageDTO.Type.FAILURE);
		message.setMessageText("Failed to create user "+user.getUsername());
		messageService.addMessage(message);
	}
	
}
