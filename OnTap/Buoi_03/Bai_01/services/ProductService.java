package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.services;

import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.reposities.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Class: ProductService
 * @Tạo vào ngày: 10/31/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //CRUD

    // tìm all product
    public List<Product> getAllProduct(){
        return  productRepository.findAll();
    }

    // tìm product theo id
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    // tìm product theo name
    public List<Product> getProductByName(String name) {
        return  productRepository.getProductsByNameContainingIgnoreCase(name);
    }

    //Update - add
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //delete
    public void delete(String id) {
        productRepository.deleteById(id);
    }

}