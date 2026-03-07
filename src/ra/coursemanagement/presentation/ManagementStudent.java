package ra.coursemanagement.presentation;

import ra.coursemanagement.TablePrinter;
import ra.coursemanagement.business.IBusinessStudent;
import ra.coursemanagement.business.impl.BusinessStudentImpl;
import ra.coursemanagement.business.valid.ValidStudent;
import ra.coursemanagement.config.StudentSex;
import ra.coursemanagement.model.entity.Courses;
import ra.coursemanagement.model.entity.Student;

import java.util.List;
import java.util.Scanner;

public class ManagementStudent {
    private static IBusinessStudent businessStudent = new BusinessStudentImpl();
    Scanner scanner = new Scanner(System.in);

    public static void displayListStudent() {
        List<Student> listStudents = businessStudent.getAllSt();

        if (listStudents == null || listStudents.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
            return;
        }
        int[] widths = {3, 15, 12, 25, 10, 10, 26};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Student s : listStudents) {
            TablePrinter.printRow(
                    new String[]{
                            String.valueOf(s.getId()),
                            s.getName(),
                            s.getDob().toString(),
                            s.getEmail(),
                            StudentSex.sexFromDB(s.isSex()).getDisplayName(),
                            s.getPhone(),
                            s.getCreateAt().toString()
                    },
                    widths
            );
        }
        TablePrinter.printLine(widths);
    }

    public static void addStudent(Scanner scanner) {
        Student student = new Student();
        // Nhap thong tin sinh vien can them
        student.inputData(scanner);
        //Goi business de thuc hien them moi trong DB
        boolean result = businessStudent.addNewSt(student);
        if (result) {
            System.out.println("Đã thêm mới 1 sinh viên!");
        } else {
            System.err.println("Thêm mới không thành công.");
        }
    }

    public static void updateInfStudent(Scanner scanner) {
        System.out.print("Mời nhập mã sinh viên mà bạn muốn sửa: ");
        int idStuUpdate = Integer.parseInt(scanner.nextLine());
        Student student = businessStudent.getStById(idStuUpdate);
        if (student == null) {
            System.err.println("Không có sinh viên mà bạn muốn cập nhật");
            return;
        }
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tên sinh viên");
            System.out.println("2. Cập nhật ngày sinh sinh viên");
            System.out.println("3. Cập nhật email sinh viên");
            System.out.println("4. Cập nhật giới tính sinh viên");
            System.out.println("5. Cập nhật số điện thoại sinh viên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn là: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
            switch (choice) {
                case 1:
                    student.setName(ValidStudent.inputNameStudent(scanner));
                    break;
                case 2:
                    student.setDob(ValidStudent.inputDoBStudent(scanner));
                    break;
                case 3:
                    student.setEmail(ValidStudent.inputEmailStudent(scanner));
                    break;
                case 4:
                    student.setSex(ValidStudent.inputSexStudent(scanner));
                    break;
                case 5:
                    student.setPhone(ValidStudent.inputPhoneStudent(scanner));
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
            boolean result = businessStudent.updateSt(student);
            if (result) {
                System.out.println("Cập nhật thông tin sinh viên thành công");
            } else System.out.println("Cập nhật không thành ");
        } while (isExit);
    }

    public static void deleteStudent(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("Mời nhập id sinh viên mà bạn muốn xóa.");
            int idDeleteStu = Integer.parseInt(scanner.nextLine());
            System.err.println("Bạn có chắc chắn muốn xóa sinh viên này không: ");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Mời chọn: ");
            int choiceConfirm = Integer.parseInt(scanner.nextLine());
            if (choiceConfirm == 1) {
                Student student = businessStudent.getStById(idDeleteStu);
                if (student == null) {
                    System.out.println("Không tồn tại nhân viên này ");
                }
                boolean resultDeleteStudent = businessStudent.deleteSt(idDeleteStu);
                if (resultDeleteStudent) {
                    System.out.println("Xóa sinh viên thành công");
                    isExit = false;
                } else System.err.println("Có lỗi trong quá trình xóa");
            } else if (choiceConfirm == 2) {
                isExit = false;
            }
        }
        while (isExit);
    }

    public static void findStudent(Scanner scanner) {
        do {
            System.out.println("Chọn tính năng tìm kiếm: ");
            System.out.println("1. Tìm kiếm theo id");
            System.out.println("2. Tìm kiếm theo tên");
            System.out.println("4. Để thoát");
            System.out.print("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    findStudentById(scanner);
                    break;
                case 2:
                    findStudentByName(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Chỉ được chọn 1 trong 3");
            }
        } while (true);
    }

    public static void findStudentById(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("Mời nhập id sinh viên mà bạn muốn tìm( Nhập 0 để thoát)");
            int idFindStu = Integer.parseInt(scanner.nextLine());
            if (idFindStu == 0) {
                isExit = false;
            } else {
                Student student = businessStudent.getStById(idFindStu);
                if (student == null) {
                    System.out.println("Không có sinh viên nào.");
                    return;
                }
                int[] widths = {3, 15, 12, 25, 10, 10, 26};
                TablePrinter.printLine(widths);
                TablePrinter.printRow(
                        new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                        widths
                );
                TablePrinter.printLine(widths);
//                for (Student s : listStudents) {
                TablePrinter.printRow(
                        new String[]{
                                String.valueOf(student.getId()),
                                student.getName(),
                                student.getDob().toString(),
                                student.getEmail(),
                                StudentSex.sexFromDB(student.isSex()).getDisplayName(),
                                student.getPhone(),
                                student.getCreateAt().toString()
                        },
                        widths
                );
//                }
                TablePrinter.printLine(widths);
            }
        } while (isExit);
    }

    public static void findStudentByName(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("Mời nhập tên sinh viên mà bạn muốn tìm: ");
            String nameFindStu = scanner.nextLine();
            List<Student> listStu = businessStudent.getStByName(nameFindStu);

            if (listStu == null || listStu.isEmpty()) {
                System.out.println("Không có sinh viên nào.");
                return;
            }
            int[] widths = {3, 15, 12, 25, 10, 10, 26};
            TablePrinter.printLine(widths);
            TablePrinter.printRow(
                    new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                    widths
            );
            TablePrinter.printLine(widths);
            for (Student s : listStu) {
                TablePrinter.printRow(
                        new String[]{
                                String.valueOf(s.getId()),
                                s.getName(),
                                s.getDob().toString(),
                                s.getEmail(),
                                StudentSex.sexFromDB(s.isSex()).getDisplayName(),
                                s.getPhone(),
                                s.getCreateAt().toString()
                        },
                        widths
                );
            }
            TablePrinter.printLine(widths);

            isExit = false;
        } while (isExit);
    }

    public static void findAllSort(Scanner scanner) {
        do {
            System.out.println("Mời bạn chọn kiểu sắp xếp:");
            System.out.println("1. Sắp xếp theo tên giảm dần");
            System.out.println("2. Sắp xếp theo tên tăng dần");
            System.out.println("3. Sắp xếp theo id giảm dần");
            System.out.println("4. Sắp xếp theo tăng dần");
            System.out.println("5. Thoát");
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
                    sortByNameDESC(scanner);
                    break;
                case 2:
                    sortByNameASC(scanner);
                    break;
                case 3:
                    sortByIdDESC(scanner);
                    break;
                case 4:
                    sortByIdASC(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (true);
    }

    public static void sortByNameDESC(Scanner scanner) {
        List<Student> listStudents = businessStudent.getAllSort("name", "DESC");
        if (listStudents == null || listStudents.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
            return;
        }
        int[] widths = {3, 15, 12, 25, 10, 10, 26};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Student s : listStudents) {
            TablePrinter.printRow(
                    new String[]{
                            String.valueOf(s.getId()),
                            s.getName(),
                            s.getDob().toString(),
                            s.getEmail(),
                            StudentSex.sexFromDB(s.isSex()).getDisplayName(),
                            s.getPhone(),
                            s.getCreateAt().toString()
                    },
                    widths
            );
        }
        TablePrinter.printLine(widths);
    }

    public static void sortByIdASC(Scanner scanner) {
        List<Student> listStudents = businessStudent.getAllSort("id", "ASC");
        if (listStudents == null || listStudents.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
            return;
        }
        int[] widths = {3, 15, 12, 25, 10, 10, 26};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Student s : listStudents) {
            TablePrinter.printRow(
                    new String[]{
                            String.valueOf(s.getId()),
                            s.getName(),
                            s.getDob().toString(),
                            s.getEmail(),
                            StudentSex.sexFromDB(s.isSex()).getDisplayName(),
                            s.getPhone(),
                            s.getCreateAt().toString()
                    },
                    widths
            );
        }
        TablePrinter.printLine(widths);
    }

    public static void sortByIdDESC(Scanner scanner) {
        List<Student> listStudents = businessStudent.getAllSort("id", "DESC");
        if (listStudents == null || listStudents.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
            return;
        }
        int[] widths = {3, 15, 12, 25, 10, 10, 26};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Student s : listStudents) {
            TablePrinter.printRow(
                    new String[]{
                            String.valueOf(s.getId()),
                            s.getName(),
                            s.getDob().toString(),
                            s.getEmail(),
                            StudentSex.sexFromDB(s.isSex()).getDisplayName(),
                            s.getPhone(),
                            s.getCreateAt().toString()
                    },
                    widths
            );
        }
        TablePrinter.printLine(widths);
    }

    public static void sortByNameASC(Scanner scanner) {
        List<Student> listStudents = businessStudent.getAllSort("name", "ASC");
        if (listStudents == null || listStudents.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
            return;
        }
        int[] widths = {3, 15, 12, 25, 10, 10, 26};
        TablePrinter.printLine(widths);
        TablePrinter.printRow(
                new String[]{"ID", "TÊN HỌC VIÊN", "NGÀY SINH", "EMAIL", "GIỚI TÍNH", "SĐT", "NGÀY TẠO"},
                widths
        );
        TablePrinter.printLine(widths);
        for (Student s : listStudents) {
            TablePrinter.printRow(
                    new String[]{
                            String.valueOf(s.getId()),
                            s.getName(),
                            s.getDob().toString(),
                            s.getEmail(),
                            StudentSex.sexFromDB(s.isSex()).getDisplayName(),
                            s.getPhone(),
                            s.getCreateAt().toString()
                    },
                    widths
            );
        }
        TablePrinter.printLine(widths);
    }

    public void displayMenuManageStudent() {
        do {
            System.out.println("1. Hiển danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên (hiển thị menu chọn thuộc tính cần sửa)");
            System.out.println("4. Xóa học viên (xác nhận trước khi xóa)");
            System.out.println("5. Tìm kiếm theo tên, email hoặc id (tương đối)");
            System.out.println("6. Sắp xếp theo tên hoặc id (tăng/giảm dần");
            System.out.println("7. Quay về menu chính");
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
                    displayListStudent();
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    updateInfStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    findStudent(scanner);
                    break;
                case 6:
                    findAllSort(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 7");
            }
        } while (true);
    }
}
