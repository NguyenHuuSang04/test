package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Class: Comment
 * @Tạo vào ngày: 10/31/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}