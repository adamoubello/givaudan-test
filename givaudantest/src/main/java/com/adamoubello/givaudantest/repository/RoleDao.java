package com.adamoubello.givaudantest.repository;

import com.adamoubello.givaudantest.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findById(long id);
}
