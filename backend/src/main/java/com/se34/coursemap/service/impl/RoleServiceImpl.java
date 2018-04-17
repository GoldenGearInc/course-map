package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Role;
import com.se34.coursemap.repository.RoleRepository;
import com.se34.coursemap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        Role savedRole = roleRepository.saveAndFlush(role);

        return role;
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
