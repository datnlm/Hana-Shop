/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.daos;

import dat.dto.OrderDTO;
import dat.dto.OrderDetailDTO;
import dat.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author macbook
 */
public class OrdersDAO {

    private static final Logger LOGGER = Logger.getLogger(OrdersDAO.class);

    public List<OrderDTO> getOrder(String userID, String productName, String date) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select od.orderID as orderID, od.orderDate as orderDate, od.totalMoney as totalMoney\n"
                        + "from tblOrders od join tblOrderDetail o on od.orderID = o.orderID join tblProduct p on o.productID = p.productID\n"
                        + "where userID = ? and (p.productName like ? or od.orderDate = ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, "%" + productName + "%");
                pst.setString(3, date);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    Date orderDate = rs.getDate("orderDate");
                    float money = rs.getFloat("totalMoney");
                    list.add(new OrderDTO(orderID, orderDate, money));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<OrderDetailDTO> getOrderDetail(String orderID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDetailDTO> list = new ArrayList();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select p.productName as productName, o.price as price, o.quantity as quantity\n"
                        + "from tblOrderDetail o join tblProduct p on o.productID = p.productID\n"
                        + "where o.orderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productName");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new OrderDetailDTO(productID, price, quantity));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
}
