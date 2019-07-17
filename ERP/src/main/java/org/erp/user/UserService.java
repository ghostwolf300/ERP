package org.erp.user;

public interface UserService {
	
	public User findUser(String userId);
	public User createUser(UserDTO user);
	public User saveUser(UserDTO user);
	public User deleteUser(UserDTO user);
	
}
