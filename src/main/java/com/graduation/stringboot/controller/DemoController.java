package com.graduation.stringboot.controller;

import com.graduation.stringboot.entity.Result;
import com.graduation.stringboot.service.UserInfoService;
import com.graduation.stringboot.utils.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestParam Map<String,Object> params) {
        System.out.println("________________________________");
        System.out.println(params.toString());
        return ResultUtil.success(params);
    }

}

