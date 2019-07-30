package org.erp.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class ViewAccessEvaluator implements PermissionEvaluator {
	
	private final Map<String,List<String>> objectRoleMap=new HashMap<String,List<String>>();
	
	public ViewAccessEvaluator() {
		List<String> roles=new ArrayList<String>();
		roles.add("ROLE_ADMIN");
		objectRoleMap.put("OBJ_USER", roles);
	}
	
	//PreAuthorize...
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
		
		//String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
		String targetType = (String) targetDomainObject;
		System.out.println("1. "+targetDomainObject+" "+permission);
		return hasPrivilege(authentication, targetType, permission.toString().toUpperCase());
	}
	
	//PostAuthorize...
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
		System.out.println("2. "+targetId+" "+targetType+" "+permission);
		return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
	}
	
	private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
		List<String> roles=objectRoleMap.get(targetType);
		boolean hasAccess=false;
		for(GrantedAuthority ga : auth.getAuthorities()) {
			System.out.println("has authority: "+ga.getAuthority());
			for(String role : roles) {
				if(role.equals(ga.getAuthority())) {
					hasAccess=true;
					break;
				}
			}
		}
		//testing...
		return true;
		//return hasAccess;
	}

}
