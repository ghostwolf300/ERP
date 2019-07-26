package org.erp.role;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class RoleRepositoryImpl implements RoleRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	/*Native query
	 * Entity:
	 * Define SQL in @NamedNativeQuery 
	 * Define Result mapping @SqlResultSetMapping
	 * 
	 */
	
	@Override
	public List<Role> findRolesAssigned(String username) {
		Query qry=em.createNamedQuery("RolesAssigned");
		qry.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Role> roles=qry.getResultList();
		return roles;
	}
	
	@Override
	public List<Role> findRolesNotAssigned(String username) {
		Query qry=em.createNamedQuery("RolesNotAssigned");
		qry.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Role> roles=qry.getResultList();
		return roles;
	}
	
	/* Another way to do native query
	 * SQL text here (no Entity @NamedNativeQuery needed)
	 * Entity : Define Result mapping @SqlResultSetMapping still needed
	 * Note 'em.createNativeQuery' instead of 'em.createNamedQuery'
	 */
	/*@Override
	public List<Role> findRolesAssigned(String username) {
		String sql="SELECT t_role.id, t_role.name,t_role.description "
				+ "FROM t_role LEFT JOIN t_user_role ON t_role.id=t_user_role.role_id "
				+ "WHERE t_user_role.user_id=:username NULL "
				+ "GROUP BY t_role.id,t_role.name,t_role.description";
		Query qry=em.createNativeQuery("RolesAssigned");
		qry.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Role> roles=qry.getResultList();
		return roles;
	}
	
	@Override
	public List<Role> findRolesNotAssigned(String username) {
		String sql="SELECT t_role.id, t_role.name,t_role.description "
				+ "FROM t_role LEFT JOIN t_user_role ON t_role.id=t_user_role.role_id "
				+ "WHERE t_user_role.user_id<>:username OR t_user_role.user_id IS NULL "
				+ "GROUP BY t_role.id,t_role.name,t_role.description";
		Query qry=em.createNativeQuery("RolesNotAssigned");
		qry.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Role> roles=qry.getResultList();
		return roles;
	}*/
	
}
