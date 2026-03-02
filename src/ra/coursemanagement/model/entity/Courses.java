package ra.coursemanagement.model.entity;

import ra.coursemanagement.business.valid.ValidCourse;

import java.time.LocalDate;
import java.util.Scanner;

public class Courses {
    private int idCourses;
    private String courseName;
    private String durationCourse;
    private String instructorCourse;
    private LocalDate createdDate;

    public Courses() {
    }

    public Courses(int idCourses, String courseName, String durationCourse, String instructorCourse, LocalDate createdDate) {
        this.idCourses = idCourses;
        this.courseName = courseName;
        this.durationCourse = durationCourse;
        this.instructorCourse = instructorCourse;
        this.createdDate = createdDate;
    }

    public int getIdCourses() {
        return idCourses;
    }

    public void setIdCourses(int idCourses) {
        this.idCourses = idCourses;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDurationCourse() {
        return durationCourse;
    }

    public void setDurationCourse(String durationCourse) {
        this.durationCourse = durationCourse;
    }

    public String getInstructorCourse() {
        return instructorCourse;
    }

    public void setInstructorCourse(String instructorCourse) {
        this.instructorCourse = instructorCourse;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void inputData(Scanner scanner){
        this.courseName = ValidCourse.inputCourseName(scanner);
        this.durationCourse =ValidCourse.inputCourseDuration(scanner);
        this.instructorCourse = ValidCourse.inputCourseInstructor(scanner);

    }

    @Override
    public String toString() {
        return String.format("ID khóa học: %d - Tên khóa học: %s - Kì học: %s - Người hướng dẫn: %s - Ngày tạo: %s "
        , idCourses, courseName, durationCourse, instructorCourse, createdDate);
    }

}
