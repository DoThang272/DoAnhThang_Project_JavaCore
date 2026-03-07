package ra.coursemanagement.presentation;

import ra.coursemanagement.business.auth.BussinessAdminAuthImpl;

import java.util.Scanner;

public class AdminLoginView {
    public static void login(Scanner scanner) {
        BussinessAdminAuthImpl adminAuth = new BussinessAdminAuthImpl();
        while (true) {
            System.out.println("**********Đăng nhập admin********");
            String username;
            String password;
            while (true) {
                System.out.println("Mời nhập tên đăng nhập: ");
                username = scanner.nextLine();
                if (!username.isEmpty()) {
                    break;
                }
                System.err.println("Tên đăng nhập không được để trống");
            }
            while (true) {
                System.out.println("Mời nhập mật khẩu: ");
                password = scanner.nextLine();
                if (!password.isEmpty()) {
                    break;
                }
                System.err.println("Mật khẩu không được để trống.");
            }
            boolean result = adminAuth.loginAdmin(username, password);
            if (result) {
                System.out.println("Đăng nhập thành công.");
                AdminView adminView = new AdminView();
                adminView.showAdminMenu();
                break;
            } else {
                System.err.println("Sai tài khoản hoặc mật khẩu.");
            }


        }
    }
}
