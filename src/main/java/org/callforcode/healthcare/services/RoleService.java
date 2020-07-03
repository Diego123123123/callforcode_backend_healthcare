package org.callforcode.healthcare.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.callforcode.healthcare.entities.Role;
import org.callforcode.healthcare.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role find(String role) {
        return roleRepository.findRoleByRole(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
