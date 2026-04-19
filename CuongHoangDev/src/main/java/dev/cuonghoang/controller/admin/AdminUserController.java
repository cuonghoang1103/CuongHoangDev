package dev.cuonghoang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Admin User Management Controller
 * Controller xử lý quản lý người dùng
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @GetMapping
    public String users(Model model) {
        model.addAttribute("pageTitle", "User Management");
        return "admin/users";
    }

    @GetMapping("/list")
    public String userList(@RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("pageTitle", "User List");
        model.addAttribute("currentPage", page);
        return "admin/users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("pageTitle", "Create User");
        return "admin/user-form";
    }

    @PostMapping("/create")
    public String processCreateUser(Model model) {
        // TODO: Implement user creation logic
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Edit User");
        model.addAttribute("userId", id);
        return "admin/user-form";
    }

    @PostMapping("/edit/{id}")
    public String processEditUser(@PathVariable Long id, Model model) {
        // TODO: Implement user edit logic
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        // TODO: Implement user deletion logic
        return "redirect:/admin/users";
    }
}
