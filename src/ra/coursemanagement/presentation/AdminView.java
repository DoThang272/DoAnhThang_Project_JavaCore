package ra.coursemanagement.presentation;

import java.util.Scanner;

public class AdminView {
    Scanner scanner = new Scanner(System.in);
    public void showAdminMenu(){
        ManagementCourse mc = new ManagementCourse();
        ManagementStudent ms = new ManagementStudent();
        do {
            System.out.println("\n=====MENU ADMIN=====");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký khóa học");
            System.out.println("4. Thống kê học viên theo khóa học");
            System.out.println("5. Đăng xuất");
            System.out.println("=======================");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    mc.displayMenuCourse();
                    break;
                case 2:
                    ms.displayMenuManageStudent();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 5");
            }
        }while (true);
    }
}
