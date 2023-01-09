package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.User;
import org.example.util.DBUtils;

import java.sql.*;
import java.util.Set;

public class UsersDAO extends AbstractDAO<User> {

    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn  = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
        if (pstmt.executeUpdate()==1){
            System.out.println("User was inserted successfully");
            return true;
        }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User getById(User user) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }

    public User getByEmail(String email) {
        Connection conn = DBUtils.getConnection();
        final String sql = "SELECT * FROM crazy_users_db.users WHERE email = '" + email + "'";
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("User is found");
                user = new User();
                user.setEmail(email);
                user.setId(rs.getInt(1));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            } else {
                System.out.println("User is not found by email " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

            DBUtils.release(conn, stmt, null, rs);
        }
        return user;
    }
}
