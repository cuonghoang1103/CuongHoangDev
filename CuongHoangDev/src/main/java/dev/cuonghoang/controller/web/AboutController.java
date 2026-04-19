package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * About & Profile Controller
 * Controller xử lý các trang giới thiệu và hồ sơ cá nhân
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping
    public String about(Model model) {
        model.addAttribute("pageTitle", "About Me");
        return "pages/about/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("pageTitle", "My Profile");
        return "pages/about/profile";
    }

    @GetMapping("/cv")
    public String cv(Model model) {
        model.addAttribute("pageTitle", "My CV");
        return "pages/about/cv";
    }

    @GetMapping("/skills")
    public String skills(Model model) {
        model.addAttribute("pageTitle", "My Skills");
        return "pages/about/skills";
    }
}
