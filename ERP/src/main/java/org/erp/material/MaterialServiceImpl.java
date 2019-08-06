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

	@Override
	public MaterialDTO updateMaterial(MaterialDTO dtoMaterial) {
		Optional<Material> opt=materialRepository.findById(dtoMaterial.getId());
		if(opt.isPresent()) {
			Material material=opt.get();
			material.setName(dtoMaterial.getName());
			material.setEan13(dtoMaterial.getEan13());
			material.setLegacyId(dtoMaterial.getLegacyId());
			material.setBaseUomId(dtoMaterial.getBaseUom().getId());
			material.setTypeId(dtoMaterial.getMaterialType().getId());
			material.setGroupId(dtoMaterial.getMaterialGroup().getId());
			material.setGrossWeight(dtoMaterial.getGrossWeight());
			material.setNetWeight(dtoMaterial.getNetWeight());
			material.setWeightUomId(dtoMaterial.getWeightUom().getId());
			material.setLength(dtoMaterial.getLength());
			material.setWidth(dtoMaterial.getWidth());
			material.setHeight(dtoMaterial.getHeight());
			material.setDimUomId(dtoMaterial.getDimUom().getId());
			material=materialRepository.mergeMaterial(material);
			return new MaterialDTO(material);
		}
		else {
			return null;
		}
	}

	@Override
	public MaterialDTO createMaterial(MaterialDTO dtoMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

}
