/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dto;

import java.sql.Date;

/**
 *
 * @author macbook
 */
public class ProductDTO {

    private String productID;
    private String productName;
    private float price;
    private int quantity;
    private String image;
    private String description;
    private String status;
    private Date createDate;
    private Date updateDate;
    private String category;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, float price, int quantity, String image, String description, String status, String category) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.status = status;
        this.category = category;
    }
    
    public ProductDTO(String productID, String productName, float price, int quantity, String image, String description) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
    }

    public ProductDTO(String productID, String productName, float price, int quantity, String image, String description, String status) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public ProductDTO(String productID, String productName, float price, int quantity, String image, String description, String status, Date createDate, Date updateDate) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
