package ra.coursemanagement.dao.impl;

import ra.coursemanagement.dao.itf.IAdminDAO;
import ra.coursemanagement.model.entity.Admin;
import ra.coursemanagement.repository.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class AdminDAOImpl implements IAdminDAO {

    @Override
    public Admin findAdminByusername(String username) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_admin_by_username(?)}");
            callSt.setString(1, username);
             rs = callSt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception ignored) {}

            ConnectionDB.closeConnection(conn, callSt);
            ConnectionDB.closeConnection(conn,callSt);
        }
        return null;
    }
}
