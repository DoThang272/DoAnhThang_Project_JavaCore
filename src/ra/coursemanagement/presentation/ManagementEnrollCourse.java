package ra.coursemanagement.presentation;

import ra.coursemanagement.business.BusinessStatistic;
import ra.coursemanagement.model.entity.StudentInCourse;

import java.util.List;
import java.util.Scanner;

public class ManagementEnrollCourse {
    Scanner scanner = new Scanner(System.in);
    public void menuEnrollCourse(){
        do {
            System.out.println("1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Thêm học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");
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
                    displayStudentInCourse();
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

    public static void displayStudentInCourse(){
        BusinessStatistic businessStatistic = new BusinessStatistic();
        List<StudentInCourse> listStInCourse = businessStatistic.getBusinessStatistic();
        String currentCourse = "";
        for (StudentInCourse sc :  listStInCourse){
            if (!sc.getCourseName().equals(currentCourse)) {
                currentCourse = sc.getCourseName();
                System.out.println("\n========== " + currentCourse.toUpperCase() + " ==========");
                System.out.println("+----+----------------------+---------------------------+");
                System.out.printf("| %-2s | %-20s | %-25s |\n", "ID", "NAME", "EMAIL");
                System.out.println("+----+----------------------+---------------------------+");
            }
            System.out.printf(
                    "| %-2d | %-20s | %-25s |\n",
                    sc.getStudentId(),
                    sc.getStudentName(),
                    sc.getEmail()
            );
        }
        System.out.println("+----+----------------------+---------------------------+\n\n");
    }
}
