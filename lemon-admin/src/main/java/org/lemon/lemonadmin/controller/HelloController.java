package org.lemon.lemonadmin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LBK
 * @create 2022-05-24 22:58
 */
@RestController
public class HelloController {

    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
//    使用自定义的权限
//    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello() {
        return "hello Security";
    }
}
