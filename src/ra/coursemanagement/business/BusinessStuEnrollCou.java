package ra.coursemanagement.business;

import ra.coursemanagement.config.SessionLogin;
import ra.coursemanagement.dao.impl.EnrollmentDAOImpl;
import ra.coursemanagement.dao.itf.IEnrollmentDAO;
import ra.coursemanagement.model.entity.Enrollment;

import java.util.List;

public class  BusinessStuEnrollCou {
    private IEnrollmentDAO enrollmentDAO =  new EnrollmentDAOImpl();

    public boolean checkStRegistedCou( int idCourse){
        int studentId = SessionLogin.idUserLogin;
        Enrollment enrollmentExist = enrollmentDAO.checkEnrollmentCourse(studentId,idCourse);
//        sout
        if(enrollmentExist != null){
            System.err.println("Sinh viên đã đăng ký khóa học này rồi");
            return false;
        }
        return enrollmentDAO.studentEnrollCourse(studentId,idCourse);
    }

}
