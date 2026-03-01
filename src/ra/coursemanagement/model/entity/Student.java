package ra.coursemanagement.model.entity;

import ra.coursemanagement.business.valid.ValidStudent;
import ra.coursemanagement.config.StudentSex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private boolean sex; // true = Nam, false = Nữ
    private String phone;
    private String password; // đã mã hóa BCrypt
    private LocalDateTime createAt;

    // Constructor không tham số
    public Student() {
    }

    // Constructor đầy đủ tham số (trừ id nếu insert)
    public Student(String name, LocalDate dob, String email,
                   boolean sex, String phone, String password) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
    }

    // Constructor full (dùng khi lấy từ DB)
    public Student(int id, String name, LocalDate dob, String email,
                   boolean sex, String phone, String password, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
        this.createAt = createAt;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void inputData(Scanner scanner) {

        this.name = ValidStudent.inputNameStudent(scanner);
        this.dob = ValidStudent.inputDoBStudent(scanner);
        this.email = ValidStudent.inputEmailStudent(scanner);
        this.sex = ValidStudent.inputSexStudent(scanner);
        this.phone = ValidStudent.inputPhoneStudent(scanner);
        this.password = ValidStudent.inputPasswordStudent(scanner);
    }

    // Hiển thị thông tin học viên (KHÔNG HIỂN THỊ PASSWORD)
    @Override
    public String toString() {
        return String.format(
                "Mã SV: %-3d | Tên SV: %-20s | Ngày sinh: %-12s | Email: %-25s | Giới tính: %-5s | SĐT: %-8s | Ngày tạo: %-12s \n"
                , id, name, dob, email, StudentSex.sexFromDB(sex).getDisplayName(), phone, createAt);
    }


}
