package ra.coursemanagement.presentation;

import ra.coursemanagement.business.IBusinessCourse;
import ra.coursemanagement.business.IBusinessStudent;
import ra.coursemanagement.business.impl.BusinessCourseImpl;
import ra.coursemanagement.business.valid.ValidCourse;
import ra.coursemanagement.model.entity.Courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementCourse {
    private static IBusinessCourse businessCourse = new BusinessCourseImpl();
    Scanner scanner = new Scanner(System.in);

    public static void displayListCourse() {
        if (businessCourse.getListCourses() == null) {
            System.out.println("Không có khóa học nào");
        } else businessCourse.getListCourses().forEach(System.out::println);
    }

    public static void addNewCourse(Scanner scanner) {
        Courses course = new Courses();
        System.out.println("Mời bạn nhập dữ liệu khóa học bạn muốn thêm: ");
        course.inputData(scanner);
        businessCourse.addCourse(course);
    }

    public static void editCourse(Scanner scanner) {
        System.out.print("Nhập id khóa học mà bạn muốn sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (businessCourse.getCourseById(id) == null) {
            System.out.println("Không tồn tại khóa học này.");
        } else {
            while (true){
                Courses course = businessCourse.getCourseById(id);
                System.out.println("Mời chọn phần bạn muốn sửa: ");
                System.out.println("1. Sửa tên khóa học: ");
                System.out.println("2. Sửa kì học");
                System.out.println("3. Sửa giảng viên");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn là: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        course.setCourseName(ValidCourse.inputCourseName(scanner));
                        break;
                    case 2:
                        course.setDurationCourse(ValidCourse.inputCourseDuration(scanner));
                        break;
                    case 3:
                        course.setInstructorCourse(ValidCourse.inputCourseInstructor(scanner));
                        break;
                    case 4:
                        return;
                    default:
                        System.err.println("Chỉ được chọn từ 1 đến 4");
                }
                boolean result = businessCourse.updateCourse(course);
                if (result) {
                    System.out.println("Sửa thành công");
                }else System.err.println("Có lỗi trong quá trình sửa.");
            }
        }
    }

    public void displayMenuCourse() {
        do {
            System.out.println("1. Hiển danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học (hiển thị menu chọn thuộc tính cần sửa)");
            System.out.println("4. Xóa khóa học (xác nhận trước khi xóa)");
            System.out.println("5. Tìm kiếm theo tên (tương đối)");
            System.out.println("6. Sắp xếp theo tên hoặc id (tăng/giảm dần");
            System.out.println("7. Quay về menu chính");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListCourse();
                    break;
                case 2:
                    addNewCourse(scanner);
                    break;
                case 3:
                    editCourse(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 7");
            }
        } while (true);
    }
}
