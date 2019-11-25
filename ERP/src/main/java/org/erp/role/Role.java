package org.erp.role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SqlResultSetMapping;

import org.erp.roleobject.RoleObject;
import org.erp.roleobject.RoleObjectDTO;
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
	
	@OneToMany(
			mappedBy="role",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	private Set<UserRole> userRoles;
	
	@OneToMany(
			mappedBy="role",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	private Set<RoleObject> roleObjects;
	
	
	public Role() {
		
	}
	
	public Role(int id,String name,String description) {
		this.id=id;
		this.name=name;
		this.description=description;
	}
	
	public Role(RoleDTO role) {
		this.id=role.getId();
		this.name=role.getName();
	
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

	public Set<RoleObject> getRoleObjects() {
		return roleObjects;
	}

	public void setRoleObjects(Set<RoleObject> roleObjects) {
		this.roleObjects = roleObjects;
	}
	
	public void addRoleObject(RoleObject ro) {
		if(roleObjects==null) {
			roleObjects=new HashSet<RoleObject>();
		}
		roleObjects.add(ro);
	}
	
	public void handleAssignedObjects(Set<RoleObjectDTO> dtoRoleObjects) {
		removeUnassignedObjects(dtoRoleObjects);
		addOrUpdateAssignedObjects(dtoRoleObjects);
	}
	
	private int addOrUpdateAssignedObjects(Set<RoleObjectDTO> assignedObjects) {
		int addCount=0;
		for(RoleObjectDTO dtoObj : assignedObjects) {
			RoleObject roleObject=getRoleObject(dtoObj.getObject().getId());
			if(roleObject!=null) {
				roleObject.setReadRights(dtoObj.isReadRights());
				roleObject.setUpdateRights(dtoObj.isUpdateRights());
				roleObject.setCreateRights(dtoObj.isCreateRights());
				roleObject.setDeleteRights(dtoObj.isDeleteRights());
			}
			else{
				roleObject=new RoleObject(dtoObj);
				addRoleObject(roleObject);
				addCount++;
			}
		}
		return addCount;
	}
	
	private boolean objectExists(int objectId) {
		boolean found=false;
		if(roleObjects!=null && roleObjects.size()>0) {
			for(RoleObject ro : roleObjects) {
				if(ro.getId().getObjectId()==objectId) {
					found=true;
				}
			}
		}
		else {
			found=false;
		}
		
		if(found) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private RoleObject getRoleObject(int objectId) {
		RoleObject roleObject=null;
		for(RoleObject ro : roleObjects) {
			if(ro.getId().getObjectId()==objectId) {
				roleObject=ro;
				break;
			}
		}
		return roleObject;
	}
	
	private int removeUnassignedObjects(Set<RoleObjectDTO> assignedObjects) {
		int removeCount=0;
		Set<RoleObject> unassignedObjects=null;
		
		if(roleObjects==null) {
			return 0;
		}
		if(assignedObjects==null || assignedObjects.size()==0) {
			unassignedObjects=new HashSet<RoleObject>(roleObjects);
		}
		else {
			unassignedObjects=new HashSet<RoleObject>();
			boolean found=false;
			for(RoleObject ro : roleObjects) {
				found=false;
				for(RoleObjectDTO dtoObj : assignedObjects) {
					if(ro.getAuthObject().getId()==dtoObj.getObject().getId()) {
						found=true;
					}
				}
				if(!found) {
					unassignedObjects.add(ro);
				}
			}
		}
		removeCount=unassignedObjects.size();
		roleObjects.removeAll(unassignedObjects);
		
		return removeCount;
	}
	
}
