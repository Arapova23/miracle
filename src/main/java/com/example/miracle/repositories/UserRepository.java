package com.example.miracle.repositories;

import com.example.miracle.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.login=?1 AND u.password = ?2")
    User getByLoginAndPass( String login,String password);
}
