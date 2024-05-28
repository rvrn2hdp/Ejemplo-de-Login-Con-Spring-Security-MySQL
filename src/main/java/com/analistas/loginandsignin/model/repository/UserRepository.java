package com.analistas.loginandsignin.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.analistas.loginandsignin.model.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

}
