package dev.cuonghoang.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model, Authentication authentication,
                      @RequestParam(value = "oauth2", required = false) String oauth2,
                      @RequestParam(value = "logout", required = false) String logout) {
        
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("isAuthenticated", true);
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        
        if ("success".equals(oauth2)) {
            model.addAttribute("successMessage", "Đăng nhập thành công!");
        }
        
        if ("true".equals(logout)) {
            model.addAttribute("infoMessage", "Đăng xuất thành công!");
        }
        
        model.addAttribute("pageTitle", "Trang chủ - Cuong Thai");
        model.addAttribute("title", "Trang chủ - CuongHoangDev");
        
        return "pages/home/index";
    }

    @GetMapping("/playlist")
    public String playlist(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("isAuthenticated", true);
        } else {
            return "redirect:/auth/login?returnUrl=/playlist";
        }
        
        model.addAttribute("pageTitle", "Playlist - Cuong Thai");
        model.addAttribute("title", "Playlist - CuongHoangDev");
        
        return "pages/utility/playlist";
    }

    @GetMapping("/fptu")
    public String fptu(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("isAuthenticated", true);
        } else {
            return "redirect:/auth/login?returnUrl=/fptu";
        }
        
        model.addAttribute("pageTitle", "FPTU Documents - Cuong Thai");
        model.addAttribute("title", "FPTU Documents - CuongHoangDev");
        
        return "pages/utility/fptu";
    }

    @GetMapping("/documents")
    public String documents(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("isAuthenticated", true);
        } else {
            return "redirect:/auth/login?returnUrl=/documents";
        }
        
        model.addAttribute("pageTitle", "Tài liệu - Cuong Thai");
        model.addAttribute("title", "Tài liệu - CuongHoangDev");
        
        return "pages/utility/documents";
    }

    @GetMapping("/settings")
    public String settings(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("isAuthenticated", true);
        } else {
            return "redirect:/auth/login?returnUrl=/settings";
        }
        
        model.addAttribute("pageTitle", "Cài đặt - Cuong Thai");
        model.addAttribute("title", "Cài đặt - CuongHoangDev");
        
        return "pages/utility/settings";
    }

    @GetMapping("/feedback")
    public String feedback(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("isAuthenticated", true);
        } else {
            return "redirect:/auth/login?returnUrl=/feedback";
        }
        
        model.addAttribute("pageTitle", "Phản hồi - Cuong Thai");
        model.addAttribute("title", "Phản hồi - CuongHoangDev");
        
        return "pages/utility/feedback";
    }
}
