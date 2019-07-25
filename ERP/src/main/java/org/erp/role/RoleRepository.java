package org.erp.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>,RoleRepositoryCustom {
	
	public Role findById(int id);
	@Transactional
	public Long removeById(int id);
	
}
