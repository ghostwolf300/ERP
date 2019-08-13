package org.erp.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.erp.component.IAuthenticationFacade;
import org.erp.exception.InitialPasswordException;
import org.erp.role.Role;
import org.erp.role.RoleDTO;
import org.erp.role.RoleRepository;
import org.erp.userrole.UserRole;
import org.erp.userrole.UserRoleKey;
import org.erp.userrole.UserRoleRepository;
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
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAuthenticationFacade authFacade;
	
	
	private boolean usernameExists(String username) {
		//TODO: add logic here
		return false;
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
		//1. use copy constructor to set basic data
		User u=new User(user);
		
		//2. role array
		List<UserRole> userRoles=new ArrayList<UserRole>();
		for(RoleDTO r : user.getRoles()) {
			//create role
			UserRole role=new UserRole(new UserRoleKey(user.getUsername(),r.getId()));
			//set roles
			role.setRole(new Role(r.getId(),r.getName(),r.getDescription()));
			//set user
			role.setUser(u);
			//add role to list
			userRoles.add(role);
		}
		
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
		//3. set userroles
		u.setUserRoles(new HashSet<UserRole>(userRoles));
		
		u=userRepository.save(u);
		//List<UserRole> savedRoles=userRoleRepository.saveAll(userRoles);
		//u.setUserRoles(new HashSet<UserRole>(savedRoles));
		
		return new UserDTO(u);
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		//fetch user entity (instead of creating new)
		User u=userRepository.findById(user.getUsername());
		
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setInitialPw(user.isInitialPw());
		u.setLocked(user.isLocked());
		u.setValidFrom(user.getValidFrom());
		u.setValidTo(user.getValidTo());
		
		Authentication auth=authFacade.getAuthentication();
		Timestamp changedTs=new Timestamp(System.currentTimeMillis());
		String changedBy=auth.getName();
		
		u.setChangedTs(changedTs);
		u.setChangedBy(changedBy);
		
		//Convenience method
		u.handleAssignedRoles(user.getRoles());
		
		//poista ne roolit, jotka eivät ole listalla
		//u.removeUnassignedRoles(user.getRoles());
		//lisää uudet roolit
		//u.addAssignedRoles(user.getRoles());

		System.out.println("updating basic data & user roles...");
		u=userRepository.mergeUser(u);
		
		System.out.println("updating password...");
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

	@Override
	public List<UserSearchResultDTO> searchUsers(UserSearchParamDTO param) {
		List<UserSearchResultDTO> searchResults=userRepository.searchUsers(param);
		return searchResults;
	}

}
