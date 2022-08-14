package com.fluid.authentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class HomeController {

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("home")
    public String printHome() {
        return "mazhar";
    }
}
