package org.callforcode.healthcare.services;

import java.util.List;
import org.callforcode.healthcare.entities.Role;


public interface IRoleService {

    Role find(String role);
    List<Role> findAll();
}
