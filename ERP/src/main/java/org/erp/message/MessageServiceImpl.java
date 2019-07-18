package org.erp.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	private final List<MessageDTO> messages=new ArrayList<MessageDTO>(); 
	
	
	public void addMessage(MessageDTO message) {
		if(message!=null) {
			messages.add(message);
		}
	}
	
	public List<MessageDTO> getMessages(){
		return messages;
	}
	
}
