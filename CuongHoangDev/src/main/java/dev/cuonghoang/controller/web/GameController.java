package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Games & Entertainment Controller
 * Controller xử lý các trang game và giải trí
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @GetMapping
    public String games(Model model) {
        model.addAttribute("pageTitle", "My Games");
        return "pages/game/collection";
    }

    @GetMapping("/collection")
    public String collection(Model model) {
        model.addAttribute("pageTitle", "Game Collection");
        return "pages/game/collection";
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("pageTitle", "Game Reviews");
        return "pages/game/reviews";
    }

    @GetMapping("/review/{id}")
    public String reviewDetail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Game Review");
        model.addAttribute("reviewId", id);
        return "pages/game/review-detail";
    }
}
