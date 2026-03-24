package com.tes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("msg", "Welcome to TES Institute");
        return "index";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("course1", "Java Full Stack");
        model.addAttribute("course2", "DevOps & Cloud");
        model.addAttribute("course3", "Data Engineering");
        return "courses";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("email", "tes@institute.com");
        model.addAttribute("phone", "+91 9876543210");
        return "contact";
    }
}
