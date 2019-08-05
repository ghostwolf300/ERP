package org.erp.materialgroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("materialGroupService")
public class MaterialGroupServiceImpl implements MaterialGroupService {
	
	@Autowired
	private MaterialGroupRepository materialGroupRepository;
	
	@Override
	public Set<MaterialGroupDTO> findAllMaterialGroups() {
		List<MaterialGroup> materialGroups=materialGroupRepository.findAll();
		Set<MaterialGroupDTO> dtoGroups=new HashSet<MaterialGroupDTO>();
		for(MaterialGroup materialGroup : materialGroups) {
			MaterialGroupDTO dtoGroup=new MaterialGroupDTO(materialGroup);
			dtoGroups.add(dtoGroup);
		}
		return dtoGroups;
	}

}
