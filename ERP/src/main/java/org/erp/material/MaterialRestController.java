package org.erp.material;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/save")
	public ResponseEntity<MaterialDTO> saveMaterial(@RequestParam("create") boolean create,@RequestBody MaterialDTO material){
		MaterialDTO m=null;
		if(create) {
			m=materialService.createMaterial(material);
		}
		else {
			m=materialService.updateMaterial(material);
		}
		if(m==null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<MaterialDTO>(material,HttpStatus.OK);
	}
	
	@RequestMapping("/search")
	public ResponseEntity<List<MaterialSearchResultDTO>> searchMaterials(@RequestBody MaterialSearchParamDTO param){
		System.out.println("MaterialRestController searchMaterials");
		List<MaterialSearchResultDTO> searchResults=materialService.searchMaterials(param);
		if(searchResults==null || searchResults.size()==0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MaterialSearchResultDTO>>(searchResults,HttpStatus.OK);
	}
	
}
