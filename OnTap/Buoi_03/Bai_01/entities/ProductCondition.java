package edu.iuh.fit.nguyenhuusang_tuan9_ontapkt.entities;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT
 * @Enum: ProductCondition
 * @Tạo vào ngày: 11/2/2025
 * @Tác giả: Nguyen Huu Sang
 */
public enum ProductCondition {
    NEW("Mới 100%"),
    REFURBISHED("Đã tân trang"),
    USED("Đã qua sử dụng");

    private String displayName;

    ProductCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}