package org.erp.uom;

import java.util.Set;

public interface UnitOfMeasureService {
	
	public Set<UnitOfMeasureDTO> findAllUnitsOfMeasure();
	public Set<UnitOfMeasureDTO> findUnitsOfMeasureFor(String uomFor);

}
