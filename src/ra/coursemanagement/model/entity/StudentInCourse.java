package ra.coursemanagement.model.entity;

public class StudentInCourse {
    private String courseName;
    private int studentId;
    private String studentName;
    private String email;

    public StudentInCourse() {
    }

    public StudentInCourse(String courseName, int studentId, String studentName, String email) {
        this.courseName = courseName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
