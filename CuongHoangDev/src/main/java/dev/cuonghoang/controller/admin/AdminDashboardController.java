package dev.cuonghoang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin Dashboard Controller
 * Controller xử lý trang dashboard admin
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("pageTitle", "Admin Dashboard");
        // TODO: Add dashboard statistics
        return "admin/dashboard";
    }
}
