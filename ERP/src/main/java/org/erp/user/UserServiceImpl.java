package org.erp.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createNewUser(UserDTO account) {
		if(usernameExists(account.getUsername())){
			//TODO: add logic here
		}
		User user=new User();
		user.setId(account.getUsername());
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setEmail(account.getEmail());
		user.setPassword(passwordEncoder.encode(account.getPassword()));
	
		return user;
	}
	
	private boolean usernameExists(String username) {
		//TODO: add logic here
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findById(username);
		if(user==null) {
			//TODO:add logic here
		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), user.isEnabled(),true,true,true,getAuthorities());

	}
	
	private List<GrantedAuthority> getAuthorities(){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

}
