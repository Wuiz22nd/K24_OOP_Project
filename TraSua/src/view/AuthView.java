/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import service.AuthService;
import java.util.Scanner;

public class AuthView {
    private final AuthService authService;
    private final Scanner sc;

    public AuthView(AuthService authService, Scanner sc) {
        this.authService = authService;
        this.sc = sc;
    }

    public boolean start() {
        while (true) {
            System.out.println("\n=== LOGIN ===");
            System.out.println("1. Dang ky");
            System.out.println("2. Dang nhap");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");

            int choice = inputInt();

            if (choice == 1) {
                signUp();
            } else if (choice == 2) {
                if (login()) {
                    return true;
                }
            } else if (choice == 3) {
                System.out.println("Tam biet!");
                return false;
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private void signUp() {
        System.out.print("Ten dang nhap: ");
        String username = sc.nextLine().trim();
        System.out.print("Mat khau: ");
        String password = sc.nextLine().trim();

        if (authService.signUp(username, password)) {
            System.out.println("Dang ky thanh cong!");
        } else {
            System.out.println("Dang ky that bai!");
        }
    }

    private boolean login() {
        System.out.print("Ten dang nhap: ");
        String username = sc.nextLine().trim();
        System.out.print("Mat khau: ");
        String password = sc.nextLine().trim();

        if (authService.login(username, password)) {
            System.out.println("Dang nhap thanh cong!");
            return true;
        } else {
            System.out.println("Sai ten dang nhap hoac mat khau!");
            return false;
        }
    }

    private int inputInt() {
        while (!sc.hasNextInt()) sc.next();
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
}