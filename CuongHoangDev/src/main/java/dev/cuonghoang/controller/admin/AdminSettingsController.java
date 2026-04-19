package dev.cuonghoang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin Settings Management Controller
 * Controller xử lý cài đặt hệ thống
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/settings")
public class AdminSettingsController {

    @GetMapping
    public String settings(Model model) {
        model.addAttribute("pageTitle", "System Settings");
        return "admin/settings";
    }

    @GetMapping("/general")
    public String generalSettings(Model model) {
        model.addAttribute("pageTitle", "General Settings");
        return "admin/settings-general";
    }

    @PostMapping("/general")
    public String processGeneralSettings(Model model) {
        // TODO: Implement general settings logic
        model.addAttribute("message", "Settings updated successfully!");
        return "admin/settings-general";
    }

    @GetMapping("/security")
    public String securitySettings(Model model) {
        model.addAttribute("pageTitle", "Security Settings");
        return "admin/settings-security";
    }

    @PostMapping("/security")
    public String processSecuritySettings(Model model) {
        // TODO: Implement security settings logic
        model.addAttribute("message", "Security settings updated successfully!");
        return "admin/settings-security";
    }

    @GetMapping("/email")
    public String emailSettings(Model model) {
        model.addAttribute("pageTitle", "Email Settings");
        return "admin/settings-email";
    }

    @PostMapping("/email")
    public String processEmailSettings(Model model) {
        // TODO: Implement email settings logic
        model.addAttribute("message", "Email settings updated successfully!");
        return "admin/settings-email";
    }
}
