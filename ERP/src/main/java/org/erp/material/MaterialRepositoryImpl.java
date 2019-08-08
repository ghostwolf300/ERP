package org.erp.material;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MaterialRepositoryImpl implements MaterialRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Material mergeMaterial(Material material) {
		Material m=em.merge(material);
		return m;
	}

	@Override
	public List<MaterialSearchResultDTO> searchMaterials(MaterialSearchParamDTO param) {
		System.out.println("param id="+param.getId());
		Query qry=em.createNamedQuery("SearchMaterials");
		param.cleanParameters();
		qry.setParameter("id", param.getId());
		qry.setParameter("name", param.getName());
		qry.setParameter("legacyId", param.getLegacyId());
		@SuppressWarnings("unchecked")
		List<MaterialSearchResultDTO> searchResults=qry.getResultList();
		return searchResults;
	}

}
