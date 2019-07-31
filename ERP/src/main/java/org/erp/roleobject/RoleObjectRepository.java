package org.erp.roleobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleObjectRepository extends JpaRepository<RoleObject, RoleObjectKey>,RoleObjectRepositoryCustom {
	
	public List<RoleObject> findByIdRoleId(int roleId);

}
