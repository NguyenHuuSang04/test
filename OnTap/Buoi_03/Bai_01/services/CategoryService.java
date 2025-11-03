package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.services;

import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities.Category;
import edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.reposities.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Class: CategoryService
 * @Tạo vào ngày: 11/2/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //CRUD
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}