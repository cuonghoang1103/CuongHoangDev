package dev.cuonghoang.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Password Management Controller
 * Controller xử lý quản lý mật khẩu
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/auth/password")
public class PasswordController {

    @GetMapping("/forgot")
    public String forgotPassword(Model model) {
        model.addAttribute("pageTitle", "Forgot Password");
        return "auth/forgot-password";
    }

    @PostMapping("/forgot")
    public String processForgotPassword(@RequestParam String email, Model model) {
        // TODO: Implement forgot password logic
        model.addAttribute("message", "Password reset link has been sent to your email!");
        return "auth/forgot-password";
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam String token, Model model) {
        model.addAttribute("pageTitle", "Reset Password");
        model.addAttribute("token", token);
        return "auth/reset-password";
    }

    @PostMapping("/reset")
    public String processResetPassword(@RequestParam String token, 
                                     @RequestParam String password, 
                                     Model model) {
        // TODO: Implement reset password logic
        model.addAttribute("message", "Password has been reset successfully!");
        return "auth/login";
    }
}
