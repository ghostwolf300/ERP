package org.erp.user;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
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

}
