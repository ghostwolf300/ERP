package org.erp.material;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.erp.materialgroup.MaterialGroup;
import org.erp.materialtype.MaterialType;
import org.erp.uom.UnitOfMeasure;

@Entity
@Table(name="t_material")
@NamedNativeQuery(
		name="SearchMaterials",
		query="SELECT t_material.id AS id,"
				+ "t_material.name AS name,"
				+ "t_material.legacy_id AS legacy_id,"
				+ "t_material.ean_13 AS ean_13,"
				+ "t_material_type.short_name AS type_name,"
				+ "t_material_group.name AS group_name "
				+ "FROM t_material "
				+ "INNER JOIN t_material_type ON t_material.type_id=t_material_type.id "
				+ "LEFT JOIN t_material_group ON t_material.group_id=t_material_group.id "
				+ "WHERE (:id is null or t_material.id like :id) "
				+ "AND (:name is null or t_material.name like :name) "
				+ "AND (:legacyId is null or t_material.legacy_id like :legacyId)"
				+ "ORDER BY t_material.name ASC",
		resultSetMapping="SearchMaterialsResults"
)
@SqlResultSetMapping(
name="SearchMaterialsResults",
classes=@ConstructorResult(
		targetClass=MaterialSearchResultDTO.class, 
		columns = { 
				@ColumnResult(name="id"),
				@ColumnResult(name="name"),
				@ColumnResult(name="legacy_id"),
				@ColumnResult(name="ean_13"),
				@ColumnResult(name="type_name"),
				@ColumnResult(name="group_name") 
		}
	)
)
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
	
	public Material(MaterialDTO material) {
		this.id=material.getId();
		this.name=material.getName();
		this.ean13=material.getEan13();
		this.legacyId=material.getLegacyId();
		this.typeId=material.getMaterialType().getId();
		this.materialType=new MaterialType(material.getMaterialType());
		this.groupId=material.getMaterialGroup().getId();
		this.materialGroup=new MaterialGroup(material.getMaterialGroup());
		this.baseUomId=material.getBaseUom().getId();
		this.baseUom=new UnitOfMeasure(material.getBaseUom());
		this.grossWeight=material.getGrossWeight();
		this.netWeight=material.getNetWeight();
		this.weightUomId=material.getWeightUom().getId();
		this.weightUom=new UnitOfMeasure(material.getWeightUom());
		this.length=material.getLength();
		this.width=material.getWidth();
		this.height=material.getHeight();
		this.dimUomId=material.getDimUom().getId();
		this.dimUom=new UnitOfMeasure(material.getDimUom());
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
