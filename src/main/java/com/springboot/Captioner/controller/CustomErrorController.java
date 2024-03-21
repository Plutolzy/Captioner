package com.springboot.Captioner.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "error"; // 返回对应的错误页面视图名称
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}

