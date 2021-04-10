/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.daos;

import dat.dto.ProductDTO;
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
public class ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);

    public List<ProductDTO> getAll(int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "select productID, productName, price, quantity, image, description, status, createDate, updateDate "
                        + " from tblProduct\n"
                        + " order by createDate desc\n"
                        + " OFFSET ? ROWS\n"
                        + " FETCH FIRST 20 ROW ONLY";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    boolean statusTMP = rs.getBoolean("status");
                    String status = "active";
                    if (!statusTMP) {
                        status = "inActive";
                    }
                    Date createDate = rs.getDate("createDate");
                    Date updateDate = rs.getDate("updateDate");
                    list.add(new ProductDTO(productID, productName, price, quantity, image, description, status, createDate, updateDate));
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

    public List<String> getCategory() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select categoryName\n"
                        + "from tblCategory";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String category = rs.getString("categoryName");
                    list.add(category);
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

    public int getPageManager() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select count(productID) as number\n"
                        + " from tblProduct";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("number");
                    result = result % 20 == 0 ? result / 20 : result / 20 + 1;
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
        return result;
    }

    public int getPage(String name, String maxPrice, String minPrice, String category) throws ClassNotFoundException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select count(productID) as number\n"
                        + "from tblProduct p join tblCategory c on p.categoryID = c.categoryID\n"
                        + "where (p.productName like ? or price BETWEEN ? and ? or c.categoryName = ?) and p.status = 1 and p.quantity > 0";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, minPrice);
                pst.setString(3, maxPrice);
                pst.setString(4, category);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("number");
                    result = result % 20 == 0 ? result / 20 : result / 20 + 1;
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
        return result;
    }

    public List<ProductDTO> getProduct(String name, String maxPrice, String minPrice, String category, int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select productID, productName, price, quantity, image, description\n"
                        + "from tblProduct p join tblCategory c on p.categoryID = c.categoryID\n"
                        + "where (p.productName like ? or price BETWEEN ? and ? or c.categoryName = ?) and p.status = 1 and p.quantity > 0\n"
                        + "order by p.createDate desc\n"
                        + "OFFSET ? ROWS\n"
                        + "FETCH FIRST 20 ROW ONLY";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, minPrice);
                pst.setString(3, maxPrice);
                pst.setString(4, category);
                pst.setInt(5, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    list.add(new ProductDTO(productID, productName, price, quantity, image, description));
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

    public int getQuantity(String productID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select quantity from tblProduct where productID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, productID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("quantity");
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
        return result;
    }

    public int create(ProductDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select c.categoryID as categoryID\n"
                        + "from tblCategory c join tblProduct p on c.categoryID = p.categoryID\n"
                        + "where c.categoryName = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getCategory());
                rs = pst.executeQuery();
                if (rs.next()) {
                    String sql2 = "insert into tblProduct(productID, productName, price, quantity, image, description, categoryID, status, createDate) values(?, ?, ?, ?, ?, ?, ?, 1, CURRENT_TIMESTAMP)";
                    pst = cn.prepareStatement(sql2);
                    pst.setString(1, dto.getProductID());
                    pst.setString(2, dto.getProductName());
                    pst.setFloat(3, dto.getPrice());
                    pst.setInt(4, dto.getQuantity());
                    pst.setString(5, dto.getImage());
                    pst.setString(6, dto.getDescription());
                    pst.setString(7, rs.getString("categoryID"));
                    result = pst.executeUpdate();
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
        return result;
    }

    public int update(ProductDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update tblProduct"
                        + " set productName = ?, image = ?, quantity = ?, price = ?, description = ?, status = ?, updateDate = CURRENT_TIMESTAMP "
                        + "where productID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getProductName());
                pst.setString(2, dto.getImage());
                pst.setInt(3, dto.getQuantity());
                pst.setFloat(4, dto.getPrice());
                pst.setString(5, dto.getDescription());
                boolean status = true;
                if (dto.getStatus().equals("inActive")) {
                    status = false;
                }
                pst.setBoolean(6, status);
                pst.setString(7, dto.getProductID());
                result = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("Error at: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }

    public int delete(String productID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update tblProduct\n"
                        + "set status = 0, updateDate = CURRENT_TIMESTAMP \n"
                        + "where productID = ? and status = 1";
                pst = cn.prepareStatement(sql);
                pst.setString(1, productID);
                result = pst.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.error("Error at: ", e);

        } finally {
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
