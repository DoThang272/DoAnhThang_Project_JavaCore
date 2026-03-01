package ra.coursemanagement.dao.impl;

import ra.coursemanagement.dao.itf.IStudentDAO;
import ra.coursemanagement.model.entity.Student;
import ra.coursemanagement.repository.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public boolean insertNewSt(Student student) {
        // TAo bien khoi tao ket noi
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            // Ket noi voi database
            conn = ConnectionDB.openConnection();
            // goi procedure trong sql
            callSt = conn.prepareCall("call create_new_student(?,?,?,?,?,?)");
            //set gia tri cho tham so truyen vao
            callSt.setString(1, student.getName());
            callSt.setDate(2, Date.valueOf(student.getDob()));
            callSt.setString(3, student.getEmail());
            callSt.setBoolean(4, student.isSex());
            callSt.setString(5, student.getPhone());
            callSt.setString(6, student.getPassword());
            // khi tao va suawr thi dung executeUpdate()
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updateSt(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call update_student(?,?,?,?,?,?)");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getName());
            callSt.setDate(3, Date.valueOf(student.getDob()));
            callSt.setString(4, student.getEmail());
            callSt.setBoolean(5, student.isSex());
            callSt.setString(6, student.getPhone());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Student> findAllSt() {
        List<Student> listStudents = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_student()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                // neu co data thi tao list khoi tao cho student
                listStudents = new ArrayList<>();
                //Sau do goi Resulset de luu gia tri goi duoc tu DB
                ResultSet resultSet = callSt.getResultSet();
                // Tao vong lap de goi tat ca sinh vien trong list ra
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setDob(resultSet.getDate("dob").toLocalDate());
                    student.setEmail(resultSet.getString("email"));
                    student.setSex(resultSet.getBoolean("sex"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setCreateAt(resultSet.getTimestamp("create_at").toLocalDateTime());
                    listStudents.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudents;
    }

    @Override
    public boolean deleteSt(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call delete_student(?)");
            callSt.setInt(1, id);
            callSt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Student findStById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_id(?)}");
            //set tham so truyen vao ham find_student_by_id
            callSt.setInt(1, id);
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet resultSet = callSt.getResultSet();
                if (resultSet.next()) {
                    student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setDob(resultSet.getDate("dob").toLocalDate());
                    student.setEmail(resultSet.getString("email"));
                    student.setSex(resultSet.getBoolean("sex"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setCreateAt(resultSet.getTimestamp("create_at").toLocalDateTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public List<Student> findStByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudents = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_name(?)}");
            callSt.setString(1, name);
            boolean hasData = callSt.execute();
            if (hasData) {
                listStudents = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setDob(resultSet.getDate("dob").toLocalDate());
                    student.setEmail(resultSet.getString("email"));
                    student.setSex(resultSet.getBoolean("sex"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setCreateAt(resultSet.getTimestamp("create_at").toLocalDateTime());
                    listStudents.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudents;
    }

    @Override
    public List<Student> findAllSort(String sortBy, String direction) {
        // Bao ve sql injection
        if (!sortBy.equals("name") && !sortBy.equals("id")) {
            try {
                throw new IllegalAccessException("Cột sắp xếp không hợp lệ!");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (!direction.equalsIgnoreCase("ASC") && !direction.equalsIgnoreCase("DESC")) {
            direction = "ASC";
        }
        String sqlSort = "select * from student order by " + sortBy + " " + direction;
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudents = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall(sqlSort);
            ResultSet resultSet = callSt.executeQuery();
            listStudents = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setDob(resultSet.getDate("dob").toLocalDate());
                student.setEmail(resultSet.getString("email"));
                student.setSex(resultSet.getBoolean("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setPassword(resultSet.getString("password"));
                student.setCreateAt(resultSet.getTimestamp("create_at").toLocalDateTime());
                listStudents.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudents;
    }
}
