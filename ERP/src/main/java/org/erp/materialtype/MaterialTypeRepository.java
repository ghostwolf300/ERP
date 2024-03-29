package org.erp.materialtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Integer>,MaterialTypeRepositoryCustom {
	
}
