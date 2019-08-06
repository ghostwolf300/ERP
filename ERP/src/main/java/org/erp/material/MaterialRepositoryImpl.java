package org.erp.material;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MaterialRepositoryImpl implements MaterialRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Material mergeMaterial(Material material) {
		Material m=em.merge(material);
		return m;
	}

}
