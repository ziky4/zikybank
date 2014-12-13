package com.ziky.bank.service;

import com.ziky.bank.domain.Role;

/**
 * Created by Ziky on 14.10.2014.
 */
public interface RoleService {
    public void createRole(Role role);
    public void deleteAll();
    public Role getRole(String name);
}
