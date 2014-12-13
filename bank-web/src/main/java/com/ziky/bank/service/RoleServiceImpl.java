package com.ziky.bank.service;

import com.ziky.bank.domain.Role;
import com.ziky.bank.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ziky on 14.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void createRole(Role role) {
        roleDao.create(role);
    }

    @Override
    public void deleteAll() {
        roleDao.deleteAll();
    }

    @Override
    public Role getRole(String name) {
        return roleDao.find(name);
    }
}
