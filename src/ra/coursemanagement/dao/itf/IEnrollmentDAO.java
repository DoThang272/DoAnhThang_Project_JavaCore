package ra.coursemanagement.dao.itf;

import ra.coursemanagement.config.SessionLogin;
import ra.coursemanagement.model.entity.Courses;
import ra.coursemanagement.model.entity.Enrollment;
import ra.coursemanagement.model.entity.Student;

public interface IEnrollmentDAO {
    boolean studentEnrollCourse(int student, int courses);
    Enrollment checkEnrollmentCourse(int studentId, int coursesId);

}
