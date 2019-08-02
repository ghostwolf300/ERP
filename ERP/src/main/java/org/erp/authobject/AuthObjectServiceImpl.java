package org.erp.authobject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authObjectService")
public class AuthObjectServiceImpl implements AuthObjectService {
	
	@Autowired
	private AuthObjectRepository authObjectRepository;
	
	@Override
	public List<AuthObjectDTO> findUnassignedObjects(int roleId) {
		// TODO Auto-generated method stub
		List<AuthObject> authObjects=authObjectRepository.findUnassignedObjects(roleId);
		List<AuthObjectDTO> dtoAuthObjects=new ArrayList<AuthObjectDTO>();
		for(AuthObject ao : authObjects) {
			AuthObjectDTO dtoAu=new AuthObjectDTO(ao);
			 dtoAuthObjects.add(dtoAu);
		}
		return dtoAuthObjects;
	}

}
