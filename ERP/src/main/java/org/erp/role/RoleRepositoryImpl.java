package org.erp.role;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class RoleRepositoryImpl implements RoleRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Role> findRolesAssigned(String username) {
		String sql="SELECT r FROM Role r LEFT JOIN UserRole ur ON r.id=ur.roleId WHERE ur.userId=:username GROUP BY r";
		Query qry=em.createNativeQuery(sql,"RolesAssigned");
		qry.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Role> roles=qry.getResultList();
		return roles;
	}
	
	@Override
	public List<Role> findRolesNotAssigned(String username) {
		String sql="SELECT r FROM Role r LEFT JOIN UserRole ur ON r.id=ur.roleId WHERE ur.userId<>:username OR ur IS NULL GROUP BY r";
		Query qry=em.createNativeQuery(sql,"RolesNotAssigned");
		qry.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Role> roles=qry.getResultList();
		return roles;
	}

}
