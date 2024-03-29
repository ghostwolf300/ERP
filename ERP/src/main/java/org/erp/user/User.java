package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.erp.role.Role;
import org.erp.role.RoleDTO;
import org.erp.userrole.UserRole;
import org.erp.userrole.UserRoleKey;

import javax.persistence.JoinColumn;

@Entity
@Table(name="t_user")
@NamedNativeQuery(
		name="SearchUsers",
		query="SELECT t_user.id AS id,"
				+ "t_user.first_name AS first_name,"
				+ "t_user.last_name AS last_name,"
				+ "t_user.email AS email "
				+ "FROM t_user "
				+ "WHERE (:id is null or t_user.id like :id) "
				+ "AND (:first_name is null or t_user.first_name like :first_name) "
				+ "AND (:last_name is null or t_user.last_name like :last_name) "
				+ "AND (:email is null or t_user.email like :email) "
				+ "ORDER BY t_user.last_name,t_user.first_name",
		resultSetMapping="SearchUsersResults"
)
@SqlResultSetMapping(
		name="SearchUsersResults",
		classes=@ConstructorResult(
				targetClass=org.erp.user.UserSearchResultDTO.class,
				columns= {
						@ColumnResult(name="id"),
						@ColumnResult(name="first_name"),
						@ColumnResult(name="last_name"),
						@ColumnResult(name="email")
				}
		)
)

public class User {

	@Id
	@Column(name="id")
	private String id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="enabled")
	private boolean enabled;
	@Column(name="initial_pw")
	private boolean initialPw;
	@Column(name="locked")
	private boolean locked;
	@Column(name="valid_from")
	private Date validFrom;
	@Column(name="valid_to")
	private Date validTo;
	@Column(name="pw_changed")
	private Date pwChanged;
	@Column(name="created_ts", nullable=false,updatable=false)
	private Timestamp createdTs;
	@Column(name="created_by", nullable=false, updatable=false)
	private String createdBy;
	@Column(name="changed_ts")
	private Timestamp changedTs;
	@Column(name="changedBy")
	private String changedBy;
	
//	@ManyToMany
//	@JoinTable(
//			name="t_user_role",
//			joinColumns= {
//					@JoinColumn(name="user_id")
//			},
//			inverseJoinColumns= {
//					@JoinColumn(name="role_id")
//			})
//	Set<Role> userRoles;
	
	@OneToMany(
			mappedBy="user",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	Set<UserRole> userRoles;
	
	public User() {
		
	}
	
	public User(UserDTO user) {
		this.id=user.getUsername();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.email=user.getEmail();
		this.enabled=true;
		this.initialPw=user.isInitialPw();
		this.locked=user.isLocked();
		this.validFrom=user.getValidFrom();
		this.validTo=user.getValidTo();
		this.pwChanged=user.getPwChanged();
		this.createdTs=user.getCreatedTs();
		this.createdBy=user.getCreatedBy();
		this.changedTs=user.getChangedTs();
		this.changedBy=user.getChangedBy();
		/*
		 * if(user.getRoles()!=null) { this.userRoles=new HashSet<UserRole>();
		 * for(RoleDTO r : user.getRoles()) { this.userRoles.add(new UserRole(new
		 * UserRoleKey(user.getUsername(),r.getId()))); } }
		 */
	}
	
	public String getId() {
		return id;
	}
	public void setId(String userName) {
		this.id = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isInitialPw() {
		return initialPw;
	}

	public void setInitialPw(boolean initialPw) {
		this.initialPw = initialPw;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Date getPwChanged() {
		return pwChanged;
	}

	public void setPwChanged(Date pwChanged) {
		this.pwChanged = pwChanged;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getChangedTs() {
		return changedTs;
	}

	public void setChangedTs(Timestamp changedTs) {
		this.changedTs = changedTs;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	
	public void addUserRole(UserRole role) {
		if(userRoles==null) {
			userRoles=new HashSet<UserRole>();
		}
		role.setUser(this);
		userRoles.add(role);
	}
	
	public void removeUserRole(UserRole role) {
		if(userRoles!=null) {
			userRoles.remove(role);
		}
	}
	
	public boolean roleExists(int roleId) {
		boolean found=false;
		if(userRoles!=null && userRoles.size()>0) {
			for(UserRole r : userRoles) {
				if(r.getId().getRoleId()==roleId) {
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
	
	public void handleAssignedRoles(Set<RoleDTO> assignedRoles) {
		removeUnassignedRoles(assignedRoles);
		addAssignedRoles(assignedRoles);
	}
	
	public int addAssignedRoles(Set<RoleDTO> assignedRoles) {
		int addCount=0;
		for(RoleDTO r : assignedRoles) {
			if(!roleExists(r.getId())) {
				Role role=new Role(r.getId(),r.getName(),r.getDescription());
				UserRoleKey userRoleKey=new UserRoleKey(this.getId(),r.getId());
				UserRole userRole=new UserRole(userRoleKey,this,role);
				addUserRole(userRole);
				addCount++;
			}
		}
		return addCount;
	}
	
	public int removeUnassignedRoles(Set<RoleDTO> assignedRoles) {
		int removeCount=0;
		Set<UserRole> unassignedRoles=null;
		
		if(userRoles==null) {
			return 0;
		}
		if(assignedRoles==null || assignedRoles.size()==0) {
			unassignedRoles=new HashSet<UserRole>(userRoles);
		}
		else {
			unassignedRoles=new HashSet<UserRole>();
			boolean found=false;
			for(UserRole ur : userRoles) {
				found=false;
				for(RoleDTO r : assignedRoles) {
					if(ur.getId().getRoleId()==r.getId()) {
						found=true;
					}
				}
				if(!found) {
					unassignedRoles.add(ur);
				}
			}
		}
		removeCount=unassignedRoles.size();
		userRoles.removeAll(unassignedRoles);
		
		return removeCount;
	}

}
