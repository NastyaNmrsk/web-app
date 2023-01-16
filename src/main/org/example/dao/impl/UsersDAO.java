package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.Office;
import org.example.model.User;
import org.example.util.DBUtils;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class UsersDAO extends AbstractDAO<User> {
    private static String COLUMNS = "U.id, U.name, U.email, U.password, U.office_id, U.is_active, U.created_ts, U.updated_ts, O.id, O.name, O.location, O.phone, O.fax";
    private static RolesDAO rolesDAO = new RolesDAO();

    @Override
    public boolean insert(User user) {
        String sql = "SELECT " + COLUMNS +
                "FROM crazy_users_db.users U\n" +
                "JOIN crazy_users_db.offices O\n" +
                "ON U.office_id=O.id AND U.email=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            if (pstmt.executeUpdate() == 1) {
                System.out.println("User was inserted successfully");
                return true;
            }
        } catch (SQLException e) {
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
        String sql = "SELECT  " + COLUMNS + " FROM crazy_users_db.users U JOIN crazy_users_db.offices O " +
                "   ON U.office_id = O.id";
        Set users = new LinkedHashSet<User>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                Office office = new Office();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getString("is_active").equalsIgnoreCase("Y"));
                user.setUpdatedTs(rs.getTimestamp("updated_ts"));
                user.setCreatedTs(rs.getTimestamp("created_ts"));

                office.setId(rs.getInt("office_id"));
                office.setName(rs.getString("office_name"));
                office.setLocation(rs.getString("location"));
                office.setPhone(rs.getString("Phone"));
                office.setFax(rs.getString("Fax"));
                user.setOffice(office);
                user.setRoles(rolesDAO.getAllByUser(user));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User getByEmail(String email) {
        Connection conn = DBUtils.getConnection();
        final String sql = "SELECT "+COLUMNS+" FROM crazy_users_db.users U JOIN crazy_users_db.offices O " +
                "ON U.office_id = O.id and email = '" + email + "'";
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new User();
                Office office = new Office();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getString("is_active").equalsIgnoreCase("Y"));
                user.setUpdatedTs(rs.getTimestamp("updated_ts"));
                user.setCreatedTs(rs.getTimestamp("created_ts"));

                //Office
                office.setId(rs.getInt("office_id"));
                office.setName(rs.getString("office_name"));
                office.setLocation(rs.getString("location"));
                office.setPhone(rs.getString("Phone"));
                office.setFax(rs.getString("Fax"));
                user.setOffice(office);

                // Set of roles
                user.setRoles(rolesDAO.getAllByUser(user));
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

    public static void main(String[] args) {
        for (User user : new UsersDAO().getAll()) {
            System.out.println(user);
        }
    }
}
