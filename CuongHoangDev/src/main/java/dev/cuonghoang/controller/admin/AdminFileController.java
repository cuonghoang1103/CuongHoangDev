package dev.cuonghoang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Admin File Management Controller
 * Controller xử lý quản lý file
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/files")
public class AdminFileController {

    @GetMapping
    public String files(Model model) {
        model.addAttribute("pageTitle", "File Management");
        return "admin/files";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("pageTitle", "Upload Files");
        return "admin/file-upload";
    }

    @PostMapping("/upload")
    public String processUpload(@RequestParam("file") MultipartFile file, Model model) {
        // TODO: Implement file upload logic
        model.addAttribute("message", "File uploaded successfully!");
        return "admin/files";
    }

    @GetMapping("/images")
    public String images(Model model) {
        model.addAttribute("pageTitle", "Image Management");
        return "admin/file-images";
    }

    @GetMapping("/documents")
    public String documents(Model model) {
        model.addAttribute("pageTitle", "Document Management");
        return "admin/file-documents";
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        // TODO: Implement file deletion logic
        return "redirect:/admin/files";
    }
}
