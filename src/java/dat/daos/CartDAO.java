/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.daos;

import dat.dto.CartDTO;
import dat.dto.ProductDTO;
import dat.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author macbook
 */
public class CartDAO {

    private static final Logger LOGGER = Logger.getLogger(CartDAO.class);

    public int save(CartDTO cart, float total) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "INSERT into tblOrders(userID, orderDate, totalMoney) values(?, CURRENT_TIMESTAMP, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, cart.getUserID());
                pst.setFloat(2, total);
                result = pst.executeUpdate();
                if (result != -1) {
                    String sql2 = "Select max(orderID) as orderID"
                            + " from tblOrders";
                    pst = cn.prepareStatement(sql2);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        int orderID = rs.getInt("orderID");
                        for (ProductDTO dto : cart.getCart().values()) {
                            result = -1;
                            String sql3 = "If(select quantity from tblProduct where productID = ?) - ? >= 0\n"
                                    + " Begin\n"
                                    + " INSERT into tblOrderDetail(productID, price, quantity, orderID) values(?, ?, ?, ?)\n"
                                    + " update tblProduct \n"
                                    + " set quantity = quantity - ?"
                                    + " where productID = ?"
                                    + " End";
                            pst = cn.prepareStatement(sql3);
                            pst.setString(1, dto.getProductID());
                            pst.setInt(2, dto.getQuantity());
                            pst.setString(3, dto.getProductID());
                            pst.setFloat(4, dto.getPrice());
                            pst.setInt(5, dto.getQuantity());
                            pst.setInt(6, orderID);
                            pst.setInt(7, dto.getQuantity());
                            pst.setString(8, dto.getProductID());

                            result = pst.executeUpdate();
                            if (result == -1) {
                                cn.rollback();
                            }
                        }
                    }
                } else {
                    result = -1;
                }
                cn.commit();
            }
        } catch (Exception e) {
            LOGGER.error("Error at: ", e);
            cn.rollback();
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
        return result;
    }
}
