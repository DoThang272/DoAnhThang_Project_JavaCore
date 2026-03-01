package ra.coursemanagement.presentation;

import java.util.Scanner;

public class StudentView {
    Scanner scanner = new Scanner(System.in);
    public void showMenuStudent(){
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

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
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
}
