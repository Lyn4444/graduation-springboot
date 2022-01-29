package com.graduation.stringboot.controller;

import com.graduation.stringboot.entity.Result;
import com.graduation.stringboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    UserInfoService userInfoService;

    @Autowired
    DemoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping("/index")
    public String index() {
        return "test dev!";
    }

    @GetMapping("/all")
    @ResponseBody
    public Result getAll() {
        return userInfoService.getAll();
    }

}

