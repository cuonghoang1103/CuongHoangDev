package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Blog & Articles Controller
 * Controller xử lý các trang blog và bài viết
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @GetMapping
    public String blog(Model model) {
        model.addAttribute("pageTitle", "My Blog");
        return "pages/blog/list";
    }

    @GetMapping("/list")
    public String blogList(@RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("pageTitle", "Blog Articles");
        model.addAttribute("currentPage", page);
        return "pages/blog/list";
    }

    @GetMapping("/post/{id}")
    public String blogDetail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Blog Post");
        model.addAttribute("postId", id);
        return "pages/blog/detail";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("pageTitle", "Blog Categories");
        return "pages/blog/categories";
    }

    @GetMapping("/category/{category}")
    public String categoryPosts(@PathVariable String category, Model model) {
        model.addAttribute("pageTitle", "Category: " + category);
        model.addAttribute("category", category);
        return "pages/blog/category-posts";
    }
}
