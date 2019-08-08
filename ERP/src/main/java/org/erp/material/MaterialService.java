package org.erp.material;

import java.util.List;

public interface MaterialService {
	
	public MaterialDTO findMaterial(String id);
	public List<MaterialSearchResultDTO> searchMaterials(MaterialSearchParamDTO param);
	public MaterialDTO updateMaterial(MaterialDTO material);
	public MaterialDTO createMaterial(MaterialDTO material);
	public int deleteMaterial(String id);
	
}
