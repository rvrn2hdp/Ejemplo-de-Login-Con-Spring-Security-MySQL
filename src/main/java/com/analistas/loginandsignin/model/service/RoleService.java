package com.analistas.loginandsignin.model.service;

import com.analistas.loginandsignin.model.entity.RoleEntity;
import com.analistas.loginandsignin.model.entity.RoleEnum;

import java.util.List;

public interface RoleService {

    public List<RoleEntity> buscarTodo();

    RoleEntity findByName(String name);

    RoleEntity findByRoleEnum(RoleEnum roleEnum);

    RoleEntity findById(Long id);
}
