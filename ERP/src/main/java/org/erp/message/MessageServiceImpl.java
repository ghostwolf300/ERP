package org.erp.message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	private final List<MessageDTO> messages=new ArrayList<MessageDTO>(); 
	private final Queue<MessageDTO> messageQueue=new LinkedList<MessageDTO>();
	
	
	public void addMessage(MessageDTO message) {
		if(message!=null) {
			messages.add(message);
			messageQueue.add(message);
		}
	}
	
	public List<MessageDTO> getMessages(){
		return messages;
	}

	@Override
	public MessageDTO getLastMessage() {
		// TODO Auto-generated method stub
		MessageDTO message=messageQueue.poll();
		return message;
	}
	
	
	
}
