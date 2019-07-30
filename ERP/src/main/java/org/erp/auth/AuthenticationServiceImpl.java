package org.erp.auth;

import java.util.ArrayList;
import java.util.List;

import org.erp.user.User;
import org.erp.user.UserRepository;
import org.erp.userrole.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findById(username);
		if(user==null) {
			//TODO:add logic here
			throw new UsernameNotFoundException(username);
		}
		else if(!user.isEnabled()) {
			throw new DisabledException(username);
		}
		else if(accountExpired(user)) {
			throw new AccountExpiredException(username);
		}
		else if(user.isLocked()) {
			System.out.println("locked");
			throw new LockedException(username);
			
		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), user.isEnabled(),true,true,true,getAuthorities(user));
	}
	
	private boolean accountExpired(User user) {
		//TODO: add logic here
		return false;
	}
	
	private List<GrantedAuthority> getAuthorities(User user){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		for(UserRole role : user.getUserRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
		}
		//authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

}
