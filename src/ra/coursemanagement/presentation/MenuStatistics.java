package ra.coursemanagement.presentation;

import java.util.Scanner;

public class MenuStatistics {
    Scanner scanner = new Scanner(System.in);
    public void menuStatistics(){
        do {
            System.out.println("1. Thống kê số lượng khóa học và học viên");
            System.out.println("2. Thống kê học viên theo từng khóa học");
            System.out.println("3. Top 5 khóa học đông học viên nhất");
            System.out.println("4. Liệt kê khoá học có trên 10 học viên");
            System.out.println("5. Quay về menu chính");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    ManagementCourse mc = new ManagementCourse();
                    mc.displayMenuCourse();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 5");
            }
        }while (true);
    }
}
