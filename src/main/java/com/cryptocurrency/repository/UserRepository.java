package com.cryptocurrency.repository;

import com.cryptocurrency.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    User findUserByUsername(String username);
}
