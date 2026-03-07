package ra.coursemanagement.business.valid;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidStudent {
//    Scanner scanner = new Scanner(System.in);

    public static String inputNameStudent(Scanner scanner) {
        System.out.println("Mời nhập tên sinh viên: ");
        while (true) {
            String nameInput = scanner.nextLine().trim(); //trim(): xoa khoang trang thua
            if (!nameInput.isEmpty()) {
                return nameInput;
            }
            System.err.println("Không được để trống. Mời nhập lại");
        }
    }

    //Nhap ngay sinh cua sinh vien
    public static LocalDate inputDoBStudent(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Mời bạn nhập vào ngày sinh sinh viên (dd/MM/yyyy): ");
        while (true) {
            try {
                LocalDate dobInput = LocalDate.parse(scanner.nextLine(), formatter);
                if (dobInput.isBefore(LocalDate.now())) {
                    return dobInput;
                } else System.out.println("Ngày sinh của bạn phải trước hiện tại. Mời nhập lại:");
            } catch (DateTimeParseException e) {
                System.err.println("Lỗi: Định dạng ngày tháng bạn nhập vào không hợp lệ (VD: 27/02/2003). Mời nhập lại");
            }
        }
    }

    public static String inputEmailStudent(Scanner scanner) {
        String regexEmailStu = "^[a-zA-Z0-9+_.-]{6,}@[a-z.-]+$";
        while (true) {
            System.out.println("Vui lòng nhập email: ");
            String emailStudentInput = scanner.nextLine().trim().toLowerCase();
            if (emailStudentInput.isEmpty()) {
                System.err.println("Vui lòng không để trống.");
            } else if (Pattern.matches(regexEmailStu, emailStudentInput)) {
                return emailStudentInput;
            } else System.err.println("Không đúng định dạng email, mời nhập lại:");
        }
    }

    public static boolean inputSexStudent(Scanner scanner) {
        System.out.println("Chọn giới tính: ");
        while (true) {
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            System.out.print("Lựa chọn: ");
            int choice ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số.");
                continue;
            }
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.err.println("Vui lòng chọn 1 trong 2: ");
            }
        }
    }

    public static String inputPhoneStudent(Scanner scanner) {
        String regPhoneStu = "^(032|033|034|035|036|037|038|039|086|096|097|098|088|091|094|081|082|083|084|085)([0-9]{7})$";
        while (true) {
            System.out.println("Vui lòng nhập số điện thoại(Bao gồm 10 số và 3 số đầu phải thuộc các đầu số điện thoại ở Việt Nam): ");
            String phoneInput = scanner.nextLine().trim();
            if (!phoneInput.isEmpty()) {
                if (Pattern.matches(regPhoneStu, phoneInput)) {
                    return phoneInput;
                }
                System.err.println("Vui lòng nhập đúng định dạng.");
            }else System.err.println("Không được để trống. Mời nhập lại:");
        }
    }

    public static String inputPasswordStudent(Scanner scanner) {
        String regPasswordStu = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";
        while (true){
            System.out.println("Mời nhập password (Gồm ít nhất 8 ký tự và bao gồm ít nhất 1 chữ thường, 1 chữ hoa, 1 ký tự đặc biệt): ");
            String passwordInput = scanner.nextLine();
            if(!passwordInput.isEmpty()){
                if (Pattern.matches(regPasswordStu, passwordInput)) {
                    String hashPass = BCrypt.hashpw(passwordInput, BCrypt.gensalt());
                    return  hashPass;
                }
                System.err.println("Không đúng định dạng.");
            }else System.out.println("Không được để trống.");
        }
    }

    public static String inputPasswordLogin(Scanner scanner) {
        while (true){
            System.out.println("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            if(!password.isEmpty()){
                return password;
            }

            System.err.println("Không được để trống.");
        }
    }

}
