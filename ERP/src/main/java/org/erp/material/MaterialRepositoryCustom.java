package org.erp.material;

import java.util.List;

import javax.transaction.Transactional;

public interface MaterialRepositoryCustom {
	
	public List<MaterialSearchResultDTO> searchMaterials(MaterialSearchParamDTO param);
	
	@Transactional
	public Material mergeMaterial(Material material);
	
}
