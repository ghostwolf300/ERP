package org.erp.authobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthObjectRepository extends JpaRepository<AuthObject, Integer>,AuthObjectRepositoryCustom {

}
