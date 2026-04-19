package dev.cuonghoang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Shop & Products Controller
 * Controller xử lý các trang shop và sản phẩm
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @GetMapping
    public String shop(Model model) {
        model.addAttribute("pageTitle", "My Shop");
        return "pages/shop/products";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("pageTitle", "Products");
        return "pages/shop/products";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Product Details");
        model.addAttribute("productId", id);
        return "pages/shop/product-detail";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("pageTitle", "Product Categories");
        return "pages/shop/categories";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("pageTitle", "Shopping Cart");
        return "pages/shop/cart";
    }
}
