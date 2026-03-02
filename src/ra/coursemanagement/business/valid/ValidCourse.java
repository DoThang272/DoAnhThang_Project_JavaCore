package ra.coursemanagement.business.valid;

import java.util.Scanner;

public class ValidCourse {
    public static String inputCourseName(Scanner scanner){
        System.out.println("Mời nhập tên khóa học: ");
        String nameCourse = scanner.nextLine();
        return nameCourse;
    }

    public  static String inputCourseDuration(Scanner scanner){
        System.out.println("Mời nhập kì học: ");
        String durationCourse = scanner.nextLine();
        return durationCourse;
    }

    public static String inputCourseInstructor(Scanner scanner){
        System.out.println("Mồi nhập người hướng dẫn: ");
        String instructorCourse = scanner.nextLine();
        return instructorCourse;
    }
}
