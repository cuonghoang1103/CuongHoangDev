package dev.cuonghoang.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Authentication Controller
 * Controller xử lý đăng nhập và đăng ký
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            String message = switch (error) {
                case "true" -> "Sai tài khoản hoặc mật khẩu!";
                case "unauthorized" -> "Bạn cần đăng nhập để tiếp tục.";
                default -> "Đăng nhập không thành công.";
            };
            model.addAttribute("error", message);
        }
        if (logout != null) {
            model.addAttribute("message", "Bạn đã đăng xuất thành công!");
        }
        model.addAttribute("pageTitle", "Login");
        return "pages/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Register");
        return "pages/auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(Model model) {
        // TODO: Implement registration logic
        model.addAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "pages/auth/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/auth/login?logout";
    }
}
