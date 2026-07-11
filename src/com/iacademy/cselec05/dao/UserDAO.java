package com.iacademy.cselec05.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.iacademy.cselec05.model.User;
import com.iacademy.cselec05.util.DBConnection;
import com.iacademy.cselec05.util.PasswordUtil;

public class UserDAO {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (username, email, password_hash) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, PasswordUtil.hashPassword(user.getPasswordHash()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
    }

    public User authenticateUser(String usernameOrEmail, String password) {
        String sql = "SELECT * FROM user WHERE username = ? OR email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);
            rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                if (PasswordUtil.verifyPassword(password, hashedPassword)) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPasswordHash(hashedPassword);
                    user.setBio(rs.getString("bio"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return null;
    }

    public boolean updateUserSettings(int userId, String username, String email, String password) {
        boolean updatePassword = (password != null && !password.trim().isEmpty());
        String sql = updatePassword
                ? "UPDATE user SET username = ?, email = ?, password_hash = ? WHERE user_id = ?"
                : "UPDATE user SET username = ?, email = ? WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            if (updatePassword) {
                ps.setString(3, PasswordUtil.hashPassword(password));
                ps.setInt(4, userId);
            } else {
                ps.setInt(3, userId);
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT 1 FROM user WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM user WHERE email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
    }
}
