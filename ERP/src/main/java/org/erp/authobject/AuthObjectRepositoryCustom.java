package org.erp.authobject;

import java.util.List;

public interface AuthObjectRepositoryCustom {
	
	public List<AuthObject> findUnassignedObjects(int roleId);
		
	
}
