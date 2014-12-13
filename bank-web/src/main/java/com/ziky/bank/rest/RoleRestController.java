package com.ziky.bank.rest;

import com.ziky.bank.domain.Role;
import com.ziky.bank.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ziky on 12.12.2014.
 */
@Controller
public class RoleRestController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/rest/role/{name}", method = RequestMethod.GET)
    public @ResponseBody Role getRole(@PathVariable("name") String name) {
        return roleService.getRole(name);
    }
}
