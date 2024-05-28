package com.analistas.loginandsignin.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.analistas.loginandsignin.model.entity.RoleEntity;
import com.analistas.loginandsignin.model.entity.RoleEnum;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    RoleEntity findByRoleEnum(RoleEnum roleEnum);
}
