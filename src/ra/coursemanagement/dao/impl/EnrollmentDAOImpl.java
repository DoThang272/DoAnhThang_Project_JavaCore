package ra.coursemanagement.dao.impl;

import ra.coursemanagement.dao.itf.IEnrollmentDAO;
import ra.coursemanagement.model.entity.Courses;
import ra.coursemanagement.model.entity.Enrollment;
import ra.coursemanagement.model.entity.Student;
import ra.coursemanagement.repository.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class EnrollmentDAOImpl  implements IEnrollmentDAO {
    @Override
    public boolean studentEnrollCourse(int student, int courses) {
//        Kết nối database
        Connection conn = null;
        CallableStatement callableStatement =  null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("call st_enroll_course(?,?)");
            callableStatement.setInt(1, student);
            callableStatement.setInt(2, courses);
            callableStatement.execute();
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(conn,callableStatement);
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
                Enrollment  enrollment = new Enrollment();
                enrollment.setId(resultSet.getInt("id"));
                return  enrollment;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callableStatement);
        }
        return null;
    }
}
