package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.erp.userrole.UserRole;

public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User updateUser(User user) {
		System.out.println("DB "+user.getId()+" Updating basic data");
		User u=(User)em.find(User.class,user.getId());
		//User u=em.merge(user);
		u.setFirstName(user.getFirstName()); 
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail()); 
		u.setInitialPw(user.isInitialPw());
		u.setLocked(user.isLocked()); 
		u.setValidFrom(user.getValidFrom());
		u.setValidTo(user.getValidTo()); 
		u.setChangedTs(user.getChangedTs());
		u.setChangedBy(user.getChangedBy()); 
		u.setUserRoles(user.getUserRoles());
		
		em.flush();
		return u;
	}
	
	@Override
	public User mergeUser(User user) {
		User u=em.merge(user);
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

	@Override
	public int updatePassword(String userId, String password, boolean initial, Date pwChanged, Timestamp changedTs,
			String changedBy) {
		System.out.println("DB "+userId+" Updating password");
		User u=(User)em.find(User.class,userId);
		u.setPassword(password);
		u.setPwChanged(pwChanged);
		u.setInitialPw(initial);
		u.setChangedTs(changedTs);
		u.setChangedBy(changedBy);
		em.flush();
		return -1;
	}

	@Override
	public List<UserSearchResultDTO> searchUsers(UserSearchParamDTO param) {
		Query qry=em.createNamedQuery("SearchUsers");
		param.cleanParameters();
		qry.setParameter("id", param.getId());
		qry.setParameter("first_name", param.getFirstName());
		qry.setParameter("last_name", param.getLastName());
		qry.setParameter("email", param.getEmail());
		@SuppressWarnings("unchecked")
		List<UserSearchResultDTO> searchResults=qry.getResultList();
		return searchResults;
	}

	
	

}
