package org.erp.authobject;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class AuthObjectRepositoryImpl implements AuthObjectRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AuthObject> findUnassignedObjects(int roleId) {
		Query qry=em.createNamedQuery("UnassignedObjects");
		qry.setParameter("roleId", roleId);
		@SuppressWarnings("unchecked")
		List<AuthObject> objects=qry.getResultList();
		return objects;
	}

}
