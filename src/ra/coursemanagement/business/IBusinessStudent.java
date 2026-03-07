package ra.coursemanagement.business;

import ra.coursemanagement.model.entity.Student;

import java.util.List;

public interface IBusinessStudent {
    boolean addNewSt(Student student);
    boolean updateSt(Student student);
    boolean deleteSt(int id);
    List<Student> getAllSt();
    Student getStById(int id);
    List<Student> getStByName(String name);
    List<Student> getAllSort(String sortBy, String direction);
    boolean loginStudent(String username, String password);
}
