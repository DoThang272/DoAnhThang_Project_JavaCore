package ra.coursemanagement;

import ra.coursemanagement.business.auth.BussinessAdminAuthImpl;
import ra.coursemanagement.dao.itf.IAdminDAO;
import ra.coursemanagement.presentation.AdminLoginView;
import ra.coursemanagement.presentation.AdminView;
import ra.coursemanagement.presentation.StudentLoginView;
import ra.coursemanagement.presentation.StudentView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n========= HỆ THỐNG QUẢN LÝ ĐÀO TẠO ========");
            System.out.println("1. Đăng nhập với tư cách Quản trị viên");
            System.out.println("2. Đăng nhập với tư cách Học viên");
            System.out.println("3. Thoát");
            System.out.println("===========================================");
            System.out.print("Nhập lựa chọn: ");
            int choice ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
            switch (choice){
                case 1:
                    AdminLoginView  adminLoginView = new AdminLoginView();
                    adminLoginView.login(scanner);
//                    AdminView adminView = new AdminView();
//                    adminView.showAdminMenu();
                    break;
                case 2:
//                    StudentView studentView = new StudentView();
//                    studentView.showMenuStudent();
                    StudentLoginView studentLoginView = new StudentLoginView();
                    studentLoginView.login(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 3");
            }
        }while (true);
    }
}
