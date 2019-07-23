package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User updateUser(User user) {
		System.out.println("DB "+user.getId()+" Updating basic data");
		User u=(User)em.find(User.class,user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setInitialPw(user.isInitialPw());
		u.setLocked(user.isLocked());
		u.setValidFrom(user.getValidFrom());
		u.setValidTo(user.getValidTo());
		u.setChangedTs(user.getChangedTs());
		u.setChangedBy(user.getChangedBy());
		em.flush();
		return u;
	}

	@Override
	public int updatePassword(String userId, String password,Date pwChanged,Timestamp changedTs,String changedBy) {
		System.out.println("DB "+userId+" Updating password");
		User u=(User)em.find(User.class,userId);
		u.setPassword(password);
		u.setPwChanged(pwChanged);
		u.setChangedTs(changedTs);
		u.setChangedBy(changedBy);
		em.flush();
		return -1;
	}
	

}
