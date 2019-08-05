package org.erp.security;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}
	
	public boolean hasAccess(int objId,String reqPermission) {
		System.out.println("Must have object: "+objId+" permission: "+reqPermission);
		User user=(User)this.getPrincipal();
		for(GrantedAuthority authority : user.getAuthorities()) {
			System.out.println(authority.getAuthority());
			if(hasAuthority(authority.getAuthority(),objId,reqPermission)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasAuthority(String authority,int objId,String reqPermission) {
		
		String[] authArray=authority.split(";");
		int authObjId=Integer.parseInt(authArray[0]);
		
		if(authObjId==objId) {
			Set<String> validPermissions=new HashSet<String>();
			if(reqPermission.equals("display")) {
				validPermissions.add("display");
				validPermissions.add("update");
				validPermissions.add("create");
				validPermissions.add("delete");
			}
			else if(reqPermission.equals("update")) {
				validPermissions.add("update");
			}
			else if(reqPermission.equals("create")){
				validPermissions.add("create");
			}
			else if(reqPermission.equals("delete")) {
				validPermissions.add("delete");
			}
			
			for(String validPermission : validPermissions) {
				if(validPermission.equals("display") && hasPermission(authArray[1])) {
					return true;
				}
				else if(validPermission.equals("update") && hasPermission(authArray[2])) {
					return true;
				}
				else if(validPermission.equals("create") && hasPermission(authArray[3])) {
					return true;
				}
				else if(validPermission.equals("delete") && hasPermission(authArray[4])) {
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	private boolean hasPermission(String permission) {
		String[] permArray=permission.split("=");
		return Boolean.parseBoolean(permArray[1]);
	}
	
	public boolean hasViewAccess(String objName) {
		System.out.println("hasViewAccess object: "+objName);
		//User user=(User)this.getPrincipal();
		return true;
	}
	
	public boolean hasWriteAccess(String objName) {
		System.out.println("hasWriteAccess object: "+objName);
		//User user=(User)this.getPrincipal();
		return true;
	}
	
	public boolean hasOrganisationalAccess(String objName) {
		System.out.println("hasOrganisationalAccess object: "+objName);
		//User user=(User)this.getPrincipal();
		return true;
	}
	
	@Override
	public void setFilterObject(Object filterObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getFilterObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getReturnObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return null;
	}

}
