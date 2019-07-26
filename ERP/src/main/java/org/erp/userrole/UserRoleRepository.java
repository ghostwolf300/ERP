package org.erp.userrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRoleRepository extends JpaRepository<UserRole,UserRoleKey> {
	
	@Transactional
	public int removeByUserId(String userId);
	
	@Transactional
	public int removeByRoleId(int roleId);
	
}
