package com.analistas.loginandsignin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analistas.loginandsignin.model.entity.RoleEntity;
import com.analistas.loginandsignin.model.entity.RoleEnum;
import com.analistas.loginandsignin.model.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> buscarTodo() {
        return (List<RoleEntity>) roleRepository.findAll();    
    }

    @Override
    public RoleEntity findByName(String name) {

        List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();

        RoleEntity roleEntity = new RoleEntity();

        for (RoleEntity role : roles) {
            if (role.getRoleEnum().name().equals(name)) {
                roleEntity = role;
            }
        }

        return roleEntity;
    }

    @Override
    public RoleEntity findByRoleEnum(RoleEnum roleEnum) {
        return roleRepository.findByRoleEnum(roleEnum);
    }

    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id).get();
    }

}
