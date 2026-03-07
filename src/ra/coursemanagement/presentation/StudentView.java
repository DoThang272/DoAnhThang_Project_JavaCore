package ra.coursemanagement.presentation;

import ra.coursemanagement.TablePrinter;
import ra.coursemanagement.business.BusinessStuEnrollCou;
import ra.coursemanagement.business.IBusinessCourse;
import ra.coursemanagement.business.impl.BusinessCourseImpl;
import ra.coursemanagement.config.SessionLogin;
import ra.coursemanagement.model.entity.Courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private static IBusinessCourse businessCourse = new BusinessCourseImpl();
    private static BusinessStuEnrollCou businessStuEnrollCou = new BusinessStuEnrollCou();
    Scanner scanner = new Scanner(System.in);

    public static void displayListCouresByName(Scanner scanner) {

        boolean isExit = true;
        while (isExit) {
            System.out.print("Nhập tên khóa học mà bạn muốn tìm: ");
            String nameSearch = scanner.nextLine();
            List<Courses> listCourses = businessCourse.getCourseByName(nameSearch);
            if (listCourses == null || listCourses.isEmpty()) {
                System.out.println("Không có khóa học nào.");
                return;
            }
            int[] widths = {5, 25, 15, 20, 25};
            TablePrinter.printLine(widths);
            TablePrinter.printRow(
                    new String[]{"ID", "TÊN KHÓA HỌC", "THỜI GIAN", "GIẢNG VIÊN", "THỜI GIAN TẠO"},
                    widths
            );
            TablePrinter.printLine(widths);
            for (Courses c : listCourses) {
                TablePrinter.printRow(
                        new String[]{
                                String.valueOf(c.getIdCourses()),
                                c.getCourseName(),
                                c.getDurationCourse(),
                                c.getInstructorCourse(),
                                c.getCreatedDate().toString()
                        },
                        widths
                );
            }
            TablePrinter.printLine(widths);
        }

    }

    public static void dislayListCoures() {
        List<Courses> listCourses = businessCourse.getListCourses();

        if (listCourses == null || listCourses.isEmpty()) {
            System.out.println("Không có khóa học nào.");
            return;
        }
        int[] widths = {5, 25, 15, 20, 25};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN KHÓA HỌC", "THỜI GIAN", "GIẢNG VIÊN", "THỜI GIAN TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Courses c : listCourses) {
            TablePrinter.printRow(
                    new String[]{
                            String.valueOf(c.getIdCourses()),
                            c.getCourseName(),
                            c.getDurationCourse(),
                            c.getInstructorCourse(),
                            c.getCreatedDate().toString()
                    },
                    widths
            );
        }
        TablePrinter.printLine(widths);
    }

    public static void displayMenuEnrollmentCourse(Scanner scanner) {
        dislayListCoures();
        System.out.print("Mời bạn nhập id khóa học bạn muốn đăng ký: ");
        int idCourseEnrollment;
        try {
            idCourseEnrollment = Integer.parseInt(scanner.nextLine());
            boolean result =  businessStuEnrollCou.checkStRegistedCou(idCourseEnrollment);
            if (result){
                System.out.println("Đăng ký thành công.");
            }
        }catch (Exception e){
            System.err.println("Mời nhập số.");
        }
    }

    public void showMenuStudent() {
        do {
            System.out.println("\n=========MENU HỌC VIÊN=========");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký (nếu chưa bắt)");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("==============================");
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
                    displayMenuEnrollmentCourse(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    return;
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
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
            switch (choice) {
                case 1:
//                    managementCourse.displayListCourse();
                    dislayListCoures();
                    break;
                case 2:
                    displayListCouresByName(scanner);
//                    managementCourse.findCourseByName(scanner);
                    break;
                case 3:
                    return;
            }
        } while (true);

    }
}
