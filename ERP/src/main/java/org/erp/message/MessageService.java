package org.erp.message;

import java.util.List;

public interface MessageService {
	
	public void addMessage(MessageDTO message);
	public List<MessageDTO> getMessages();
	
}
