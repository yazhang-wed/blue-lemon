package org.lemon.lemonsafety.controller;

import java.util.Map;

import org.lemon.lemoncrod.common.Result;
import org.lemon.lemoncrod.common.ResultCode;
import org.lemon.lemoncrod.common.ResultResponse;
import org.lemon.lemonsafety.bean.User;
import org.lemon.lemonsafety.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    public Result<?> login(@RequestBody User user) {
        Map<String, Object> login = loginService.login(user);
        return ResultResponse.success(login);
    }

    /**
     * 退出接口
     * @return
     */
    @GetMapping("/user/logout")
    public Result<?> logout() {
        if (loginService.logout()) {
            return ResultResponse.success("退出成功");
        } else {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR);
        }
    }
}
