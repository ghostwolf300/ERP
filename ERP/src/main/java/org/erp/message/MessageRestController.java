package org.erp.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/getAll")
	public ResponseEntity<List<MessageDTO>> getAll(){
		List<MessageDTO> messages=messageService.getMessages();
		if(messages!=null && messages.size()>0) {
			return new ResponseEntity<List<MessageDTO>>(messages,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/getLast")
	public ResponseEntity<MessageDTO> getLast(){
		MessageDTO message=messageService.getLastMessage();
		if(message!=null) {
			return new ResponseEntity<MessageDTO>(message,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	

}
