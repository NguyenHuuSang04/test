package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Class: Product
 * @Tạo vào ngày: 10/31/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"comments"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String image;

    //check box
    private boolean inStock;

    //Radio
    @Enumerated(EnumType.STRING)
    private ProductCondition productCondition;

    //select multiple
    @ElementCollection
    @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id") )// định nghĩa tên bảng và forein key trong db để lưu collection
    @Column(name = "color") // định nghĩa tên cột ở bảng phụ
    private List<String> colors;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate; // ngày thêm sản phẩm

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}