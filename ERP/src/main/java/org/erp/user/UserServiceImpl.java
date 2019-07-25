package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.erp.component.IAuthenticationFacade;
import org.erp.exception.InitialPasswordException;
import org.erp.role.Role;
import org.erp.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAuthenticationFacade authFacade;
	
	
	private boolean usernameExists(String username) {
		//TODO: add logic here
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,AuthenticationException {
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
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), user.isEnabled(),true,true,true,getAuthorities(username));
	}
	
	private boolean accountExpired(User user) {
		return false;
	}
	
	private List<GrantedAuthority> getAuthorities(String username){
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		//authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

	@Override
	public UserDTO findUser(String userId) {
		UserDTO u=null;
		User user=userRepository.findById(userId);
		if(user!=null) {
			System.out.println("test: "+user.getUserRoles().size());
			List<Role> roles=roleRepository.findRolesNotAssigned(userId);
			System.out.println(roles.size());
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
		
		Authentication auth=authFacade.getAuthentication();
		String createdBy=auth.getName();
		Timestamp createdTs=new Timestamp(System.currentTimeMillis());
		Date pwChanged=new Date(System.currentTimeMillis());
		
		u.setCreatedTs(createdTs);
		u.setCreatedBy(createdBy);
		u.setChangedTs(createdTs);
		u.setChangedBy(createdBy);
		u.setPwChanged(pwChanged);
		
		u=userRepository.save(u);
		return new UserDTO(u);
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		User u=new User(user);
	
		Authentication auth=authFacade.getAuthentication();
		Timestamp changedTs=new Timestamp(System.currentTimeMillis());
		String changedBy=auth.getName();
		u.setChangedTs(changedTs);
		u.setChangedBy(changedBy);
		u=userRepository.updateUser(u);
		
		if(user.getPassword()!=null && !user.getPassword().isEmpty()) {
			System.out.println("Updating password...");
			Date pwChanged=new Date(System.currentTimeMillis());
			String pw=passwordEncoder.encode(user.getPassword());
			userRepository.updatePassword(user.getUsername(),pw,pwChanged,changedTs,changedBy);
		}
		return new UserDTO(u);
	}

	@Override
	public UserDTO deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changeOwnPassword(String password) {
		Authentication auth=authFacade.getAuthentication();
		String username=auth.getName();
		Timestamp changedTs=new Timestamp(System.currentTimeMillis());
		String changedBy=auth.getName();
		Date pwChanged=new Date(System.currentTimeMillis());
		String pw=passwordEncoder.encode(password);
		userRepository.updatePassword(username, pw,false, pwChanged, changedTs, changedBy);
		return 0;
	}

	@Override
	public boolean isInitialPassword(String username) {
		User u=userRepository.findById(username);
		if(u.isInitialPw()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int logout() {
		authFacade.getAuthentication().setAuthenticated(false);
		return 0;
	}

	@Override
	public int allowPasswordChangeOnly() {
		Authentication auth=authFacade.getAuthentication();
		
		return 0;
	}

}
