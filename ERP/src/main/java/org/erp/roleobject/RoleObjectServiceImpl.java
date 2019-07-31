package org.erp.roleobject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleObjectService")
public class RoleObjectServiceImpl implements RoleObjectService {
	
	
	@Autowired
	private RoleObjectRepository roleObjectRepository;
	
	@Override
	public List<RoleObjectDTO> findRoleObjects(int roleId) {
		List<RoleObject> roleObjects=roleObjectRepository.findByIdRoleId(roleId);
		List<RoleObjectDTO> dtoRoleObjects=new ArrayList<RoleObjectDTO>();
		for(RoleObject ro : roleObjects) {
			System.out.println(ro.getAuthObject().getId()+" "+ro.getAuthObject().getName());
			RoleObjectDTO dtoRo=new RoleObjectDTO(ro);
			dtoRoleObjects.add(dtoRo);
		}
		return dtoRoleObjects;
	}

}
