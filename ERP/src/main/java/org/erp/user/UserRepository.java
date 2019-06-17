package org.erp.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>,UserRepositoryCustom {
	
	public Optional<User> findById(Integer id);
	public User findByUsername(String username);
	public User findByUsernameAndEnabled(String username,boolean enabled);
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User save(User user);
	
	@Transactional
	public Long removeById(int userId);
	
	
}
