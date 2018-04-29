package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Role;
import com.se34.coursemap.repository.RoleRepository;
import com.se34.coursemap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRole(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public void editRole(Role role) {
        roleRepository.saveAndFlush(role);
    }
}
