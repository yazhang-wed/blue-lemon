package org.lemon.lemoncrod.controller;

import javax.validation.Valid;

import org.lemon.lemoncrod.annotation.Declassify;
import org.lemon.lemoncrod.annotation.EncapsulationTemplates;
import org.lemon.lemoncrod.annotation.Encryption;
import org.lemon.lemoncrod.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 加密解密接口测试
 *
 * @author LBK
 * @create 2021-12-04 14:41
 */
@RestController
@EncapsulationTemplates
public class UserController {

    @Encryption
    @PostMapping("/users_encrypt")
    public User getRegister(@Valid @RequestBody User user) {
        return user;
    }

    @Declassify
    @PostMapping("/users_decrypt")
    public User postRegister(@Valid @RequestBody User user) {
        return user;
    }
}
