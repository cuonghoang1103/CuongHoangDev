package dev.cuonghoang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Admin Content Management Controller
 * Controller xử lý quản lý nội dung
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/content")
public class AdminContentController {

    @GetMapping
    public String content(Model model) {
        model.addAttribute("pageTitle", "Content Management");
        return "admin/content";
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        model.addAttribute("pageTitle", "Blog Management");
        return "admin/content-blogs";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("pageTitle", "Project Management");
        return "admin/content-projects";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("pageTitle", "Product Management");
        return "admin/content-products";
    }

    @GetMapping("/create/{type}")
    public String createContent(@PathVariable String type, Model model) {
        model.addAttribute("pageTitle", "Create " + type);
        model.addAttribute("contentType", type);
        return "admin/content-form";
    }

    @PostMapping("/create/{type}")
    public String processCreateContent(@PathVariable String type, Model model) {
        // TODO: Implement content creation logic
        return "redirect:/admin/content/" + type + "s";
    }

    @GetMapping("/edit/{type}/{id}")
    public String editContent(@PathVariable String type, @PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Edit " + type);
        model.addAttribute("contentType", type);
        model.addAttribute("contentId", id);
        return "admin/content-form";
    }

    @PostMapping("/edit/{type}/{id}")
    public String processEditContent(@PathVariable String type, @PathVariable Long id, Model model) {
        // TODO: Implement content edit logic
        return "redirect:/admin/content/" + type + "s";
    }
}
