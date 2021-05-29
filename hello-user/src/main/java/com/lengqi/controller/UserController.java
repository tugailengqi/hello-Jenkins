package com.lengqi.controller;

import com.lengqi.common.constant.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lengqi
 * @DATE: 2021/5/29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     *
     * @return
     */
    @GetMapping("/**")
    public String getUser(){
        return Constant.SA_TOKEN;
    }
}
