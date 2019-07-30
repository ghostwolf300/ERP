package org.erp.security;


import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
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
