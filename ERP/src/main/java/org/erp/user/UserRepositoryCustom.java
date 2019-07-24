package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.transaction.annotation.Transactional;

public interface UserRepositoryCustom {
	
	@Transactional
	public User updateUser(User user);
	@Transactional
	public int updatePassword(String userId,String password,Date pwChange,Timestamp changedTs,String changedBy);
	@Transactional
	public int updatePassword(String userId,String password,boolean initial,Date pwChange,Timestamp changedTs,String changedBy);
}
