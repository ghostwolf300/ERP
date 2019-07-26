package org.erp.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

import org.erp.userrole.UserRole;

@Entity
@Table(name="t_role")
//@SqlResultSetMapping(
//name="RolesNotAssigned",
//classes= {
//		@ConstructorResult(targetClass=org.erp.role.Role.class,
//				columns={
//					@ColumnResult(name="id"),
//					@ColumnResult(name="name"),
//					@ColumnResult(name="description")
//		})
//})
@NamedNativeQuery(
		name="RolesAssigned",
		query="SELECT t_role.id, t_role.name,t_role.description "
				+ "FROM t_role LEFT JOIN t_user_role ON t_role.id=t_user_role.role_id "
				+ "WHERE t_user_role.user_id=:username NULL "
				+ "GROUP BY t_role.id,t_role.name,t_role.description",
		resultSetMapping="RolesAssignedResult"
)
@NamedNativeQuery(
		name="RolesNotAssigned",
		query="SELECT t_role.id, t_role.name,t_role.description "
				+ "FROM t_role LEFT JOIN t_user_role ON t_role.id=t_user_role.role_id "
				+ "WHERE t_user_role.user_id<>:username OR t_user_role.user_id IS NULL "
				+ "GROUP BY t_role.id,t_role.name,t_role.description",
		resultSetMapping="RolesNotAssignedResult"
)
@SqlResultSetMapping(
name="RolesAssignedResult",
entities={
		@EntityResult(
				entityClass=org.erp.role.Role.class
		)
})
@SqlResultSetMapping(
name="RolesNotAssignedResult",
entities={
		@EntityResult(
				entityClass=org.erp.role.Role.class
		)
})
public class Role {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="role")
	private Set<UserRole> userRoles;
	
	
	public Role() {
		
	}
	
	public Role(int id,String name,String description) {
		this.id=id;
		this.name=name;
		this.description=description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}
