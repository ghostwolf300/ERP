package org.erp.material;

import java.util.Set;

public interface MaterialService {
	
	public MaterialDTO findMaterial(String id);
	public Set<MaterialSearchResultDTO> searchMaterials(String name);
	public MaterialDTO updateMaterial(MaterialDTO material);
	public MaterialDTO createMaterial(MaterialDTO material);
	public int deleteMaterial(String id);
	
}
