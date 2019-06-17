package org.erp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>,UserRepositoryCustom {
	
	public User findById(String id);
	//public User findByUsername(String username);
	//public User findByUsernameAndEnabled(String username,boolean enabled);
	public User findByIdAndEnabled(String id,boolean enabled);
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User save(User user);
	
	@Transactional
	public Long removeById(String id);
	
	
}
