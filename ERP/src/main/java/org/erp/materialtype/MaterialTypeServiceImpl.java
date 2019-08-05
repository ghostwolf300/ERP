package org.erp.materialtype;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("materialTypeService")
public class MaterialTypeServiceImpl implements MaterialTypeService {
	
	@Autowired
	private MaterialTypeRepository materialTypeRepository;
	
	@Override
	public Set<MaterialTypeDTO> findAllMaterialTypes() {
		List<MaterialType> types=materialTypeRepository.findAll();
		Set<MaterialTypeDTO> dtoTypes=new HashSet<MaterialTypeDTO>();
		for(MaterialType type : types) {
			MaterialTypeDTO dtoType=new MaterialTypeDTO(type);
			dtoTypes.add(dtoType);
		}
		return dtoTypes;
	}

}
