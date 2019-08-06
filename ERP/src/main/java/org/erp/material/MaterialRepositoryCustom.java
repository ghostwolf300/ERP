package org.erp.material;

import javax.transaction.Transactional;

public interface MaterialRepositoryCustom {
	
	@Transactional
	public Material mergeMaterial(Material material);
	
}
