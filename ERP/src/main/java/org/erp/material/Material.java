package org.erp.material;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.erp.materialgroup.MaterialGroup;
import org.erp.materialtype.MaterialType;
import org.erp.uom.UnitOfMeasure;

@Entity
@Table(name="t_material")
public class Material {
	
	@Id
	@Column(name="id")
	private String id;
	@Column(name="legacy_id")
	private String legacyId;
	@Column(name="name")
	private String name;
	@Column(name="ean_13")
	private String ean13;
	@Column(name="type_id")
	private int typeId;
	@Column(name="group_id")
	private Integer groupId;
	@Column(name="parent_id")
	private String parentId;
	@Column(name="base_uom_id")
	private int baseUomId;
	@Column(name="gross_weight")
	private double grossWeight;
	@Column(name="net_weight")
	private double netWeight;
	@Column(name="wt_uom_id")
	private Integer weightUomId;
	@Column(name="length")
	private double length;
	@Column(name="width")
	private double width;
	@Column(name="height")
	private double height;
	@Column(name="dim_uom_id")
	private Integer dimUomId;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_ts")
	private Timestamp createdTs;
	@Column(name="changed_by")
	private String changedBy;
	@Column(name="changed_ts")
	private Timestamp changedTs;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("type_id")
	@JoinColumn(name="type_id")
	private MaterialType materialType;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("group_id")
	@JoinColumn(name="group_id")
	private MaterialGroup materialGroup;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("base_uom_id")
	@JoinColumn(name="base_uom_id")
	private UnitOfMeasure baseUom;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("wt_uom_id")
	@JoinColumn(name="wt_uom_id")
	private UnitOfMeasure weightUom;
	
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("dim_uom_id")
	@JoinColumn(name="dim_uom_id")
	private UnitOfMeasure dimUom;
	
	public Material() {
		
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

	public void setEan13(String ean) {
		this.ean13 = ean;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getBaseUomId() {
		return baseUomId;
	}

	public void setBaseUomId(int baseUomId) {
		this.baseUomId = baseUomId;
	}

	public double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	public Integer getWeightUomId() {
		return weightUomId;
	}

	public void setWeightUomId(Integer weightUomId) {
		this.weightUomId = weightUomId;
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

	public Integer getDimUomId() {
		return dimUomId;
	}

	public void setDimUomId(Integer dimensionUomId) {
		this.dimUomId = dimensionUomId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Timestamp getChangedTs() {
		return changedTs;
	}

	public void setChangedTs(Timestamp changedTs) {
		this.changedTs = changedTs;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public MaterialGroup getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(MaterialGroup materialGroup) {
		this.materialGroup = materialGroup;
	}

	public UnitOfMeasure getBaseUom() {
		return baseUom;
	}

	public void setBaseUom(UnitOfMeasure baseUom) {
		this.baseUom = baseUom;
	}

	public UnitOfMeasure getWeightUom() {
		return weightUom;
	}

	public void setWeightUom(UnitOfMeasure weightUom) {
		this.weightUom = weightUom;
	}

	public UnitOfMeasure getDimUom() {
		return dimUom;
	}

	public void setDimUom(UnitOfMeasure dimUom) {
		this.dimUom = dimUom;
	}
	
}
