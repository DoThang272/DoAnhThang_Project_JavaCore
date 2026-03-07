package ra.coursemanagement.presentation;

import ra.coursemanagement.business.impl.BusinessStudentImpl;
import ra.coursemanagement.business.valid.ValidStudent;
import ra.coursemanagement.model.entity.Student;

import java.util.Scanner;

public class StudentLoginView {
    public static void login(Scanner scanner) {
        BusinessStudentImpl businessStudentImpl = new BusinessStudentImpl();
        ValidStudent validStudent = new ValidStudent();
        while (true) {
            System.out.println("**********Đăng nhập học viên********");
            String username;
            String password;
            while (true) {
                username = validStudent.inputEmailStudent(scanner);
                if (!username.isEmpty()) {
                    break;
                }
                System.err.println("Tên đăng nhập không được để trống");
            }
            while (true) {
                password = validStudent.inputPasswordLogin(scanner);
                if (!password.isEmpty()) {
                    break;
                }
                System.err.println("Mật khẩu không được để trống.");
            }
            boolean result = businessStudentImpl.loginStudent(username, password);
            if (result) {
                System.out.println("Đăng nhập thành công.");
                StudentView studentView = new StudentView();
                studentView.showMenuStudent();
                break;
            } else {
                System.err.println("Sai tài khoản hoặc mật khẩu.");
            }
        }
    }
}
