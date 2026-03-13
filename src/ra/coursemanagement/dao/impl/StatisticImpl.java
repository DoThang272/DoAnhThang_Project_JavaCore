package ra.coursemanagement.dao.impl;

import ra.coursemanagement.dao.itf.IStatisticDAO;
import ra.coursemanagement.model.entity.StudentInCourse;
import ra.coursemanagement.repository.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticImpl implements IStatisticDAO {
    @Override
    public List<StudentInCourse> getStudentInCourse() {
        List<StudentInCourse> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps =  null;
        String sql = """
                SELECT c.name, s.id, s.name as "stu_name", s.email
                FROM enrollment e
                JOIN course c ON e.course_id = c.id
                JOIN student s ON e.student_id = s.id
                ORDER BY c.name
                """;

        try {
            conn = ConnectionDB.openConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                StudentInCourse sc = new StudentInCourse(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getString("stu_name"),
                        rs.getString("email")
                );

                list.add(sc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(conn, ps);
        }

        return list;
    }
}
