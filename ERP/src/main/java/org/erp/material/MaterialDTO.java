package org.erp.material;

import java.io.Serializable;

import org.erp.materialgroup.MaterialGroupDTO;
import org.erp.materialtype.MaterialTypeDTO;
import org.erp.uom.UnitOfMeasureDTO;

public class MaterialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String legacyId;
	private String name;
	private String ean13;
	private UnitOfMeasureDTO baseUom;
	private MaterialTypeDTO materialType;
	private MaterialGroupDTO materialGroup;
	private double netWeight;
	private double grossWeight;
	private UnitOfMeasureDTO weightUom;
	private double length;
	private double width;
	private double height;
	private UnitOfMeasureDTO dimUom;
	
	
	public MaterialDTO() {
		
	}
	
	public MaterialDTO(String id) {
		this.id=id;
	}
	
	public MaterialDTO(Material m) {
		this.id=m.getId();
		this.legacyId=m.getLegacyId();
		this.name=m.getName();
		this.ean13=m.getEan13();
		this.baseUom=new UnitOfMeasureDTO(m.getBaseUom());
		this.materialType=new MaterialTypeDTO(m.getMaterialType());
		if(m.getMaterialGroup()!=null) {
			this.materialGroup=new MaterialGroupDTO(m.getMaterialGroup());
		}
		this.netWeight=m.getNetWeight();
		this.grossWeight=m.getGrossWeight();
		this.weightUom=new UnitOfMeasureDTO(m.getWeightUom());
		this.length=m.getLength();
		this.width=m.getWidth();
		this.height=m.getHeight();
		this.dimUom=new UnitOfMeasureDTO(m.getDimUom());
	
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLegacyId() {
		return legacyId;
	}


	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEan13() {
		return ean13;
	}


	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}


	public MaterialTypeDTO getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialTypeDTO materialType) {
		this.materialType = materialType;
	}

	public MaterialGroupDTO getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(MaterialGroupDTO materialGroup) {
		this.materialGroup = materialGroup;
	}

	public UnitOfMeasureDTO getBaseUom() {
		return baseUom;
	}

	public void setBaseUom(UnitOfMeasureDTO baseUom) {
		this.baseUom = baseUom;
	}

	public double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	public double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public UnitOfMeasureDTO getWeightUom() {
		return weightUom;
	}

	public void setWeightUom(UnitOfMeasureDTO weightUom) {
		this.weightUom = weightUom;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public UnitOfMeasureDTO getDimUom() {
		return dimUom;
	}

	public void setDimUom(UnitOfMeasureDTO dimUom) {
		this.dimUom = dimUom;
	}
}
