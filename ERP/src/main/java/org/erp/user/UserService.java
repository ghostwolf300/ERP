package org.erp.user;

public interface UserService {
	
	public User createNewUser(UserDTO account);
	public User findUser(String userId);
	
}
