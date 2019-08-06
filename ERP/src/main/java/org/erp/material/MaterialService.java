package org.erp.material;

public interface MaterialService {
	
	public MaterialDTO findMaterial(String id);
	public MaterialDTO updateMaterial(MaterialDTO material);
	public MaterialDTO createMaterial(MaterialDTO material);
	
}
