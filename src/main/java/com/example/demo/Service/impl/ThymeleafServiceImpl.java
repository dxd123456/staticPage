package com.example.demo.Service.impl;

import com.example.demo.Service.ThymeleafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
@Service("thymeleafService")
public class ThymeleafServiceImpl implements ThymeleafService {
    public  String destPath;
    {
        try {
             destPath = this.getClass().getResource("/").toURI().getPath()+"templates/user";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private TemplateEngine templateEngine;

    public Map<String,Object> loadModel(String  name){
        Map<String,Object> maps = new HashMap<>();
        System.out.println("进入数据库查了");
        maps.put("name","tellsea");
        maps.put("age",20);
        maps.put("email","1203508278@qq");
        return maps;
    }

    @Override
    public String createHTML(String  name) {
        //输出流
        File dest = new File(destPath, name + ".html");
        if(dest.exists()){
            return "user/"+name+"";
        }
        //上下文
        Context context = new Context();
        context.setVariables(loadModel(name));
        try {
            PrintWriter writer = new PrintWriter(dest,"UTF-8");
            //生成html，第一个参数是thymeleaf页面下的原型名称
            templateEngine.process("static", context, writer);
        }catch (Exception e){
            System.out.println("[静态页服务]：生成静态页异常"+e);
        }
        return "user/"+name+"";
    }
    @Override
    public void deleteHtml(String name) {
    //输出流
        File dest = new File(destPath,"test.html");
        if(dest.exists()){
            dest.delete();
        }
    }
}
