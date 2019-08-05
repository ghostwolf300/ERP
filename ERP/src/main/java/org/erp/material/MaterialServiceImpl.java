package org.erp.material;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	private MaterialRepository materialRepository;
	
	@Override
	public MaterialDTO findMaterial(String id) {
		Optional<Material> matOpt=materialRepository.findById(id);
		Material mat=null;
		MaterialDTO dtoMat=null;
		if(matOpt.isPresent()) {
			mat=matOpt.get();
			dtoMat=new MaterialDTO(mat);
		}
		return dtoMat;
	}

}
