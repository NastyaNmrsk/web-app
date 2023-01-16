package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.Role;
import org.example.model.User;
import org.example.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RolesDAO extends AbstractDAO<Role> {
    @Override
    public boolean insert(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }

    @Override
    public Role getById(Role role) {
        return null;
    }

    @Override
    public Set<Role> getAll() {
        return null;
    }

    public Set<Role> getAllByUser(User user) {
        String sql = "SELECT U.user_id, U.role_id, R.name, R.descr FROM  users_roles U " +
                "INNER JOIN roles R ON U.role_id = R.id WHERE U.user_id = ?";
        Set roles = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getId());

            ResultSet rs = preparedStatement.executeQuery();
            roles = new HashSet<Role>();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("descr"));
                roles.add(role);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return roles;
    }

}
