package ra.coursemanagement.presentation;

import ra.coursemanagement.business.IBusinessCourse;
import ra.coursemanagement.business.impl.BusinessCourseImpl;

import java.util.Scanner;

public class StudentView {
    private static IBusinessCourse businessCourse = new BusinessCourseImpl();
    Scanner scanner = new Scanner(System.in);
    ManagementCourse managementCourse = new ManagementCourse();

    public void showMenuStudent() {
        do {
            System.out.println("\n=====MENU HỌC VIÊN=====");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký (nếu chưa bắt)");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("===========================");
            System.out.print("Chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayMenuListCourse(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 6");
            }
        } while (true);
    }

    public void displayMenuListCourse(Scanner scanner) {
        do {
            System.out.println("1. Hiển thị danh sách khóa học.");
            System.out.println("2. Tìm kiếm khóa học theo tên.");
            System.out.println("3. Thoát");
            System.out.print("Mời chọn: ");
            int choice ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
            switch (choice) {
                case 1:
//                    managementCourse.displayListCourse();
                    break;
                case 2:
//                    managementCourse.findCourseByName(scanner);
                    break;
                case 3:
                    return;
            }
        } while (true);

    }
}
