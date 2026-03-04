package ra.coursemanagement.dao.impl;

import ra.coursemanagement.dao.itf.ICoursesDAO;
import ra.coursemanagement.model.entity.Courses;
import ra.coursemanagement.repository.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesDAOImpl implements ICoursesDAO {
    @Override
    public List<Courses> getAllCourses() {
        List<Courses> listCourses = null;
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("{CALL get_all_course()}");
            boolean hasData = callableStatement.execute();
            if (hasData) {
                listCourses = new ArrayList<>();
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Courses courses = new Courses();
                    courses.setIdCourses(resultSet.getInt("id"));
                    courses.setCourseName(resultSet.getString("name"));
                    courses.setDurationCourse(resultSet.getString("duration"));
                    courses.setInstructorCourse(resultSet.getString("instructor"));
                    courses.setCreatedDate(resultSet.getDate("craate_at").toLocalDate());
                    listCourses.add(courses);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return listCourses;
    }

    @Override
    public boolean insertCourses(Courses courses) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            //Ket nối với database
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("CALL insert_new_course(?,?,?)");
            // gán giá trị cho tham số truyền vào
            callableStatement.setString(1, courses.getCourseName());
            callableStatement.setString(2, courses.getDurationCourse());
            callableStatement.setString(3, courses.getInstructorCourse());
            callableStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public Courses getCourseById(int id) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        Courses courses = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("{call find_course_by_id(?)}");
            // Them tham so truyen vao
            callableStatement.setInt(1, id);
            boolean hasData = callableStatement.execute();
            if (hasData) {
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    courses = new Courses();
                    courses.setIdCourses(resultSet.getInt("id"));
                    courses.setCourseName(resultSet.getString("name"));
                    courses.setDurationCourse(resultSet.getString("duration"));
                    courses.setInstructorCourse(resultSet.getString("instructor"));
                    courses.setCreatedDate(resultSet.getDate("craate_at").toLocalDate());

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return courses;
    }

    @Override
    public boolean updateCourse(Courses courses) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("call update_course_by_id(?,?,?,?)");
            callableStatement.setInt(1, courses.getIdCourses());
            callableStatement.setString(2, courses.getCourseName());
            callableStatement.setString(3, courses.getDurationCourse());
            callableStatement.setString(4, courses.getInstructorCourse());
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
    public boolean deleteCourse(int id) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("call delete_course_by_id(?)");
            callableStatement.setInt(1, id);
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
    public List<Courses> getCourseByName(String name) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        List<Courses> coursesList = null;
        try {
            conn = ConnectionDB.openConnection();
            callableStatement = conn.prepareCall("{call find_course_by_nam(?)}");
            callableStatement.setString(1, name);
            boolean hasData = callableStatement.execute();
            if (hasData) {
                coursesList = new ArrayList<>();
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Courses courses = new Courses();
                    courses.setIdCourses(resultSet.getInt("id"));
                    courses.setCourseName(resultSet.getString("name"));
                    courses.setDurationCourse(resultSet.getString("duration"));
                    courses.setInstructorCourse(resultSet.getString("instructor"));
                    courses.setCreatedDate(resultSet.getDate("craate_at").toLocalDate());
                    coursesList.add(courses);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return coursesList;
    }

    @Override
    public List<Courses> getAllSortById(String diraction) {
//        if (!diraction.equals("asc") || !diraction.equals("desc")) {
//            diraction = "asc";
//        }
        Connection conn = null;
        CallableStatement callableStatement = null;
        List<Courses> coursesList = null;
        String sqlSortCoure = "select * from course order by id " + diraction;
        try {
            conn = ConnectionDB.openConnection();
            coursesList = new ArrayList<>();
            callableStatement = conn.prepareCall(sqlSortCoure);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Courses courses = new Courses();
                courses.setIdCourses(resultSet.getInt("id"));
                courses.setCourseName(resultSet.getString("name"));
                courses.setDurationCourse(resultSet.getString("duration"));
                courses.setInstructorCourse(resultSet.getString("instructor"));
                courses.setCreatedDate(resultSet.getDate("craate_at").toLocalDate());
                coursesList.add(courses);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return coursesList;
    }
}
