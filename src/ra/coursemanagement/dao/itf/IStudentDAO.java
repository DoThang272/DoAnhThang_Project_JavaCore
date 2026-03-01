package ra.coursemanagement.dao.itf;

import ra.coursemanagement.model.entity.Student;

import java.util.List;

public interface IStudentDAO {
    boolean insertNewSt(Student student);
    boolean updateSt(Student student);
    boolean deleteSt(int id);
    List<Student> findAllSt();
    Student findStById(int id);
    List<Student> findStByName(String name);
    List<Student> findAllSort(String sortBy, String direction);

}
