package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Explore & Travel Controller
 * Controller xử lý các trang khám phá và du lịch
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/explore")
public class ExploreController {

    @GetMapping
    public String explore(Model model) {
        model.addAttribute("pageTitle", "Explore");
        return "pages/explore/travel";
    }

    @GetMapping("/travel")
    public String travel(Model model) {
        model.addAttribute("pageTitle", "Travel Stories");
        return "pages/explore/travel";
    }

    @GetMapping("/dreams")
    public String dreams(Model model) {
        model.addAttribute("pageTitle", "My Dreams");
        return "pages/explore/dreams";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("pageTitle", "Photo Gallery");
        return "pages/explore/gallery";
    }
}
