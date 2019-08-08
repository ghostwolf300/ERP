package org.erp.material;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.erp.component.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	private MaterialRepository materialRepository;
	
	@Autowired
	private IAuthenticationFacade authFacade;
	
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
			
			Authentication auth=authFacade.getAuthentication();
			Timestamp changedTs=new Timestamp(System.currentTimeMillis());
			String changedBy=auth.getName();
			
			material.setChangedTs(changedTs);
			material.setChangedBy(changedBy);
			
			material=materialRepository.mergeMaterial(material);
			return new MaterialDTO(material);
		}
		else {
			return null;
		}
	}

	@Override
	public MaterialDTO createMaterial(MaterialDTO dtoMaterial) {
		Material material=new Material(dtoMaterial);
		
		Authentication auth=authFacade.getAuthentication();
		Timestamp createdTs=new Timestamp(System.currentTimeMillis());
		String createdBy=auth.getName();
		Timestamp changedTs=createdTs;
		String changedBy=createdBy;
		
		material.setCreatedTs(createdTs);
		material.setCreatedBy(createdBy);
		material.setChangedTs(changedTs);
		material.setChangedBy(changedBy);
		
		material=materialRepository.save(material);
		MaterialDTO dtoNew=new MaterialDTO(material);
		
		return dtoNew;
	}

	@Override
	public List<MaterialSearchResultDTO> searchMaterials(MaterialSearchParamDTO param) {
		List<MaterialSearchResultDTO> searchResult=materialRepository.searchMaterials(param);
		return searchResult;
	}

	@Override
	public int deleteMaterial(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
