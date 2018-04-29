package com.se34.coursemap.service;

import com.se34.coursemap.entity.Role;

public interface RoleService {
    void addRole(Role role);

    void deleteRole(int id);

    Role getRole(int id);

    void editRole(Role role);
}
