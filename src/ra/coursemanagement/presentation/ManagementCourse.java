package ra.coursemanagement.presentation;

import ra.coursemanagement.TablePrinter;
import ra.coursemanagement.business.IBusinessCourse;
import ra.coursemanagement.business.impl.BusinessCourseImpl;
import ra.coursemanagement.business.valid.ValidCourse;
import ra.coursemanagement.model.entity.Courses;

import java.util.List;
import java.util.Scanner;

public class ManagementCourse {
    private static IBusinessCourse businessCourse = new BusinessCourseImpl();
    Scanner scanner = new Scanner(System.in);

    public static void displayListCourse() {
        List<Courses> list = businessCourse.getListCourses();
        if (list == null || list.isEmpty()) {
            System.out.println("Không có khóa học nào.");
            return;
        }
        int[] widths = {5, 25, 15, 20,25};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN KHÓA HỌC", "THỜI GIAN", "GIẢNG VIÊN", "THỜI GIAN TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Courses c : list) {
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
            while (true) {
                Courses course = businessCourse.getCourseById(id);
                System.out.println("Mời chọn phần bạn muốn sửa: ");
                System.out.println("1. Sửa tên khóa học: ");
                System.out.println("2. Sửa kì học");
                System.out.println("3. Sửa giảng viên");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn là: ");
                int choice ;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.err.println("Vui lòng nhập số.");
                    continue;
                }
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
                } else System.err.println("Có lỗi trong quá trình sửa.");
            }
        }
    }

    public static void findCourseByName(Scanner scanner) {
        boolean isExit = true;
        while (isExit) {
            System.out.print("Nhập tên khóa học mà bạn muốn tìm: ");
            String nameSearch = scanner.nextLine();
            List<Courses> listCourses = businessCourse.getCourseByName(nameSearch);
            if (listCourses == null || listCourses.isEmpty()) {
                System.out.println("Không có khóa học nào.");
                return;
            }
            int[] widths = {5, 25, 15, 20,25};
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
            isExit = false;
        }
    }

    public static void sortCourseById(Scanner scanner) {
        while (true) {
            System.out.println("Chọn loại sắp xếp theo id: ");
            System.out.println("1. Sắp xếp theo id giảm dần.");
            System.out.println("2. Sắp xếp theo id tăng dần");
            System.out.println("3. Thoát");
            int choice ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
            switch (choice) {
                case 1:
                    displaySortByIdDESC();
                    break;
                case 2:
                    displaySortByIdASC();
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Chỉ được chọn 1 hoặc 2");
            }
        }
    }

    public static void displaySortByIdASC() {
        List<Courses> listByIdDesc = businessCourse.getAllCoursesSortById("asc");
        if (listByIdDesc == null || listByIdDesc.isEmpty()) {
            System.out.println("Không có khóa học nào.");
            return;
        }
        int[] widths = {5, 25, 15, 20,25};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN KHÓA HỌC", "THỜI GIAN", "GIẢNG VIÊN", "THỜI GIAN TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Courses c : listByIdDesc) {
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

    public static void displaySortByIdDESC() {
        List<Courses> listByIdDesc = businessCourse.getAllCoursesSortById("desc");
        if (listByIdDesc == null || listByIdDesc.isEmpty()) {
            System.out.println("Không có khóa học nào.");
            return;
        }
        int[] widths = {5, 25, 15, 20,25};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN KHÓA HỌC", "THỜI GIAN", "GIẢNG VIÊN", "THỜI GIAN TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Courses c : listByIdDesc) {
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

    public void deleteCourse(Scanner scanner) {
        System.out.print("Nhập id khóa học mà bạn muốn xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (businessCourse.getCourseById(id) == null) {
            System.out.println("Không tồn tại khóa học này");
        } else {
            System.out.println("Bạn có chắc chắn muốn xóa khóa học này: ");
            System.out.println("1. Có");
            System.out.println("2. Không");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean result = businessCourse.deleteCourse(id);
                    if (result) {
                        System.out.println("Xóa khóa học thành công");
                    } else System.err.println("Xóa khóa học không thành công.");
                    break;
                case 2:
                    return;
                default:
                    System.err.println("Chỉ được chọn 1 trong 2");
            }

        }
    }

    public void displayMenuCourse() {
        do {
            System.out.println("\n=========== QUẢN LÝ KHÓA HỌC ===========");

            System.out.printf("%-3s | %-40s\n", "1", "Hiển thị danh sách khóa học");
            System.out.printf("%-3s | %-40s\n", "2", "Thêm mới khóa học");
            System.out.printf("%-3s | %-40s\n", "3", "Chỉnh sửa thông tin khóa học");
            System.out.printf("%-3s | %-40s\n", "4", "Xóa khóa học");
            System.out.printf("%-3s | %-40s\n", "5", "Tìm kiếm khóa học theo tên");
            System.out.printf("%-3s | %-40s\n", "6", "Sắp xếp khóa học (ID)");
            System.out.printf("%-3s | %-40s\n", "7", "Quay về menu chính");

            System.out.println("========================================");
            System.out.print("Chọn chức năng: ");
            int choice ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
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
                    deleteCourse(scanner);
                    break;
                case 5:
                    findCourseByName(scanner);
                    break;
                case 6:
                    sortCourseById(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 7");
            }
        } while (true);
    }
}
