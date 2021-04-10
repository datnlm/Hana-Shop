/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author macbook
 */
public class CartDTO {

    private String userID;
    private Map<String, ProductDTO> cart;

    public CartDTO() {
    }

    public CartDTO(String userID, Map<String, ProductDTO> cart) {
        this.userID = userID;
        this.cart = cart;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public void add(ProductDTO dto) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(dto.getProductID())) {
            int quantity = this.cart.get(dto.getProductID()).getQuantity();
            dto.setQuantity(quantity + dto.getQuantity());
        }
        this.cart.put(dto.getProductID(), dto);
    }

    public void delete(String productID) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(productID)) {
            this.cart.remove(productID);
        }
    }

    public void update(ProductDTO dto) {
        if (this.cart != null) {
            if (this.cart.containsKey(dto.getProductID())) {
                this.cart.replace(dto.getProductID(), dto);
            }
        }
    }
}
