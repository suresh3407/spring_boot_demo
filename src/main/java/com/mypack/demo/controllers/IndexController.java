/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author suresh
 */
@RestController
public class IndexController {

    @RequestMapping("/jpt")
    public String hello() {
        return "im coming fa";
    }
//    @RequestMapping("/jpt1")
//    public String hello1(){
//        return "hello";
//    }

}
