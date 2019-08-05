package org.erp.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/material")
public class MaterialRestController {
	
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping("/findById")
	public ResponseEntity<MaterialDTO> findMaterial(@RequestParam(value="materialId") String materialId){
		MaterialDTO material=materialService.findMaterial(materialId);
		if(material==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<MaterialDTO>(material,HttpStatus.OK);
	}
	
}
