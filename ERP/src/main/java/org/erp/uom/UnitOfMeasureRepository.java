package org.erp.uom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Integer>, UnitOfMeasureRepositoryCustom {

}
