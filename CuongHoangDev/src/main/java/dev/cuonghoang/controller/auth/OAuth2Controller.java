package dev.cuonghoang.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * OAuth2 Handlers Controller
 * Controller xử lý OAuth2 authentication
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/auth/oauth2")
public class OAuth2Controller {

    @GetMapping("/success")
    public String oauth2Success() {
        return "redirect:/";
    }

    @GetMapping("/failure")
    public String oauth2Failure() {
        return "redirect:/auth/login?error=oauth2";
    }
}
