package ra.coursemanagement.dao.itf;

import ra.coursemanagement.model.entity.Courses;

import java.util.List;

public interface ICoursesDAO {
    List<Courses> getAllCourses();
    boolean insertCourses(Courses courses);
    Courses getCourseById(int id);
    boolean updateCourse(Courses courses);
    boolean deleteCourse(int id);
}
