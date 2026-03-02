package ra.coursemanagement.business;

import ra.coursemanagement.model.entity.Courses;

import java.util.List;

public interface IBusinessCourse {
    List<Courses>  getListCourses();
    boolean addCourse(Courses course);
    Courses getCourseById(int id);
    boolean updateCourse(Courses course);
    boolean deleteCourse(int id);
}
