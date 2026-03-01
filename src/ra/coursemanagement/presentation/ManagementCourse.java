package ra.coursemanagement.presentation;

import java.util.Scanner;

public class ManagementCourse {
    Scanner scanner = new Scanner(System.in);
    public void displayMenuCourse(){
        do {
            System.out.println("1. Hiển danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa trang thông tin khóa học (hiển thị menu chọn thuộc tính cần sửa)");
            System.out.println("4. Xóa khóa học (xác nhận trước khi xóa)");
            System.out.println("5. Tìm kiếm theo tên (tương đối)");
            System.out.println("6. Sắp xếp theo tên hoặc id (tăng/giảm dần");
            System.out.println("7. Quay về menu chính");
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
                case 7:
                    return ;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 7");
            }
        }while (true);
    }
}
