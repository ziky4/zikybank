package com.ziky.bank.repository;

import com.ziky.bank.domain.Role;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface RoleDao extends Dao<Role>{
    //public Role findRoleByName(String roleName);
    public List<Role> findAllRoles();
    //public void saveRole(Role role);
    //public void deleteRole(Role role);
    //public void deleteAll();
    //public Role updateRole(Role role);
}
