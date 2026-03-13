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
        if(enrollmentExist != null){
            System.err.println("Sinh viên đã đăng ký khóa học này rồi");
            return false;
        }
        return enrollmentDAO.studentEnrollCourse(studentId,idCourse);
    }

    public List<Enrollment> displayEnrollmentedCou(int idStudent){
        List<Enrollment> beEnrollments = enrollmentDAO.getAllEnrolledCourse(idStudent);
        if (beEnrollments == null || beEnrollments.size() == 0) {
            System.err.println("Sinh viên chưa đăng ký khóa học nào.");
            return null;
        }
        return beEnrollments;

    }
    public boolean cancelEnrollmentCou(int idStudent , int courseId) {
        return enrollmentDAO.cancelEnrollmentCourse(idStudent, courseId);
    }
    public boolean changePassword(String email, String oldPassword, String newPassword){
        return enrollmentDAO.changePasswordSt(email, oldPassword, newPassword);
    }

}
