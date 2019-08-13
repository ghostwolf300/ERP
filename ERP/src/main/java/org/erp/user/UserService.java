package org.erp.user;

import java.util.List;

public interface UserService {
	
	public UserDTO findUser(String userId);
	public UserDTO createUser(UserDTO user);
	public UserDTO saveUser(UserDTO user);
	public UserDTO deleteUser(UserDTO user);
	public boolean isInitialPassword(String username);
	public int allowPasswordChangeOnly();
	public int changeOwnPassword(String password);
	public int logout();
	public List<UserSearchResultDTO> searchUsers(UserSearchParamDTO param);
	
}
