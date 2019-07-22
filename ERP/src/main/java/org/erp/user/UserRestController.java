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
		UserDTO user=userService.findUser(userId);
		if(user==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit/save", method=RequestMethod.POST)
	public ResponseEntity<UserDTO> saveUser(@RequestParam("create") boolean create,@RequestBody UserDTO user){
		UserDTO u=null;
		if(create) {
			System.out.println("Create new user");
			System.out.println(user.getUsername());
			u=userService.createUser(user);
			if(u!=null) {
				createSuccessMessageNew(u);
				return new ResponseEntity<UserDTO>(u,HttpStatus.OK);
			}
			else {
				createFailureMessageNew(u);
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
		else {
			System.out.println("Update existing user");
			u=userService.saveUser(user);
			if(u!=null) {
				createSuccessMessageChange(u);
				return new ResponseEntity<UserDTO>(u,HttpStatus.OK);
			}
			else {
				createFailureMessageChange(u);
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
	}
	
	private void createSuccessMessageNew(UserDTO user) {
		MessageDTO message=new MessageDTO();
		message.setTs(new Timestamp(System.currentTimeMillis()));
		message.setType(MessageDTO.Type.SUCCESS);
		message.setMessageText("User "+user.getUsername()+" created.");
		messageService.addMessage(message);
	}
	
	private void createSuccessMessageChange(UserDTO user) {
		MessageDTO message=new MessageDTO();
		message.setTs(new Timestamp(System.currentTimeMillis()));
		message.setType(MessageDTO.Type.SUCCESS);
		message.setMessageText("User "+user.getUsername()+" updated.");
		messageService.addMessage(message);
	}
	
	private void createFailureMessageNew(UserDTO user) {
		MessageDTO message=new MessageDTO();
		message.setTs(new Timestamp(System.currentTimeMillis()));
		message.setType(MessageDTO.Type.FAILURE);
		message.setMessageText("Failed to create user "+user.getUsername());
		messageService.addMessage(message);
	}
	
	private void createFailureMessageChange(UserDTO user) {
		MessageDTO message=new MessageDTO();
		message.setTs(new Timestamp(System.currentTimeMillis()));
		message.setType(MessageDTO.Type.FAILURE);
		message.setMessageText("Failed to update user "+user.getUsername());
		messageService.addMessage(message);
	}
	
}
