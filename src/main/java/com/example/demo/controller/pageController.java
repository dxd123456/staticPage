package com.example.demo.controller;

import com.example.demo.Service.ThymeleafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageController {
    @Autowired
    private ThymeleafService thymeleafService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }
    @GetMapping("/index")
    public String toIndex(Model model){
        thymeleafService.createHTML("test");
        model.addAttribute("msg","生成成功");
        return "user/test";
    }
    @GetMapping("/delete")
    public String delte(Model model){
        thymeleafService.deleteHtml("test");
        model.addAttribute("msg","删除成功");
        return "index";
    }
}
