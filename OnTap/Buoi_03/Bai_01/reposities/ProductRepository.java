package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.reposities;

import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Interface: ProductRepository
 * @Tạo vào ngày: 10/31/2025
 * @Tác giả: Nguyen Huu Sang
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> getProductsByNameContainingIgnoreCase(String name);
}