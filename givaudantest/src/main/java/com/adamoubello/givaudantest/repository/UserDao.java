package com.adamoubello.givaudantest.repository;

import com.adamoubello.givaudantest.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    //User findByUsername(String username);
    User findByEmail(String email);
}
