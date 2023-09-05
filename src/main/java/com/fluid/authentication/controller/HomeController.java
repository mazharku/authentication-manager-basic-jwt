package com.fluid.authentication.controller;

import com.fluid.authentication.annotation.AuthorizedAPI;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
@Data

public class HomeController {

    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("home")
    @AuthorizedAPI
    public String printHome() {
        return "mazhar";
    }

}
