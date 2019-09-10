package org.erp.bp;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.erp.bpaddress.BPAddress;

@Entity
@Table(name="t_business_partner")
public class BusinessPartner {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="group_id")
	private int groupId;
	@Column(name="name_1")
	private String name1;
	@Column(name="name_2")
	private String name2;
	@Column(name="name_3")
	private String name3;
	@Column(name="name_4")
	private String name4;
	@Column(name="keyword_1")
	private String keyword1;
	@Column(name="keyword_2")
	private String keyword2;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_ts")
	private Timestamp createdTs;
	@Column(name="changed_by")
	private String changedBy;
	@Column(name="changed_ts")
	private Timestamp changedTs;
	
	@OneToMany(
			mappedBy="businessPartner",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	private Set<BPAddress> addresses;
	
	
	public BusinessPartner() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
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

}
