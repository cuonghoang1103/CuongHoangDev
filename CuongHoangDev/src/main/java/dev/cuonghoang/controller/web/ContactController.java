package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contact & Communication Controller
 * Controller xử lý các trang liên hệ và giao tiếp
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    @GetMapping
    public String contact(Model model) {
        model.addAttribute("pageTitle", "Contact Me");
        return "pages/contact/form";
    }

    @GetMapping("/form")
    public String contactForm(Model model) {
        model.addAttribute("pageTitle", "Contact Form");
        return "pages/contact/form";
    }

    @PostMapping("/form")
    public String submitContactForm(Model model) {
        // TODO: Implement contact form submission logic
        model.addAttribute("message", "Thank you for your message!");
        return "pages/contact/form";
    }

    @GetMapping("/work")
    public String contactForWork(Model model) {
        model.addAttribute("pageTitle", "Contact for Work");
        return "pages/contact/work";
    }

    @GetMapping("/info")
    public String contactInfo(Model model) {
        model.addAttribute("pageTitle", "Contact Information");
        return "pages/contact/info";
    }
}
