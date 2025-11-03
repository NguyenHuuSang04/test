package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Class: Category
 * @Tạo vào ngày: 11/2/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Data
@Table(name = "category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "products")
public class Category {
    @Id
    private String id;
    private String name;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}