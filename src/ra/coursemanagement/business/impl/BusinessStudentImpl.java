package ra.coursemanagement.business.impl;

import ra.coursemanagement.business.IBusinessStudent;
import ra.coursemanagement.dao.impl.StudentDAOImpl;
import ra.coursemanagement.dao.itf.IStudentDAO;
import ra.coursemanagement.model.entity.Student;

import java.util.List;


public class BusinessStudentImpl implements IBusinessStudent {
    private IStudentDAO businessStudent = new StudentDAOImpl();

    @Override
    public List<Student> getAllSt() {
        return businessStudent.findAllSt();
    }
    @Override
    public boolean addNewSt(Student student) {
        return  businessStudent.insertNewSt(student);
    }

    @Override
    public boolean updateSt(Student student) {
        return businessStudent.updateSt(student);
    }

    @Override
    public boolean deleteSt(int id) {
        return businessStudent.deleteSt(id);
    }

    @Override
    public Student getStById(int id) {
        return businessStudent.findStById(id);
    }

    @Override
    public List<Student> getStByName(String name) {
        return  businessStudent.findStByName(name);
    }

    @Override
    public List<Student> getAllSort(String sortBy, String direction) {
        return businessStudent.findAllSort(sortBy, direction);
    }
}
