package org.erp.user;

import org.springframework.transaction.annotation.Transactional;

public interface UserRepositoryCustom {
	
	@Transactional
	public User updateUser(User user);
	@Transactional
	public int updatePassword(String userId,String password);
}
