package com.vercity.transport.dao;

import com.vercity.transport.model.Admin;
import java.sql.*;

public class AdminDAO {
    public Admin getAdminByUsername(String username) {
        String sql = "SELECT * FROM admins WHERE username = ?";
        Admin admin = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setFullName(rs.getString("full_name"));
                admin.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}