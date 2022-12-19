package org.example.util;

import java.sql.*;

import static org.example.util.AppConstants.*;

public class DBUtils {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection -> " + conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return conn;

    }

    public static void release(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection is closed -> " + conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement is closed -> " + stmt);
            }
            if (pstmt != null) {
                pstmt.close();
                System.out.println("PrepareStatement is closed -> " + pstmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet is closed -> " + rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
