package ra.coursemanagement.dao.impl;

import org.mindrot.jbcrypt.BCrypt;
import ra.coursemanagement.dao.itf.IEnrollmentDAO;
import ra.coursemanagement.model.entity.Courses;
import ra.coursemanagement.model.entity.Enrollment;
import ra.coursemanagement.model.entity.Student;
import ra.coursemanagement.repository.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements IEnrollmentDAO {
    @Override
    public boolean studentEnrollCourse(int student, int courses) {
//        Kết nối database
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("call st_enroll_course(?,?)");
            callableStatement.setInt(1, student);
            callableStatement.setInt(2, courses);
            callableStatement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public Enrollment checkEnrollmentCourse(int studentId, int coursesId) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        String sql = """
                select * from enrollment where course_id = ? and student_id = ?;
                """;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall(sql);
            callableStatement.setInt(1, coursesId);
            callableStatement.setInt(2, studentId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(resultSet.getInt("id"));
                return enrollment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return null;
    }

    @Override
    public List<Enrollment> getAllEnrolledCourse(int studentId) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = """
                select * from enrollment where student_id = ?;
                """;
        try {

            conn = ConnectionDB.openConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(resultSet.getInt("id"));
                enrollment.setStudentId(resultSet.getInt("student_id"));
                enrollment.setCourseId(resultSet.getInt("course_id"));
                enrollment.setRegistered_at(resultSet.getTimestamp("registered_at").toLocalDateTime());
                enrollment.setStatus(resultSet.getString("status"));
                enrollments.add(enrollment);
            }
            return enrollments;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return null;
    }

    @Override
    public boolean cancelEnrollmentCourse(int studentId, int coursesId) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("call cancel_enrolled(?,?)");
            callableStatement.setInt(1, studentId);
            callableStatement.setInt(2, coursesId);
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return false;
    }

    @Override
    public boolean changePasswordSt(String email, String oldPassword, String newPassword) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();

            // 1. Lấy password đã hash trong DB
            String sql = "SELECT password FROM student WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {

                String hashedPassword = rs.getString("password");

                // 2. Kiểm tra mật khẩu cũ
                if (!BCrypt.checkpw(oldPassword, hashedPassword)) {
                    return false;
                }

                // 3. Hash mật khẩu mới
                String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                // 4. Update password
                String updateSql = "UPDATE student SET password = ? WHERE email = ?";
                ps = conn.prepareStatement(updateSql);
                ps.setString(1, newHashedPassword);
                ps.setString(2, email);

                int rows = ps.executeUpdate();

                return rows > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }

        return false;
    }
}
