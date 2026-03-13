package ra.coursemanagement.dao.itf;

import ra.coursemanagement.model.entity.StudentInCourse;

import java.util.List;

public interface IStatisticDAO {
    List<StudentInCourse>  getStudentInCourse();
}
