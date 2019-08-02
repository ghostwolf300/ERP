package org.erp.authobject;

import java.util.List;

public interface AuthObjectService {
	
	public List<AuthObjectDTO> findUnassignedObjects(int roleId);
	
}
