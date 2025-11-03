package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.controller;

import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.services.CategoryService;
import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Class: ProductController
 * @Tạo vào ngày: 10/31/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //Đươờng dẫn thư mục upload đc cấu hình trong application
    @Value("src/main/resources/static/uploads")
    private String uploadDir;

    //Hiển thị toàn bộ sản phẩm
    @GetMapping
    public String listProducts(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product/products";
    }

    // chi tiết
    @GetMapping("/detail/{id}")
    public String viewDetail (@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categorys", categoryService.findAllCategory());
        return "product/product-detail";
    }

    // hiển thị form thêm sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categorys", categoryService.findAllCategory());
        return "product/product-form";
    }

    //Lưu sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("fileImage")MultipartFile file
                            ) throws IOException {

//        // ✅ Set ID thủ công nếu chưa có
//        if (product.getId() == null || product.getId().isEmpty()) {
//            product.setId(UUID.randomUUID().toString());
//        }

        if(!file.isEmpty()) {
            // sử dụng cấu hình uploadDir từ application
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath); // tạo thư mục nếu chưa tồn tại
            file.transferTo(uploadPath.resolve(fileName)); //Lưu file vào thư mục cấu hình
            product.setImage(fileName); // Lưu tên file vào database
        }
        productService.save(product);
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categorys", categoryService.findAllCategory());
        return "product/product-form";
    }

    //Cập nhạt sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, @ModelAttribute Product product,
                                @RequestParam(value = "fileImage", required = false) MultipartFile file) throws IOException {
        product.setId(id);

        // nếu có file upload mơới
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            file.transferTo(uploadPath.resolve(fileName));
            product.setImage(fileName);
        } else {
            //Giữ lại ảnh cũ
            Product existingProduct = productService.getProductById(id);
            if(existingProduct != null) {
                product.setImage(existingProduct.getImage());
            }
        }
        productService.save(product);
        return "redirect:/products";
    }

    // xóa sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/products";
    }

    // tìm kiếm sản phẩm
    @GetMapping("/search")
    public String searchProduct(@RequestParam String name, Model model) {
        List<Product> products = productService.getProductByName(name);
        model.addAttribute("products", products);
        model.addAttribute("keyword", name);
        return "product/products";
    }
}