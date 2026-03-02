package ra.coursemanagement.business.impl;

import ra.coursemanagement.business.IBusinessCourse;
import ra.coursemanagement.dao.impl.CoursesDAOImpl;
import ra.coursemanagement.dao.itf.ICoursesDAO;
import ra.coursemanagement.model.entity.Courses;

import java.util.List;

public class BusinessCourseImpl implements IBusinessCourse {
    private ICoursesDAO coursesDAO =  new CoursesDAOImpl();

    @Override
    public List<Courses> getListCourses() {
        return coursesDAO.getAllCourses();
    }

    @Override
    public boolean addCourse(Courses course) {
        return coursesDAO.insertCourses(course);
    }

    @Override
    public Courses getCourseById(int id) {
        return coursesDAO.getCourseById(id);
    }

    @Override
    public boolean updateCourse(Courses course) {
        return coursesDAO.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        return  coursesDAO.deleteCourse(id);
    }
}
