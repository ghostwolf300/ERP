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

	@Override
	public UserDTO findUser(String userId) {
		UserDTO u=null;
		User user=userRepository.findById(userId);
		if(user!=null) {
			u=new UserDTO(user);
		}
		return u;
		
	}
	
	@Override
	public UserDTO createUser(UserDTO user) {
		if(usernameExists(user.getUsername())){
			//TODO: add logic here
		}
		User u=new User(user);
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("New user id: "+u.getId());
		u=userRepository.save(u);
		return new UserDTO(u);
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		User u=new User(user);
		u=userRepository.updateUser(u);
		if(user.getPassword()!=null) {
			String pw=passwordEncoder.encode(user.getPassword());
			userRepository.updatePassword(user.getUsername(), pw);
		}
		return new UserDTO(u);
	}

	@Override
	public UserDTO deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

}
