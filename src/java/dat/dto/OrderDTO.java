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
public class OrderDTO {

    private String orderID;
    private Date orderDate;
    private float totalMoney;

    public OrderDTO(String orderID, Date orderDate, float totalMoney) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
    }

    public OrderDTO() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

}
