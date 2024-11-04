package com.example.KTCK.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/home2")
    public String home2Page(){
        return "home2";
    }

    @GetMapping("/home3")
    public String home3Page(){
        return "home3";
    }

    @GetMapping("/shop")
    public String shopPage(){
        return "shop";
    }

    @GetMapping("/shop-detail")
    public String shopdetailPage(){
        return "shop-detail";
    }

    @GetMapping("/contact-us")
    public String contactusPage(){
        return "contact-us";
    }
    @GetMapping("/cart-page")
    public String cartpagePage(){
        return "cart-page";
    }
    @GetMapping("/blog-detail")
    public String blogdetail(){
        return "blog-detail";
    }
    @GetMapping("/blog")
    public String blogPage(){
        return "blog";
    }
    @GetMapping("/about-us")
    public String aboutus(){
        return "about-us";
    }
}
