package org.erp.user;

public class UserServiceImpl implements UserService {

	@Override
	public UserDTO createNewUser(UserDTO account) {
		if(usernameExists(account.getUsername())){
			//TODO: add logic here
		}
		User user=new User();
		
		return null;
	}
	
	private boolean usernameExists(String username) {
		//TODO: add logic here
		return false;
	}

}
