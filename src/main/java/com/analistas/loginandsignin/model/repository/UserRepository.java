package com.analistas.loginandsignin.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.analistas.loginandsignin.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
