package com.melolchik.shopapp.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "PRODUCT".
 */
@Entity
public class Product {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private int productId;

    @NotNull
    private String productName;
    private String productImage;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    @Generated
    public Product(Long id, int productId, String productName, String productImage) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @NotNull
    public String getProductName() {
        return productName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
