package org.erp.uom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("uomService")
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	@Autowired
	private UnitOfMeasureRepository uomRepository;
	
	@Override
	public Set<UnitOfMeasureDTO> findUnitsOfMeasureFor(String uomFor) {
		List<UnitOfMeasure> uoms=uomRepository.findByUomFor(uomFor);
		Set<UnitOfMeasureDTO> dtoUoms=new HashSet<UnitOfMeasureDTO>();
		for(UnitOfMeasure uom : uoms) {
			UnitOfMeasureDTO dtoUom=new UnitOfMeasureDTO(uom);
			dtoUoms.add(dtoUom);
		}
		return dtoUoms;
	}

	@Override
	public Set<UnitOfMeasureDTO> findAllUnitsOfMeasure() {
		List<UnitOfMeasure> uoms=uomRepository.findAll();
		Set<UnitOfMeasureDTO> dtoUoms=new HashSet<UnitOfMeasureDTO>();
		for(UnitOfMeasure uom : uoms) {
			UnitOfMeasureDTO dtoUom=new UnitOfMeasureDTO(uom);
			dtoUoms.add(dtoUom);
		}
		return dtoUoms;
	}

}
