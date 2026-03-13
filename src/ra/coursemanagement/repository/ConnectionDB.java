package ra.coursemanagement.repository;

import java.sql.*;

public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/course_management";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "admin";

//    ket noi db
    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

//    dong khi lam viec xong
public static void closeConnection(Connection conn, Statement st){
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
}
