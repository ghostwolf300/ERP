package org.erp.user;

public interface UserService {
	
	public UserDTO findUser(String userId);
	public UserDTO createUser(UserDTO user);
	public UserDTO saveUser(UserDTO user);
	public UserDTO deleteUser(UserDTO user);
	
}
