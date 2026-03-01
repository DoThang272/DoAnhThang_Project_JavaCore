package ra.coursemanagement.presentation;

import java.util.Scanner;

public class ManagementEnrollCourse {
    Scanner scanner = new Scanner(System.in);
    public void menuEnrollCouurse(){
        do {
            System.out.println("1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Thêm học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");
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
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 4");
            }
        }while (true);
    }
}
