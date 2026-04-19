package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Portfolio & Projects Controller
 * Controller xử lý các trang portfolio và dự án
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @GetMapping
    public String portfolio(Model model) {
        model.addAttribute("pageTitle", "My Portfolio");
        return "pages/portfolio/projects";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("pageTitle", "My Projects");
        return "pages/portfolio/projects";
    }

    @GetMapping("/projects/{id}")
    public String projectDetail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Project Details");
        model.addAttribute("projectId", id);
        return "pages/portfolio/project-detail";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("pageTitle", "Project Gallery");
        return "pages/portfolio/gallery";
    }

    @GetMapping("/achievements")
    public String achievements(Model model) {
        model.addAttribute("pageTitle", "My Achievements");
        return "pages/portfolio/achievements";
    }
}
