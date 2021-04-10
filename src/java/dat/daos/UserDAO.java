/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.daos;

import dat.dto.UserDTO;
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
public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO result = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select fullName, email, roleID from tblUsers "
                        + "where userID = ? and password = ? and roleID != 'memberGM'";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    result = new UserDTO(userID, "***", fullName, email, roleID);
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
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    public void create(UserDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "If Not Exists(select userID from tblUsers where userID = ?)\n"
                        + "Begin\n"
                        + "insert into tblUsers (userID, password, fullName, email, roleID) values (? , ?, ?, ?, ?)\n"
                        + "End";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getUserID());
                pst.setString(2, dto.getUserID());
                pst.setString(3, "!@#!@#!@@");
                pst.setString(4, dto.getFullName());
                pst.setString(5, dto.getEmail());
                pst.setString(6, dto.getRoleID());
                pst.executeUpdate();
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
    }
}
