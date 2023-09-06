package com.fluid.authentication.controller;

import com.fluid.authentication.annotation.ADMIN;
import com.fluid.authentication.annotation.AuthorizedAPI;
import com.fluid.authentication.annotation.CASHIER;
import com.fluid.authentication.annotation.Manager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class RBACController {

    @GetMapping("admin")
    @ADMIN
    @AuthorizedAPI
    public String adminOnly() {
        return "admin";
    }

    @GetMapping("system")
    @AuthorizedAPI
    public String system(){
        return "all access";
    }

    @GetMapping("manager")
    @Manager
    @AuthorizedAPI
    public String managarial() {
        return "manager";
    }

    @GetMapping("cash")
    @CASHIER
    @AuthorizedAPI
    public String cashManager(){
        return "cash manager";
    }

}
