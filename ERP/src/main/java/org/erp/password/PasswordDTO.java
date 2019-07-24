package org.erp.password;

public class PasswordDTO {
	
	private String username;
	private String token;
	private String password;
	
	public PasswordDTO() {
		
	}
	
	public PasswordDTO(String username,String token,String password) {
		this.username=username;
		this.token=token;
		this.password=password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String username) {
		this.token = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
