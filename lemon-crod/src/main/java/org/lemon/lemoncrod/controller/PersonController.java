package org.lemon.lemoncrod.controller;

import org.lemon.lemoncrod.annotation.EncapsulationTemplates;
import org.lemon.lemoncrod.bean.Person;
import org.lemon.lemoncrod.common.Result;
import org.lemon.lemoncrod.common.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author LBK
 * @create 2021-12-02 21:58
 */
@EncapsulationTemplates
@RestController
@RequestMapping("/test")
public class PersonController {

    /**
     * 测试 javabean 参数自动封装返回模板
     *
     * @return 返回javabean
     */
    @PostMapping("/person")
    public Person getHelloWorld(Person person) {
        person.setName("张三");
        person.setSex(1);
        person.setAge(18);
        return person;
    }

    /**
     * 测试返回字符串封装进返回模板
     *
     * @return 字符串
     */
    @GetMapping("/get_string")
    public String getString() {
        return "hello world";
    }

    /**
     * 测试返回模板函数
     *
     * @return 成功的模板函数
     */
    @GetMapping("/get_result")
    public Result<?> getResult() {
        return ResultResponse.success();
    }

    /**
     * 空指针错误测试
     *
     * @return
     */
    @GetMapping("/null")
    public String getNull() {
        String str = null;
        str.length();
        return str;
    }
//
//    /**
//     * 自定义错误测试
//     * @return
//     * @throws AuthenticationException
//     */
//    @GetMapping("/my_null")
//    public String getNull1() throws AuthenticationException{
//        throw new AuthenticationException("自定义错误测试");
//    }
}

