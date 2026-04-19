package dev.cuonghoang.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * File API Controller
 * REST API Controller cho quản lý file
 * 
 * @author CuongHoang
 * @version 1.0
 */
@RestController
@RequestMapping("/api/files")
public class FileApiController {

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        // TODO: Implement file upload logic
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PostMapping("/upload/multiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        // TODO: Implement multiple file upload logic
        return ResponseEntity.ok("Files uploaded successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFileById(@PathVariable Long id) {
        // TODO: Implement get file by id logic
        return ResponseEntity.ok("File details for ID: " + id);
    }

    @GetMapping
    public ResponseEntity<?> getAllFiles(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String type) {
        // TODO: Implement get all files logic
        return ResponseEntity.ok("Files list");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        // TODO: Implement delete file logic
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/images")
    public ResponseEntity<?> getAllImages() {
        // TODO: Implement get all images logic
        return ResponseEntity.ok("Images list");
    }

    @GetMapping("/documents")
    public ResponseEntity<?> getAllDocuments() {
        // TODO: Implement get all documents logic
        return ResponseEntity.ok("Documents list");
    }

    @PostMapping("/images/resize")
    public ResponseEntity<?> resizeImage(@RequestParam("file") MultipartFile file,
                                        @RequestParam int width,
                                        @RequestParam int height) {
        // TODO: Implement image resize logic
        return ResponseEntity.ok("Image resized successfully");
    }
}
